package com.example.foodflow.planner.all_meals.presenter;

import android.util.Log;

import com.example.foodflow.models.PlannerMeal;
import com.example.foodflow.planner.all_meals.view.AllMealsViewInterface;
import com.example.foodflow.repositories.RepositoryInterface;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AllMealsPresenter implements AllMealsPresenterInterface{
    RepositoryInterface _repo;
    AllMealsViewInterface _view;
    private String TAG = "AllMealsPresenter";

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
        _repo.getAllMeals().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(mealsResponse -> _view.displayMeals(mealsResponse.getPlannerMeals()),
                        throwable -> Log.i(TAG, "Data Retrival Error: " + throwable.getMessage()));
    }


}
