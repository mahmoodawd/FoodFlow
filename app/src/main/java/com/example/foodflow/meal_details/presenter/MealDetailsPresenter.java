package com.example.foodflow.meal_details.presenter;

import com.example.foodflow.meal_details.view.MealDetailsViewInterface;
import com.example.foodflow.models.Meal;
import com.example.foodflow.network.NetworkDelegate;
import com.example.foodflow.repositories.RepositoryInterface;

import java.util.List;

public class MealDetailsPresenter implements MealDetailsPresenterInterface, NetworkDelegate {
    RepositoryInterface _repo;
    MealDetailsViewInterface _view;

    public MealDetailsPresenter(MealDetailsViewInterface _view, RepositoryInterface _repo) {
        this._repo = _repo;
        this._view = _view;
    }

    @Override
    public void getMealDetails(String mealID) {
        _repo.getMealDetails(this, mealID);
    }

    @Override
    public void onSuccess(List mealList) {
        _view.displayMealDetails(mealList);
    }

    @Override
    public void onFailure(Throwable t) {

    }
}
