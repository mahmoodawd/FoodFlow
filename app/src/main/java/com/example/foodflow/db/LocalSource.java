package com.example.foodflow.db;


import androidx.lifecycle.LiveData;

import com.example.foodflow.models.Meal;

import java.util.List;

public interface LocalSource {
    LiveData<List<Meal>> getStoredMeals();

    void insert(Meal Meal);

    void delete(Meal Meal);
}
