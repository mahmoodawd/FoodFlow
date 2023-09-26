package com.example.foodflow.db;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.foodflow.models.Meal;
import com.example.foodflow.models.PlannerMeal;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;
import java.util.Objects;

import io.reactivex.Observable;
import io.reactivex.Single;

public class ConcreteLocalSource implements LocalSource {
    MealDoa mealDoa;
    PlannerMealDoa plannerMealDoa;
    private static ConcreteLocalSource instance = null;


    private ConcreteLocalSource(Context context) {
        AppDB db = AppDB.getInstance(context.getApplicationContext());
        mealDoa = db.mealDao();
        plannerMealDoa = db.plannerMealDoa();

    }

    @NonNull
    private String getUserID() {
        return Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
    }

    public static ConcreteLocalSource getInstance(Context context) {
        if (instance == null) {
            instance = new ConcreteLocalSource(context);
        }
        return instance;
    }

    @Override
    public Observable<List<Meal>> getStoredMeals() {
        return mealDoa.getStoredMeals(getUserID());
    }

    @Override
    public Single<List<Meal>> getMealDetails(String id) {
        return mealDoa.getMealDetails(id);
    }

    @Override
    public void insert(Meal meal) {
        meal.setUserID(getUserID());
        new Thread(() -> mealDoa.insertAll(meal)).start();
    }

    @Override
    public void delete(Meal meal) {
        meal.setUserID(getUserID());
        new Thread(() -> mealDoa.delete(meal)).start();
    }

    @Override
    public Observable<List<PlannerMeal>> getWeekMeals() {
        return plannerMealDoa.getWeekMeals(getUserID());
    }

    @Override
    public Observable<List<PlannerMeal>> getMealsByDay(String weekDay) {
        return plannerMealDoa.getDayMeals(weekDay, getUserID());

    }

    @Override
    public void plan(PlannerMeal meal) {
        meal.setUserID(getUserID());
        new Thread(() -> plannerMealDoa.insertAll(meal)).start();
    }

    @Override
    public void unPlan(PlannerMeal meal) {
        meal.setUserID(getUserID());
        new Thread(() -> plannerMealDoa.delete(meal)).start();
    }




}
