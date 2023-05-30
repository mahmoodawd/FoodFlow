package com.example.foodflow.planner.all_meals.presenter;

import androidx.lifecycle.LifecycleOwner;

import com.example.foodflow.models.PlannerMeal;

public interface AllMealsPresenterInterface {
    void addMealToPlan(PlannerMeal meal);
    void getAllMeals();
}
