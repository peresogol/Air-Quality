package com.example.qualitair;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String choice = this.getIntent().getStringExtra("choice");
        if (choice.equals("@string/radioButton_Weather")) {
            setContentView(R.layout.display_result_weather);
        } else if (choice.equals("@string/radioButton_Pollution")) {
            setContentView(R.layout.display_result_pollution);
        } else {
            setContentView(R.layout.display_result_weather_and_pollution);
        }
    }
}
