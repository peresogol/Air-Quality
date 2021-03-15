package com.example.qualitair;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    WeatherResult weatherResult;
    PollutionResult pollutionResult;
    LocationResult locationResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button geoLoc = (Button) findViewById(R.id.boutonGeolocalisation);
        geoLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCallAPI = new Intent(MainActivity.this, CallAPI.class);
                startActivityForResult(intentCallAPI, 2);
            }
        });
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if(resultCode == Activity.RESULT_OK){
                weatherResult = (WeatherResult) data.getExtras().getSerializable("weather");
                pollutionResult = (PollutionResult) data.getExtras().getSerializable("pollution");
                locationResult = (LocationResult) data.getExtras().getSerializable("location");
                Log.v("raoue", weatherResult.toString());
                Log.v("raoue", pollutionResult.toString());
                Log.v("raoue", locationResult.toString());
            }
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