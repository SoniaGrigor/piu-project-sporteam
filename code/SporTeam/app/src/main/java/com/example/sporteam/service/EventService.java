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
        events.add(new Event(R.drawable.football, "Fotbal",
                "duminică, ian. 12, 2020", "02:00 PM", "Baza Sportivă", "5.50 LEI", 10, 14, "Plată cu cardul", "Public"));

        events.add(new Event(R.drawable.pingpong, "Ping-Pong",
                "luni, feb. 13, 2020", "06:30 PM", "RevCourt", "10 LEI", 1, 3, "Plată cu cardul", "Public"));

        events.add(new Event(R.drawable.swimming, "Înot",
                "sâmbătă, ian. 11, 2020", "05:00 PM", "Baza Sportivă", "10 LEI", 1, 10, "Plată prin cash", "Public"));
        return events;
    }

    public void addNewEvent(Event newEvent){
        events.add(newEvent);
    }

    public List<Event> getEvents(){
        return this.events; }
}
