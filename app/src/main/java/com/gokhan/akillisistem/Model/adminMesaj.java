package com.gokhan.akillisistem.Model;

public class adminMesaj {
    private String baslik;
    private String icerik;
    private int gId;
    private int id;
    private int alanId;
    public adminMesaj(String baslik,String icerik){
        this.icerik=icerik;
        this.baslik=baslik;

    }
    public adminMesaj(String baslik,String icerik,int gId,int alanId){
        this.alanId=alanId;
        this.baslik=baslik;
        this.icerik=icerik;
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

    public int getAlanId() {
        return alanId;
    }

    public void setAlanId(int alanId) {
        this.alanId = alanId;
    }
    public String toString(){
        return baslik + icerik + alanId + id + gId;
    }
}
