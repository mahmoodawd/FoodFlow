package com.example.foodflow.search.areas.view;

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
import com.example.foodflow.models.Area;
import com.example.foodflow.network.API_Client;
import com.example.foodflow.repositories.Repository;
import com.example.foodflow.search.areas.presenter.AreasPresenter;

import java.util.ArrayList;
import java.util.List;


public class AreasFragment extends Fragment implements AreasViewInterface, OnThumbnailClickListener {
    RecyclerView areaRecyclerView;
    AreasAdapter areasAdapter;
    AreasPresenter areasPresenter;


    public AreasFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_areas, container, false);
        areaRecyclerView = view.findViewById(R.id.areasRecyclerView);


        areasPresenter = new AreasPresenter(this, Repository.
                getInstance(this.getContext(), API_Client.getInstance(),
                        ConcreteLocalSource.getInstance(this.getContext())));
        LinearLayoutManager areaLayoutManager = new LinearLayoutManager(this.getContext());
        areaLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        areasAdapter = new AreasAdapter(this.getContext(), new ArrayList<>(), this);
        areaRecyclerView.setHasFixedSize(true);
        areaRecyclerView.setLayoutManager(areaLayoutManager);
        areaRecyclerView.setAdapter(areasAdapter);
        areasPresenter.getAreas();


        return view;
    }

    @Override
    public void displayAreas(List<Area> areaList) {
        areasAdapter.setAreas(areaList);
        areasAdapter.notifyDataSetChanged();
    }

    private void showAreaMeals(View view, String areaName) {
        AreasFragmentDirections.ActionAreasFragmentToAreaMealsFragment action = AreasFragmentDirections
                .actionAreasFragmentToAreaMealsFragment(areaName);
        Navigation.findNavController(view).navigate(action);

    }

    @Override
    public void onImageClick(View view, String areaName) {
        showAreaMeals(view, areaName);
    }
}