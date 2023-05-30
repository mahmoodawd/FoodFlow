package com.example.foodflow.models;

import com.example.foodflow.models.PlannerMeal;

import java.util.List;

public class WeekDay {
    private String title;
    private List<PlannerMeal> meals;

    public WeekDay(String title, List<PlannerMeal> meals) {
        this.title = title;
        this.meals = meals;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<PlannerMeal> getMeals() {
        return meals;
    }

    public void setMeals(List<PlannerMeal> meals) {
        this.meals = meals;
    }
}
