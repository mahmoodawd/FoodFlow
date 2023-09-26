package com.example.foodflow.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodflow.models.PlannerMeal;

import java.util.List;

import io.reactivex.Observable;


@Dao
public interface PlannerMealDoa {

    @Query(value = "SELECT * FROM plannermeal WHERE USER_ID = :uID")
    Observable<List<PlannerMeal>> getWeekMeals(String uID);

    @Query(value = "SELECT * FROM plannermeal WHERE ID = :weekDay AND  USER_ID = :uID")
    Observable<List<PlannerMeal>> getDayMeals(String weekDay, String uID);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(PlannerMeal meals);

    @Delete
    void delete(PlannerMeal meal);

}
