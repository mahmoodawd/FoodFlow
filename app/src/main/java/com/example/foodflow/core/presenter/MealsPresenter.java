package com.example.foodflow.core.presenter;

import android.util.Log;

import com.example.foodflow.core.view.MealsViewInterface;
import com.example.foodflow.models.Meal;
import com.example.foodflow.models.MealsResponse;
import com.example.foodflow.repositories.RepositoryInterface;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MealsPresenter implements MealsPresenterInterface {
    private MealsViewInterface _view;
    private RepositoryInterface _repo;
    private String TAG = "MealsPresenter";

    public MealsPresenter(MealsViewInterface _view, RepositoryInterface _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getMealOfTheDay() {
        Single<MealsResponse> mealsResponseSingle = _repo.getMealOfTheDay()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        mealsResponseSingle.subscribe(mealsResponse -> _view.displayMeals(mealsResponse.getMeals()),
                throwable -> Log.i(TAG, "Data Retrival Error: " + throwable.getMessage()));
    }

    @Override
    public void getMealsByCategory(String categoryTitle) {
        _repo.getMealsByCategory(categoryTitle).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(mealsResponse -> _view.displayMeals(mealsResponse.getMeals()),
                        throwable -> Log.i(TAG, "Data Retrival Error: " + throwable.getMessage()));
    }

    @Override
    public void getMealsByIngredient(String title) {
        _repo.getMealsByIngredient(title).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(mealsResponse -> _view.displayMeals(mealsResponse.getMeals()),
                        throwable -> Log.i(TAG, "Data Retrival Error: " + throwable.getMessage()));
    }

    @Override
    public void getMealsByArea(String title) {
        _repo.getMealsByArea(title).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(mealsResponse -> _view.displayMeals(mealsResponse.getMeals()),
                        throwable -> Log.i(TAG, "Data Retrival Error: " + throwable.getMessage()));
    }


    //Local
    @Override
    public void addMealToFav(Meal meal) {
        _repo.insertIntoFavorites(meal);
    }

    @Override
    public void deleteMealFromFav(Meal meal) {
        _repo.deleteFromFavorites(meal);
    }

    @Override
    public void getFavorites() {
        _repo.getFavoritesMeals().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(meals -> _view.displayMeals(meals),
                        throwable -> Log.i(TAG, "Data Retrival Error: " + throwable.getMessage()));
    }


}
