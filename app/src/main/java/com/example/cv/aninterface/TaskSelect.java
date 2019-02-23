package com.example.cv.aninterface;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class TaskSelect extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private List<dbReminder> remtasklist;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_select);

        recyclerView = findViewById(R.id.recyclerViewTask);
        // linear layout
        //recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // grid layout
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        remtasklist = new ArrayList<>();
        adapter = new TaskAdapter(this, remtasklist);

        recyclerView.setAdapter(adapter);

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
                            adapter.notifyDataSetChanged();
                        }
                    }
                });



        /*BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

        BottomNavigationView bottomNavigationView2 = findViewById(R.id.navigation);
        bottomNavigationView2.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

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
        }); */
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}
