package com.example.foodflow.network;


import retrofit2.Call;
import retrofit2.http.GET;

public interface API_Service {


    @GET("random.php")
    Call<MyResponse> getMealOfTheDay();
}
