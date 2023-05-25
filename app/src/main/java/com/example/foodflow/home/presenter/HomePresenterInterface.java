package com.example.foodflow.home.presenter;

import com.example.foodflow.models.Meal;

public interface HomePresenterInterface {
    void getMealOfTheDay();
    void addMeal(Meal meal);
}
