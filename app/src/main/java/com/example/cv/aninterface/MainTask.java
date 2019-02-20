package com.example.cv.aninterface;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainTask extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private List<dbReminder> itemList;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    //private DocumentReference xx = db.collection("Reminder").document();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintask);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        itemList = new ArrayList<>();
        adapter = new MainAdapter();
        //adapter.setItemList(createItem());
        recyclerView.setAdapter(adapter);

        //header Navigation Bar
        Toolbar toolbar = findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Home.class));
            }
        });

        //display database
        db.collection("Reminder").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                            for(DocumentSnapshot d : list) {
                                dbReminder p = d.toObject(dbReminder.class);
                                itemList.add(p);
                            }

                            adapter.notifyDataSetChanged();

                        }
                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainplan_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        MenuItem z1 = (MenuItem) findViewById(R.id.action_add);
        Intent Z1 = new Intent(MainTask.this, AddPlan.class);
        startActivity(Z1);

        return false;
    }

    /*private List<BaseItem> createItem() {
        List<BaseItem> itemList = new ArrayList<>();
        itemList.add(new CardViewItem()
                .setText("Hello World"));
        itemList.add(new CardViewItem()
                .setText("Hello RecyclerView"));
        itemList.add(new CardViewItem()
                .setText("Hello Android"));

        return itemList;

    }*/


}
