package com.example.foodflow.home.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.foodflow.R;
import com.example.foodflow.db.ConcreteLocalSource;
import com.example.foodflow.home.presenter.HomePresenter;
import com.example.foodflow.models.Meal;
import com.example.foodflow.repositories.Repository;
import com.example.foodflow.network.API_Client;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeViewInterface {
    RecyclerView mealsRecyclerView;
    MealsAdapter mealsAdapter;
    HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mealsRecyclerView = findViewById(R.id.mealsRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mealsAdapter = new MealsAdapter(this, new ArrayList<>());
        homePresenter = new HomePresenter(this, Repository.getInstance(this, API_Client.getInstance(), ConcreteLocalSource.getInstance(this)));
        mealsRecyclerView.setHasFixedSize(true);
        mealsRecyclerView.setLayoutManager(layoutManager);
        mealsRecyclerView.setAdapter(mealsAdapter);
        homePresenter.getMealOfTheDay();
    }

    @Override
    public void displayMeals(List<Meal> mealList) {
        mealsAdapter.setMealsList(mealList);
        mealsAdapter.notifyDataSetChanged();
    }

    @Override
    public void addToFavourites(Meal meal) {

    }
}