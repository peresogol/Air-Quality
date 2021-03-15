package com.example.qualitair;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class CallAPI extends AppCompatActivity {

    private static final String key = "fb2d9bd0-77c5-458e-b830-fac56be1ec93";
    private String longitude;
    private String latitude;
  //  private static final String BASE_URL = "http://api.airvisual.com/v2/nearest_city?lat=" + lattitude + "&lon="+ longitude +"&key=fb2d9bd0-77c5-458e-b830-fac56be1ec93";

    private TextView textViewJSON; // TextView dans lequel on va insérer le JSON récupéré de l'API

    // URL de base de l'API (doit se terminer par /)
    private static final String API_BASE_URL = "https://api.airvisual.com/v2/";

    // Instance nécessaires au traitement (pour Retrofit)
    Retrofit retrofit;
    AirVisualAPI serviceAPI;

    // Instance necessaire a la recuperation de la localisation
    FusedLocationProviderClient fusedLocationProviderClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_api);

        // Init fuse location provider
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Check permission
        if (ActivityCompat.checkSelfPermission(CallAPI.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getLocation();
        } else {
            ActivityCompat.requestPermissions(CallAPI.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        // récupération du textView
        this.textViewJSON = (TextView) findViewById(R.id.idTextView);

        // Construction d'une instance de retrofit (Etape #2 du cours)
        this.retrofit = new Retrofit.Builder()
                .baseUrl(CallAPI.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.serviceAPI = retrofit.create(AirVisualAPI.class);

        // Construit le traitement lorsque l'on clique sur le bouton appel
        Button boutonFiche = (Button) findViewById(R.id.idButtonFiche);
        boutonFiche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // appel méthode getResult de l'interface AirVisualAPI
                Call<JsonElement> appel = serviceAPI.getResult(CallAPI.this.latitude, CallAPI.this.longitude, "fb2d9bd0-77c5-458e-b830-fac56be1ec93");
                appel.enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                        if (response.isSuccessful()) {
                            // Récupère le contenu JSON de la réponse
                            JsonElement contenu = response.body();
                            textViewJSON.setText(contenu.toString());

                            /*   // Convertir en JsonObject pour pouvoir accéder aux attributs
                            JsonObject jsonGlobal = contenu.getAsJsonObject();

                            // Récupère les aptitudes du Pokemon
                            JsonArray abilities = jsonGlobal.getAsJsonArray("abilities");

                            StringBuffer chaineConstruite = new StringBuffer("Aptitudes : \n");

                            for (JsonElement element : abilities){
                                // récupère le nom de l'abilité
                                JsonObject obj = element.getAsJsonObject();

                                // Extraction "rapide"
                                chaineConstruite.append(obj.get("ability").getAsJsonObject().get("name").getAsString()+"\n");
                            }

                            // Affiche la chaine sur l'interface
                            textViewJSON.setText(chaineConstruite.toString());
                        } else {
                            Toast.makeText(DisplayResult.this, "Erreur lors de l'appel à l'API :" + response.errorBody(), Toast.LENGTH_SHORT).show();
                        */}
                    }

                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {
                        Toast.makeText(CallAPI.this, "Erreur lors de l'appel à l'API :" + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                // Init location
                Location location = task.getResult();
                if (location != null) {
                    try {
                        // Init geoCoder
                        Geocoder geoCoder = new Geocoder(CallAPI.this, Locale.getDefault());

                        //Init adresse list
                        List<Address> adress = geoCoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1);

                        // Set latitude and longitude in attribute
                        CallAPI.this.longitude = String.valueOf(adress.get(0).getLongitude());
                        CallAPI.this.latitude = String.valueOf(adress.get(0).getLatitude());

                        Log.v("azyla", adress.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }
}



