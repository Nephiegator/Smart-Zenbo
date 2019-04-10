package com.example.cv.aninterface;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddTaskMed extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText txt_title;
    private TextInputEditText txt_description;
    private String yy, xx, per, med;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtaskmed);
        db = FirebaseFirestore.getInstance();

        txt_title = findViewById(R.id.task_title);
        txt_description = findViewById(R.id.task_des);

        //header Navigation Bar
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button Create = (Button) findViewById(R.id.create_btn);
        Create.setOnClickListener(this);
        //Spinner period
        Spinner spperiod = (Spinner) findViewById(R.id.period);

        ArrayAdapter<AddTaskMed.State3> spinnerArrayAdapter3 = new ArrayAdapter<AddTaskMed.State3> (this,
                android.R.layout.simple_spinner_item, new AddTaskMed.State3[] {
                new AddTaskMed.State3("None"),
                new AddTaskMed.State3("Before food"),
                new AddTaskMed.State3("After food")
        });
        spperiod.setPrompt("Period");
        spperiod.setAdapter(spinnerArrayAdapter3);
        spperiod.setOnItemSelectedListener(new AddTaskMed.MyOnItemSelectedListener());

        //Spinner pills
        Spinner sppill = (Spinner) findViewById(R.id.pill);
        ArrayAdapter<AddTaskMed.State4> spinnerArrayAdapter4 = new ArrayAdapter<State4> (this,
                android.R.layout.simple_spinner_item, new AddTaskMed.State4[] {
                new AddTaskMed.State4("None"),
                new AddTaskMed.State4("1 pill"),
                new AddTaskMed.State4("2 pills"),
                new AddTaskMed.State4("3 pills"),
                new AddTaskMed.State4("4 pills")
        });
        sppill.setPrompt("Amount of pill");
        sppill.setAdapter(spinnerArrayAdapter4);
        sppill.setOnItemSelectedListener(new AddTaskMed.MyOnItemSelectedListener());

        //Spinner location and object person
        Spinner splocation = (Spinner) findViewById(R.id.inLocation);
        Spinner spperson = (Spinner) findViewById(R.id.ObjPerson);

        //Spinner method to read the selected value
        ArrayAdapter<AddTaskMed.State1> spinnerArrayAdapter1 = new ArrayAdapter<AddTaskMed.State1> (this,
                android.R.layout.simple_spinner_item, new AddTaskMed.State1[] {
                new AddTaskMed.State1("None"),
                new AddTaskMed.State1("Bedroom"),
                new AddTaskMed.State1("Kitchen")
        });
        splocation.setAdapter(spinnerArrayAdapter1);
        splocation.setOnItemSelectedListener(new AddTaskMed.MyOnItemSelectedListener());

        ArrayAdapter<AddTaskMed.State2> spinnerArrayAdapter2 = new ArrayAdapter<AddTaskMed.State2> (this,
                android.R.layout.simple_spinner_item, new AddTaskMed.State2[] {
                new AddTaskMed.State2("None"),
                new AddTaskMed.State2("Johnny"),
                new AddTaskMed.State2("Irene"),
                new AddTaskMed.State2("Wendy")
        });
        spperson.setAdapter(spinnerArrayAdapter2);
        spperson.setOnItemSelectedListener(new AddTaskMed.MyOnItemSelectedListener());


    }

    private boolean validateInputs(String title, String description, String location, String person,String period, String pill){
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
        if (location.isEmpty()){
            return false;
        }
        if (person.isEmpty()){
            return false;
        }
        if (period.isEmpty()) {
            return false;
        }
        if (pill.isEmpty()) {
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

    public class State3 {
        public String period = "";

        public State3(String _period){
            period = _period;
        }

        public String toString() {
            return period;
        }
    }

    public class State4 {
        public String pill = "";

        public State4(String _pill){
            pill = _pill;
        }

        public String toString() {
            return pill;
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
                case R.id.period:
                    per = parent.getItemAtPosition(position).toString();
                    break;
                case R.id.pill:
                    med = parent.getItemAtPosition(position).toString();
                    break;
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    public void createTask() {
        String title = txt_title.getText().toString().trim();
        String description = txt_description.getText().toString().trim();
        String location = xx;
        String person = yy;
        String period = per;
        String pill = med;

        if (!validateInputs(title, description, location, person, period, pill)) {
            CollectionReference dbTaskMed = db.collection("Medication Task");

            dbTaskMed tm = new dbTaskMed(
                    title,
                    description,
                    location,
                    person,
                    period,
                    pill
            );

            dbTaskMed.add(tm)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(AddTaskMed.this, "Created Successfully", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddTaskMed.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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

}
