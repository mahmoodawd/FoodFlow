package com.example.foodflow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {
    Button logOutBtn;
    TextView userNameTV;
    TextView userEmailTV;
    ImageView userAvatar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        logOutBtn = findViewById(R.id.logOutBtn);
        userNameTV = findViewById(R.id.userNameTV);
        userAvatar = findViewById(R.id.userAvatar);
        userEmailTV = findViewById(R.id.userEmailTV);
        mAuth = FirebaseAuth.getInstance();
        userNameTV.setText(mAuth.getCurrentUser().getDisplayName());
        userEmailTV.setText(mAuth.getCurrentUser().getEmail());

        Glide.with(this).load(mAuth.getCurrentUser().getPhotoUrl()).into(userAvatar);

        logOutBtn.setOnClickListener(view -> logOut());
    }

    private void logOut() {
        mAuth.signOut();
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}