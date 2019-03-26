package com.example.cv.aninterface;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class SignIn extends AppCompatActivity {

    private CallbackManager callbackManager;
    private FirebaseAuth firebaseAuth, mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    //private TextView txtUser;
    private TextView txtEmail;
    //private ImageView imgProfile;
    private LoginButton loginButton;
    private EditText iEmail, iPassword;
    private Button Loginbtn;
    private EditText editTextPassword;
    private EditText editTextEmail;
    private static final String TAG = "SignIn";

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

        iEmail = (EditText) findViewById(R.id.userlogin);
        iPassword = (EditText) findViewById(R.id.password);
        Loginbtn = (Button) findViewById(R.id.login);


        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    toastMessage("Successfully signed in with: " + user.getEmail());
                    onLoginSuccess();
                    Intent myIntent = new Intent(SignIn.this,Home.class);
                    SignIn.this.startActivity(myIntent);
                } /*else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    toastMessage("Successfully signed out.");
                }*/
                // ...
            }
        };

        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = iEmail.getText().toString();
                String pass = iPassword.getText().toString();
                if (!email.equals("") && !pass.equals("")) {
                    mAuth.signInWithEmailAndPassword(email, pass);
                } else {
                    toastMessage("You didn't fill in all the fields.");
                }
            }
        });

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

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
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
        finish();
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
}

