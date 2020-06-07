package com.gokhan.akillisistem.Model;
/*String gName, String gDesc, String zLev, String tTime, String skils, int userId */
public class Gorev {



    private String gTarih;
    private int id;
    private String gName;
    private String gDesc;
    private String zLev;
    private String tTime;
    private String skills;
    private int userId;
    private String userName;

    public Gorev(int id, String gName, String gDesc, String zLev, String sure, String skills, String userName) {
        this.id=id;
        this.gName=gName;
        this.gDesc=gDesc;
        this.zLev=zLev;
        this.tTime=sure;
        this.skills=skills;
        this.userName=userName;

    }

    public Gorev(int id, String gTarih) {
        this.id=id;
        this.gTarih=gTarih;

    }
    public Gorev(int id, String gTarih,String zLev,String tTime,String gName) {
        this.id=id;
        this.gTarih=gTarih;
        this.tTime=tTime;
        this.zLev=zLev;
        this.gName=gName;

    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public String getgDesc() {
        return gDesc;
    }

    public void setgDesc(String gDesc) {
        this.gDesc = gDesc;
    }

    public String getzLev() {
        return zLev;
    }

    public void setzLev(String zLev) {
        this.zLev = zLev;
    }

    public String gettTime() {
        return tTime;
    }

    public void settTime(String tTime) {
        this.tTime = tTime;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getgTarih() {
        return gTarih;
    }
    public void setgTarih(String gTarih){
     this.gTarih=gTarih;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Override
    public String toString(){
        return id + gName + gDesc +zLev + tTime + skills + userName;
    }
}
