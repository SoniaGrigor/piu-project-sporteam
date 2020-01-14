package com.example.sporteam.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Location implements Serializable {

    private String name;
    private String address;
    private String ownerName;
    private String ownerPhoneNumber;
    private LocalDate availableFrom;
    private LocalDate availableTo;
    private LocalTime startTime;
    private LocalTime endTime;
    private int image;
    private String reviews;
    private String rating;

    public Location() {}

    public Location(String name, String address, String ownerName, String ownerPhoneNumber, LocalDate availableFrom, LocalDate availableTo, LocalTime startTime, LocalTime endTime, int image, String reviews, String rating) {
        this.name = name;
        this.address = address;
        this.ownerName = ownerName;
        this.ownerPhoneNumber = ownerPhoneNumber;
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
        this.startTime = startTime;
        this.endTime = endTime;
        this.image = image;
        this.reviews = reviews;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhoneNumber() {
        return ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }

    public LocalDate getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(LocalDate availableFrom) {
        this.availableFrom = availableFrom;
    }

    public LocalDate getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(LocalDate availableTo) {
        this.availableTo = availableTo;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image){
        this.image = image;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
