package com.example.sporteam.model;

public class MemberData {
    private String name;
    private int image;

    public MemberData(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public MemberData() {
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "MemberData{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}