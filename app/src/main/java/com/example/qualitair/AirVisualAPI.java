package com.example.qualitair;

import com.google.gson.JsonElement;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface AirVisualAPI {
    @GET("nearest_city")
    Call<JsonElement> getResult(@Query("lat") String latitude, @Query("lon") String longitude, @Query("key") String key);
    //v2/nearest_city?lat=2.328508&lon=48.852346&key=fb2d9bd0-77c5-458e-b830-fac56be1ec93
}
