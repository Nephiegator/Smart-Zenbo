package com.example.cv.aninterface;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toolbar;

public class AddTask extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText task_title;
    private TextInputEditText task_des;
    private Spinner mySpinner1;
    private Spinner mySpinner2;

    private Firebase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        Spinner mySpinner1 =  findViewById(R.id.inLocation);
        Spinner mySpinner2 =  findViewById(R.id.ObjPerson);

        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(AddTask.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.inLocation));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner1.setAdapter(myAdapter1);

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(AddTask.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ObjPerson));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner2.setAdapter(myAdapter2);

        //header Navigation Bar
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        task_title = findViewById(R.id.task_title);
        task_des = findViewById(R.id.task_des);

        findViewById(R.id.create_btn).setOnClickListener(this);
    }

    private boolean validateInputs(String title, String inLocation, String ObjPerson) {

        Spinner mySpinner1 =  findViewById(R.id.inLocation);
        Spinner mySpinner2 =  findViewById(R.id.ObjPerson);

        if (title.isEmpty()) {
            task_title.setError("Title required");
            task_title.requestFocus();
            return true;
        }
        if (inLocation.isEmpty()) {
            mySpinner1.setFocusable(true);
            mySpinner1.setFocusableInTouchMode(true);
        }
        if (ObjPerson.isEmpty()) {
            mySpinner2.setFocusable(true);
            mySpinner2.setFocusableInTouchMode(true);
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        String title = task_title.getText().toString().trim();

        if(validateInputs(title)) {

        }
    }
}
