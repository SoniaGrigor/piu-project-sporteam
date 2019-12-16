package com.example.sporteam.service;

import com.example.sporteam.model.MemberData;
import com.example.sporteam.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageService {
    public static List<Message> messages = new ArrayList<>();

    public static ArrayList<Message> initMessages() {
        messages.add(new Message("Salut", new MemberData("Dani","red"), false));
        messages.add(new Message("Cf", new MemberData("Dani","red"), false));
        messages.add(new Message("Imi fac proiectul la PIU", new MemberData("Dani","red"), true));
        messages.add(new Message("Si tu?", new MemberData("Dani","red"), false));
        messages.add(new Message(":)))", new MemberData("Dani","red"), true));
        return (ArrayList<Message>) messages;
    }
}
