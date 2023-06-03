package com.example.foodflow.network;


import com.example.foodflow.models.AreasResponse;
import com.example.foodflow.models.CategoriesResponse;
import com.example.foodflow.models.IngredientsResponse;
import com.example.foodflow.models.MealsResponse;
import com.example.foodflow.models.PlannerMealResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class API_Client implements RemoteSource {
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static final String TAG = "API_Client";
    private static API_Client instance = null;
    API_Service apiService;

    private API_Client() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        apiService = retrofit.create(API_Service.class);
    }

    public static API_Client getInstance() {
        if (instance == null) {
            instance = new API_Client();
        }
        return instance;
    }


    @Override
    public Single<MealsResponse> getMealOfTheDay() {

        return apiService.getMealOfTheDay();
    }

    @Override
    public Single<PlannerMealResponse> getAllMeals() {
        return apiService.getAllMeals("");
    }

    @Override
    public Single<MealsResponse> getMealDetails(String mealId) {
        return apiService.getMealDetails(mealId);
    }


    @Override
    public Single<MealsResponse> searchMeals(String title) {
        return apiService.getSearchedMeals(title);
    }

    @Override
    public Single<MealsResponse> getMealsByCategory(String category) {
        return apiService.getMealsByCategory(category);
    }

    @Override
    public Single<MealsResponse> getMealsByArea(String area) {
        return apiService.getMealsByArea(area);
    }

    @Override
    public Single<MealsResponse> getMealsByIngredient(String ingredient) {
        return apiService.getMealsByIngredient(ingredient);
    }

    @Override
    public Single<CategoriesResponse> getCategories() {
        return apiService.getCategories();
    }

    @Override
    public Single<AreasResponse> getAreas() {
        return apiService.getAreas("list");
    }

    @Override
    public Single<IngredientsResponse> getIngredients() {
        return apiService.getIngredients("list");
    }


}