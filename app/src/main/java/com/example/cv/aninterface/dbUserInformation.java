package com.example.cv.aninterface;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class dbUserInformation implements Serializable {

    @Exclude
    private String id;
    public String fname;
    public String lname;
    public String email;
    public String phone;
    public String username;
    public String bdate;

    public dbUserInformation(){

    }

    public dbUserInformation(String fname, String lname, String email, String phone, String username, String bdate){
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.bdate = bdate;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getfname() { return fname; }

    public void setfname(String fname) { this.fname = fname; }

    public String getlname() { return lname; }

    public void setlname(String lname) { this.lname = lname; }

    public String getemail() { return email; }

    public void setemail(String email) { this.email = email; }

    public String getphone() { return phone; }

    public void setphone(String phone) { this.phone = phone; }

    public String getUsername() {
        return username;
    }

    public String getBdate() {
        return bdate;
    }

    public String getId() { return id; }

}
