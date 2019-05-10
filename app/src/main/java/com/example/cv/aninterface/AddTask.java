package com.example.cv.aninterface;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddTask extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private TextInputEditText txt_title;
    private TextInputEditText txt_description;
    private TextView timeTextView;
    private String TAG = "AddTask";
    private FirebaseFirestore db;
    private String yy, xx, zz, rp;
    private List<dbReminder> reminderList;
    Toolbar toolbar;
    private FirebaseAuth firebaseAuth;
    private LinearLayout pickdatetime;
    private String dateText = "";
    private String timeText;
    private int year, month, day, hour, min;
    private Long datetime;
    private int sec = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        // timePicker = findViewById(R.id.alarmTimePicker)

        //Firestore
        db = FirebaseFirestore.getInstance();
        //Firebase auth
        firebaseAuth = FirebaseAuth.getInstance();


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

        pickdatetime = findViewById(R.id.pickdatetime);
        pickdatetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });


        Spinner splocation = (Spinner) findViewById(R.id.inLocation);
        Spinner spperson = (Spinner) findViewById(R.id.ObjPerson);


        //Spinner method to read the selected value
        ArrayAdapter<State1> spinnerArrayAdapter1 = new ArrayAdapter<State1>(this,
                android.R.layout.simple_spinner_item, new State1[]{
                new State1("None"),
                new State1("Bedroom"),
                new State1("Kitchen"),
                new State1("Living room")
        });
        spinnerArrayAdapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        splocation.setAdapter(spinnerArrayAdapter1);
        splocation.setOnItemSelectedListener(new MyOnItemSelectedListener());

        ArrayAdapter<State2> spinnerArrayAdapter2 = new ArrayAdapter<State2>(this,
                android.R.layout.simple_spinner_item, new State2[]{
                new State2("None"),
                new State2("Johnny"),
                new State2("Irene"),
                new State2("Wendy")
        });
        spinnerArrayAdapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spperson.setAdapter(spinnerArrayAdapter2);
        spperson.setOnItemSelectedListener(new MyOnItemSelectedListener());

        Spinner Pri = findViewById(R.id.priority);
        ArrayAdapter<State3> spinnerArrayAdapter3 = new ArrayAdapter<State3>(this,
                android.R.layout.simple_spinner_item, new State3[] {
                new State3("!"),
                new State3("!!"),
                new State3("!!!")
        });
        Pri.setAdapter(spinnerArrayAdapter3);
        Pri.setOnItemSelectedListener(new MyOnItemSelectedListener());

        Spinner rep = findViewById(R.id.repeat);
        ArrayAdapter<State4> spinnerArrayAdapter4 = new ArrayAdapter<State4>(this,
                android.R.layout.simple_spinner_item, new State4[] {
                new State4("None"),
                new State4("Sunday"),
                new State4("Monday"),
                new State4("Tuesday"),
                new State4("Wednesday"),
                new State4("Thursday"),
                new State4("Friday"),
                new State4("Saturday")
        });
        rep.setAdapter(spinnerArrayAdapter4);
        rep.setOnItemSelectedListener(new MyOnItemSelectedListener());

    }

    private boolean validateInputs(String title, String description, String location, String person, String time) {
        if (title.isEmpty()) {
            txt_title.setError("Title Required");
            txt_title.requestFocus();
            return true;
        }

        if (description.isEmpty()) {
            return false;
        }
        if (location.isEmpty()) {
            return false;
        }
        if (person.isEmpty()) {
            return false;
        }
        if (time.isEmpty()) {
            return false;
        }


        return false;
    }

    public class State1 {
        public String loc = "";

        public State1(String _loc) {
            loc = _loc;
        }

        public String toString() {
            return loc;
        }
    }

    public class State2 {
        public String name = "";

        public State2(String _name) {
            name = _name;
        }

        public String toString() {
            return name;
        }
    }

    public class State3 {
        public String priority = "";

        public State3(String _priority) {
            priority = _priority;
        }

        public String toString() {
            return priority;
        }
    }

    public class State4 {
        public String repeat = "";

        public State4(String _repeat) {
            repeat = _repeat;
        }

        public String toString() {
            return repeat;
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
                case R.id.priority:
                    zz = parent.getItemAtPosition(position).toString();
                    break;
                case R.id.repeat:
                    rp = parent.getItemAtPosition(position).toString();
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
        hour = hourOfDay;
        min = minute;

        updateDateTimeText(c);

    }

    public void onDateSet(DatePicker view, int yy, int mm, int dd) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, yy);
        c.set(Calendar.MONTH, mm);
        c.set(Calendar.DAY_OF_MONTH, dd);

        year = yy;
        month = mm;
        day = dd;

        if ((mm+1) < 10) {
            if (dd < 10) {
                dateText =  "0" + dd + "/" + "0" + (mm+1) + "/" + yy ;

            } else if (dd >= 10) {
                dateText = dd + "/" + "0" + (mm+1) + "/" + yy ;
            }
        } else if ((mm+1) == 10) {
            if (dd < 10) {
                dateText =  "0" + dd + "/" + (mm+1) + "/" + yy ;
            } else if (dd >= 10) {
                dateText = dd + "/" + (mm+1) + "/" + yy ;
            }
        } else if ((mm+1) > 10) {
            if (dd < 10) {
                dateText =  "0" + dd + "/" + (mm+1) + "/" + yy;
            } else if (dd >= 10) {
                dateText = dd + "/" + (mm+1) + "/" + yy;
            }
        }

        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");

    }

    private void updateDateTimeText(Calendar c) {

        timeText = "" + DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        //String datetimetext = timeText + " " + dateText;

        timeTextView.setText("  " + timeText + " " + dateText);

        setDateTime();

    }

    private void setDateTime() {
        Calendar c = Calendar.getInstance();

        if (min == 0) {
            if (hour == 0) {
                c.set(Calendar.HOUR_OF_DAY, 23);
                c.set(Calendar.MINUTE, 59);
            }
            c.set(Calendar.HOUR_OF_DAY, hour - 1);
            c.set(Calendar.MINUTE, 59);
        } else {
            c.set(Calendar.HOUR_OF_DAY, hour);
            c.set(Calendar.MINUTE, min - 1);
        }
        c.set(Calendar.SECOND, 0);

        c.set(year, month, day);
        datetime = c.getTimeInMillis();
        System.out.println("Datetime is: " + datetime);
    }

    public void createTask() {

        String title = txt_title.getText().toString().trim();
        String description = txt_description.getText().toString().trim();
        String location = xx;
        String person = yy;
        String status = "true";
        String username = firebaseAuth.getCurrentUser().getEmail();
        String pri = zz;
        String repeat = rp;
        Long epochTime = datetime;
        String date = dateText;
        String time = timeText;

        if (!validateInputs(title, description, location, person, time)) {
            CollectionReference dbReminder = db.collection("Reminder");

            dbReminder reminder = new dbReminder(
                    title, description, location, person, date, time,
                    status, username, pri, repeat, epochTime
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
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addtask_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.create_btn:
                createTask();
                finish();
                break;
        }
        return false;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

