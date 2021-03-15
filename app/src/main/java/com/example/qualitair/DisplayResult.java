package com.example.qualitair;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String choice = this.getIntent().getStringExtra("choice");
        if (choice.equals(getResources().getString(R.string.radioButton_Meteo))) {
            setContentView(R.layout.display_result_weather);
        } else if (choice.equals(getResources().getString(R.string.radioButton_Pollution))) {
            setContentView(R.layout.display_result_pollution);
        } else if (choice.equals(getResources().getString(R.string.radioButton_MeteoAndPollution))){
            setContentView(R.layout.display_result_weather_and_pollution);
        } else {
            Log.e("Error", "Choice from getStringExtra must be Weather one of those from RadioGroup from MainActivity");
        }
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
                if(item.isChecked()){
                    item.setIcon(R.drawable.filled_star);
                    item.setChecked(false);
                } else {
                    item.setIcon(R.drawable.empty_star);
                    item.setChecked(true);
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
