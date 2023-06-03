package com.example.foodflow.core.presenter;

import com.example.foodflow.models.Meal;

public interface MealsPresenterInterface {

    void getMealOfTheDay();

    void addMealToFav(Meal meal);

    void deleteMealFromFav(Meal meal);

    void getFavorites();

    void getMealsByCategory(String categoryTitle);

    void getMealsByIngredient(String title);

    void getMealsByArea(String title);
}
