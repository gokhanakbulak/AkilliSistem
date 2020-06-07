package com.gokhan.akillisistem.Model;

public class Ekip {
    private int id;
    private String name;

    public Ekip() {
    }
    public Ekip(String name)
    {
        this.setName(name);
    }
    public Ekip(int id, String name)
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

    @Override
    public String toString() {
        return name;
    }
}
