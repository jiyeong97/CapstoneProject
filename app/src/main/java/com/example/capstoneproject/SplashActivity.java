package com.example.capstoneproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;

import com.example.capstoneproject.databinding.ActivitySplashBinding;
import com.example.capstoneproject.utilities.Constants;
import com.example.capstoneproject.utilities.PreferenceManager;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;


public class SplashActivity extends AppCompatActivity {

    CollectionReference reference;
    int i = 0;
    private final int SPLASH_DISPLAY_LENGTH = 1000;
    CountDownTimer mCountDownTimer;
    ActivitySplashBinding binding;
    PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseApp.initializeApp(SplashActivity.this);
        preferenceManager = new PreferenceManager(this);

        binding.progressBar.setProgress(i);
        new Handler().postDelayed(() -> {
            if (preferenceManager.getBoolean(Constants.KEY_SIGNED_IN)) {
                Intent intent = new Intent(SplashActivity.this, Category.class);
                startActivity(intent);
            } else {
                startActivity(new Intent(SplashActivity.this, MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();

            }

        }, SPLASH_DISPLAY_LENGTH);

    }
}