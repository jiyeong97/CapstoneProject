package com.example.capstoneproject.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.capstoneproject.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Random;

public class AddFaqActivity extends AppCompatActivity {

    CollectionReference reference;
    ImageView back;
    Button addFaqButton;
    EditText title, description;
    Spinner categorySpinner;
    TextView toolbarTitle;
    private String[] categories = {"Application & Leasing", "Roommate Replacement", "Rent Issues", "Warning Letters", "Lease Renewal", "Hearing", "Maintenance", "Rent Control"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faq);
        reference = FirebaseFirestore.getInstance().collection("Faqs");

        back = (ImageView) findViewById(R.id.back);
        addFaqButton = (Button) findViewById(R.id.addFaqButton);
        toolbarTitle = (TextView) findViewById(R.id.toolbarTitle);

        title = (EditText) findViewById(R.id.title);
        description = (EditText) findViewById(R.id.description);
        categorySpinner = findViewById(R.id.category_spinner);

        toolbarTitle.setText("Add FAQ");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

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
                    uploadDescription(FaqTitle, FaqDescription);
                }
            }
        });
    }



    private void uploadDescription(String title, String description) {
        ProgressDialog progressDialog = new ProgressDialog(AddFaqActivity.this);
        progressDialog.setMessage("uploading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String id = String.valueOf(new Random().nextInt(100000));

        String category = categories[categorySpinner.getSelectedItemPosition()];

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
}
