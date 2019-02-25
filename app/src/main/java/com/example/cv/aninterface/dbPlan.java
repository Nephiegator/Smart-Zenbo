package com.example.cv.aninterface;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@IgnoreExtraProperties
public class dbPlan implements Serializable {

    @Exclude
    private String id;

    private String title;
    private Date date;

    public dbPlan(String title, Date date) {
        this.title = title;
        this.date = date;
    }

    public dbPlan() {
        //empty construct needed
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
