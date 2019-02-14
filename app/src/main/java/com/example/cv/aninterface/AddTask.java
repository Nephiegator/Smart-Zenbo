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
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddTask extends AppCompatActivity { // implements View.OnClickListener {

    private static final String TAG = "AddTask";

    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
    private TextInputEditText task_title;
    private TextInputEditText task_desc;
    private Spinner mySpinner1;
    private Spinner mySpinner2;

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
        ArrayAdapter<State> spinnerArrayAdapter = new ArrayAdapter<State> (this,
                android.R.layout.simple_spinner_item, new State[] {
                        new State("Mom Room"),
                        new State ("Dad Room"),
                        new State ("Kitchen"),
                        new State ("None")
        });
        mySpinner1.setAdapter(spinnerArrayAdapter);
        //mySpinner1.setOnItemSelectedListener(AddTask.this);



        /*ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(AddTask.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.inLocation));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner1.setAdapter(myAdapter1);*/



        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(AddTask.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ObjPerson));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner2.setAdapter(myAdapter2);

        //header Navigation Bar
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public class State {
        public String loc = "";
        //public String name = "";

        public State(String _loc){
        loc = _loc;
        //name = _name;
        }

        public String toString() {
            return loc;
        }
    }



    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        // Get the currently selected State object from the spinner
        State st = (State)mySpinner1.getSelectedItem();

        // Show it via a toast
        //toastState( "onItemSelected", st );
    }

    /*public void toastState(String loc, State st)
    {
        if ( st != null )
        {
            Gen = st.loc;
            //Toast.makeText(getBaseContext(), Gen, Toast.LENGTH_SHORT).show();

        }

    }*/

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void saveTask (View v) {
        String title = task_title.getText().toString();
        String desc = task_desc.getText().toString();


        Map<String, Object> reminder = new HashMap<>();
        reminder.put(KEY_TITLE, title);
        reminder.put(KEY_DESCRIPTION, desc);


        db.collection("Reminder").document("Reminder Task").set(reminder)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AddTask.this, "Saved", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddTask.this, "Error", Toast.LENGTH_LONG).show();
                        Log.d(TAG, e.toString());
                    }
                });
    }

}
