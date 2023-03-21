package com.example.capstoneproject.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.capstoneproject.Category;
import com.example.capstoneproject.R;
import com.example.capstoneproject.faq.ApplicationLeasingActivity;
import com.example.capstoneproject.faq.HearingActivity;
import com.example.capstoneproject.faq.LeaseRenewalActivity;
import com.example.capstoneproject.faq.MaintenanceActivity;
import com.example.capstoneproject.faq.RentControlActivity;
import com.example.capstoneproject.faq.RentIssuesActivity;
import com.example.capstoneproject.faq.RoommateReplacementActivity;
import com.example.capstoneproject.faq.WarningLettersActivity;

public class ManageFaqActivity extends AppCompatActivity {

    LinearLayout ApplicationLeasing;
    LinearLayout RoommateReplacement;
    LinearLayout rentIssues;
    LinearLayout warningLetters;
    LinearLayout leaseRenewal;
    LinearLayout hearing;
    LinearLayout maintenance;
    LinearLayout rentControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_faq);

        ApplicationLeasing = findViewById(R.id.ApplicationLeasing);
        RoommateReplacement = findViewById(R.id.RoommateReplacement);
        rentIssues = findViewById(R.id.rentIssues);
        warningLetters = findViewById(R.id.warningLetters);
        leaseRenewal = findViewById(R.id.leaseRenewal);
        hearing = findViewById(R.id.hearing);
        maintenance = findViewById(R.id.maintenance);
        rentControl = findViewById(R.id.rentControl);


        ApplicationLeasing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageFaqActivity.this, AddFaqActivity.class);
                intent.putExtra("category", "ApplicationLeasing");
                startActivity(intent);
            }
        });

        RoommateReplacement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageFaqActivity.this, AddFaqActivity.class);
                intent.putExtra("category", "RoommateReplacement");
                startActivity(intent);
            }
        });

        rentIssues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageFaqActivity.this, AddFaqActivity.class);
                intent.putExtra("category", "RentIssues");
                startActivity(intent);
            }
        });

        warningLetters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageFaqActivity.this, AddFaqActivity.class);
                intent.putExtra("category", "WarningLetters");
                startActivity(intent);
            }
        });

        leaseRenewal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageFaqActivity.this, AddFaqActivity.class);
                intent.putExtra("category", "LeaseRenewal");
                startActivity(intent);
            }
        });

        hearing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageFaqActivity.this, AddFaqActivity.class);
                intent.putExtra("category", "Hearing");
                startActivity(intent);
            }
        });

        maintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageFaqActivity.this, AddFaqActivity.class);
                intent.putExtra("category", "Maintenance");
                startActivity(intent);
            }
        });

        rentControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageFaqActivity.this, AddFaqActivity.class);
                intent.putExtra("category", "RentControl");
                startActivity(intent);
            }
        });


    }

    public void goBackToPreviousPage(View view){
        finish();
    }
}