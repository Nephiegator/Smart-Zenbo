package com.example.cv.aninterface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AddPlan extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        Spinner categories = (Spinner) findViewById(R.id.ctgspinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(AddPlan.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.categories));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categories.setAdapter(myAdapter);
    }
}
