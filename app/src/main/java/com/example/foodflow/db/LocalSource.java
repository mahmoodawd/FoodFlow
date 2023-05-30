package com.example.foodflow.db;


import androidx.lifecycle.LiveData;

import com.example.foodflow.models.Meal;
import com.example.foodflow.models.PlannerMeal;

import java.util.List;

public interface LocalSource {
    LiveData<List<Meal>> getStoredMeals();

    void insert(Meal meal);

    void delete(Meal meal);
    LiveData<List<PlannerMeal>> getWeekMeals();
    LiveData<List<PlannerMeal>> getMealsByDay(String weekDay);
    void plan(PlannerMeal meal);
    void unPlan(PlannerMeal meal);


}
