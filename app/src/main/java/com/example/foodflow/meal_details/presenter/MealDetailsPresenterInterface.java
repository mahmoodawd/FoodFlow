package com.example.foodflow.meal_details.presenter;

import com.example.foodflow.models.Meal;

public interface MealDetailsPresenterInterface {
    void getMealDetails(String mealID);
    void addMeal(Meal meal);
    void deleteMeal(Meal meal);
}
