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

import io.reactivex.Observable;
import io.reactivex.Single;


@Dao
public interface PlannerMealDoa {

    @Query(value = "SELECT * FROM plannermeal" )
    Observable<List<PlannerMeal>> getWeekMeals();

    @Query(value = "SELECT * FROM plannermeal WHERE  ID = :weekDay" )
    Observable<List<PlannerMeal>> getDayMeals(String weekDay);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(PlannerMeal meals);

    @Delete
    void delete(PlannerMeal meal);

}
