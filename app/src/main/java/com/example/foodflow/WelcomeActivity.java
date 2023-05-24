package com.example.foodflow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button joinBtn;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        joinBtn = findViewById(R.id.signUpButton);
        joinBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);

        });
    }
}