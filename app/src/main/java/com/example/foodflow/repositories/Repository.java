package com.example.foodflow.repositories;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.foodflow.db.ConcreteLocalSource;
import com.example.foodflow.models.Meal;
import com.example.foodflow.network.NetworkDelegate;
import com.example.foodflow.network.RemoteSource;

import java.util.List;

public class Repository implements RepositoryInterface {
    private Context context;
    RemoteSource remoteSource;
    ConcreteLocalSource concreteLocalSource;
    private static Repository instance = null;


    private Repository(Context context, RemoteSource remoteSource, ConcreteLocalSource concreteLocalSource) {
        this.context = context;
        this.remoteSource = remoteSource;
        this.concreteLocalSource = concreteLocalSource;
    }

    public static Repository getInstance(Context context, RemoteSource remoteSource, ConcreteLocalSource concreteLocalSource) {
        if (instance == null) {
            instance = new Repository(context, remoteSource, concreteLocalSource);
        }
        return instance;
    }


    @Override
    public void getMealOfTheDay(NetworkDelegate networkDelegate) {
        remoteSource.getMealOfTheDay(networkDelegate);
    }

    @Override
    public void getCategories(NetworkDelegate networkDelegate) {
        remoteSource.getCategories(networkDelegate);
    }

    @Override
    public void getMealDetails(NetworkDelegate networkDelegate, String mealId) {
        remoteSource.getMealDetails(networkDelegate, mealId);
    }

    @Override
    public void searchMeals(NetworkDelegate networkDelegate, String title) {
        remoteSource.searchMeals(networkDelegate, title);
    }

    @Override
    public void getMealsByCategories(NetworkDelegate networkDelegate, String category) {
        remoteSource.getMealsByCategories(networkDelegate, category);
    }

    @Override
    public void getMealsByArea(NetworkDelegate networkDelegate, String area) {
        remoteSource.getMealsByArea(networkDelegate, area);
    }

    @Override
    public void getMealsByIngredient(NetworkDelegate networkDelegate, String ingredient) {
        remoteSource.getMealsByIngredient(networkDelegate, ingredient);
    }

    @Override
    public LiveData<List<Meal>> getFavoritesMeals() {
        return concreteLocalSource.getStoredMeals();
    }

    @Override
    public void insert(Meal meal) {
        concreteLocalSource.insert(meal);
    }

    @Override
    public void delete(Meal meal) {
        concreteLocalSource.delete(meal);
    }
}
