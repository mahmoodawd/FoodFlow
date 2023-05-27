package com.example.foodflow.favorites.presenter;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;


import com.example.foodflow.favorites.view.FavoritesViewInterface;
import com.example.foodflow.models.Meal;
import com.example.foodflow.repositories.RepositoryInterface;

import java.util.List;

public class FavoritesPresenter implements FavoritesPresenterInterface {
    FavoritesViewInterface _view;
    RepositoryInterface _repo;

    public FavoritesPresenter(FavoritesViewInterface _view, RepositoryInterface _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public LiveData<List<Meal>> getFavorites() {
        return _repo.getFavoritesMeals();
    }

    @Override
    public void informView(LifecycleOwner lifecycleOwner) {
        _repo.getFavoritesMeals().observe(lifecycleOwner, meals -> {
            _view.displayFavorites(meals);
        });
    }
}
