package com.gokhan.akillisistem.Model;

public class Mesaj {
    private String baslik;
    private String icerik;
    private int gId;
    private int id;

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    private String uName;
    public Mesaj(String baslik,String icerik,int gId){
        this.baslik=baslik;
        this.icerik=icerik;
        this.gId=gId;
    }
    public Mesaj(String baslik, String icerik, String uName,int gId) {
        this.baslik=baslik;
        this.icerik=icerik;
        this.uName=uName;
        this.gId=gId;

    }


    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    public int getgId() {
        return gId;
    }

    public void setgId(int gId) {
        this.gId = gId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String toString(){
        return baslik + icerik + uName + id + gId;
    }
}
