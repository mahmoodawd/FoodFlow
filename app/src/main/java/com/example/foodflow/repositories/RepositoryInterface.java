package com.example.foodflow.repositories;


import com.example.foodflow.network.NetworkDelegate;


public interface RepositoryInterface {
    void getMealOfTheDay(NetworkDelegate callback);

    void getCategories(NetworkDelegate callback);

    void getMealDetails(NetworkDelegate callback, String mealId);

    void searchMeals(NetworkDelegate callback, String title);

    void getMealsByCategories(NetworkDelegate callback, String category);

    void getMealsByArea(NetworkDelegate callback, String area);

    void getMealsByIngredient(NetworkDelegate callback, String ingredient);

}
