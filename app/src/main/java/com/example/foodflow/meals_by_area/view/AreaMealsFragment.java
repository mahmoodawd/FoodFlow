package com.example.foodflow.meals_by_area.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodflow.R;
import com.example.foodflow.core.presenter.MealsPresenter;
import com.example.foodflow.core.view.MealsAdapter;
import com.example.foodflow.core.view.MealsViewInterface;
import com.example.foodflow.core.view.OnThumbnailClickListener;
import com.example.foodflow.db.ConcreteLocalSource;
import com.example.foodflow.meals_by_category.view.CategoryMealsFragmentArgs;
import com.example.foodflow.meals_by_category.view.CategoryMealsFragmentDirections;
import com.example.foodflow.models.Meal;
import com.example.foodflow.network.API_Client;
import com.example.foodflow.repositories.Repository;

import java.util.ArrayList;
import java.util.List;


public class AreaMealsFragment extends Fragment implements MealsViewInterface, OnThumbnailClickListener {
    RecyclerView mealsRecyclerView;
    MealsAdapter mealsAdapter;
    MealsPresenter mealsPresenter;
    TextView areaTitle;

    public AreaMealsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_area_meals, container, false);
        areaTitle = view.findViewById(R.id.areaTitleHeader);
        mealsRecyclerView = view.findViewById(R.id.areaMealsRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mealsAdapter = new MealsAdapter(this.getContext(), new ArrayList<>(), this);
        mealsPresenter = new MealsPresenter(this, Repository
                .getInstance(this.getContext(), API_Client.getInstance(), ConcreteLocalSource.getInstance(this.getContext())));
        mealsRecyclerView.setHasFixedSize(true);
        mealsRecyclerView.setLayoutManager(layoutManager);
        mealsRecyclerView.setAdapter(mealsAdapter);
        String title = AreaMealsFragmentArgs.fromBundle(getArguments()).getAreaName();
        areaTitle.setText(title);
        mealsPresenter.getMealsByArea(title);
        return view;
    }

    private void showMealDetails(View view, String mealId) {
        AreaMealsFragmentDirections.ActionAreaMealsFragmentToMealDetailsFragment action = AreaMealsFragmentDirections
                .actionAreaMealsFragmentToMealDetailsFragment(mealId);
        Navigation.findNavController(view).navigate(action);
    }

    @Override
    public void displayMeals(List<Meal> mealList) {
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