package com.example.capstoneproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstoneproject.R;
import com.example.capstoneproject.SignIn;
import com.example.capstoneproject.auth.LoginActivity;
import com.example.capstoneproject.auth.RegisterActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void signup(View view) {
        // build an intent to open another activity page
        Intent intent = new Intent (this, RegisterActivity.class);
        startActivity(intent);
    }
    public void login(View view) {
        // build an intent to open another activity page
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
