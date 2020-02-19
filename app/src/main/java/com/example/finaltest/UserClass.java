package com.example.finaltest;

public class UserClass {

    int id;
    String fname, lname, address;
    double pnumber;

    public UserClass(int id, String fname, String lname, String address, double pnumber) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.pnumber = pnumber;
    }

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getAddress() {
        return address;
    }

    public double getPnumber() {
        return pnumber;
    }
}
