package com.example.capstoneproject.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.capstoneproject.databinding.ActivityForgotPasswordBinding;
import com.google.firebase.auth.FirebaseAuth;


public class ForgotPasswordActivity extends AppCompatActivity {

    ActivityForgotPasswordBinding binding;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        binding.sendEmail.setOnClickListener(view -> {
            String email = binding.EtEmail.getText().toString();
            if (email.isEmpty()) {
                Toast.makeText(ForgotPasswordActivity.this, "Enter your email", Toast.LENGTH_SHORT).show();
            } else {
                ProgressDialog progressDialog = new ProgressDialog(ForgotPasswordActivity.this);
                progressDialog.setMessage("Sending....");
                progressDialog.setCancelable(false);
                progressDialog.show();
                auth.sendPasswordResetEmail(email).addOnSuccessListener(unused -> {
                    progressDialog.dismiss();
                    binding.EtEmail.setText("");
                    Toast.makeText(ForgotPasswordActivity.this, "Reset email has been sent to: " + email, Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    Toast.makeText(ForgotPasswordActivity.this, "Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });


    }
}