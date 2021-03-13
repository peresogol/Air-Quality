package com.example.qualitair;

import com.google.gson.JsonElement;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface AirVisualAPI {
    @GET("nearest_city")
    Call<JsonElement> getResult(@Query("lat") String latitude, @Query("lon") String longitude, @Query("key") String key);
}
