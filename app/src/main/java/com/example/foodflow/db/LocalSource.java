package com.example.foodflow.db;


import com.example.foodflow.models.Meal;
import com.example.foodflow.models.PlannerMeal;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface LocalSource {
    Observable<List<Meal>> getStoredMeals();

    Single<List<Meal>> getMealDetails(String id);

    void insert(Meal meal);

    void delete(Meal meal);

    Observable<List<PlannerMeal>> getWeekMeals();

    Observable<List<PlannerMeal>> getMealsByDay(String weekDay);

    void plan(PlannerMeal meal);

    void unPlan(PlannerMeal meal);


}
