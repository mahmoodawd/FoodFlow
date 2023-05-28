package com.example.foodflow.search.view;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodflow.R;
import com.example.foodflow.core.view.MealsAdapter;
import com.example.foodflow.db.ConcreteLocalSource;
import com.example.foodflow.core.view.OnThumbnailClickListener;
import com.example.foodflow.models.Meal;
import com.example.foodflow.network.API_Client;
import com.example.foodflow.repositories.Repository;
import com.example.foodflow.search.presenter.SearchPresenter;

import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment implements SearchViewInterface, OnThumbnailClickListener {

    RecyclerView searchResultRecyclerView;
    MealsAdapter searchResultAdapter;
    SearchView searchView;
    private TextView areaTV;
    private TextView ingredientTV;
    private TextView categoryTv;
    private TextView searchResultTv;


    SearchPresenter searchPresenter;


    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        searchResultRecyclerView = view.findViewById(R.id.searchResultRecyclerView);
        areaTV = view.findViewById(R.id.areaTV);
        ingredientTV = view.findViewById(R.id.ingredientTV);
        categoryTv = view.findViewById(R.id.categoryTV);
        searchResultTv = view.findViewById(R.id.searchResultTV);
        searchResultTv.setVisibility(View.GONE);
        searchView = view.findViewById(R.id.searchView);


        searchPresenter = new SearchPresenter(this, Repository.
                getInstance(this.getContext(), API_Client.getInstance(),
                        ConcreteLocalSource.getInstance(this.getContext())));


        areaTV.setOnClickListener(this::navToAreas);
        ingredientTV.setOnClickListener(this::navToIngredients);
        categoryTv.setOnClickListener(this::navToCategories);

        LinearLayoutManager searchResultLayoutManager = new LinearLayoutManager(this.getContext());
        searchResultLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        searchResultAdapter = new MealsAdapter(this.getContext(), new ArrayList<>(), this);
        searchResultRecyclerView.setHasFixedSize(true);
        searchResultRecyclerView.setLayoutManager(searchResultLayoutManager);
        searchResultRecyclerView.setAdapter(searchResultAdapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchPresenter.searchMeal(query);
                hideSoftKeyBoard();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                searchPresenter.searchMeal(newText);
                return false;
            }
        });
        return view;
    }

    @Override
    public void showSearchResult(List<Meal> searchedMeals) {
        if (searchedMeals == null || searchedMeals.isEmpty()) {
            Toast.makeText(getContext(), "No search results found", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this.getContext(), "Search Done", Toast.LENGTH_SHORT).show();
            searchResultTv.setVisibility(View.VISIBLE);
            searchResultAdapter.setMealsList(searchedMeals);
            searchResultAdapter.notifyDataSetChanged();
        }
    }

    private void navToAreas(View view) {
        Navigation.findNavController(view).navigate(R.id.areasFragment);
    }

    private void navToIngredients(View view) {
        Navigation.findNavController(view).navigate(R.id.ingredientsFragment);
    }

    private void navToCategories(View view) {
        Navigation.findNavController(view).navigate(R.id.categoriesFragment);
    }


    private void showMealDetails(View view, String mealId) {
        SearchFragmentDirections.ActionSearchFragmentToMealDetailsFragment action = SearchFragmentDirections
                .actionSearchFragmentToMealDetailsFragment(mealId);
        Navigation.findNavController(view).navigate(action);

    }


    @Override
    public void onImageClick(View view, String mealId) {
        showMealDetails(view, mealId);
    }

    private void hideSoftKeyBoard() {
        // Hide the keyboard
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }
}