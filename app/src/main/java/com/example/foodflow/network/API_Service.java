package com.example.foodflow.network;


import com.example.foodflow.models.AreasResponse;
import com.example.foodflow.models.CategoriesResponse;
import com.example.foodflow.models.IngredientsResponse;
import com.example.foodflow.models.MealsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API_Service {

    @GET("random.php")
    Call<MealsResponse> getRandomMeal();

    @GET("search.php")
    Call<MealsResponse> getSearchedMeals(@Query("s") String meal);

    @GET("lookup.php")
    Call<MealsResponse> getMealDetails(@Query("i") String mealId);

    @GET("categories.php")
    Call<CategoriesResponse> getCategories();

    @GET("filter.php")
    Call<MealsResponse> getMealsByCategory(@Query("c") String category);

    @GET("filter.php")
    Call<MealsResponse> getMealsByArea(@Query("a") String area);

    @GET("filter.php")
    Call<MealsResponse> getMealsByIngredient(@Query("i") String ingredient);

    @GET("list.php")
    Call<AreasResponse> getMealsAreas(@Query("a") String filter);

    @GET("list.php")
    Call<IngredientsResponse> getMealsIngredients(@Query("i") String filter);


}
