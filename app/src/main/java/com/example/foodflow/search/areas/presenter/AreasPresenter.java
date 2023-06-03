package com.example.foodflow.search.areas.presenter;

import android.util.Log;

import com.example.foodflow.repositories.RepositoryInterface;
import com.example.foodflow.search.areas.view.AreasViewInterface;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AreasPresenter implements AreasPresenterInterface{

    private AreasViewInterface _view;
    private RepositoryInterface _repo;
    private String TAG = "AreasPresenter";

    public AreasPresenter(AreasViewInterface view, RepositoryInterface repo) {
        _view = view;
        _repo = repo;
    }



    @Override
    public void getAreas() {
        _repo.getAreas().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(areasResponse -> _view.displayAreas(areasResponse.getAreas()),
                        throwable -> Log.i(TAG, "Data Retrival Error: " + throwable.getMessage()));
    }
}
