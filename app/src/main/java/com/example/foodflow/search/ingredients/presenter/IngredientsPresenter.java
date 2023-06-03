package com.example.foodflow.search.ingredients.presenter;

import android.util.Log;

import com.example.foodflow.repositories.RepositoryInterface;
import com.example.foodflow.search.ingredients.view.IngredientsViewInterface;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class IngredientsPresenter implements IngredientsPresenterInterface {

    private IngredientsViewInterface _view;
    private RepositoryInterface _repo;
    private String TAG = "IngredientsPresenter";

    public IngredientsPresenter(IngredientsViewInterface view, RepositoryInterface repo) {
        _view = view;
        _repo = repo;
    }

    @Override
    public void getIngredients() {
        _repo.getIngredients().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(ingredientsResponse -> _view.displayIngredients(ingredientsResponse.getIngredients()),
                        throwable -> Log.i(TAG, "Data Retrival Error: " + throwable.getMessage()));
    }


}
