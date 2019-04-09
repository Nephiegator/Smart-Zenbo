package com.example.cv.aninterface;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class AddTaskMed extends AppCompatActivity  {

    private TextInputEditText txt_title;
    private TextInputEditText txt_description;
    RadioGroup r1, r2;
    RadioButton bf, at;
    private String sBefore, sAfter;
    private String one_tab, two_tab, thee_tab;
    private String yy, xx;

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


        Spinner splocation = (Spinner) findViewById(R.id.inLocation);
        Spinner spperson = (Spinner) findViewById(R.id.ObjPerson);

        //Spinner method to read the selected value
        ArrayAdapter<AddTaskMed.State1> spinnerArrayAdapter1 = new ArrayAdapter<AddTaskMed.State1> (this,
                android.R.layout.simple_spinner_item, new AddTaskMed.State1[] {
                new AddTaskMed.State1("None"),
                new AddTaskMed.State1("Bedroom"),
                new AddTaskMed.State1("Kitchen"),
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

    private boolean validateInputs(String title, String description, String location, String person){
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

    public void creatTask() {
        String title = txt_title.getText().toString().trim();
        String description = txt_description.getText().toString().trim();
        String location = xx;
        String person = yy;
        String period = sBefore;
    }

}
