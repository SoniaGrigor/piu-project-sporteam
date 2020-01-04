package com.example.sporteam.service;

import com.example.sporteam.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private List<User> users;
    private static UserService instance = new UserService();
    private UserService(){
        users = initializeUsers();
    }

    public static UserService getInstance(){
        return instance;
    }

    private List<User> initializeUsers(){
        List<User> users = new ArrayList<>();
        users.add(new User("Grigor Sonia", "soni_valoare", "soni65@yahoo.com", "nuitispunparola"));
        users.add(new User("Smecherul 22", "valoare", "gigibecali65@gmail.com", "suntBecali"));

        return users;
    }

    public void addNewUser(User newUser){
        users.add(newUser);
    }

    public List<User> getUsers(){
        return this.users;
    }
}
