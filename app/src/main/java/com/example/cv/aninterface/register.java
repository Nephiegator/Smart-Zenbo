package com.example.cv.aninterface;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private Button buttonSignup;
    private EditText emailSignup;
    private EditText passwordSignup;
    private EditText FNameSignup;
    private EditText LNameSignup;
    private TextView textViewSignup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonSignup = (Button) findViewById(R.id.signup_btn);
        emailSignup = (EditText) findViewById(R.id.signup_email);
        passwordSignup = (EditText) findViewById(R.id.signup_password);
        FNameSignup = (EditText) findViewById(R.id.signup_fname);
        LNameSignup = (EditText) findViewById(R.id.signup_lname);

        firebaseAuth = FirebaseAuth.getInstance();

        buttonSignup.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);

    }

    private void registerUser() {

        String email = emailSignup.getText().toString().trim();
        String pass = passwordSignup.getText().toString().trim();
        String fname = FNameSignup.getText().toString().trim();
        String lname = LNameSignup.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(fname)) {
            Toast.makeText(this, "Please enter your First Name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(lname)) {
            Toast.makeText(this, "Please enter your First Name", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(register.this, "Successfully registered.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(register.this, "Error occurred, please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (view == buttonSignup)
        {
            registerUser();
        }
        if (view == textViewSignup) {
            // will open login activity here
        }
    }

}
