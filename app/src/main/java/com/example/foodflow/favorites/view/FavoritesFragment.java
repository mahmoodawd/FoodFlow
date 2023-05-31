package com.example.foodflow.favorites.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodflow.R;
import com.example.foodflow.core.presenter.MealsPresenter;
import com.example.foodflow.core.view.MealsViewInterface;
import com.example.foodflow.core.view.OnFavIconClickListener;
import com.example.foodflow.db.ConcreteLocalSource;
import com.example.foodflow.core.view.MealsAdapter;
import com.example.foodflow.core.view.OnThumbnailClickListener;
import com.example.foodflow.models.Meal;
import com.example.foodflow.models.PlannerMeal;
import com.example.foodflow.network.API_Client;
import com.example.foodflow.planner.view.OnDelIconClickListener;
import com.example.foodflow.repositories.Repository;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment implements MealsViewInterface, OnThumbnailClickListener, OnDelImgClickListener {
    RecyclerView mealsRecyclerView;
    FavoritesMealsAdapter mealsAdapter;
    MealsPresenter mealsPresenter;

    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        mealsRecyclerView = view.findViewById(R.id.mealsRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mealsAdapter = new FavoritesMealsAdapter(this.getContext(), new ArrayList<>(), this, this);
        mealsPresenter = new MealsPresenter(this, Repository
                .getInstance(this.getContext(), API_Client.getInstance(), ConcreteLocalSource.getInstance(this.getContext())));
        mealsRecyclerView.setHasFixedSize(true);
        mealsRecyclerView.setLayoutManager(layoutManager);
        mealsRecyclerView.setAdapter(mealsAdapter);
        mealsPresenter.getFavorites();
        mealsPresenter.informView(this.getViewLifecycleOwner());
        return view;
    }


    @Override
    public void onImageClick(View view, String mealId) {
        showMealDetails(view, mealId);

    }

    private void showMealDetails(View view, String mealId) {
        FavoritesFragmentDirections.ActionFavoritesFragmentToMealDetailsFragment action = FavoritesFragmentDirections
                .actionFavoritesFragmentToMealDetailsFragment(mealId);
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
    public void deleteFromFavorites(Meal meal) {
        mealsPresenter.deleteMealFromFav(meal);
    }


    @Override
    public void onDelIconClick(Meal meal) {
        deleteFromFavorites(meal);
        mealsAdapter.notifyDataSetChanged();
    }
}