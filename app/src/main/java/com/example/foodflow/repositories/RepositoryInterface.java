package com.example.foodflow.repositories;


import androidx.lifecycle.LiveData;

import com.example.foodflow.db.PlannerMealDoa;
import com.example.foodflow.models.Meal;
import com.example.foodflow.models.PlannerMeal;
import com.example.foodflow.network.NetworkDelegate;

import java.util.List;


public interface RepositoryInterface {
    void getAllMeals(NetworkDelegate networkDelegate);
    void getMealOfTheDay(NetworkDelegate callback);

    void getCategories(NetworkDelegate callback);

    void getAreas(NetworkDelegate callback);

    void getIngredients(NetworkDelegate callback);


    void getMealDetails(NetworkDelegate callback, String mealId);

    void searchMeals(NetworkDelegate callback, String title);

    void getMealsByCategories(NetworkDelegate callback, String category);

    void getMealsByArea(NetworkDelegate callback, String area);

    void getMealsByIngredient(NetworkDelegate callback, String ingredient);

    LiveData<List<Meal>> getFavoritesMeals();

    void insert(Meal meal);

    void delete(Meal meal);

    LiveData<List<PlannerMeal>> getDayMeals(String weekDay);

    LiveData<List<PlannerMeal>> getCurrentWeekMeals();

    void plan(PlannerMeal meal);

    void unPlan(PlannerMeal meal);


}
