package com.example.foodflow.search.categories.presenter;

import com.example.foodflow.network.NetworkDelegate;
import com.example.foodflow.repositories.RepositoryInterface;
import com.example.foodflow.search.categories.view.CategoriesViewInterface;

import java.util.List;

public class CategoriesPresenter implements CategoriesPresenterInterface, NetworkDelegate {
    private CategoriesViewInterface _view;
    private RepositoryInterface _repo;

    public CategoriesPresenter(CategoriesViewInterface view, RepositoryInterface repo) {
        this._view = view;
        this._repo = repo;
    }


    @Override
    public void onSuccess(List items) {
        _view.displayCategories(items);
    }


    @Override
    public void onFailure(Throwable t) {

    }

    @Override
    public void getCategories() {
        _repo.getCategories(this);
    }

}
