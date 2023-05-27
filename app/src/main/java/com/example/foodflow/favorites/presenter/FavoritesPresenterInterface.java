package com.example.foodflow.favorites.presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.foodflow.models.Meal;

import java.util.List;

public interface FavoritesPresenterInterface {
LiveData<List<Meal>> getFavorites();
void informView(LifecycleOwner lifecycleOwner);
}
