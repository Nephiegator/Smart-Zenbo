package com.example.cv.aninterface;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toolbar;

public class AddTask extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        Spinner mySpinner1 = (Spinner) findViewById(R.id.inLocation);
        Spinner mySpinner2 = (Spinner) findViewById(R.id.ObjPerson);

        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(AddTask.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.inLocation));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner1.setAdapter(myAdapter1);

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(AddTask.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ObjPerson));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner2.setAdapter(myAdapter2);

        //header Navigation Bar
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
}
