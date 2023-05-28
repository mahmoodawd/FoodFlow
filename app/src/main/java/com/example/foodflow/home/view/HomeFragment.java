package com.example.foodflow.home.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodflow.R;
import com.example.foodflow.core.view.MealsAdapter;
import com.example.foodflow.core.view.MealsViewInterface;
import com.example.foodflow.core.view.OnThumbnailClickListener;
import com.example.foodflow.db.ConcreteLocalSource;
import com.example.foodflow.models.Meal;
import com.example.foodflow.network.API_Client;
import com.example.foodflow.core.presenter.MealsPresenter;
import com.example.foodflow.repositories.Repository;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements MealsViewInterface, OnThumbnailClickListener {
    RecyclerView mealsRecyclerView;
    MealsAdapter mealsAdapter;
    MealsPresenter mealsPresenter;
    CircularProgressIndicator loadingBar;

    public HomeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mealsRecyclerView = view.findViewById(R.id.mealsRecyclerView);
        loadingBar = view.findViewById(R.id.loadingIndicator);
        loadingBar.setVisibility(View.VISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mealsAdapter = new MealsAdapter(this.getContext(), new ArrayList<>(), this);
        mealsPresenter = new MealsPresenter(this, Repository
                .getInstance(this.getContext(), API_Client.getInstance(), ConcreteLocalSource.getInstance(this.getContext())));
        mealsRecyclerView.setHasFixedSize(true);
        mealsRecyclerView.setLayoutManager(layoutManager);
        mealsRecyclerView.setAdapter(mealsAdapter);
        mealsPresenter.getMealOfTheDay();
        return view;
    }

    private void showMealDetails(View view, String mealId) {
        HomeFragmentDirections.ActionHomeFragmentToMealDetailsFragment action = HomeFragmentDirections
                .actionHomeFragmentToMealDetailsFragment(mealId);
        Navigation.findNavController(view).navigate(action);
    }

    @Override
    public void displayMeals(List<Meal> mealList) {
        loadingBar.setVisibility(View.GONE);
        mealsAdapter.setMealsList(mealList);
        mealsAdapter.notifyDataSetChanged();
    }

    @Override
    public void addToFavourites(Meal meal) {

    }

    @Override
    public void onImageClick(View view, String mealId) {
        showMealDetails(view, mealId);
    }
}