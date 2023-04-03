package com.example.capstoneproject.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.capstoneproject.AboutUs;
import com.example.capstoneproject.NavigatorC;
import com.example.capstoneproject.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Random;

import kotlinx.coroutines.internal.LockFreeLinkedListNode;

public class AddFaqActivity extends AppCompatActivity {

    CollectionReference reference;
    ImageView back;
    Button addFaqButton;
    EditText title, description;
    String category;
    TextView toolbarTitle;
    private String[] categories = {"Application & Leasing", "Roommate Replacement", "Rent Issues", "Warning Letters", "Lease Renewal", "Hearing", "Maintenance", "Rent Control"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faq);
        category = getIntent().getStringExtra("category");
        reference = FirebaseFirestore.getInstance().collection("Faqs");

        back = (ImageView) findViewById(R.id.back);
        addFaqButton = (Button) findViewById(R.id.addFaqButton);
        toolbarTitle = (TextView) findViewById(R.id.toolbarTitle);

        title = (EditText) findViewById(R.id.title);
        description = (EditText) findViewById(R.id.description);

        toolbarTitle.setText(category + " (Add Faq)");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        addFaqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String FaqTitle = title.getText().toString();
                String FaqDescription = description.getText().toString();
                if (FaqTitle.isEmpty() && FaqDescription.isEmpty()) {
                    Toast.makeText(AddFaqActivity.this, "All fields mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    uploadDescription(category, FaqTitle, FaqDescription);
                }
            }
        });
    }



    private void uploadDescription(String category, String title, String description) {
        ProgressDialog progressDialog = new ProgressDialog(AddFaqActivity.this);
        progressDialog.setMessage("uploading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String id = String.valueOf(new Random().nextInt(100000));

        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("category", category);
        map.put("title", title);
        map.put("description", description);

        reference.document(id).set(map).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                progressDialog.dismiss();
                Toast.makeText(AddFaqActivity.this, "Uploaded!", Toast.LENGTH_SHORT).show();
                onBackPressed();
            } else {
                progressDialog.dismiss();
                Toast.makeText(AddFaqActivity.this, "Error: " + task.getException()
                        .getMessage(), Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });



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

    public void goToNavi(View view) {
        Intent intent = new Intent(AddFaqActivity.this, NavigatorC.class);
        startActivity(intent);
    }
}