package com.example.foodflow.db;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodflow.models.Meal;
import com.example.foodflow.models.PlannerMeal;

import java.util.List;


@Dao
public interface PlannerMealDoa {

    @Query(value = "SELECT * FROM plannermeal" )
    LiveData<List<PlannerMeal>> getWeekMeals();

    @Query(value = "SELECT * FROM plannermeal WHERE  ID = :weekDay" )
    LiveData<List<PlannerMeal>> getDayMeals(String weekDay);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(PlannerMeal meals);

    @Delete
    void delete(PlannerMeal meal);

}
