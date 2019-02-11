package com.example.cv.aninterface;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class login_info extends AppCompatActivity {

    private TextView txtusername;
    private ImageView avatar;
    private TextView txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header_login);

        txtusername = (TextView) findViewById(R.id.txtusername);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
    }
}
