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


public class MainTask extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private List<dbReminder> remtasklist;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintask);

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
                                remtasklist.add(p);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }


}
