package com.example.foodflow.search.presenter;

import com.example.foodflow.models.Meal;
import com.example.foodflow.network.NetworkDelegate;
import com.example.foodflow.repositories.RepositoryInterface;
import com.example.foodflow.search.view.SearchViewInterface;

import java.util.List;

public class SearchPresenter implements SearchPresenterInterface, NetworkDelegate {
    private SearchViewInterface _view;
    private RepositoryInterface _repo;

    public SearchPresenter(SearchViewInterface view, RepositoryInterface repo) {
        this._view = view;
        this._repo = repo;
    }


    @Override
    public void onSuccess(List items) {
        _view.showSearchResult(items);

    }


    @Override
    public void onFailure(Throwable t) {

    }

    @Override
    public void getCategories() {
        _repo.getCategories(this);
    }

    @Override
    public void getAreas() {
        _repo.getAreas(this);
    }

    @Override
    public void getIngredients() {
        _repo.getIngredients(this);
    }

    @Override
    public void searchMeal(String mealName) {
        _repo.searchMeals(this, mealName);
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
