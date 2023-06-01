package com.example.foodflow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;


public class SplashScreenActivity extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;
    ImageView nameSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        lottieAnimationView = findViewById(R.id.lottiAnimation);
        nameSlogan = findViewById(R.id.nameImageView);

        nameSlogan.setTranslationX(2000);
        lottieAnimationView.setTranslationY(-400);

        nameSlogan.animate().translationX(0).setDuration(2000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(-35).setDuration(2000).setStartDelay(5000);
        new Handler().postDelayed(() -> {
            finish();
            Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
            startActivity(intent);
        }, 10000);
    }
}