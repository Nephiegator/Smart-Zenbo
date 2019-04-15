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
    private String period;
    private String pill;

    public dbTaskMed(String title, String desc, String inLocation, String objPerson, String period, String pill) {
        this.title = title;
        this.desc = desc;
        this.inLocation = inLocation;
        this.ObjPerson = objPerson;
        this.period = period;
        this.pill = pill;
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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPill() {
        return pill;
    }

    public void setPill(String pill) {
        this.pill = pill;
    }
}
