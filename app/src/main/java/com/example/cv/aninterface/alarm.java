package com.example.cv.aninterface;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;

public class alarm extends AppCompatActivity {


    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimePicker timePicker;
    private static AlarmManager inst;
    private TextView alarmText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
    }
}
