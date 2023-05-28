package com.example.foodflow.network;


import java.util.List;

public interface NetworkDelegate<T> {
    void onSuccess(List<T> items);

    void onFailure(Throwable t);
}
