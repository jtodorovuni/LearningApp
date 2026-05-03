package com.example.learningapp;

public class Exercise {
    private long id;
    private String name;
    private String description;

    public Exercise(long id, String name, String desc){
        this.name = name;
        this.description = desc;
        this.id = id;
    }

    public Exercise() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
