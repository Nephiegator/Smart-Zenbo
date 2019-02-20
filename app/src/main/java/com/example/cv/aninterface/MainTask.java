package com.example.cv.aninterface;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
