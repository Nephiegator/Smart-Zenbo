package com.example.cv.aninterface;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class MainTask extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference dd = db.collection("Reminder");

    private TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintask);

        setUpRecyclerView();


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        NavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Intent a1 = new Intent(Home.this, Home.class);
                        startActivity(a1);
                        break;
                    case R.id.navigation_task:
                        Intent a2 = new Intent(Home.this, MainTask.class);
                        startActivity(a2);
                        break;
                    case R.id.navigation_plan:
                        Intent a3 = new Intent(Home.this, MainListActivity.class);
                        startActivity(a3);
                        break;
                }
                return false;
            }
        });
    }

    private void setUpRecyclerView() {
        Query query = dd.orderBy("Date", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<dbReminder> options = new FirestoreRecyclerOptions.Builder<dbReminder>()
                .setQuery(query, dbReminder.class)
                .build();

        adapter =new TaskAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewTask);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}
