package com.example.cv.aninterface;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment  {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
    }
//        return new DatePickerDialog(getActivity(), this, yy, mm, dd);
//    }
//
//    public void onDateSet(DatePicker view, int yy, int mm, int dd) {
//        populateSetDate(yy, mm+1, dd);
//    }
//    public void populateSetDate(int year, int month, int day) {
//
//    }
}
