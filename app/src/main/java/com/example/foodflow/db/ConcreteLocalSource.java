package com.example.foodflow.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodflow.models.Meal;
import com.example.foodflow.models.PlannerMeal;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public class ConcreteLocalSource implements LocalSource {
    private Context context;
    MealDoa mealDoa;
    PlannerMealDoa plannerMealDoa;
    LiveData<List<PlannerMeal>> dayMeals;
    private static ConcreteLocalSource instance = null;


    private ConcreteLocalSource(Context context) {
        this.context = context;
        AppDB db = AppDB.getInstance(context.getApplicationContext());
        mealDoa = db.mealDao();
        plannerMealDoa = db.plannerMealDoa();



    }

    public static ConcreteLocalSource getInstance(Context context) {
        if (instance == null) {
            instance = new ConcreteLocalSource(context);
        }
        return instance;
    }

    @Override
    public Observable<List<Meal>> getStoredMeals() {
        return mealDoa.getStoredMeals();
    }

    @Override
    public Single<List<Meal>> getMealDetails(String id) {
        return mealDoa.getMealDetails(id);
    }

    @Override
    public void insert(Meal meal) {
        new Thread(() -> mealDoa.insertAll(meal)).start();
    }

    @Override
    public void delete(Meal meal) {

        new Thread(() -> mealDoa.delete(meal)).start();
    }

    @Override
    public Observable<List<PlannerMeal>> getWeekMeals() {
        return plannerMealDoa.getWeekMeals();
    }

    @Override
    public Observable<List<PlannerMeal>> getMealsByDay(String weekDay) {
        return  plannerMealDoa.getDayMeals(weekDay);

    }

    @Override
    public void plan(PlannerMeal meal) {
        new Thread(() -> plannerMealDoa.insertAll(meal)).start();
    }

    @Override
    public void unPlan(PlannerMeal meal) {
        new Thread(() -> plannerMealDoa.delete(meal)).start();
    }


}
