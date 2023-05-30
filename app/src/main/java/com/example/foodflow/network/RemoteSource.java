package com.example.foodflow.network;

public interface RemoteSource {
    void getAllMeals(NetworkDelegate networkDelegate);

    void getMealOfTheDay(NetworkDelegate networkDelegate);

    void getMealDetails(NetworkDelegate networkDelegate, String mealId);

    void searchMeals(NetworkDelegate networkDelegate, String title);

    void getMealsByCategories(NetworkDelegate networkDelegate, String category);

    void getMealsByArea(NetworkDelegate networkDelegate, String area);

    void getMealsByIngredient(NetworkDelegate networkDelegate, String ingredient);

    void getCategories(NetworkDelegate networkDelegate);

    void getAreas(NetworkDelegate networkDelegate);

    void getIngredients(NetworkDelegate networkDelegate);

}
