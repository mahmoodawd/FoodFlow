package com.example.foodflow.search.presenter;


import com.example.foodflow.models.Meal;

public interface SearchPresenterInterface {

    void searchMeal(String mealName);

    void addMealToFav(Meal meal);

    void deleteMealFromFav(Meal meal);
}
