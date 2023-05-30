package com.example.foodflow.planner.all_meals.view;

import com.example.foodflow.models.PlannerMeal;

import java.util.List;

public interface AllMealsViewInterface {
    void displayMeals(List<PlannerMeal> plannerMeals);
    void addToPlan(PlannerMeal meal);

}
