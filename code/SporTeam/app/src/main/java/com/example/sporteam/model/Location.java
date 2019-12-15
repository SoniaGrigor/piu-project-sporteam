package com.example.sporteam.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Location implements Serializable {

    private String name;
    private String address;
    private String ownerName;
    private String ownerPhoneNumber;
    private LocalDateTime availableFrom;
    private LocalDateTime availableTo;
    private int image;

    public Location() {}

    public Location(String name, String address, String ownerName, String ownerPhoneNumber, LocalDateTime availableFrom, LocalDateTime availableTo, int image) {
        this.name = name;
        this.address = address;
        this.ownerName = ownerName;
        this.ownerPhoneNumber = ownerPhoneNumber;
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
        this.image = image;
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

    public void setPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }

    public LocalDateTime getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(LocalDateTime availableFrom) {
        this.availableFrom = availableFrom;
    }

    public LocalDateTime getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(LocalDateTime availableTo) {
        this.availableTo = availableTo;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
