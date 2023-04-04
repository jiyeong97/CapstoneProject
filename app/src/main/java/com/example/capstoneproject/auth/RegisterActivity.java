package com.example.capstoneproject.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.capstoneproject.SplashActivity;
import com.example.capstoneproject.databinding.ActivitySignUpBinding;
import com.example.capstoneproject.model.UserModel;
import com.example.capstoneproject.utilities.Constants;
import com.example.capstoneproject.utilities.PreferenceManager;
import com.example.capstoneproject.utilities.getUserId;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class RegisterActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    PreferenceManager preferenceManager;

    CollectionReference reference;
    FirebaseAuth auth;


    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        FirebaseApp.initializeApp(RegisterActivity.this);
        preferenceManager = new PreferenceManager(this);
        auth = FirebaseAuth.getInstance();

        binding.registerBtn.setOnClickListener(v -> {
            String fullName = binding.EtName.getText().toString();
            String email = binding.EtEmail.getText().toString();
            String phone = binding.EtMobile.getText().toString();
            String properties = binding.EtProperty.getText().toString();
            String password = binding.EtPassword.getText().toString();
            String confirmPassword = binding.EtConfirmPassword.getText().toString();

            if (!binding.EtPassword.getText().toString().equals(binding.EtConfirmPassword.getText().toString())) {

                Toast.makeText(this, "Confirm password is not correct", Toast.LENGTH_SHORT).show();

            } else {

                if (fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || properties.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {
                    ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
                    progressDialog.setMessage("Creating account....");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            createAccount(fullName, email, phone, properties, progressDialog);
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Failed: " + task.getException()
                                    .getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


                }

            }

        });


        binding.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void createAccount(String username, String email, String phone, String properties, ProgressDialog progressDialog) {

        reference = FirebaseFirestore.getInstance().collection(Constants.KEY_USERS);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UserModel model = new UserModel();
        model.setUsername(username);
        model.setEmail(email);
        model.setPhone(phone);
        model.setProperties(properties);
        model.setBlocked(false);
        model.setEmployee(false);
        assert user != null;
        model.setId(user.getUid());
        model.setImage("");
        model.setTimestamp(System.currentTimeMillis());


        reference.document(getUserId.id).set(model)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        preferenceManager.putBoolean(Constants.KEY_SIGNED_IN, false);
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        preferenceManager.putString(Constants.USERNAME, username);
                        preferenceManager.putString(Constants.EMAIL, email);
                        preferenceManager.putString(Constants.MOBILE, phone);
                        preferenceManager.putString(Constants.PROPERTIES, properties);
                        preferenceManager.putBoolean("blocked", false);
                        preferenceManager.putBoolean("employee", false);
                        preferenceManager.putBoolean(Constants.KEY_SIGNED_IN, false);
                        progressDialog.dismiss();
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, task.getException()
                                .getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }
                });

    }
}