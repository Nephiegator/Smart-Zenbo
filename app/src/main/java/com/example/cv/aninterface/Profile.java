package com.example.cv.aninterface;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;



public class Profile extends AppCompatActivity{

    private FirebaseFirestore db;
    private FirebaseAuth firebaseAuth;
    private TextView view_fname;
    private TextView view_lname;
    private TextView view_email;
    private TextView view_phone;
    private TextView view_username;
    private TextView view_bdate;
    private TextView edit_profile;
    private String mail, name1, name2, phonenum, username, bdate;
    private List<dbUserInformation> profileList;
    private Button logoubtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        String currentusermail = firebaseAuth.getCurrentUser().getEmail();

        view_fname = findViewById(R.id.view_fname);
        view_lname = findViewById(R.id.view_lname);
        view_email = findViewById(R.id.view_email);
        view_phone = findViewById(R.id.view_phone);
        view_username = findViewById(R.id.textViewUserName);
        view_bdate = findViewById(R.id.view_bdate);
        logoubtn = findViewById(R.id.logout_button);

        edit_profile = findViewById(R.id.edit_profile);
        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, UpdateProfile.class);
                startActivity(intent);
            }
        });

        logoubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("email");
                editor.remove("password");
                editor.commit();
                Intent intent = new Intent(getApplicationContext(), SignIn.class);
                startActivity(intent);
            }
        });

        profileList = new ArrayList<>();

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Intent a3 = new Intent(Profile.this, Home.class);
                        startActivity(a3);
                        menuItem.setIcon(R.drawable.ic_home_black_24dp);
                        finish();
                        break;
                    case R.id.navigation_task:
                        Intent a2 = new Intent(Profile.this, MainTask.class);
                        startActivity(a2);
                        menuItem.setIcon(R.drawable.ic_task);
                        finish();
                        break;
                    case R.id.navigation_plan:
                        menuItem.setIcon(R.drawable.man_user);
                        break;
                }
                return false;
            }
        });

        db = FirebaseFirestore.getInstance();
        db.collection("Profile").whereEqualTo("email", currentusermail).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot d : list) {
                                dbUserInformation p = d.toObject(dbUserInformation.class);
                                p.setId(d.getId());
                                profileList.add(p);
                                mail = p.getemail();
                                name1 = p.getfname();
                                name2 = p.getlname();
                                phonenum = p.getphone();
                                username = p.getUsername();
                                bdate = p.getBdate();
                            }
                            view_fname.setText(name1);
                            view_email.setText(mail);
                            view_lname.setText(name2);
                            view_phone.setText(phonenum);
                            view_username.setText(username);
                            view_bdate.setText(bdate);
                        }
                    }
                });



    }

    @Override
    public void onBackPressed() {

    }



}