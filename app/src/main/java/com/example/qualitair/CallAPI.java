package com.example.qualitair;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CallAPI extends AppCompatActivity {

    private static final String key = "fb2d9bd0-77c5-458e-b830-fac56be1ec93";
    private String longitude;
    private String latitude;

    // URL de base de l'API (doit se terminer par /)
    private static final String API_BASE_URL = "https://api.airvisual.com/v2/";

    // Instance nécessaires au traitement (pour Retrofit)
    Retrofit retrofit;
    AirVisualAPI serviceAPI;

    // Instance nécessaire a la recuperation de la localisation
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_api);

        // Init fuse location provider
        this.fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Construction d'une instance de retrofit (Etape #2 du cours)
        this.retrofit = new Retrofit.Builder()
                .baseUrl(CallAPI.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.serviceAPI = retrofit.create(AirVisualAPI.class);

        // Call GPS and permission check
        if (ActivityCompat.checkSelfPermission(CallAPI.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            this.getLocation();
        } else {
            ActivityCompat.requestPermissions(CallAPI.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
    }

    // appel méthode getResult de l'interface AirVisualAPI
    private void ApiRequest(double lat, double lon) {
        Log.v("api", this.latitude + " ");
        Log.v("api", this.longitude + " ");
        Call<JsonElement> appel = serviceAPI.getResult(Double.toString(lat), Double.toString(lon), this.key);
        appel.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.isSuccessful()) {
                    // Recupere le contenu JSON de la réponse
                    JsonElement contenu = response.body();

                    // Convertir en JsonObject pour pouvoir accéder aux attributs
                    JsonObject jsonGlobal = contenu.getAsJsonObject();
                    JsonObject data = jsonGlobal.getAsJsonObject("data");

                    // Recupere les infos de localisation
                    JsonObject location = data.getAsJsonObject("location");
                    JsonArray coordinates = location.getAsJsonArray("coordinates");

                    // Create Place (location) class
                    Place placeResult = new Place(
                            data.get("city").getAsString(),
                            data.get("state").getAsString(),
                            data.get("country").getAsString(),
                            data.get("city").getAsString(),
                            coordinates.get(0).getAsString(),
                            coordinates.get(1).getAsString(),
                            false
                    );

                    // Donnees des sondes (meteo et pollution)
                    JsonObject current = data.getAsJsonObject("current");

                    // Recupere les infos meteo
                    JsonObject weather = current.getAsJsonObject("weather");
                    WeatherResult weatherResult = getWeatherResult(weather);

                    // Recupere les infos pollution
                    JsonObject pollution = current.getAsJsonObject("pollution");
                    PollutionResult pollutionResult = getPollutionResult(pollution);

                    // Return objects to main activity
                    Intent intentRetour = new Intent();
                    intentRetour.putExtra("place", placeResult);
                    intentRetour.putExtra("weather", weatherResult);
                    intentRetour.putExtra("pollution", pollutionResult);
                    setResult(Activity.RESULT_OK, intentRetour);
                    finish();
                } else {
                    Toast.makeText(CallAPI.this, getString(R.string.api_error) + response.errorBody(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Toast.makeText(CallAPI.this, getString(R.string.api_error) + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    private PollutionResult getPollutionResult(JsonObject pollution) {
        String[] timestamp = pollution.get("ts").getAsString().split("T");
        String date = timestamp[0];
        String hour = timestamp[1].substring(0, 8);

        // Create pollution result class
        return new PollutionResult(
                hour,
                date,
                pollution.get("mainus").getAsString(),
                pollution.get("aqius").getAsString()
        );
    }

    private WeatherResult getWeatherResult(JsonObject weather) {
        String[] timestamp = weather.get("ts").getAsString().split("T");
        String date = timestamp[0];
        String hour = timestamp[1].substring(0, 8);

        // Create meteo result class
        return new WeatherResult(
                hour,
                date,
                weather.get("ic").getAsString(),
                weather.get("tp").getAsString(),
                weather.get("pr").getAsString(),
                weather.get("hu").getAsString(),
                weather.get("ws").getAsString(),
                weather.get("wd").getAsString()
        );
    }


    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        this.fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                // Init location
                Location location = task.getResult();
                if (location != null) {
                    CallAPI.this.ApiRequest(location.getLatitude(), location.getLongitude());
                } else {
                    Toast.makeText(CallAPI.this, getString(R.string.gps_error), Toast.LENGTH_LONG).show();
                    CallAPI.this.finish();
                }
            }
        });
    }
}



