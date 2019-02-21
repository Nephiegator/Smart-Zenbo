package com.example.cv.aninterface;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UpdateTask extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText txt_title;
    private TextInputEditText txt_description;

    private FirebaseFirestore db;

    private dbReminder tt;
    private  String yy,xx;

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

        Button update = (Button) findViewById(R.id.update_btn);
        update.setOnClickListener(this);

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

    private boolean hasvalidateInputs(String title, String description, String location, String person){
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

    @Override
    public void onClick(View v){

        String title = txt_title.getText().toString().trim();
        String description = txt_description.getText().toString().trim();
        String location = xx;
        String person = yy;

        if (!hasvalidateInputs(title,description,location,person)){



            final dbReminder reminder = new dbReminder(
                    title, description, location, person
            );

            db.collection("Reminder").document(tt.getId())
                    .set(reminder)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(UpdateTask.this, "Updated", Toast.LENGTH_LONG).show();
                        }
                    });

        } finish();
    }

}
