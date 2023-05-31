package com.example.foodflow.search.view;

import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodflow.R;
import com.example.foodflow.db.ConcreteLocalSource;
import com.example.foodflow.core.view.OnThumbnailClickListener;
import com.example.foodflow.network.API_Client;
import com.example.foodflow.repositories.Repository;
import com.example.foodflow.search.presenter.SearchPresenter;
import com.facebook.shimmer.ShimmerFrameLayout;

import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment implements SearchViewInterface, OnThumbnailClickListener {

    RecyclerView searchResultRecyclerView;
    SearchAdapter searchResultAdapter;
    SearchView searchView;
    private Button AreasBtn;
    private Button ingredientsBtn;
    private Button categoriesBtn;
    private TextView searchResultTv;
    ShimmerFrameLayout shimmerFrameLayout;
    CardView placeHolder;


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
        AreasBtn = view.findViewById(R.id.areasNavBtn);
        ingredientsBtn = view.findViewById(R.id.ingredientNavBtn);
        categoriesBtn = view.findViewById(R.id.categoriesNavBtn);
        searchResultTv = view.findViewById(R.id.searchResultTV);
        searchView = view.findViewById(R.id.searchView);
        shimmerFrameLayout = view.findViewById(R.id.search_shimmer_view_container);
        placeHolder = view.findViewById(R.id.mealThumbPlaceHolder);
        searchResultTv.setVisibility(View.GONE);


        searchPresenter = new SearchPresenter(this, Repository.
                getInstance(this.getContext(), API_Client.getInstance(),
                        ConcreteLocalSource.getInstance(this.getContext())));


        ingredientsBtn.setOnClickListener(this::navToIngredients);
        AreasBtn.setOnClickListener(this::navToAreas);
        categoriesBtn.setOnClickListener(this::navToCategories);

        LinearLayoutManager searchResultLayoutManager = new LinearLayoutManager(this.getContext());
        searchResultLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        searchResultAdapter = new SearchAdapter(this.getContext(), new ArrayList<>(), this);
        searchResultRecyclerView.setHasFixedSize(true);
        searchResultRecyclerView.setLayoutManager(searchResultLayoutManager);
        searchResultRecyclerView.setAdapter(searchResultAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL);
        searchResultRecyclerView.addItemDecoration(dividerItemDecoration);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                shimmerFrameLayout.showShimmer(true);
                placeHolder.setVisibility(View.VISIBLE);
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
    public void showSearchResult(List<Object> searchedMeals) {
        if (searchedMeals == null || searchedMeals.isEmpty()) {
            Toast.makeText(getContext(), "No search results found", Toast.LENGTH_SHORT).show();
            searchResultTv.setText(R.string.no_results);
        } else {
            searchResultTv.setText(R.string.searchResult);
            placeHolder.setVisibility(View.GONE);
        }
        searchResultAdapter.setItemList(searchedMeals);
        searchResultAdapter.notifyDataSetChanged();
        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.hideShimmer();
        searchResultTv.setVisibility(View.VISIBLE);
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