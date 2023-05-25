package com.example.foodflow.network;

import com.example.foodflow.models.Meal;

import java.util.List;

public interface NetworkDelegate<T> {
    void onSuccess(List<T> productList);

    void onFailure(Throwable t);
}
