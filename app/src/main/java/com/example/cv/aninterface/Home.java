package com.example.cv.aninterface;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    CompactCalendarView compactcalendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM", Locale.getDefault());
    private SimpleDateFormat dateFormatYear = new SimpleDateFormat("yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //test
        final TextView yLabel = findViewById(R.id.yLabel);
        final TextView mLabel = findViewById(R.id.mLabel);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MMMM", Locale.US);
        String strDate = sdf.format(cal.getTime());

        String[] values = strDate.split("/",0);

        //Create Calendar
        compactcalendar = findViewById(R.id.compactcalendar_view);
        compactcalendar.setUseThreeLetterAbbreviation(true);

        //Set an event for Teachers' Day of 2019
        Event ev1 = new Event(Color.rgb(3, 169, 244), 1546300800000L, "New Year's Day");
        compactcalendar.addEvent(ev1);

        compactcalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();

                if (dateClicked.toString().compareTo("Tue Jan 1 07:00:00 GMT+07:00 2019") == 0) {
                    Toast.makeText(context, "New Year's Day", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "No Event Planned for this day", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {

                mLabel.setText(dateFormatMonth.format(firstDayOfNewMonth));
                yLabel.setText(dateFormatYear.format(firstDayOfNewMonth));
            }
        });
      /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); */

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

     /*   CalendarView calendarView;
        final TextView myDate;

        calendarView = (CalendarView) findViewById(R.id.calendarView);
         myDate = (TextView) findViewById(R.id.myDate);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = (month + 1) + "/" + dayOfMonth + "/" + year;
                myDate.setText(date);
            }
        });*/

        //Bottom Navigation Bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        //bottomnavigation.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:

                        break;
                    case R.id.navigation_task:
                        Intent a2 = new Intent(Home.this, MainTask.class);
                        startActivity(a2);
                        break;
                    case R.id.navigation_plan:
                        Intent a3 = new Intent(Home.this, MainListActivity.class);
                        startActivity(a3);
                        break;
                }
                return false;
            }
        });



        FloatingActionMenu materialDesignFAM;
        FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3,
                floatingActionButton4, floatingActionButton5;

        materialDesignFAM = findViewById(R.id.fab);
        floatingActionButton1 = findViewById(R.id.floating_btn_1);
        floatingActionButton2 = findViewById(R.id.floating_btn_2);
        floatingActionButton3 = findViewById(R.id.floating_btn_3);
        floatingActionButton4 = findViewById(R.id.floating_btn_4);
        floatingActionButton5 = findViewById(R.id.floating_btn_5);


        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked

            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent f3 = new Intent(Home.this, AddTaskApm.class);
                startActivity(f3);
            }
        });
        floatingActionButton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent f4 = new Intent(Home.this, AddTaskMed.class);
                startActivity(f4);
            }
        });
        floatingActionButton5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent f5 = new Intent(Home.this, AddTask.class);
                startActivity(f5);
            }
        });

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}