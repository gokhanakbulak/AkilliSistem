package com.gokhan.akillisistem.Model;

public class Toplanti {
    private String tName;
    private String tDate;
    public Toplanti(String tName,String tDate){
        this.tDate=tDate;
        this.tName=tName;
    }
    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettDate() {
        return tDate;
    }

    public void settDate(String tDate) {
        this.tDate = tDate;
    }
    public String toString(){return tName + tDate;
    }
}
