package com.example.cv.aninterface;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class dbTaskMed implements Serializable {

    @Exclude
    private String id;

    private String title;
    private String desc;
    private String inLocation;
    private String ObjPerson;

    public dbTaskMed(String title, String desc, String inLocation, String objPerson) {
        this.title = title;
        this.desc = desc;
        this.inLocation = inLocation;
        this.ObjPerson = objPerson;
    }
}
