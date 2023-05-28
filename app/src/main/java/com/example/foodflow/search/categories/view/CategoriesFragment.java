package com.example.foodflow.search.categories.view;

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


        categoriesPresenter = new CategoriesPresenter(this, Repository.
                getInstance(this.getContext(), API_Client.getInstance(),
                        ConcreteLocalSource.getInstance(this.getContext())));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        categoriesAdapter = new CategoriesAdapter(this.getContext(), new ArrayList<>(), this);
        categoryRecyclerView.setHasFixedSize(true);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryRecyclerView.setAdapter(categoriesAdapter);
        categoriesPresenter.getCategories();
        return view;
    }

    @Override
    public void displayCategories(List<Category> categoryList) {
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