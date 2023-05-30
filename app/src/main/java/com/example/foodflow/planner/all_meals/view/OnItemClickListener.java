package com.example.foodflow.planner.all_meals.view;

import android.view.View;

import com.example.foodflow.models.PlannerMeal;

public interface OnItemClickListener {
    void onImageClick(View view, PlannerMeal meal);
}
