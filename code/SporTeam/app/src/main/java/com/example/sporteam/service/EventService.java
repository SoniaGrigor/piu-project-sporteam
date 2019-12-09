package com.example.sporteam.service;

import com.example.sporteam.R;
import com.example.sporteam.model.Event;

import java.util.ArrayList;
import java.util.List;

public class EventService {

    public List<Event> resetEvents(){
        return getEvents();
    }

    public List<Event> getEvents(){
        List<Event> events = new ArrayList<>();

        events.add(new Event(R.drawable.football, "Football",
                "Dum, 12 Oct", "14:00", "Baza Sportiva"));

        events.add(new Event(R.drawable.pingpong, "Tenis de masa",
                "Luni, 13 Oct", "18:30", "RevCourt"));

        return events;
    }
}
