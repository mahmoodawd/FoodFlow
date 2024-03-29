package com.example.foodflow;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.bumptech.glide.Glide;
import com.example.foodflow.db.AppDB;
import com.example.foodflow.db.ConcreteLocalSource;
import com.example.foodflow.network.API_Client;
import com.example.foodflow.repositories.Repository;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    private TextView userName;
    private ImageView userAvatar;
    private FirebaseUser user;
    private boolean isUserAtHome;
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        userName = findViewById(R.id.userNameTV);
        userAvatar = findViewById(R.id.userAvatar);

        displayUserInfo();
        userName.setOnClickListener(v -> navToProfile());

        List<Integer> bottomNavigationChildren = new ArrayList<>(Arrays.asList(R.id.homeFragment, R.id.searchFragment, R.id.favoritesFragment, R.id.plannerFragment));
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (bottomNavigationChildren.contains(destination.getId())) {
                bottomNavigationView.setVisibility(View.VISIBLE);

            } else {
                bottomNavigationView.setVisibility(View.GONE);
            }


            //Only show user info for logged in users
            if (destination.getId() == R.id.homeFragment && user != null) {
                userName.setVisibility(View.VISIBLE);
                userAvatar.setVisibility(View.VISIBLE);
                isUserAtHome = true;
            } else {
                userName.setVisibility(View.GONE);
                userAvatar.setVisibility(View.GONE);
                isUserAtHome = false;
            }
        });
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    @Override
    public void onBackPressed() {
        if (isUserAtHome) {
            if (user == null) {
                Intent intent = new Intent(this, WelcomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            } else {
                finishAffinity();
                System.exit(0);
            }
        } else {
            super.onBackPressed();
        }
    }


    private void displayUserInfo() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            userName.setText(user.getDisplayName());
            Glide.with(this).load(user.getPhotoUrl()).into(userAvatar);
        } else {
            userName.setVisibility(View.GONE);
            userAvatar.setVisibility(View.GONE);
        }
    }


    private void navToProfile() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        new Thread(this::backup).start();
        super.onStop();
        Log.i(TAG, "onStop");
    }

    private void backup() {
//        AppDB appDB = AppDB.getInstance(this);
//        String appDBName = appDB.getOpenHelper().getDatabaseName();
//        appDB.close();
//
//        String backupFileName = "foodFlow_backup.db";
//        File backupFile = new File(this.getExternalFilesDir(null), backupFileName);
//        SQLiteDatabase backupDb = SQLiteDatabase.openOrCreateDatabase(backupFile, null);
//
//        backupDb.execSQL("ATTACH DATABASE '" + appDBName + "' AS original");
//
//        backupDb.execSQL("CREATE TABLE backup_data AS SELECT * FROM original.main");
//
//        backupDb.execSQL("DETACH DATABASE original");
//
//        appDB.close();
//        backupDb.close();



    }


}