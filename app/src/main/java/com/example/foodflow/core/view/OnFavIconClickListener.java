package com.example.foodflow.core.view;

import com.example.foodflow.models.Meal;

public interface OnFavIconClickListener {
    void onFavClick(boolean isChecked, Meal meal);
}
