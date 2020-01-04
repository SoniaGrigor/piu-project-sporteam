package com.example.sporteam.service;

import com.example.sporteam.R;
import com.example.sporteam.model.Event;
import java.util.ArrayList;
import java.util.List;


public class EventService {

    private List<Event> events;
    private static EventService instance = null;

    private EventService(){
        this.events = initializeEvents();
    }

    public static EventService getInstance() {
        if(instance == null){
            instance = new EventService();
        }

        return instance;
    }


    private List<Event> initializeEvents(){
        List<Event> events = new ArrayList<>();
        events.add(new Event(R.drawable.football, "Football",
                "Dum, 12 Oct", "14:00", "Baza Sportiva"));

        events.add(new Event(R.drawable.pingpong, "Tenis de masa",
                "Luni, 13 Oct", "18:30", "RevCourt"));
        return events;
    }

    public void addNewEvent(Event newEvent){
        events.add(newEvent);
    }

    public List<Event> getEvents(){
        return this.events; }
}
