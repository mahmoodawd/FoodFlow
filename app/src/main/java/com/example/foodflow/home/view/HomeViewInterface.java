package com.example.foodflow.home.view;

import com.example.foodflow.model.Meal;

import java.util.List;

public interface HomeViewInterface {
    void displayMeals(List<Meal> mealList);
    void addToFavourites(Meal meal);

}
