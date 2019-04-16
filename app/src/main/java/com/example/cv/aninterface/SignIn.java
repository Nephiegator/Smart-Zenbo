package com.example.cv.aninterface;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class SignIn extends AppCompatActivity implements View.OnClickListener {

    private CallbackManager callbackManager;
    private FirebaseAuth firebaseAuth, mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private TextView txtEmail;
    private LoginButton loginButton;
    private EditText iEmail, iPassword;
    private Button Loginbtn;
    private Button login;
    private TextView textViewSignUp;
    private TextView textViewForgot;
    private static final String TAG = "SignIn";

    private TextInputEditText emailLogin;
    private TextInputEditText passwordLogin;

    public SignIn() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        //txtUser = (TextView) findViewById(R.id.txtUser);
        /* txtEmail = (TextView) findViewById(R.id.txtEmail);
        //imgProfile = (ImageView) findViewById(R.id.imgProfile);
        firebaseAuth = FirebaseAuth.getInstance();
        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.login_fb_btn);
        //logoutButton.setOnClickListener(this);


        loginButton.setReadPermissions("email", "public_profile");//user_status, publish_actions..
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(SignIn.this, "Sign In canceled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(SignIn.this, "Something bad happened", Toast.LENGTH_SHORT).show();
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (AccessToken.getCurrentAccessToken() != null)
                    Toast.makeText(SignIn.this, AccessToken.getCurrentAccessToken().getExpires().toString(), Toast.LENGTH_SHORT).show();
                if (user != null) {
                    String email = user.getEmail();
                    String userName = user.getDisplayName();
                    txtEmail.setText(email);
                    //txtUser.setText(userName);
                //    Picasso.with(SignIn.this).load(user.getPhotoUrl()).into(imgProfile);
                    loginButton.setVisibility(View.GONE);
                    //logoutButton.setVisibility(View.VISIBLE);
                } else {
                    Log.d("TG", "SIGNED OUT");
                    txtEmail.setText("");
                    //txtUser.setText("");
                    //imgProfile.setImageBitmap(null);
                    loginButton.setVisibility(View.VISIBLE);
                    //logoutButton.setVisibility(View.GONE);
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            firebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void handleFacebookAccessToken(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignIn.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,
                resultCode, data);
    }

    @Override
    public void onClick(View v) {
        firebaseAuth.signOut();
        LoginManager.getInstance().logOut();  */

        findViewById(R.id.textViewSignUp).setOnClickListener(this);
        findViewById(R.id.textViewForgot).setOnClickListener(this);
        findViewById(R.id.login).setOnClickListener(this);

        emailLogin = findViewById(R.id.userlogin);
        passwordLogin = findViewById(R.id.password);
        passwordLogin.setTransformationMethod(new PasswordTransformationMethod());

        login = findViewById(R.id.login);


        mAuth = FirebaseAuth.getInstance();


        /*btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                toastMessage("Signing Out...");
            }
        });

        btnAddItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Switching Activities.");
                Intent intent = new Intent(MainActivity.this, AddItemsToDatabase.class);
                startActivity(intent);
            }
        });*/
    }

    private void userLogin(){

        final String email = emailLogin.getText().toString().trim();
        final String pass = passwordLogin.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }


        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(SignIn.this, Home.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    onLoginSuccess();
                }
            }
        };
        mAuth.addAuthStateListener(mAuthListener);
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        Loginbtn.setEnabled(true);
        SharedPreferences sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", emailLogin.getText().toString());
        editor.putString("password", passwordLogin.getText().toString());
        editor.commit();
        Intent intent = new Intent(getApplicationContext(), Home.class);
        startActivity(intent);
    }

    //add a toast to show when successfully signed in

    /**
     * customizable toast
     *
     * @param message
     */
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textViewSignUp:

                startActivity(new Intent(this, register.class));


                break;

            case R.id.login:
                userLogin();
                startActivity(new Intent(this, register.class));
                break;

            case R.id.textViewForgot:
                startActivity(new Intent(this, reset.class));
        }
    }
}

