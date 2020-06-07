package com.gokhan.akillisistem.Model;

public class UserToplanti {
    private int id;
    private String name;
    private String lname;


    public UserToplanti(){}

    public UserToplanti(int id, String name)
    {
        this.setName(name);
        this.setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    @Override
    public String toString() {
        return name +"  " + lname;
    }


}
