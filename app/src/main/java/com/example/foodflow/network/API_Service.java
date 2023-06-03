package com.example.foodflow.network;


import com.example.foodflow.models.AreasResponse;
import com.example.foodflow.models.CategoriesResponse;
import com.example.foodflow.models.IngredientsResponse;
import com.example.foodflow.models.MealsResponse;
import com.example.foodflow.models.PlannerMealResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API_Service {


    @GET ("random.php")
    Single<MealsResponse> getMealOfTheDay();

    @GET("search.php")
    Single<PlannerMealResponse> getAllMeals(@Query("s") String meal);

    @GET("random.php")
    Single<MealsResponse> getRandomMeal();

    @GET("search.php")
    Single<MealsResponse> getSearchedMeals(@Query("s") String meal);

    @GET("lookup.php")
    Single<MealsResponse> getMealDetails(@Query("i") String mealId);

    @GET("filter.php")
    Single<MealsResponse> getMealsByCategory(@Query("c") String category);

    @GET("filter.php")
    Single<MealsResponse> getMealsByArea(@Query("a") String area);

    @GET("filter.php")
    Single<MealsResponse> getMealsByIngredient(@Query("i") String ingredient);

    @GET("categories.php")
    Single<CategoriesResponse> getCategories();

    @GET("list.php")
    Single<AreasResponse> getAreas(@Query("a") String filter);

    @GET("list.php")
    Single<IngredientsResponse> getIngredients(@Query("i") String filter);


}
