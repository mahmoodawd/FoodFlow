package com.example.foodflow.network;

import com.example.foodflow.model.Meal;

import java.util.List;

public class MyResponse {

    private List<Meal> meals;

    public MyResponse(int total, int skip, int limit, List<Meal> meals) {
        this.meals = meals;
    }


    public List<Meal> getMeals() {
        return meals;
    }
}
