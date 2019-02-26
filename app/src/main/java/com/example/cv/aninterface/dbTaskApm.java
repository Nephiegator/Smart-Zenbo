package com.example.cv.aninterface;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class dbTaskApm extends dbReminder implements Serializable {

    @Exclude
    private String id;

    private String title;
    private String desc;
    private String inLocation;
    private String ObjPerson;

    public dbTaskApm(String title, String desc, String inLocation, String objPerson) {
        this.title = title;
        this.desc = desc;
        this.inLocation = inLocation;
        this.ObjPerson = objPerson;
    }

    public dbTaskApm() {
        //empty constructor needed

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

}
