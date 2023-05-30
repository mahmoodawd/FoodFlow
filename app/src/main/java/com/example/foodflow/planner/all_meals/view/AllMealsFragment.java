package com.example.foodflow.planner.all_meals.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodflow.R;
import com.example.foodflow.db.ConcreteLocalSource;
import com.example.foodflow.models.PlannerMeal;
import com.example.foodflow.network.API_Client;
import com.example.foodflow.planner.all_meals.presenter.AllMealsPresenter;
import com.example.foodflow.repositories.Repository;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;


public class AllMealsFragment extends Fragment implements AllMealsViewInterface, OnItemClickListener {
    AllMealsPresenter allMealsPresenter;
    AllMealsAdapter allMealsAdapter;
    RecyclerView allMealsRecyclerView;

    public AllMealsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_meals, container, false);
        allMealsRecyclerView = view.findViewById(R.id.allMealsRecyclerView);
        allMealsPresenter = new AllMealsPresenter(
                Repository.getInstance(this.getContext(), API_Client.getInstance(),
                        ConcreteLocalSource.getInstance(this.getContext())), this);
        allMealsAdapter = new AllMealsAdapter(this.getContext(), new ArrayList<>(), this);
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        allMealsRecyclerView.setHasFixedSize(true);
        allMealsRecyclerView.setLayoutManager(gridLayoutManager);
        allMealsRecyclerView.setAdapter(allMealsAdapter);
        allMealsPresenter.getAllMeals();
        return view;
    }

    @Override
    public void displayMeals(List<PlannerMeal> mealList) {
        allMealsAdapter.setMealsList(mealList);
    }

    @Override
    public void addToPlan(PlannerMeal meal) {
        String weekDay = AllMealsFragmentArgs.fromBundle(getArguments()).getWeekDay();
        meal.setWeekDay(weekDay);
        allMealsPresenter.addMealToPlan(meal);
        allMealsAdapter.notifyDataSetChanged();

    }

    @Override
    public void onImageClick(View view, PlannerMeal meal) {
        addToPlan(meal);
        Navigation.findNavController(view).navigateUp();
    }
}