package com.example.foodflow.planner.presenter;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.foodflow.models.PlannerMeal;
import com.example.foodflow.planner.view.PlannerViewInterface;
import com.example.foodflow.repositories.RepositoryInterface;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PlannerPresenter implements PlannerPresenterInterface {
    RepositoryInterface _repo;
    PlannerViewInterface _view;
    private String TAG = "PlannerPresenter";

    public PlannerPresenter(RepositoryInterface repo, PlannerViewInterface view) {
        this._repo = repo;
        this._view = view;

    }


    @Override
    public void deleteMealFromPlan(PlannerMeal meal) {
        _repo.unPlan(meal);
    }

    @Override
    public void getMealsOfTheWeek(LifecycleOwner lifecycleOwner) {

        _repo.getCurrentWeekMeals().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(plannerMeals -> _view.displayMeals(plannerMeals),
                        throwable -> Log.i(TAG, "Data Retrival Error: " + throwable.getMessage()));

    }
}
