package com.example.sporteam.model;


public class Event {
    private Integer eventImage;
    private String eventSport;
    private String eventDate;
    private String eventTime;
    private String eventLocation;
    private String eventPrice; // we don't need to do any calculation with the price so it can be a String to avoid conversions
    private int eventMinimumPersonNumber;
    private int eventMmaximumPersonNumber;
    private String eventPaymentMethod;
    private String eventType;

    public Event(Integer eventImage, String eventSport, String eventDate,
                 String eventTime, String eventLocation, String eventPrice, int eventMinimumPersonNumber,
                 int eventMaximumPersonNumber, String eventPaymentMethod,
                 String eventType){
        this.eventImage = eventImage;
        this.eventSport = eventSport;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventLocation = eventLocation;
        this.eventPrice = eventPrice;
        this.eventMinimumPersonNumber = eventMinimumPersonNumber;
        this.eventMmaximumPersonNumber = eventMaximumPersonNumber;
        this.eventPaymentMethod = eventPaymentMethod;
        this.eventType = eventType;
    }

    public int getEventImage(){
        return this.eventImage;
    }

    public void setEventImage(Integer eventImage){
        this.eventImage = eventImage;
    }

    public String getEventSport(){
        return this.eventSport;
    }

    public void setEventSport(String eventSport){
        this.eventSport = eventSport;
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

    public String getEventPrice() {
        return eventPrice;
    }

    public void setEventPrice(String eventPrice) {
        this.eventPrice = eventPrice;
    }

    public int getEventMinimumPersonNumber() {
        return eventMinimumPersonNumber;
    }

    public void setEventMinimumPersonNumber(int eventMinimumPersonNumber) {
        this.eventMinimumPersonNumber = eventMinimumPersonNumber;
    }

    public int getEventMmaximumPersonNumber() {
        return eventMmaximumPersonNumber;
    }

    public void setEventMmaximumPersonNumber(int eventMmaximumPersonNumber) {
        this.eventMmaximumPersonNumber = eventMmaximumPersonNumber;
    }

    public String getEventPaymentMethod() {
        return eventPaymentMethod;
    }

    public void setEventPaymentMethod(String eventPaymentMethod) {
        this.eventPaymentMethod = eventPaymentMethod;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

}
