package com.example.cv.aninterface;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class register extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private Button buttonSignup;
    private TextInputEditText emailSignup;
    private TextInputEditText passwordSignup;
    private TextInputEditText FNameSignup;
    private TextInputEditText LNameSignup;
    private TextView textViewSignup;
    private TextView textViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonSignup = (Button) findViewById(R.id.signup_btn);
        emailSignup = findViewById(R.id.signup_email);
        passwordSignup = findViewById(R.id.signup_password);
        FNameSignup =  findViewById(R.id.signup_fname);
        LNameSignup =  findViewById(R.id.signup_lname);

        textViewLogin = findViewById(R.id.textViewLogin);
        firebaseAuth = FirebaseAuth.getInstance();

        buttonSignup.setOnClickListener(this);

    }

    private void registerUser() {

        final String email = emailSignup.getText().toString().trim();
        final String pass = passwordSignup.getText().toString().trim();
        final String fname = FNameSignup.getText().toString().trim();
        final String lname = LNameSignup.getText().toString().trim();

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
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            User user = new User(
                                    email,
                                    pass,
                                    fname,
                                    lname
                            );

                            FirebaseDatabase.getInstance().getReference("User")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(register.this, "Successfully registered.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

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
            Intent a3 = new Intent(register.this, Home.class);
            startActivity(a3);
        }
        if (view == textViewLogin) {
            // will open login activity here
            /*Intent login = new Intent(register.this, SignIn.class);
            startActivity(login);*/
            startActivity(new Intent(this, SignIn.class));
        }
        if (view == textViewSignup) {
            // will open login activity here

        }

    }

}
