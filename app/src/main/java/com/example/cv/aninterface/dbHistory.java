package com.example.cv.aninterface;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class dbHistory implements Serializable {

    @Exclude
    private String id;
    private String title;
    private String desc;
    private String inLocation;
    private String ObjPerson;
    private String time;
    private String verify;

    public dbHistory(String title, String desc, String inLocation, String objPerson, String time, String verify) {
        this.title = title;
        this.desc = desc;
        this.inLocation = inLocation;
        this.ObjPerson = objPerson;
        this.time = time;
        this.verify = verify;

    }

    public dbHistory(){}

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }
}
