package com.example.sporteam.service;

import com.example.sporteam.R;
import com.example.sporteam.model.MemberData;
import com.example.sporteam.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageService {
    public static List<Message> messages = new ArrayList<>();

    public static ArrayList<Message> initMessages() {
        messages.add(new Message("Salut!", new MemberData("Dani", R.drawable.avatar), false));
        messages.add(new Message("Ce faci?", new MemberData("Dani",R.drawable.avatar), false));
        messages.add(new Message("Lucrez la proiectul pentru PIU.", new MemberData("Dani",R.drawable.avatar), true));
        messages.add(new Message("Și tu?", new MemberData("Dani",R.drawable.avatar), false));
        messages.add(new Message("Da. Tu ai terminat?", new MemberData("Dani",R.drawable.avatar), true));
        return (ArrayList<Message>) messages;
    }

    public static ArrayList<MemberData> initConversations() {
        List<MemberData> memberData = new ArrayList<>();
        memberData.add(new MemberData("Dani", R.drawable.avatar));
        memberData.add(new MemberData("Sonia", R.drawable.avatar_girl));
        memberData.add(new MemberData("Maria", R.drawable.avatar_girl));
        memberData.add(new MemberData("Robert", R.drawable.avatar));
        return (ArrayList<MemberData>) memberData;
    }
}
