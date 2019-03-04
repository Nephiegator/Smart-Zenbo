package com.example.cv.aninterface;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class MainTask extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;
    private RecyclerView recyclerView;
    private TaskAdapter adapter1, adapter2;
    private List<dbReminder> remtasklist;
    private List<dbReminder> apptasklist;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintask);

        recyclerView = findViewById(R.id.recyclerViewTask);
        // linear layout
        //recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        toolbar =(Toolbar) findViewById(R.id.maintask_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" Tasks");
        toolbar.setTitleTextColor(0xFFFFFFFF);

        // grid layout
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        apptasklist = new ArrayList<>();
        adapter1 = new TaskAdapter(this, apptasklist);

        remtasklist = new ArrayList<>();
        adapter2 = new TaskAdapter(this, remtasklist);

        recyclerView.setAdapter(adapter1);
        recyclerView.setAdapter(adapter2);


        db = FirebaseFirestore.getInstance();
        db.collection("Reminder").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot d : list) {
                                dbReminder p = d.toObject(dbReminder.class);
                                p.setId(d.getId());
                                remtasklist.add(p);
                            }
                            adapter1.notifyDataSetChanged();
                        }
                    }
                });

        db.collection("Appointment").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot d : list) {
                                dbTaskApm q = d.toObject(dbTaskApm.class);
                                q.setId(d.getId());
                                apptasklist.add(q);
                            }
                            adapter2.notifyDataSetChanged();
                        }
                    }
                });



        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Intent a1 = new Intent(MainTask.this, Home.class);
                        startActivity(a1);
                        break;
                    case R.id.navigation_task:

                        break;
                    case R.id.navigation_plan:
                        Intent a3 = new Intent(MainTask.this, MainListActivity.class);
                        startActivity(a3);
                        break;
                }
                return false;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.maintask_menu, menu);
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}