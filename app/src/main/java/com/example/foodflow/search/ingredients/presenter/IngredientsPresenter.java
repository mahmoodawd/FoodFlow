package com.example.foodflow.search.ingredients.presenter;

import com.example.foodflow.network.NetworkDelegate;
import com.example.foodflow.repositories.RepositoryInterface;
import com.example.foodflow.search.ingredients.view.IngredientsViewInterface;

import java.util.List;

public class IngredientsPresenter implements IngredientsPresenterInterface, NetworkDelegate {

    private IngredientsViewInterface _view;
    private RepositoryInterface _repo;

    public IngredientsPresenter(IngredientsViewInterface view, RepositoryInterface repo) {
        _view = view;
        _repo = repo;
    }

    @Override
    public void getIngredients() {
        _repo.getIngredients(this);
    }

    @Override
    public void onSuccess(List items) {
        _view.displayIngredients(items);
    }

    @Override
    public void onFailure(Throwable t) {

    }
}
