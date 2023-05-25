package com.example.foodflow.model;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodflow.db.ConcreteLocalSource;
import com.example.foodflow.network.NetworkDelegate;
import com.example.foodflow.network.RemoteSource;

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
    public void getMeals(NetworkDelegate networkDelegate) {
        remoteSource.startCall(networkDelegate);
    }
}
