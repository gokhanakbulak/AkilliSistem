package com.gokhan.akillisistem.Model;

public class UserEkip {
    private  String sifre;
    private int id;
    private int ekipid;
    private String fname;
    private String lname;
    private String email;
    private String pass;
    private String ekipname;
    private String puan;
    public UserEkip(){}
    public UserEkip(int id, String fname, String lname, String email, String ekipname,String sifre,String puan) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.ekipname = ekipname;
        this.sifre=sifre;
        this.puan=puan;
    }
    public UserEkip(int id, String fname, String lname, String email,String sifre, String ekipname) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.ekipname = ekipname;
        this.sifre =sifre;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEkipname() {
        return ekipname;
    }

    public void setEkipname(String ekipname) {
        this.ekipname = ekipname;
    }
    public String getSifre() {
        return sifre;
    }
    public String getPuan() {
        return puan;
    }

    public void setPuan(String puan) {
        this.puan = puan;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
    public  String toString()
    {
        return id + fname + lname+email+ekipname+sifre+puan;
    }



}
