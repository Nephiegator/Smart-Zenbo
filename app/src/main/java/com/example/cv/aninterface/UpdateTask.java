package com.example.cv.aninterface;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UpdateTask extends AppCompatActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private TextInputEditText txt_title;
    private TextInputEditText txt_description;
    private FirebaseFirestore db;
    private dbReminder tt;
    private  String yy,xx, zz, rp;
    private TextView timeTextView;
    private TextView PickDate;
    private Switch sswitch;
    private String status = "true";
    Toolbar toolbar;
    private FirebaseAuth firebaseAuth;
    private TextView textview_username;
    private LinearLayout PickDateTime;
    private String dateText = "";
    private int year, month, day, hour, min;
    private Long datetime;
    private String timeText;
    private int sec = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatetask);

        tt = (dbReminder) getIntent().getSerializableExtra("Reminder");
        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        txt_title = findViewById(R.id.task_title);
        txt_description = findViewById(R.id.task_des);
        timeTextView = findViewById(R.id.time_textview);
        textview_username = findViewById(R.id.textview_username);

        toolbar =(Toolbar) findViewById(R.id.update_task_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" Update Task");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        String[] test = tt.getUsername().toString().split("@");
        String usern = test[0];

        txt_title.setText(tt.getTitle());
        txt_description.setText(tt.getDesc());
        textview_username.setText(usern);

        //set switch to ON
        //sswitch.setChecked(false);
        sswitch = findViewById(R.id.Switch);
        sswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    status = "true";
                } else {
                    status = "false";
                }
            }
        });

        //TimePicker
//        PickTime = findViewById(R.id.picktime);
//        PickTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DialogFragment timePicker = new TimePickerFragment();
//                timePicker.show(getSupportFragmentManager(), "time picker");
//            }
//        });

        //DatePicker
//        PickDate = findViewById(R.id.datepicker);
//        Calendar c = Calendar.getInstance();
//        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//        CharSequence sDate = df.format(c.getTime());
//
//        PickDate.setText(sDate);
//
//
//        PickDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DialogFragment datePicker = new DatePickerFragment();
//                datePicker.show(getSupportFragmentManager(), "date picker");
//            }
//        });

        PickDateTime = findViewById(R.id.pickdatetime);
        PickDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });


        findViewById(R.id.update_btn).setOnClickListener(this);


        Spinner splocation =  (Spinner) findViewById(R.id.inLocation);
        Spinner spperson =  (Spinner) findViewById(R.id.ObjPerson);

        //Spinner method to read the selected value
        ArrayAdapter<UpdateTask.State1> spinnerArrayAdapter1 = new ArrayAdapter<UpdateTask.State1> (this,
                android.R.layout.simple_spinner_item, new UpdateTask.State1[] {
                new UpdateTask.State1("None"),
                new UpdateTask.State1("Bedroom"),
                new UpdateTask.State1("Kitchen"),
                new UpdateTask.State1("Living room")
        });

        splocation.setAdapter(spinnerArrayAdapter1);
        splocation.setOnItemSelectedListener(new UpdateTask.MyOnItemSelectedListener());

        ArrayAdapter<UpdateTask.State2> spinnerArrayAdapter2 = new ArrayAdapter<UpdateTask.State2> (this,
                android.R.layout.simple_spinner_item, new UpdateTask.State2[] {
                new UpdateTask.State2("None"),
                new UpdateTask.State2("Johny"),
                new UpdateTask.State2("Irene"),
                new UpdateTask.State2("Wendy")
        });
        spperson.setAdapter(spinnerArrayAdapter2);
        spperson.setOnItemSelectedListener(new UpdateTask.MyOnItemSelectedListener());

        Spinner Pri = findViewById(R.id.priority);
        ArrayAdapter<UpdateTask.State3> spinnerArrayAdapter3 = new ArrayAdapter<UpdateTask.State3>(this,
                android.R.layout.simple_spinner_item, new UpdateTask.State3[] {
                new UpdateTask.State3("1"),
                new UpdateTask.State3("2"),
                new UpdateTask.State3("3")
        });
        Pri.setAdapter(spinnerArrayAdapter3);
        Pri.setOnItemSelectedListener(new UpdateTask.MyOnItemSelectedListener());

    }

    private boolean hasvalidateInputs(String title, String description, String location,
                                      String person, String date, String time, String status, String username,
                                      String priority){
        if (title.isEmpty()){
            txt_title.setError("Title Required");
            txt_title.requestFocus();
            return true;
        }

        if (description.isEmpty()){
            txt_description.setError("Description Required");
            txt_description.requestFocus();
            return true;
        }
        if (location.isEmpty()){ return false; }
        if (person.isEmpty()){ return false; }
        if (status.isEmpty()) { return false; }
        if (username.isEmpty()) { return false; }
        if (priority.isEmpty()) { return false; }



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

        updateTimeText(c);

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

    private void updateTimeText(Calendar c) {
        timeText = "" + DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        //String datetimetext = dateText + " " + timeText;

        timeTextView.setText("  " + timeText + " " + dateText);

        setDateTime();
    }

    private void deleteTask() {
        db.collection("Reminder").document(tt.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(UpdateTask.this, "Deleted", Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(new Intent(UpdateTask.this, Home.class));
                        }
                    }
                });
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
        c.set(Calendar.MILLISECOND, 0);

        c.set(year, month, day);
        datetime = c.getTimeInMillis();
        System.out.println("Datetime is: " + datetime);
    }

    public void updateTask() {
        String title = txt_title.getText().toString().trim();
        String description = txt_description.getText().toString().trim();
        String location = xx;
        String person = yy;
        String sstatus = status;
        String username = firebaseAuth.getCurrentUser().getEmail();
        String priority = zz;
        String repeat = rp;
        Long epochTime = datetime;
        String date = dateText;
        String time = timeText;


        if (!hasvalidateInputs(title,description,location,person, date, time, sstatus, username, priority)){

            dbReminder reminder = new dbReminder(
                    title, description, location, person, date, time, sstatus, username, priority, epochTime
            );

            db.collection("Reminder").document(tt.getId())
                    .set(reminder)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(UpdateTask.this, "Updated", Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update_btn:
                updateTask();
                Intent intent = new Intent(UpdateTask.this, MainTask.class);
                startActivity(intent);
                break;
//            case R.id.delete_btn:
//                /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setTitle("Are your sure to delete it?");
//                builder.setMessage("Deletion of permanent...");
//
//                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        deleteTask();
//                    }
//                });
//
//                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//
//                AlertDialog ad = builder.create();
//                ad.show(); */
//                deleteTask();
//                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.update_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String msg = " ";
        switch (item.getItemId()) {
            case R.id.delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Are your sure to delete it?");
                builder.setMessage("Deletion of permanent...");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteTask();
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
        }
        return false;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
