package com.example.foodflow.planner.presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.foodflow.models.PlannerMeal;

import java.util.List;

public interface PlannerPresenterInterface {
    void deleteMealFromPlan(PlannerMeal meal);
    void getMealsOfTheWeek(LifecycleOwner lifecycleOwner);
}
