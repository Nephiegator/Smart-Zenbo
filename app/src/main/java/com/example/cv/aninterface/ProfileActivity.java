package com.example.cv.aninterface;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;

    private TextView name_profile;
    private TextView email_profile;

    private DatabaseReference databaseReference;

    @Override
    public void onClick(View v) {

    }
}
