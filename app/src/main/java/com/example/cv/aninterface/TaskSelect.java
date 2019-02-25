package com.example.cv.aninterface;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class TaskSelect extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    Toolbar toolbar;
    private RecyclerView recyclerView2;
    private TaskAdapterSelected adapter;
    private List<dbReminder> remtasklist;
    private CheckBox checkBox;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_select);

        toolbar =(Toolbar) findViewById(R.id.task_select_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" Select Tasks");
        toolbar.setTitleTextColor(0xFFFFFFFF);

        recyclerView2 = findViewById(R.id.recyclerViewTaskSelect);
        // linear layout
        //recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // grid layout
        int numberOfColumns = 2;
        recyclerView2.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        remtasklist = new ArrayList<>();
        adapter = new TaskAdapterSelected (this, remtasklist);

        recyclerView2.setAdapter(adapter);
        adapter = new TaskAdapterSelected(this, remtasklist);

        recyclerView2.setAdapter(adapter);

        //Database
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
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.selecttask_menu, menu);
        return true;
    }

    public  void Checked() {
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    CollectionReference dbPlan = db.collection("Plans");
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.select_task:
                //Checked();
                break;
        }
        return false;
    }
}
