package com.example.qualitair;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface AirVisualAPI {
    @GET("posts")
    Call<List> getPosts();
}
