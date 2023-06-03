package com.example.foodflow.network;

import com.example.foodflow.models.AreasResponse;
import com.example.foodflow.models.CategoriesResponse;
import com.example.foodflow.models.IngredientsResponse;
import com.example.foodflow.models.MealsResponse;
import com.example.foodflow.models.PlannerMealResponse;

import io.reactivex.Single;


public interface RemoteSource {
    Single<MealsResponse> getMealOfTheDay();

    Single<PlannerMealResponse> getAllMeals();

    Single<MealsResponse> getMealDetails(String mealId);

    Single<MealsResponse> searchMeals(String title);

    Single<MealsResponse> getMealsByCategory(String category);

    Single<MealsResponse> getMealsByArea(String area);

    Single<MealsResponse> getMealsByIngredient(String ingredient);

    Single<CategoriesResponse> getCategories();

    Single<AreasResponse> getAreas();

    Single<IngredientsResponse> getIngredients();

}
