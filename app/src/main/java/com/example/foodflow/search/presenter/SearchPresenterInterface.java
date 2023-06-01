package com.example.foodflow.search.presenter;


import com.example.foodflow.models.Meal;

public interface SearchPresenterInterface {
    void getCategories();
    void getAreas();
    void getIngredients();
    void searchMeal(String mealName);
    void addMealToFav(Meal meal);
    void deleteMealFromFav(Meal meal);
}
