package com.example.cv.aninterface;

import android.app.TimePickerDialog;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddTask extends AppCompatActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {

    private TextInputEditText txt_title;
    private TextInputEditText txt_description;
    private TextView timeTextView;
    //private TextView txt_hour;
    //private TextView txt_minute;

    private String TAG = "AddTask";
    private FirebaseFirestore db;
    private String yy, xx;
    private String time;
    private List<dbReminder> reminderList;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

       // timePicker = findViewById(R.id.alarmTimePicker)

        db = FirebaseFirestore.getInstance();

        toolbar = findViewById(R.id.task_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" New Tasks");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        txt_title = findViewById(R.id.task_title);
        txt_description = findViewById(R.id.task_des);
        timeTextView = findViewById(R.id.time_textview);

        Button Create = (Button) findViewById(R.id.create_btn);
        Create.setOnClickListener(this);

        findViewById(R.id.time_set_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });


        Spinner splocation = (Spinner) findViewById(R.id.inLocation);
        Spinner spperson = (Spinner) findViewById(R.id.ObjPerson);

        //Spinner method to read the selected value
        ArrayAdapter<State1> spinnerArrayAdapter1 = new ArrayAdapter<State1> (this,
                android.R.layout.simple_spinner_item, new State1[] {
                new State1("None"),
                new State1("Bedroom"),
                new State1("Kitchen"),
        });
        splocation.setAdapter(spinnerArrayAdapter1);
        splocation.setOnItemSelectedListener(new MyOnItemSelectedListener());

        ArrayAdapter<State2> spinnerArrayAdapter2 = new ArrayAdapter<State2> (this,
                android.R.layout.simple_spinner_item, new State2[] {
                new State2("None"),
                new State2("Johnny"),
                new State2("Irene"),
                new State2("Wendy")
        });
        spperson.setAdapter(spinnerArrayAdapter2);
        spperson.setOnItemSelectedListener(new MyOnItemSelectedListener());

    }

    private boolean validateInputs(String title, String description, String location, String person, String time) {
        if (title.isEmpty()) {
            txt_title.setError("Title Required");
            txt_title.requestFocus();
            return true;
        }

        if (description.isEmpty()){
            txt_description.setError("Description Required");
            txt_description.requestFocus();
            return true;
        }
        if (location.isEmpty()){
            return false;
        }
        if (person.isEmpty()){
            return false;
        }
        if (time.isEmpty()){
            return false;
        }


        return false;
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

    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (parent.getId()) {
                case R.id.inLocation:
                    xx = parent.getItemAtPosition(position).toString();
                    break;
                case R.id.ObjPerson:
                    yy = parent.getItemAtPosition(position).toString();
                    break;
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        updateTimeText(c);

    }

    private void updateTimeText(Calendar c) {
        String timeText = "" + DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());

        timeTextView.setText(timeText);
    }

    public void createTask(){

        String title = txt_title.getText().toString().trim();
        String description = txt_description.getText().toString().trim();
        String location = xx;
        String person = yy;
        String time = timeTextView.getText().toString().trim();
        String status = "true";



        if (!validateInputs(title, description, location, person, time)) {
            CollectionReference dbReminder = db.collection("Reminder");

            dbReminder reminder = new dbReminder(
                    title,
                    description,
                    location,
                    person,
                    time,
                    status
            );


            dbReminder.add(reminder)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(AddTask.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddTask.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_btn:
                createTask();
                finish();
                break;

        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
