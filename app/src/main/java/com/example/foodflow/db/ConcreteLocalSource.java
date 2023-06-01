package com.example.foodflow.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodflow.models.Meal;
import com.example.foodflow.models.PlannerMeal;

import java.util.List;

public class ConcreteLocalSource implements LocalSource {
    private Context context;
    MealDoa mealDoa;
    PlannerMealDoa plannerMealDoa;
    LiveData<List<Meal>> storedMeals;
    LiveData<List<PlannerMeal>> dayMeals;
    private static ConcreteLocalSource instance = null;


    private ConcreteLocalSource(Context context) {
        this.context = context;
        AppDB db = AppDB.getInstance(context.getApplicationContext());
        mealDoa = db.mealDao();
        plannerMealDoa = db.plannerMealDoa();
        storedMeals = mealDoa.getStoredMeals();
        dayMeals = plannerMealDoa.getWeekMeals();



    }

    public static ConcreteLocalSource getInstance(Context context) {
        if (instance == null) {
            instance = new ConcreteLocalSource(context);
        }
        return instance;
    }

    @Override
    public LiveData<List<Meal>> getStoredMeals() {
        return storedMeals;
    }

    @Override
    public void insert(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDoa.insertAll(meal);
            }
        }).start();
    }

    @Override
    public void delete(Meal meal) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDoa.delete(meal);
            }
        }).start();
    }

    @Override
    public LiveData<List<PlannerMeal>> getWeekMeals() {
        return dayMeals;
    }

    @Override
    public LiveData<List<PlannerMeal>> getMealsByDay(String weekDay) {
        dayMeals = plannerMealDoa.getDayMeals(weekDay);
        return dayMeals;
    }

    @Override
    public void plan(PlannerMeal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                plannerMealDoa.insertAll(meal);
            }
        }).start();
    }

    @Override
    public void unPlan(PlannerMeal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                plannerMealDoa.delete(meal);
            }
        }).start();
    }


}
