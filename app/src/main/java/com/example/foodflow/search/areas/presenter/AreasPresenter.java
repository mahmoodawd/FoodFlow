package com.example.foodflow.search.areas.presenter;

import com.example.foodflow.network.NetworkDelegate;
import com.example.foodflow.repositories.RepositoryInterface;
import com.example.foodflow.search.areas.view.AreasViewInterface;

import java.util.List;

public class AreasPresenter implements AreasPresenterInterface, NetworkDelegate {

    private AreasViewInterface _view;
    private RepositoryInterface _repo;

    public AreasPresenter(AreasViewInterface view, RepositoryInterface repo) {
        _view = view;
        _repo = repo;
    }



    @Override
    public void onSuccess(List items) {
        _view.displayAreas(items);
    }

    @Override
    public void onFailure(Throwable t) {

    }

    @Override
    public void getAreas() {
        _repo.getAreas(this);
    }
}
