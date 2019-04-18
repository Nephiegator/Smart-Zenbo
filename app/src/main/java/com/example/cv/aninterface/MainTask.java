package com.example.cv.aninterface;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AlertDialog;
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

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintask);

        recyclerView = findViewById(R.id.recyclerViewTask);
        // linear layout
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        toolbar =(Toolbar) findViewById(R.id.maintask_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" Tasks");
        toolbar.setTitleTextColor(0xFFFFFFFF);

        // grid layout
//        int numberOfColumns = 2;
//        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        remtasklist = new ArrayList<>();
        adapter1 = new TaskAdapter(this, remtasklist);

        recyclerView.setAdapter(adapter1);
        //recyclerView.setAdapter(adapter2);


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

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        Intent a1 = new Intent(MainTask.this, Home.class);
                        menuItem.setIcon(R.drawable.ic_home_black_24dp);
                        startActivity(a1);
                        break;
                    case R.id.navigation_task:
                        menuItem.setIcon(R.drawable.ic_task);
                        break;
                    case R.id.navigation_plan:
                        Intent a3 = new Intent(MainTask.this, Profile.class);
                        startActivity(a3);
                        menuItem.setIcon(R.drawable.man_user);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String msg = " ";
        switch (item.getItemId()) {
            case R.id.addplan:
                //msg = "Add";
                //break;
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Make New Plan");
                builder.setMessage("Are you sure to make new plan list");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent a = new Intent(MainTask.this, TaskSelect.class);
                        startActivity(a);
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog ad = builder.create();
                ad.show();
                break;
            case R.id.refresh:
                super.onRestart();
                Intent i = new Intent (MainTask.this, MainTask.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                break;
        }
        return false;
    }
}