package com.example.foodflow.planner.presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.example.foodflow.models.PlannerMeal;
import com.example.foodflow.planner.view.PlannerViewInterface;
import com.example.foodflow.repositories.RepositoryInterface;

import java.util.List;

public class PlannerPresenter implements PlannerPresenterInterface {
    RepositoryInterface _repo;
    PlannerViewInterface _view;

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

        _repo.getCurrentWeekMeals().observe(lifecycleOwner, plannerMeals -> _view.displayMeals(plannerMeals));

    }
}
