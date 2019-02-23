package com.example.cv.aninterface;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.lang.reflect.Field;

public class bottomnavigation extends AppCompatActivity {

   private TextView mTextMessage;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent in;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_task:
                    mTextMessage.setText(R.string.title_task);
                    return true;
                case R.id.navigation_plan:
                    mTextMessage.setText(R.string.title_plan);
                    return true;
            }
            return false;
        }
    };
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottomnavigation);

        mTextMessage = (TextView) findViewById(R.id.message);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent in;
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        in = new Intent(getBaseContext(), Home.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.navigation_task:
                        Intent a3 = new Intent(bottomnavigation.this, MainListActivity.class);
                        startActivity(a3);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.navigation_plan:
                        in = new Intent(getBaseContext(), MainListActivity.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        break;
                    default:
                        break;
                } return false;
            }

        });


    }*/


}
