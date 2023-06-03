package com.example.foodflow.meal_details.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodflow.R;
import com.example.foodflow.db.ConcreteLocalSource;
import com.example.foodflow.meal_details.presenter.MealDetailsPresenter;
import com.example.foodflow.models.Meal;
import com.example.foodflow.network.API_Client;
import com.example.foodflow.repositories.Repository;

import java.util.ArrayList;
import java.util.List;


public class MealDetailsFragment extends Fragment implements MealDetailsViewInterface {
    RecyclerView mealDetailsRecyclerView;
    MealDetailsAdapter mealsAdapter;
    MealDetailsPresenter mealDetailsPresenter;
    private String TAG = "MealDetailsFragment";

    public MealDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meal_details, container, false);

        mealDetailsRecyclerView = view.findViewById(R.id.mealsDetailsRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mealsAdapter = new MealDetailsAdapter(this.getContext(), this.getLifecycle(), new ArrayList<>());
        mealDetailsPresenter = new MealDetailsPresenter(this, Repository
                .getInstance(this.getContext(), API_Client.getInstance(), ConcreteLocalSource.getInstance(this.getContext())));
        mealDetailsRecyclerView.setHasFixedSize(true);
        mealDetailsRecyclerView.setLayoutManager(layoutManager);
        mealDetailsRecyclerView.setAdapter(mealsAdapter);
        String mealId = MealDetailsFragmentArgs.fromBundle(getArguments()).getMealId();
        mealDetailsPresenter.getMealDetails(mealId);
        return view;
    }

    @Override
    public void displayMealDetails(List<Meal> mealList) {
        mealsAdapter.setMealsList(mealList);
        mealsAdapter.notifyDataSetChanged();
        Log.i(TAG, "displayMealDetails:" + mealList.size());

    }


}