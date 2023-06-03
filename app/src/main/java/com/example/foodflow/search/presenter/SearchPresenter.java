package com.example.foodflow.search.presenter;

import android.util.Log;

import com.example.foodflow.models.Meal;
import com.example.foodflow.repositories.RepositoryInterface;
import com.example.foodflow.search.view.SearchViewInterface;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter implements SearchPresenterInterface {
    private SearchViewInterface _view;
    private RepositoryInterface _repo;
    private String TAG = "SearchPresenter";

    public SearchPresenter(SearchViewInterface view, RepositoryInterface repo) {
        this._view = view;
        this._repo = repo;
    }


    @Override
    public void searchMeal(String mealName) {
        _repo.searchMeal(mealName).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(mealsResponse -> _view.showSearchResult(mealsResponse.getMeals()),
                        throwable -> Log.i(TAG, "Data Retrival Error: " + throwable.getMessage()));
    }

    @Override
    public void addMealToFav(Meal meal) {
        _repo.insertIntoFavorites(meal);
    }

    @Override
    public void deleteMealFromFav(Meal meal) {
        _repo.deleteFromFavorites(meal);
    }


}
