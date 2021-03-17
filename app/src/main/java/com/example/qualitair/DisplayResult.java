package com.example.qualitair;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayResult extends AppCompatActivity {

    private SQLClient db;
    private Place place;
    private WeatherResult weatherResult;
    private PollutionResult pollutionResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        this.place = (Place) intent.getSerializableExtra("place");
        this.weatherResult = (WeatherResult) intent.getSerializableExtra("weather");
        this.pollutionResult = (PollutionResult) intent.getSerializableExtra("pollution");
        String choice = intent.getStringExtra("choice");

        // a supprimer lorsque l'api fonctionnera
        this.db = new SQLClient(this);
        if (this.db.insertData(place.getCity(), place.getLongitude(), place.getLatitude())) {
            Log.v("tag", "inseré oui");
        } else {
            Log.v("tag", "inséré non");
        }
        if (choice.equals(getResources().getString(R.string.radioButton_Meteo))) {
            this.displayWeather();
        } else if (choice.equals(getResources().getString(R.string.radioButton_Pollution))) {
            this.displayPollution();
        } else if (choice.equals(getResources().getString(R.string.radioButton_MeteoAndPollution))) {
            this.displayWeatherAndPollution();
        } else {
            Log.e("Error", "Choice from getStringExtra must be Weather one of those from RadioGroup from MainActivity");
        }
    }

    private void displayWeatherAndPollution() {
        setContentView(R.layout.activity_display_result_weather_and_pollution);

    }

    private void displayPollution() {
        setContentView(R.layout.activity_display_result_pollution);
        TextView date = findViewById(R.id.pollutionDate);
        TextView hour = findViewById(R.id.pollutionHour);
        TextView pollutant = findViewById(R.id.pollutant);
        TextView aqi = findViewById(R.id.pollutionLevel);
        date.setText(this.pollutionResult.getDate());
        hour.setText(this.pollutionResult.getHour());
        pollutant.setText(this.pollutionResult.getMainPollutant());
        aqi.setText(this.pollutionResult.getAirQualityIndexUS() + " (AQI US)");
    }

    private void displayWeather() {
        setContentView(R.layout.activity_display_result_weather);
        TextView date = findViewById(R.id.weatherDate);
        TextView hour = findViewById(R.id.weatherHour);
        TextView temp = findViewById(R.id.weatherTemp);
        TextView pr = findViewById(R.id.weatherPressure);
        TextView hu = findViewById(R.id.weatherHumidity);
        TextView ws = findViewById(R.id.weatherWindSpeed);
        TextView wd = findViewById(R.id.weatherWindDirection);
        date.setText(this.weatherResult.getDate());
        hour.setText(this.weatherResult.getHour());
        temp.setText(this.weatherResult.getTemperature());
        pr.setText(this.weatherResult.getPressure());
        hu.setText(this.weatherResult.getHumidity());
        ws.setText(this.weatherResult.getWindSpeed());
        wd.setText(this.weatherResult.getWindDirection());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_display_result, menu);
        menu.getItem(0).setIcon(R.drawable.check_star);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.setFavourite:
                // ajouter code afin d'insérer le fait que cette ville soit favorite dans la base de données
                item.setChecked(!item.isChecked());
                this.place.setIsFavourite(!item.isChecked());
                this.db.updateData(this.place.getPlaceName(),this.place.getLongitude(),this.place.getLatitude(),this.place.getIsFavourite());
                if (item.isChecked()) {
                    item.setIcon(R.drawable.filled_star);
                    Toast.makeText(DisplayResult.this,"" + this.place.getPlaceName() + " a été rajouté aux favoris", Toast.LENGTH_SHORT).show();
                } else {
                    item.setIcon(R.drawable.empty_star);
                    Toast.makeText(DisplayResult.this,"" + this.place.getPlaceName() + " a été enlevé des favoris", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.Favourite:
                Intent intentFavourite = new Intent(DisplayResult.this, FavouriteCities.class);
                startActivity(intentFavourite);
                break;
            case R.id.History:
                Intent intentHistory = new Intent(DisplayResult.this, History.class);
                startActivity(intentHistory);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}
