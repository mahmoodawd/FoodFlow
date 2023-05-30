package com.example.foodflow.planner.view;

import com.example.foodflow.models.PlannerMeal;

import java.util.List;

public interface PlannerViewInterface {
    void displayMeals(List<PlannerMeal> plannerMeals);
    void removeFromPlan(PlannerMeal meal);

}
