package com.example.cv.aninterface;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.content.Intent;

import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SignIn extends AppCompatActivity {

    private static final String LOGIN_KEY = "LOGIN_KEY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        /*SharedPreferences pref = getPreferences(Context.MODE_PRIVATE);
        if (pref.getBoolean(LOGIN_KEY, false)) {
            startActivity(new Intent(this, login.class));
            finish();
        } else {
            pref.edit().putBoolean(LOGIN_KEY, true).apply();
        }

        printKeyHash(); */
    }

    /*private void printKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.example.cv.aninterface", PackageManager.GET_SIGNATURES);
            for (Signature signature:info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }*/
}