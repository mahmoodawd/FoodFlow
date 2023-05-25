package com.example.foodflow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.foodflow.auth.view.RegisterActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class WelcomeActivity extends AppCompatActivity {
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button joinBtn;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        joinBtn = findViewById(R.id.joinButton);

//        mViewPager = (ViewPager) findViewById(R.id.container);
//        setupViewPager(mViewPager);
//
//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(mViewPager);
//
//        joinBtn = findViewById(R.id.joinButton);
//        joinBtn.setOnClickListener(v -> {
//            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
//                    WelcomeActivity.this);
//            View bottomSheetView = LayoutInflater.from(getApplicationContext())
//                    . inflate(R.layout.modal_bottom_sheet,
//                            (ConstraintLayout) findViewById(R.id.modalBottomSheetContainer));
//            bottomSheetDialog.setContentView(bottomSheetView);
//            bottomSheetDialog.show();
//
//        });

        joinBtn.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}