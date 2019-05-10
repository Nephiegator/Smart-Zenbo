package com.example.cv.aninterface;

import android.telephony.mbms.StreamingServiceInfo;
import android.widget.TextView;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.Date;

@IgnoreExtraProperties
public class dbReminder implements Serializable {

    @Exclude private String id;

    private String title;
    private String desc;
    private String inLocation;
    private String ObjPerson;
    private String date;
    private String time;
    private String status;
    private String username;
    private String priority;
    private String repeat;
    private Long epochTime;


    public dbReminder(String title, String desc, String inLocation, String objPerson, String date, String time,
                      String status, String username, String priority, String repeat, Long epochTime) {

        this.title = title;
        this.desc = desc;
        this.inLocation = inLocation;
        this.ObjPerson = objPerson;
        this.time = time;
        this.date = date;
        this.status = status;
        this.username = username;
        this.priority = priority;
        this.repeat = repeat;
        this.epochTime = epochTime;
    }

    public dbReminder() {
        //empty constructor needed

    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getInLocation() {
        return inLocation;
    }

    public void setInLocation(String inLocation) {
        this.inLocation = inLocation;
    }

    public String getObjPerson() {
        return ObjPerson;
    }

    public void setObjPerson(String objPerson) {
        ObjPerson = objPerson;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPriority() {
        return priority;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public Long getEpochTime() {
        return epochTime;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
