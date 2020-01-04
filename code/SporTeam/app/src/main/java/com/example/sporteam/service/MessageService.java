package com.example.sporteam.service;

import com.example.sporteam.R;
import com.example.sporteam.model.MemberData;
import com.example.sporteam.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageService {
    public static List<Message> messages = new ArrayList<>();
    public static List<MemberData> memberData = new ArrayList<>();

    public static ArrayList<Message> initMessages() {
        messages.add(new Message("Salut", new MemberData("Dani", R.drawable.avatar), false));
        messages.add(new Message("Cf", new MemberData("Dani",R.drawable.avatar), false));
        messages.add(new Message("Imi fac proiectul la PIU", new MemberData("Dani",R.drawable.avatar), true));
        messages.add(new Message("Si tu?", new MemberData("Dani",R.drawable.avatar), false));
        messages.add(new Message(":)))", new MemberData("Dani",R.drawable.avatar), true));
        return (ArrayList<Message>) messages;
    }

    public static ArrayList<MemberData> initConversations() {
        memberData.add(new MemberData("Dani", R.drawable.avatar));
        memberData.add(new MemberData("Sonia", R.drawable.avatar_girl));
        memberData.add(new MemberData("Maria", R.drawable.avatar_girl));
        memberData.add(new MemberData("Robert", R.drawable.avatar));
        return (ArrayList<MemberData>) memberData;
    }
}
