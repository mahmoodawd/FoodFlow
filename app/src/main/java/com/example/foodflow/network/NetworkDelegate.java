package com.example.foodflow.network;

import com.example.foodflow.model.Meal;

import java.util.List;

public interface NetworkDelegate {
    void onSuccess(List<Meal> productList);

    void onFailure(Throwable t);
}
