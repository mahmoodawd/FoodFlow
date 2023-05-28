package com.example.foodflow.core.presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.foodflow.core.view.MealsViewInterface;
import com.example.foodflow.models.Meal;
import com.example.foodflow.network.NetworkDelegate;
import com.example.foodflow.repositories.RepositoryInterface;

import java.util.List;

public class MealsPresenter implements MealsPresenterInterface, NetworkDelegate {
    private MealsViewInterface _view;
    private RepositoryInterface _repo;

    public MealsPresenter(MealsViewInterface _view, RepositoryInterface _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void onSuccess(List mealList) {
        _view.displayMeals(mealList);
    }


    @Override
    public void onFailure(Throwable t) {

    }

    @Override
    public void getMealOfTheDay() {
        _repo.getMealOfTheDay(this);
    }

    @Override
    public void addMeal(Meal meal) {
        _repo.insert(meal);
    }

    @Override
    public LiveData<List<Meal>> getFavorites() {
        return _repo.getFavoritesMeals();
    }
    @Override
    public void informView(LifecycleOwner lifecycleOwner) { //in case of data retrieved from room(Favorites)
        _repo.getFavoritesMeals().observe(lifecycleOwner, meals -> {
            _view.displayMeals(meals);
        });
    }

    @Override
    public void getMealsByCategory(String categoryTitle) {
        _repo.getMealsByCategories(this, categoryTitle);
    }

    @Override
    public void getMealsByIngredient(String title) {
        _repo.getMealsByIngredient(this, title);
    }

    @Override
    public void getMealsByArea(String title) {
        _repo.getMealsByArea(this, title);
    }
}
