package com.example.foodflow.db;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodflow.models.Meal;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;


@Dao
public interface MealDoa {

    @Query("SELECT * FROM meal")
    Observable<List<Meal>> getStoredMeals();

    @Query("SELECT * FROM meal WHERE ID = :mealID")
    Single<List<Meal>> getMealDetails(String mealID);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(Meal meals);

    @Delete
    void delete(Meal meal);

}
