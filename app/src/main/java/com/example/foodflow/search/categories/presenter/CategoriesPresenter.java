package com.example.foodflow.search.categories.presenter;

import android.util.Log;

import com.example.foodflow.repositories.RepositoryInterface;
import com.example.foodflow.search.categories.view.CategoriesViewInterface;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CategoriesPresenter implements CategoriesPresenterInterface {
    private CategoriesViewInterface _view;
    private RepositoryInterface _repo;
    private String TAG = "CategoriesPresenter";

    public CategoriesPresenter(CategoriesViewInterface view, RepositoryInterface repo) {
        this._view = view;
        this._repo = repo;
    }




    @Override
    public void getCategories() {
        _repo.getCategories().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(categoriesResponse -> _view.displayCategories(categoriesResponse.getCategories()),
                        throwable -> Log.i(TAG, "Data Retrival Error: " + throwable.getMessage()));
    }

}
