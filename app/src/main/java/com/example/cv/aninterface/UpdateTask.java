package com.example.cv.aninterface;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UpdateTask extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText txt_title;
    private TextInputEditText txt_description;

    private FirebaseFirestore db;

    private dbReminder tt;
    private  String yy,xx,ttime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatetask);


        tt = (dbReminder) getIntent().getSerializableExtra("Reminder");
        db = FirebaseFirestore.getInstance();

        txt_title = findViewById(R.id.task_title);
        txt_description = findViewById(R.id.task_des);

        txt_title.setText(tt.getTitle());
        txt_description.setText(tt.getDesc());


        findViewById(R.id.update_btn).setOnClickListener(this);
        findViewById(R.id.delete_btn).setOnClickListener(this);


        Spinner splocation =  (Spinner) findViewById(R.id.inLocation);
        Spinner spperson =  (Spinner) findViewById(R.id.ObjPerson);

        //Spinner method to read the selected value
        ArrayAdapter<UpdateTask.State1> spinnerArrayAdapter1 = new ArrayAdapter<UpdateTask.State1> (this,
                android.R.layout.simple_spinner_item, new UpdateTask.State1[] {
                new UpdateTask.State1("None"),
                new UpdateTask.State1("Dad Room"),
                new UpdateTask.State1("Kitchen"),
        });
        splocation.setAdapter(spinnerArrayAdapter1);
        splocation.setOnItemSelectedListener(new UpdateTask.MyOnItemSelectedListener());

        ArrayAdapter<UpdateTask.State2> spinnerArrayAdapter2 = new ArrayAdapter<UpdateTask.State2> (this,
                android.R.layout.simple_spinner_item, new UpdateTask.State2[] {
                new UpdateTask.State2("None"),
                new UpdateTask.State2("Lisa"),
                new UpdateTask.State2("Jisoo"),
                new UpdateTask.State2("Rose")
        });
        spperson.setAdapter(spinnerArrayAdapter2);
        spperson.setOnItemSelectedListener(new UpdateTask.MyOnItemSelectedListener());

    }

    private boolean hasvalidateInputs(String title, String description, String location, String person, String time){
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
        /*if (time.isEmpty())
        {
            return false;
        }*/


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

    private void deleteTask() {
        db.collection("Reminder").document(tt.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(UpdateTask.this, "Deleted", Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(new Intent(UpdateTask.this, MainTask.class));
                        }
                    }
                });
    }

    public void updateTask() {

        String title = txt_title.getText().toString().trim();
        String description = txt_description.getText().toString().trim();
        String location = xx;
        String person = yy;
        String time = ttime;

        if (!hasvalidateInputs(title,description,location,person,time)){



            dbReminder reminder = new dbReminder(
                    title, description, location, person, time
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
                break;
            case R.id.delete_btn:
                /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
                ad.show(); */
                deleteTask();
                break;
        }
    }

}
