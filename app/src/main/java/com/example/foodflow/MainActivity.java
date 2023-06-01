package com.example.foodflow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    private TextView userName;
    private ImageView userAvatar;
    private FirebaseUser user;

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

            if (destination.getId() == R.id.homeFragment && user != null) {
                userName.setVisibility(View.VISIBLE);
                userAvatar.setVisibility(View.VISIBLE);
            } else {
                userName.setVisibility(View.GONE);
                userAvatar.setVisibility(View.GONE);
            }
        });
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (user == null) {
            Intent intent = new Intent(this, WelcomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        } else {
            finishAffinity();
            System.exit(0);
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
}