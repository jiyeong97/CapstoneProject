package com.example.capstoneproject.faq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.capstoneproject.AboutUs;
import com.example.capstoneproject.NavigatorC;
import com.example.capstoneproject.R;

public class HearingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hearing);
    }

    public void goToNavi(View view) {
        Intent intent = new Intent(HearingActivity.this, NavigatorC.class);
        startActivity(intent);
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