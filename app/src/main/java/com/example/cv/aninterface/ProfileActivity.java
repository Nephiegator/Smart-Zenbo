package com.example.cv.aninterface;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;

    private TextInputEditText fname_profile;
    private TextInputEditText lname_profile;
    private TextInputEditText phone_profile;
    private EditText dd, mm, yyyy;
    private TextInputEditText username_profile;
    private Button save_profile;
    private FirebaseFirestore db;

    private DatabaseReference databaseReference;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        db = FirebaseFirestore.getInstance();

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(ProfileActivity.this, Profile.class));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();

        fname_profile = findViewById(R.id.fname_profile);
        lname_profile = findViewById(R.id.lname_profile);
        phone_profile = findViewById(R.id.phone_profile);
        save_profile = findViewById(R.id.save_profile);
        username_profile = findViewById(R.id.username_profile);
        dd = findViewById(R.id.day_profile);
        mm = findViewById(R.id.month_profile);
        yyyy = findViewById(R.id.year_profile);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        save_profile.setOnClickListener(this);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Intent a3 = new Intent(ProfileActivity.this, Home.class);
                        startActivity(a3);
                        finish();
                        break;
                    case R.id.navigation_task:
                        Intent a2 = new Intent(ProfileActivity.this, MainTask.class);
                        startActivity(a2);
                        finish();
                        break;
                    case R.id.navigation_plan:

                        break;
                }
                return false;
            }
        });
    }

    private boolean validateInputs(String fname, String lname, String email, String phone, String username, String bdate) {
        if (fname.isEmpty()) {
            fname_profile.setError("First name Required");
            fname_profile.requestFocus();
            return true;
        }

        if (lname.isEmpty()) {
            lname_profile.setError("Last name Required");
            lname_profile.requestFocus();
            return true;
        }


        if (phone.isEmpty()) {
            phone_profile.setError("Phone number Required");
            phone_profile.requestFocus();
            return true;
        }

        if (username.isEmpty()) {
            username_profile.setError("Username Required");
            username_profile.requestFocus();
        }


        return false;
    }

    private void saveUserInformation() {
        String fname = fname_profile.getText().toString().trim();
        String lname = lname_profile.getText().toString().trim();
        String email = firebaseAuth.getCurrentUser().getEmail();
        String phone = phone_profile.getText().toString().trim();
        String username = username_profile.getText().toString().trim();
        String bdate = dd.getText().toString().trim()+mm.getText().toString().trim()+yyyy.getText().toString().trim();

        if (!validateInputs(fname, lname, email, phone, username, bdate)) {

            CollectionReference dbUserInformation = db.collection("Profile");

            dbUserInformation profile = new dbUserInformation(
                    fname,
                    lname,
                    email,
                    phone,
                    username,
                    bdate

            );


            dbUserInformation.add(profile)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(ProfileActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save_profile:
                saveUserInformation();
                startActivity(new Intent(this, Home.class));
                break;

        }
    }
}