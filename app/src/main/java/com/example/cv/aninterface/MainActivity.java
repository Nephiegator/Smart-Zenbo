package com.example.cv.aninterface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.content.Intent;

import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {
    ImageButton a1, a2, a3, a4, a5, s6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a1 = (ImageButton) findViewById(R.id.addtaskbtn);
        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent A1 = new Intent (MainActivity.this,AddTask.class);
                startActivity(A1);
            }
        });

        a2 = (ImageButton) findViewById(R.id.addplanbtn);
        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent A2 = new Intent (MainActivity.this, AddPlan.class);
                startActivity(A2);
            }
        });
    }
}
