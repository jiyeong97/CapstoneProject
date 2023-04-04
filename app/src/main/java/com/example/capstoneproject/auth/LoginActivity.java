package com.example.capstoneproject.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.capstoneproject.Category;
import com.example.capstoneproject.databinding.ActivityLoginBinding;
import com.example.capstoneproject.model.UserModel;
import com.example.capstoneproject.utilities.Constants;
import com.example.capstoneproject.utilities.PreferenceManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class LoginActivity extends AppCompatActivity {


    ActivityLoginBinding binding;

    FirebaseAuth auth;
    PreferenceManager preferenceManager;
    CollectionReference reference;

    public static boolean Employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferenceManager = new PreferenceManager(this);
        auth = FirebaseAuth.getInstance();
        reference = FirebaseFirestore.getInstance().collection(Constants.KEY_USERS);


        binding.loginButton.setOnClickListener(view -> {
            String email = binding.EtEmail.getText().toString();
            String password = binding.EtPassword.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
            } else {
                ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("Logging....");
                progressDialog.setCancelable(false);
                progressDialog.show();

                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                        reference.document(user.getUid()).get().addOnSuccessListener(documentSnapshot -> {
                            if (documentSnapshot.exists()) {
                                UserModel model = documentSnapshot.toObject(UserModel.class);

                                preferenceManager.putBoolean(Constants.KEY_SIGNED_IN, true);
                                Intent intent = new Intent(LoginActivity.this, Category.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                preferenceManager.putString(Constants.USERNAME, model.getUsername());
                                preferenceManager.putString(Constants.EMAIL, email);
                                preferenceManager.putString(Constants.MOBILE, model.getPhone());
                                preferenceManager.putBoolean("Employee", model.getEmployee());
                                preferenceManager.putBoolean("blocked", false);
                                Employee = model.getEmployee();
                                progressDialog.dismiss();
                                finish();
                            }
                        }).addOnFailureListener(e -> {
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });


                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Failed: " + task.getException()
                                .getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

        binding.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        binding.tvForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });


    }
}