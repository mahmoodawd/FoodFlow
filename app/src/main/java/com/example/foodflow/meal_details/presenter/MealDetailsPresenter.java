package com.example.foodflow.meal_details.presenter;

import android.util.Log;

import com.example.foodflow.meal_details.view.MealDetailsViewInterface;
import com.example.foodflow.repositories.RepositoryInterface;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MealDetailsPresenter implements MealDetailsPresenterInterface {
    RepositoryInterface _repo;
    MealDetailsViewInterface _view;
    private String TAG = "MealDetailsPresenter";

    public MealDetailsPresenter(MealDetailsViewInterface _view, RepositoryInterface _repo) {
        this._repo = _repo;
        this._view = _view;
    }

    @Override
    public void getMealDetails(String mealID) {
        _repo.getMealDetails(mealID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals -> {
                            Log.i(TAG, "getMealDetails: " + meals.size());
                            _view.displayMealDetails(meals);

                        },
                        throwable -> Log.d(TAG, "getMealDetails: " + throwable.getMessage()));


    }


}
