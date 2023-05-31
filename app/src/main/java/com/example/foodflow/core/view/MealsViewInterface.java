package com.example.foodflow.core.view;

import com.example.foodflow.models.Meal;

import java.util.List;

public interface MealsViewInterface {
    void displayMeals(List<Meal> mealList);
    void addToFavourites(Meal meal);
    void deleteFromFavorites(Meal meal);

}
