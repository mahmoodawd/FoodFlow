package com.example.foodflow.search.presenter;


public interface SearchPresenterInterface {
    void getCategories();
    void getAreas();
    void getIngredients();
    void searchMeal(String mealName);
}
