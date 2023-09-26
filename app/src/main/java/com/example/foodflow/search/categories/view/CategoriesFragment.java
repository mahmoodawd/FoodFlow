package com.example.foodflow.search.categories.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.foodflow.R;
import com.example.foodflow.core.view.OnThumbnailClickListener;
import com.example.foodflow.db.ConcreteLocalSource;
import com.example.foodflow.models.Category;
import com.example.foodflow.network.API_Client;
import com.example.foodflow.repositories.Repository;
import com.example.foodflow.search.categories.presenter.CategoriesPresenter;

import java.util.ArrayList;
import java.util.List;


public class CategoriesFragment extends Fragment implements CategoriesViewInterface, OnThumbnailClickListener {
    RecyclerView categoryRecyclerView;
    CategoriesAdapter categoriesAdapter;
    CategoriesPresenter categoriesPresenter;
    ProgressBar loading_indicator;

    public CategoriesFragment() {
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
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        categoryRecyclerView = view.findViewById(R.id.categoriesRecyclerView);
        loading_indicator = view.findViewById(R.id.loading_indicator);

        categoriesPresenter = new CategoriesPresenter(this, Repository.
                getInstance(this.getContext(), API_Client.getInstance(),
                        ConcreteLocalSource.getInstance(this.getContext())));
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        categoriesAdapter = new CategoriesAdapter(this.getContext(), new ArrayList<>(), this);
        categoryRecyclerView.setHasFixedSize(true);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryRecyclerView.setAdapter(categoriesAdapter);
        categoriesPresenter.getCategories();
        return view;
    }

    @Override
    public void displayCategories(List<Category> categoryList) {
        loading_indicator.setVisibility(View.GONE);
        categoriesAdapter.setCategories(categoryList);
        categoriesAdapter.notifyDataSetChanged();
    }


    private void showCategoryMeals(View view, String categoryTitle) {
        CategoriesFragmentDirections.ActionCategoriesFragmentToCategoryMealsFragment action = CategoriesFragmentDirections
                .actionCategoriesFragmentToCategoryMealsFragment(categoryTitle);
        Navigation.findNavController(view).navigate(action);

    }


    @Override
    public void onImageClick(View view, String categoryId) {
        showCategoryMeals(view, categoryId);
    }
}