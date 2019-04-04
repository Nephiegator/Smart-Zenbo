package com.example.cv.aninterface;

public class dbUserInformation {
    public String fname;
    public String lname;
    public String email;
    public String phone;

    public dbUserInformation(){

    }
    public dbUserInformation(String fname, String lname, String email, String phone){
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
    }

    public String getfname() { return fname; }

    public void setfname(String fname) { this.fname = fname; }

    public String getlname() { return lname; }

    public void setlname(String lname) { this.fname = lname; }

    public String getemail() { return email; }

    public void setemail(String email) { this.email = email; }

    public String getphone() { return phone; }

    public void setphone(String phone) { this.phone = phone; }

}
