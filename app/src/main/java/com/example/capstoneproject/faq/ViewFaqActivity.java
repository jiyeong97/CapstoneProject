package com.example.capstoneproject.faq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.capstoneproject.NavigatorC;
import com.example.capstoneproject.R;
import com.example.capstoneproject.adapter.DataAdapter;
import com.example.capstoneproject.model.DataModel;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class ViewFaqActivity extends AppCompatActivity {

    CollectionReference reference;
    ArrayList<DataModel> list = new ArrayList<>();
    DataAdapter adapter;

    RecyclerView recyclerView;
    ProgressBar progressBar;
    LinearLayout noDataLyt;
    String category;
    String title;
    TextView Title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_faq);
        category = getIntent().getStringExtra("category");
        title = getIntent().getStringExtra("title");
        reference = FirebaseFirestore.getInstance().collection("Faqs");

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        noDataLyt = findViewById(R.id.noDataLyt);
        Title = findViewById(R.id.Title);

        Title.setText(title);

        getFaqs(category);
    }

    public void goToNavi(View view) {
        Intent intent = new Intent(ViewFaqActivity.this, NavigatorC.class);
        startActivity(intent);
    }
    private void getFaqs(String category) {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);

        Query query = reference.orderBy("id", Query.Direction.DESCENDING).limit(10);
        query.get().addOnSuccessListener(queryDocumentSnapshots -> {
            if (queryDocumentSnapshots.isEmpty()) {
                return;
            }
            list.clear();
            progressBar.setVisibility(View.GONE);
            for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                DataModel model = snapshot.toObject(DataModel.class);

                if (model.getCategory().equals(category)) {
                    list.add(model);

                }

            }

            LinearLayoutManager manager = new LinearLayoutManager(ViewFaqActivity.this);
            recyclerView.setLayoutManager(manager);

            adapter = new DataAdapter(ViewFaqActivity.this, list);
            recyclerView.setAdapter(adapter);

            if (list.size() > 0) {
                noDataLyt.setVisibility(View.GONE);
            } else {
                noDataLyt.setVisibility(View.VISIBLE);

            }


        }).addOnFailureListener(e -> Toast.makeText(ViewFaqActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());


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