package com.example.cv.aninterface;

import android.content.Intent;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddTask extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText task_title;
    private TextInputEditText task_desc;
    private Spinner mySpinner1;
    private Spinner mySpinner2;
    Button create;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        db = FirebaseFirestore.getInstance();

        Spinner mySpinner1 =  (Spinner) findViewById(R.id.inLocation);
        Spinner mySpinner2 =  (Spinner) findViewById(R.id.ObjPerson);

        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(AddTask.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.inLocation));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner1.setAdapter(myAdapter1);

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(AddTask.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ObjPerson));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner2.setAdapter(myAdapter2);

        //header Navigation Bar
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        task_title = findViewById(R.id.task_title);
        task_desc = findViewById(R.id.task_des);

        findViewById(R.id.create_btn).setOnClickListener(this);
    }

    private boolean validateInputs(String title, String desc, String inLocation, String ObjPerson) {

        Spinner mySpinner1 =  (Spinner) findViewById(R.id.inLocation);
        Spinner mySpinner2 =  (Spinner) findViewById(R.id.ObjPerson);

        if (title.isEmpty()) {
            task_title.setError("Title required");
            task_title.requestFocus();
            return true;
        }
        if (desc.isEmpty()) {
            task_desc.setError("Title required");
            task_desc.requestFocus();
            return true;
        }
        if (inLocation.isEmpty()) {
            mySpinner1.setFocusable(true);
            mySpinner1.setFocusableInTouchMode(true);
        }
        if (ObjPerson.isEmpty()) {
            mySpinner2.setFocusable(true);
            mySpinner2.setFocusableInTouchMode(true);
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        String title = task_title.getText().toString().trim();
        String desc = task_desc.getText().toString().trim();
        String inLocation = mySpinner1.getSelectedItem().toString();
        String ObjPerson = mySpinner2.getSelectedItem().toString();

        if(!validateInputs(title, desc, inLocation, ObjPerson)) {
            CollectionReference dbReminder = db.collection("reminder");

            //db = FirebaseFirestore.getInstance().getReference().child("inLocation").child(inLocation);

            dbReminder reminder = new dbReminder(
                    title,
                    desc,
                    inLocation,
                    ObjPerson
            );

            dbReminder.add(reminder)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(AddTask.this, "Reminder Created" + documentReference.getId(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddTask.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
