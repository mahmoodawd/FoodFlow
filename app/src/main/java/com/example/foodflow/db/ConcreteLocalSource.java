package com.example.foodflow.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodflow.model.Meal;

import java.util.List;

public class ConcreteLocalSource implements LocalSource {
    private Context context;
    MealDoa MealDoa;
    LiveData<List<Meal>> StoredMeals;
    LiveData<List<Meal>> targetMeal;
    private static ConcreteLocalSource instance = null;


    private ConcreteLocalSource(Context context) {
        this.context = context;
        FavoritesDB db = FavoritesDB.getInstance(context.getApplicationContext());
//        MealDoa = db.MealDoa();
//        StoredMeals = MealDoa.();
    }

    public static ConcreteLocalSource getInstance(Context context){
        if(instance == null){
            instance = new ConcreteLocalSource(context);
        }
        return instance;
    }

//    @Override
//    public LiveData<List<Meal>> getStoredMeals() {
//        return StoredMeals;
//    }

//    @Override
//    public LiveData<List<Meal>> searchMeal(String MealName) {
//        return targetMeal;
//    }
//
//    @Override
//    public void insert(Meal Meal) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                MealDoa.insertAll(Meal);
//            }
//        }).start();
//    }
//
//    @Override
//    public void delete(Meal Meal) {
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                MealDoa.delete(Meal);
//            }
//        }).start();
//    }


}
