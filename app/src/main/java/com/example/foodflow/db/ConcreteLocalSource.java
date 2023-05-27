package com.example.foodflow.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodflow.models.Meal;

import java.util.List;

public class ConcreteLocalSource implements LocalSource {
    private Context context;
    MealDoa mealDoa;
    LiveData<List<Meal>> storedMeals;
    private static ConcreteLocalSource instance = null;


    private ConcreteLocalSource(Context context) {
        this.context = context;
        FavoritesDB db = FavoritesDB.getInstance(context.getApplicationContext());
        mealDoa = db.mealDao();
        storedMeals = mealDoa.getStoredMeals();

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
    public void insert(Meal Meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDoa.insertAll(Meal);
            }
        }).start();
    }

    @Override
    public void delete(Meal Meal) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDoa.delete(Meal);
            }
        }).start();
    }


}
