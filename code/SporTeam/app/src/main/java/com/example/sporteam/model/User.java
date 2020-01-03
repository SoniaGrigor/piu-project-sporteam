package com.example.sporteam.model;

import java.util.List;

public class User {

    private String name;
    private String username;
    private String email;
    private String password;
    private String sex;
    private int age;
    private List<String> sports;

    public User(String name, String username, String email, String password){
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getSex(){
        return this.sex;
    }

    public void setSex(String sex){
        this.sex = sex;
    }

    public int getAge(){
        return this.age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public List<String> getSports(){
        return this.sports;
    }

    public void setSports(List<String> sports){
        this.sports = sports;
    }
}
