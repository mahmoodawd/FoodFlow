package com.example.foodflow.search.ingredients.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodflow.R;
import com.example.foodflow.core.view.OnThumbnailClickListener;
import com.example.foodflow.db.ConcreteLocalSource;
import com.example.foodflow.models.Ingredient;
import com.example.foodflow.network.API_Client;
import com.example.foodflow.repositories.Repository;
import com.example.foodflow.search.ingredients.presenter.IngredientsPresenter;

import java.util.ArrayList;
import java.util.List;


public class IngredientsFragment extends Fragment implements IngredientsViewInterface, OnThumbnailClickListener {
    RecyclerView ingredientRecyclerView;
    IngredientsAdapter ingredientsAdapter;
    IngredientsPresenter ingredientsPresenter;

    public IngredientsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ingredientsPresenter = new IngredientsPresenter(this, Repository.
                getInstance(this.getContext(), API_Client.getInstance(),
                        ConcreteLocalSource.getInstance(this.getContext())));
        View view = inflater.inflate(R.layout.fragment_ingredients, container, false);
        ingredientRecyclerView = view.findViewById(R.id.ingredientsRecyclerView);
        LinearLayoutManager ingredientLayoutManager = new LinearLayoutManager(this.getContext());
        ingredientLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        ingredientsAdapter = new IngredientsAdapter(this.getContext(), new ArrayList<>(), this);
        ingredientRecyclerView.setHasFixedSize(true);
        ingredientRecyclerView.setLayoutManager(ingredientLayoutManager);
        ingredientRecyclerView.setAdapter(ingredientsAdapter);
        ingredientsPresenter.getIngredients();

        return view;
    }

    @Override
    public void displayIngredients(List<Ingredient> ingredientList) {
        ingredientsAdapter.setIngredients(ingredientList);
        ingredientsAdapter.notifyDataSetChanged();
    }

    private void showIngredientMeals(View view, String ingredientName) {
        IngredientsFragmentDirections.ActionIngredientsFragmentToIngredientMealsFragment action = IngredientsFragmentDirections
                .actionIngredientsFragmentToIngredientMealsFragment(ingredientName);
        Navigation.findNavController(view).navigate(action);

    }


    @Override
    public void onImageClick(View view, String ingredientName) {
        showIngredientMeals(view, ingredientName);
    }
}