package com.example.cv.aninterface;

import java.util.Comparator;

public class CustomComparator implements Comparator<dbReminder> {


    @Override
    public int compare(dbReminder o1, dbReminder o2) {
        Long l1 = o1.getEpochTime();
        Long l2 = o2.getEpochTime();
        int lComp = l1.compareTo(l2);

        if(lComp != 0){
            return lComp;
        }

        String s1 = o1.getPriority();
        String s2 = o2.getPriority();
        return s1.compareTo(s2);
    }




}
