package com.example.foodflow.home.presenter;

import com.example.foodflow.home.view.HomeViewInterface;
import com.example.foodflow.model.Meal;
import com.example.foodflow.model.RepositoryInterface;
import com.example.foodflow.network.NetworkDelegate;

import java.util.List;

public class HomePresenter implements HomePresenterInterface, NetworkDelegate {
    private HomeViewInterface _view;
    private RepositoryInterface _repo;

    public HomePresenter(HomeViewInterface _view, RepositoryInterface _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getMeals() {
        _repo.getMeals(this);
    }

    @Override
    public void addMeal(Meal meal) {

    }

    @Override
    public void onSuccess(List<Meal> mealList) {
        _view.displayMeals(mealList);
    }


    @Override
    public void onFailure(Throwable t) {

    }
}