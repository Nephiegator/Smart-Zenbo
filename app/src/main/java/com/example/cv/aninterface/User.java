package com.example.cv.aninterface;

public class User {
    public String email, pass;

    public User(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

}
