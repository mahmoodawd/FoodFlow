package com.example.foodflow.core.presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.foodflow.models.Meal;

import java.util.List;

public interface MealsPresenterInterface {

    void getMealOfTheDay();

    void addMeal(Meal meal);

    LiveData<List<Meal>> getFavorites();

    void informView(LifecycleOwner lifecycleOwner);

    void getMealsByCategory(String categoryTitle);

    void getMealsByIngredient(String title);

    void getMealsByArea(String title);
}
