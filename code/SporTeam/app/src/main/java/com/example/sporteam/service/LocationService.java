package com.example.sporteam.service;

import com.example.sporteam.R;
import com.example.sporteam.model.Location;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LocationService {

    public static List<Location> getLocations(){
        List<Location> locations = new ArrayList<>();

        Location location1 = new Location();
        location1.setName("Location1");
        location1.setAddress("address1");
        location1.setOwnerName("Pop Alexandru");
        location1.setPhoneNumber("735 634 266");
        location1.setAvailableFrom(LocalDateTime.now());
        location1.setAvailableTo(LocalDateTime.now());
        location1.setImage(R.drawable.location);

        Location location2 = new Location();
        location2.setName("Location2");
        location2.setAddress("address2");
        location2.setOwnerName("Pop Ioan");
        location2.setPhoneNumber("735 111 111");
        location2.setAvailableFrom(LocalDateTime.now());
        location2.setAvailableTo(LocalDateTime.now());
        location2.setImage(R.drawable.location);

        locations.add(location1);
        locations.add(location2);

        return locations;
    }

}
