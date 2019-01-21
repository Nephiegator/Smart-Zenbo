package com.example.cv.aninterface;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class bottomnavigation extends AppCompatActivity {

    private TextView mTextMessage;

    Intent a1, a2, a3;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent in;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_plan:
                    mTextMessage.setText(R.string.title_plan);
                    return true;
                case R.id.navigation_history:
                    mTextMessage.setText(R.string.title_history);
                    return true;
            }
            return false;
        }
    };

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
                        in = new Intent(getBaseContext(), login.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.navigation_plan:
                        in = new Intent(getBaseContext(), AddPlan.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        break;
                    default:
                        break;
                }
            } 

        });


    }

}
