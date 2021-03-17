package com.example.qualitair;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WeatherResult weatherResult;
    private PollutionResult pollutionResult;
    private Place placeResult;
    private TextView nearestCity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.nearestCity = (TextView) findViewById(R.id.villeGeolocalisation);
        Button geoLoc = (Button) findViewById(R.id.boutonGeolocalisation);
        geoLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCallAPI = new Intent(MainActivity.this, CallAPI.class);
                startActivityForResult(intentCallAPI, 2);
            }
        });

        Button displayResult = (Button) findViewById(R.id.buttonRechercher);
        displayResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup radioGroup_Choices = (RadioGroup) findViewById(R.id.radioGroup);
                int radioButton_id = radioGroup_Choices.getCheckedRadioButtonId();
                if (radioButton_id != -1) {
                    RadioButton radioButton_Choices = (RadioButton) findViewById(radioButton_id);
                    Intent intent = new Intent(MainActivity.this, DisplayResult.class);
                    intent.putExtra("choice", radioButton_Choices.getText());
                    intent.putExtra("place", placeResult);
                    intent.putExtra("weather", weatherResult);
                    intent.putExtra("pollution", pollutionResult);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, R.string.warning_no_radio_selection, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
            this.weatherResult = (WeatherResult) data.getExtras().getSerializable("weather");
            this.pollutionResult = (PollutionResult) data.getExtras().getSerializable("pollution");
            this.placeResult = (Place) data.getExtras().getSerializable("place");
            this.nearestCity.setText(this.placeResult.getCity());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.Favourite:
                Intent intentFavourite = new Intent(MainActivity.this, FavouriteCities.class);
                startActivity(intentFavourite);
                break;
            case R.id.History:
                Intent intentHistory = new Intent(MainActivity.this, History.class);
                startActivity(intentHistory);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}