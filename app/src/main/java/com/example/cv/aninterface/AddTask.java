package com.example.cv.aninterface;

import android.content.Intent;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddTask extends AppCompatActivity implements AdapterView.OnItemSelectedListener { // implements View.OnClickListener {

    private static final String TAG = "AddTask";

    private static final String KEY_TITLE = "Title";
    private static final String KEY_DESCRIPTION = "Description";
    private static final String KEY_LOC = "Indoor Location";
    private static final String KEY_PERSON = "Person";
    private TextInputEditText task_title;
    private TextInputEditText task_desc;
    private  String yy,xx;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

   // @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        task_title = findViewById(R.id.task_title);
        task_desc = findViewById(R.id.task_des);
        Spinner mySpinner1 =  (Spinner) findViewById(R.id.inLocation);
        Spinner mySpinner2 =  (Spinner) findViewById(R.id.ObjPerson);

        //Spinner method to read the selected value
        ArrayAdapter<State1> spinnerArrayAdapter1 = new ArrayAdapter<State1> (this,
                android.R.layout.simple_spinner_item, new State1[] {
                        new State1("None"),
                        new State1 ("Dad Room"),
                        new State1 ("Kitchen"),
        });
        mySpinner1.setAdapter(spinnerArrayAdapter1);
        mySpinner1.setOnItemSelectedListener(this);

        ArrayAdapter<State2> spinnerArrayAdapter2 = new ArrayAdapter<State2> (this,
                android.R.layout.simple_spinner_item, new State2[] {
                new State2("None"),
                new State2 ("Lisa"),
                new State2 ("Jisoo"),
                new State2 ("Rose")
        });
        mySpinner2.setAdapter(spinnerArrayAdapter2);
        mySpinner2.setOnItemSelectedListener(this);


        //header Navigation Bar
        Toolbar toolbar = findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainplan_menu, menu);
        return true;
    }



    public class State1 {
        public String loc = "";

        public State1(String _loc){
        loc = _loc;
        }

        public String toString() {
            return loc;
        }
    }

    public class State2 {
        public String name = "";

        public State2(String _name){
            name = _name;
        }

        public String toString() {
            return name;
        }
    }



    public void onItemSelected (AdapterView<?> parent, View view, int position, long id)
    {
        switch (parent.getId()) {
            case R.id.inLocation:
                xx = parent.getItemAtPosition(position).toString();
                break;
            case R.id.ObjPerson:
                yy = parent.getItemAtPosition(position).toString();
                break;
        }
    }



    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

    public void saveTask (View v) {
        String title = task_title.getText().toString();
        String desc = task_desc.getText().toString();

        /*Map<String, Object> reminder = new HashMap<>();
        reminder.put(KEY_TITLE, title);
        reminder.put(KEY_DESCRIPTION, desc);
        reminder.put(KEY_LOC, xx);
        reminder.put(KEY_PERSON, yy);
        reminder.put("Date", new Timestamp(new Date()));


        db.collection("Reminder")
                .add(reminder)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        //Toast.makeText(AddTask.this, "Saved", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
        finish();*/
        if (!validateInputs(title, desc, xx, yy)) {

            CollectionReference dbReminder = db.collection("Reminder");

            Reminder reminder = new Reminder(
                    title,
                    desc,
                    xx,
                    yy
            );


    }

}


