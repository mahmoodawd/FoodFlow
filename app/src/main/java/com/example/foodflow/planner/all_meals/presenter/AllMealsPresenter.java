package com.example.foodflow.planner.all_meals.presenter;

import androidx.lifecycle.LifecycleOwner;

import com.example.foodflow.models.PlannerMeal;
import com.example.foodflow.network.NetworkDelegate;
import com.example.foodflow.planner.all_meals.view.AllMealsViewInterface;
import com.example.foodflow.planner.presenter.PlannerPresenterInterface;
import com.example.foodflow.planner.view.PlannerViewInterface;
import com.example.foodflow.repositories.RepositoryInterface;

import java.util.List;

public class AllMealsPresenter implements AllMealsPresenterInterface, NetworkDelegate {
    RepositoryInterface _repo;
    AllMealsViewInterface _view;

    public AllMealsPresenter(RepositoryInterface repo, AllMealsViewInterface view) {
        this._repo = repo;
        this._view = view;

    }

    @Override
    public void addMealToPlan(PlannerMeal meal) {
        _repo.plan(meal);
    }

    @Override
    public void getAllMeals() {
        _repo.getAllMeals(this);
    }


    @Override
    public void onSuccess(List items) {
        _view.displayMeals(items);
    }

    @Override
    public void onFailure(Throwable t) {

    }
}
