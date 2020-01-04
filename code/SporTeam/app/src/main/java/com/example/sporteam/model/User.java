package com.example.sporteam.model;

import java.io.Serializable;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class User implements Serializable {

    private String name;
    private String username;
    private String email;
    private String password;
    private String sex;
    private int age;
    private List<String> sports;
    private String barriers;
    private String description;
    private transient CircleImageView profileImage;

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

    public String getBarriers(){
        return this.barriers;
    }

    public void setBarriers(String barriers) {
        this.barriers = barriers;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CircleImageView getProfileImage(){
        return this.profileImage;
    }

    public void setProfileImage(CircleImageView profileImage){
        this.profileImage = profileImage;
    }
}
