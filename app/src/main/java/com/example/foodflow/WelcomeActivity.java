package com.example.foodflow;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodflow.auth.view.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button joinBtn;
        Button guestBtn;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        joinBtn = findViewById(R.id.joinButton);
        guestBtn = findViewById(R.id.guestButton);

        joinBtn.setOnClickListener(v -> navigate(RegisterActivity.class));
        guestBtn.setOnClickListener(v -> navigate(MainActivity.class));
    }

    private void navigate(Class<?> destinationActivity) {
        Intent intent = new Intent(WelcomeActivity.this, destinationActivity);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }
}