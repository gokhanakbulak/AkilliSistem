package com.gokhan.akillisistem.Model;

public class User {
    private int id;
    private int ekipid;
    private String fname;
    private String lname;
    private String pass;
    private String email;
    public User(){}

    public User(int id, String fname, String lname,String email)
    {
    this.id=id;
    this.fname=fname;
    this.lname=lname;
    this.email=email;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;

    }

    public int getEkipid() {
        return ekipid;
    }

    public void setEkipid(int ekipid) {
        this.ekipid = ekipid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public  String toString()
    {
        return id  + fname  + lname  +  email;
    }

}
