package com.example.capstoneproject.utilities;

import com.google.firebase.auth.FirebaseAuth;

public interface getUserId {
    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
}
