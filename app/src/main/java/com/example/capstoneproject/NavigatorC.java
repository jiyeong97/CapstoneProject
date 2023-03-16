package com.example.capstoneproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class NavigatorC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator_c);
    }

    //This function links to About Us activity.
    public void goToAboutUsPage(View view){
        Intent intent = new Intent(this, AboutUs.class);
        startActivity(intent);
    }

    //This function links to Ask Question activity.
    public void goToAskQuestionPage(View view){
        Intent intent = new Intent(this, AskQuestion.class);
        startActivity(intent);
    }

    //This function links to FAQ categories activity.
    public void goToFAQPage(View view){
        Intent intent = new Intent(this, Category.class);
        startActivity(intent);
    }

    //This function close navigator activity.
    public void goBackToPreviousPage(View view){
        finish();
    }

    //This function show the exit dialog and close all the activities.
    public void exitDialog(View view){
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.logout)
                .setTitle("Exit application")
                .setMessage("Do you want to log-out and exit this application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}