package com.example.foodflow.search.ingredients.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodflow.R;
import com.example.foodflow.core.view.OnThumbnailClickListener;
import com.example.foodflow.db.ConcreteLocalSource;
import com.example.foodflow.models.Ingredient;
import com.example.foodflow.network.API_Client;
import com.example.foodflow.repositories.Repository;
import com.example.foodflow.search.ingredients.presenter.IngredientsPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class IngredientsFragment extends Fragment implements IngredientsViewInterface, OnThumbnailClickListener {
    RecyclerView ingredientRecyclerView;
    IngredientsAdapter ingredientsAdapter;
    IngredientsPresenter ingredientsPresenter;
    List<Ingredient> newIngredientList;
    SearchView searchView;
    ProgressBar loading_indicator;


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
        View view = inflater.inflate(R.layout.fragment_ingredients, container, false);
        ingredientRecyclerView = view.findViewById(R.id.ingredientsRecyclerView);
        searchView = view.findViewById(R.id.ingredientSearchView);
        loading_indicator = view.findViewById(R.id.loading_indicator);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                hideSoftKeyBoard();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterByIngredient(newText);
                return false;
            }
        });


        ingredientsPresenter = new IngredientsPresenter(this, Repository.
                getInstance(this.getContext(), API_Client.getInstance(),
                        ConcreteLocalSource.getInstance(this.getContext())));
        newIngredientList = new ArrayList<>();
        GridLayoutManager ingredientLayoutManager = new GridLayoutManager(requireContext(), 3);
        ingredientsAdapter = new IngredientsAdapter(this.getContext(), newIngredientList, this);
        ingredientRecyclerView.setHasFixedSize(true);
        ingredientRecyclerView.setLayoutManager(ingredientLayoutManager);
        ingredientRecyclerView.setAdapter(ingredientsAdapter);
        ingredientsPresenter.getIngredients();

        return view;
    }

    @Override
    public void displayIngredients(List<Ingredient> ingredientList) {
        loading_indicator.setVisibility(View.GONE);
        ingredientsAdapter.setIngredients(ingredientList);
        ingredientsAdapter.notifyDataSetChanged();
        newIngredientList = ingredientList;
    }

    private void filterByIngredient(String ingredientName) {
        List<Ingredient> list = newIngredientList.stream()
                .filter(s -> s.getName().toLowerCase().contains(ingredientName.toLowerCase()))
                .collect(Collectors.toList());
        ingredientsAdapter.setIngredients(list);
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

    private void hideSoftKeyBoard() {
        // Hide the keyboard
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }
}