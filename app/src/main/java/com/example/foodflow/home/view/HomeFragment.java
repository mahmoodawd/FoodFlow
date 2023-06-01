package com.example.foodflow.home.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodflow.core.view.OnFavIconClickListener;
import com.facebook.shimmer.ShimmerFrameLayout;

import androidx.cardview.widget.CardView;
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
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements MealsViewInterface, OnThumbnailClickListener, OnFavIconClickListener {
    RecyclerView mealsRecyclerView;
    MealsAdapter mealsAdapter;
    MealsPresenter mealsPresenter;
    ShimmerFrameLayout shimmerFrameLayout;
    CardView placeHolder;


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
        shimmerFrameLayout = view.findViewById(R.id.shimmer_view_container);
        placeHolder = view.findViewById(R.id.mealThumbPlaceHolder);
        mealsRecyclerView = view.findViewById(R.id.mealsRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mealsAdapter = new MealsAdapter(this.getContext(), new ArrayList<>(), this, this);
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
        shimmerFrameLayout.hideShimmer();
        placeHolder.setVisibility(View.GONE);
        mealsAdapter.setMealsList(mealList);
        mealsAdapter.notifyDataSetChanged();
    }

    @Override
    public void addToFavourites(Meal meal) {
        mealsPresenter.addMealToFav(meal);
    }

    @Override
    public void deleteFromFavorites(Meal meal) {
        mealsPresenter.deleteMealFromFav(meal);
    }

    @Override
    public void onImageClick(View view, String mealId) {
        showMealDetails(view, mealId);
    }

    @Override
    public void onFavClick(boolean isChecked, Meal meal) {
        if (isChecked) {
            addToFavourites(meal);
            Toast.makeText(this.getContext(), "Meal added to Favorites",
                    Toast.LENGTH_SHORT).show();
        } else {
            deleteFromFavorites(meal);
            Toast.makeText(this.getContext(), "Meal Removed from Favorites",
                    Toast.LENGTH_SHORT).show();
        }
    }
}