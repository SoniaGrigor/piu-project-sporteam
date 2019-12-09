package com.example.sporteam.model;

import android.widget.ImageView;

public class Event {
    private Integer eventImage;
    private String eventTitle;
    private String eventDate;
    private String eventTime;
    private String eventLocation;

    public Event(Integer eventImage, String eventTitle, String eventDate,
    String eventTime, String eventLocation){
        this.eventImage = eventImage;
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventLocation = eventLocation;
    }

    public int getEventImage(){
        return this.eventImage;
    }

    public void setEventImage(Integer eventImage){
        this.eventImage = eventImage;
    }

    public String getEventTitle(){
        return this.eventTitle;
    }

    public void setEventTitle(String eventTitle){
        this.eventTitle = eventTitle;
    }

    public String getEventDate(){
        return this.eventDate;
    }

    public void setEventDate(){
        this.eventDate = eventDate;
    }

    public String getEventTime(){
        return this.eventTime;
    }

    public void setEventTime(){
        this.eventTime = eventTime;
    }

    public String getEventLocation(){
        return this.eventLocation;
    }

    public void setEventLocation(){
        this.eventLocation = eventLocation;
    }
}
