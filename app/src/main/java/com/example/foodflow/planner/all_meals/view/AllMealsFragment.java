package com.example.foodflow.planner.all_meals.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.foodflow.R;
import com.example.foodflow.db.ConcreteLocalSource;
import com.example.foodflow.models.PlannerMeal;
import com.example.foodflow.network.API_Client;
import com.example.foodflow.planner.all_meals.presenter.AllMealsPresenter;
import com.example.foodflow.repositories.Repository;

import java.util.ArrayList;
import java.util.List;


public class AllMealsFragment extends Fragment implements AllMealsViewInterface, OnItemClickListener {
    AllMealsPresenter allMealsPresenter;
    AllMealsAdapter allMealsAdapter;
    RecyclerView allMealsRecyclerView;
    ProgressBar loadingIndicator;

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
        initUi(view);
        allMealsPresenter = new AllMealsPresenter(
                Repository.getInstance(this.getContext(), API_Client.getInstance(),
                        ConcreteLocalSource.getInstance(this.getContext())), this);
        allMealsPresenter.getAllMeals();
        return view;
    }

    private void initUi(View view) {
        loadingIndicator = view.findViewById(R.id.loading_indicator);
        allMealsRecyclerView = view.findViewById(R.id.allMealsRecyclerView);
        allMealsAdapter = new AllMealsAdapter(this.getContext(), new ArrayList<>(), this);
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        allMealsRecyclerView.setHasFixedSize(true);
        allMealsRecyclerView.setLayoutManager(gridLayoutManager);
        allMealsRecyclerView.setAdapter(allMealsAdapter);
    }

    @Override
    public void displayMeals(List<PlannerMeal> mealList) {
        loadingIndicator.setVisibility(View.GONE);
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