package com.example.cv.aninterface;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class UpdateProfile extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText username_Updateprofile;
    private TextInputEditText fname_Updateprofile;
    private TextInputEditText lname_Updateprofile;
    private TextInputEditText phone_Updateprofile;
    private EditText bdate_Updateprofile;
    private FirebaseAuth firebaseAuth;

    private FirebaseFirestore db;
    private dbUserInformation dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updateprofile);

        dp = (dbUserInformation) getIntent().getSerializableExtra("Profile");
        db = FirebaseFirestore.getInstance();

        username_Updateprofile = findViewById(R.id.username_Updateprofile);
        fname_Updateprofile = findViewById(R.id.fname_Updateprofile);
        lname_Updateprofile = findViewById(R.id.lname_Updateprofile);
        phone_Updateprofile = findViewById(R.id.phone_Updateprofile);
        bdate_Updateprofile = findViewById(R.id.bdate_Updateprofile);

//        username_Updateprofile.setText(dp.getUsername());
//        fname_Updateprofile.setText(dp.getfname());
//        lname_Updateprofile.setText(dp.getlname());
//        phone_Updateprofile.setText(dp.getphone());
//        bdate_Updateprofile.setText(dp.bdate);

        findViewById(R.id.save_Updateprofile).setOnClickListener(this);



    }

    private boolean hasvalidateInputs (String username, String fname, String lname, String phone, String bdate, String email){
        if (username.isEmpty()){
            username_Updateprofile.setError("User Required");
            username_Updateprofile.requestFocus();
            return true;
        }

        if (fname.isEmpty()){
            fname_Updateprofile.setError("First name Required");
            fname_Updateprofile.requestFocus();
            return true;
        }
        if (lname.isEmpty()){
            lname_Updateprofile.setError("Last name Required");
            lname_Updateprofile.requestFocus();
            return true;
        }
        if (phone.isEmpty()){
            phone_Updateprofile.setError("Phone number Required");
            phone_Updateprofile.requestFocus();
            return true;
        }
        if (bdate.isEmpty()){

            bdate_Updateprofile.setError("Birth day Required");
            bdate_Updateprofile.requestFocus();
            return true;
        }

        return false;
    }

    public void updateTask() {

        String username = username_Updateprofile.getText().toString().trim();
        String email = firebaseAuth.getCurrentUser().getEmail();
        String fname = fname_Updateprofile.getText().toString().trim();
        String lname = lname_Updateprofile.getText().toString().trim();
        String phone = phone_Updateprofile.getText().toString().trim();
        String bdate = bdate_Updateprofile.getText().toString().trim();

        if (!hasvalidateInputs(username, fname, lname, bdate, phone, email)){

            dbUserInformation profile = new dbUserInformation(
                    username, fname, lname, bdate, phone, email
            );

            db.collection("Profile").document(dp.getemail())
                    .set(profile)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(UpdateProfile.this, "Updated", Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update_btn:
                updateTask();
                break; }

        }
}
