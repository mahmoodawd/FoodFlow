package com.example.foodflow.favorites.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodflow.R;
import com.example.foodflow.WelcomeActivity;
import com.example.foodflow.core.presenter.MealsPresenter;
import com.example.foodflow.core.view.MealsViewInterface;
import com.example.foodflow.core.view.OnThumbnailClickListener;
import com.example.foodflow.db.ConcreteLocalSource;
import com.example.foodflow.models.Meal;
import com.example.foodflow.network.API_Client;
import com.example.foodflow.repositories.Repository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment
        implements MealsViewInterface, OnThumbnailClickListener, OnDelImgClickListener {
    RecyclerView mealsRecyclerView;
    FavoritesMealsAdapter mealsAdapter;
    MealsPresenter mealsPresenter;
    View guestPrompt;
    Button navToLoginBtn;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

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
        initUi(view);
        navToLoginBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this.getContext(), WelcomeActivity.class);
            startActivity(intent);
        });
        mealsPresenter = new MealsPresenter(this, Repository
                .getInstance(this.getContext(), API_Client.getInstance(), ConcreteLocalSource.getInstance(this.getContext())));
        if (user != null) {
            mealsPresenter.getFavorites();
        }
        return view;
    }

    private void initUi(View view) {
        mealsRecyclerView = view.findViewById(R.id.mealsRecyclerView);
        mealsRecyclerView.setVisibility(user != null ? View.VISIBLE : View.GONE);
        guestPrompt = view.findViewById(R.id.guestPromptView1);
        navToLoginBtn = guestPrompt.findViewById(R.id.navToAuthBtn);
        guestPrompt.setVisibility(user == null ? View.VISIBLE : View.GONE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mealsAdapter = new FavoritesMealsAdapter(this.getContext(), new ArrayList<>(), this, this);
        mealsRecyclerView.setHasFixedSize(true);
        mealsRecyclerView.setLayoutManager(layoutManager);
        mealsRecyclerView.setAdapter(mealsAdapter);
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
        if (user != null) {
            mealsAdapter.setMealsList(mealList);
            mealsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void addToFavourites(Meal meal) {

    }

    @Override
    public void deleteFromFavorites(Meal meal) {
        mealsPresenter.deleteMealFromFav(meal);
        mealsAdapter.notifyDataSetChanged();
    }


    @Override
    public void onDelIconClick(Meal meal) {
        deleteFromFavorites(meal);

    }
}