package com.example.cv.aninterface;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.view.Menu;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    private FirebaseFirestore db;
    private FirebaseAuth firebaseAuth;
    private TextView view_fname;
    private TextView view_user;
    private TextView view_email;
    private TextView view_gender;
    private TextView view_username;
    private TextView view_bdate;
    private TextView view_relation;
    private String mail, name, username, relation;
    private Integer gen, admin;
    private Long bdate;
    private List<dbZenboUser> profileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        toolbar = findViewById(R.id.profile_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("  Profile");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);


        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        String currentusermail = firebaseAuth.getCurrentUser().getEmail();
        String test[] = currentusermail.toString().split("@");
        String usern = test[0];
        System.out.println(usern);

        view_fname = findViewById(R.id.view_name);
        view_user = findViewById(R.id.view_user);
        view_email = findViewById(R.id.view_email);
        view_gender = findViewById(R.id.view_gender);
        view_username = findViewById(R.id.textViewUserName);
        view_bdate = findViewById(R.id.view_bdate);
        view_relation = findViewById(R.id.view_relation);


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
        db.collection("ZenboUser").whereEqualTo("username", usern).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot d : list) {
                                dbZenboUser z = d.toObject(dbZenboUser.class);
                                z.setId(d.getId());
                                profileList.add(z);

                                username = z.getUsername();
                                mail = z.getUserId();
                                name = z.getNickName();
                                admin = z.getAdmin();
                                bdate = z.getBirthday();
                                relation = z.getRelation();
                                gen = z.getGender();
                            }
                            view_username.setText(username);
                            view_fname.setText(name);
                            view_email.setText(mail);
                            view_bdate.setText(getDate(bdate));
                            view_user.setText(getAdmin(admin));
                            view_relation.setText(relation);
                            view_gender.setText(getGender(gen));
                        }
                    }
                });



    }

//    @Override
//    public void onBackPressed() {
//    }
    private String getAdmin(int ad) {
        String state = null;
        if (ad == 1) {
            state = "Regular user";
        }else if (ad == 2) {
            state = "Advance user";
        }else if (ad == 3) {
            state = "Admin user";
        }
        return state;
    }

    private String getGender(int gen) {
        String state = null;
        if (gen == 1) {
            state = "Male";
        }else if (gen == 2) {
            state = "Female";
        }
        return state;
    }

    private String getDate(Long timestamp){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date netDate = (new Date(timestamp));
            return sdf.format(netDate);
        }
        catch (Exception e){
            return "xx";
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                SharedPreferences sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("email");
                editor.remove("password");
                editor.commit();
                Intent intent = new Intent(getApplicationContext(), SignIn.class);
                startActivity(intent);
                break;
        }
        return false;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}