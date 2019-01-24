package com.example.cv.aninterface;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class AddTaskMed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtaskmed);

        //Spinner (Drop Down List
        Spinner mySpinner1 = (Spinner) findViewById(R.id.inLocation);
        Spinner mySpinner2 = (Spinner) findViewById(R.id.ObjPerson);

        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(AddTaskMed.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.inLocation));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner1.setAdapter(myAdapter1);

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(AddTaskMed.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ObjPerson));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner2.setAdapter(myAdapter2);

        //header Navigation Bar
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Radio button
        RadioGroup radio_g1 = (RadioGroup) findViewById(R.id.radio_g1);
        radio_g1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });

        RadioGroup radio_g2 = (RadioGroup) findViewById(R.id.radio_g2);
        radio_g2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });
    }

}
