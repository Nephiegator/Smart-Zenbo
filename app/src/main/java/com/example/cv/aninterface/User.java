package com.example.cv.aninterface;

public class User {
    public String email, pass, fname, lname;

    public User(String email, String pass, String fname, String lname) {
        this.email = email;
        this.pass = pass;
        this.fname = fname;
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }
}
