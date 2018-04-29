package com.jama.maestracanuta10.model;

public class Misconduct {

    String id;

    String name;

    String type;


    public Misconduct(String misconductId, String name, String type) {
        this.id = misconductId;
        this.name = name;
        this.type = type;
    }

    public Misconduct() {
    }

    public String getId() {
        return id;
    }

    public void setId(String misconductId) {
        this.id = misconductId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
