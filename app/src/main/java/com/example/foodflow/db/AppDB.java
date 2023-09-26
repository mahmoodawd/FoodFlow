package com.example.foodflow.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodflow.models.Meal;
import com.example.foodflow.models.PlannerMeal;

@Database(entities = {Meal.class, PlannerMeal.class}, version = 1)
public abstract class AppDB extends RoomDatabase {
    private static AppDB instance = null;

    public abstract MealDoa mealDao();

    public abstract PlannerMealDoa plannerMealDoa();

    public static synchronized AppDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDB.class, "foodFlow-db")
                    .build();
        }

        return instance;
    }
}
