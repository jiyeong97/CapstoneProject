package com.example.capstoneproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.capstoneproject.admin.ManageFaqActivity;
import com.example.capstoneproject.faq.ViewFaqActivity;

public class Category extends AppCompatActivity {

    LinearLayout ApplicationLeasing;
    LinearLayout RoommateReplacement;
    LinearLayout rentIssues;
    LinearLayout warningLetters;
    LinearLayout leaseRenewal;
    LinearLayout hearing;
    LinearLayout maintenance;
    LinearLayout rentControl;

    ImageView naviBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        ApplicationLeasing = findViewById(R.id.ApplicationLeasing);
        RoommateReplacement = findViewById(R.id.RoommateReplacement);
        rentIssues = findViewById(R.id.rentIssues);
        warningLetters = findViewById(R.id.warningLetters);
        leaseRenewal = findViewById(R.id.leaseRenewal);
        hearing = findViewById(R.id.hearing);
        maintenance = findViewById(R.id.maintenance);
        rentControl = findViewById(R.id.rentControl);

        naviBtn = findViewById(R.id.naviBtn);

        naviBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Category.this, NavigatorC.class);
                startActivity(intent);
            }
        });

        ApplicationLeasing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToDetailsActivity("ApplicationLeasing", "Application & Leasing");
            }
        });

        RoommateReplacement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToDetailsActivity("RoommateReplacement", "Roommate Replacement");
            }
        });

        rentIssues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToDetailsActivity("RentIssues", "Rent Issues");
            }
        });

        warningLetters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToDetailsActivity("WarningLetters", "Warning Letters");
            }
        });

        leaseRenewal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToDetailsActivity("LeaseRenewal", "Lease Renewal");
            }
        });

        hearing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToDetailsActivity("Hearing", "Hearing");
            }
        });

        maintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToDetailsActivity("Maintenance", "Maintenance");
            }
        });

        rentControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToDetailsActivity("RentControl", "Rent Control");
            }
        });


    }

    private void sendToDetailsActivity(String category, String title) {
        Intent i = new Intent(Category.this, ViewFaqActivity.class);
        i.putExtra("category", category);
        i.putExtra("title", title);
        startActivity(i);
    }

    private void goToUrl(String url){
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
    public void goToTwitter (View view){
        goToUrl("https://twitter.com/WinnipegProper1");
    }

    public void goToFacebook (View view){
        goToUrl("https://www.facebook.com/winnipegpropertymanagement/");
    }

    public void goToYoutube (View view){
        goToUrl("https://www.youtube.com/channel/UCRs3SSmwVFbR2QEjIDqU0Ug");
    }

    public void goToLinkIn (View view){
        goToUrl("https://www.linkedin.com/company/garamark-property-management/");
    }


}