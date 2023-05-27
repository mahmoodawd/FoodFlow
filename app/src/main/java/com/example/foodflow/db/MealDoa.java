package com.example.foodflow.db;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodflow.models.Meal;

import java.util.List;


@Dao
public interface MealDoa {

    @Query("SELECT * FROM meal")
    LiveData<List<Meal>> getStoredMeals();


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(Meal meals);

    @Delete
    void delete(Meal meal);

}
