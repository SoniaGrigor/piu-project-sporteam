package com.example.sporteam.service;

import com.example.sporteam.R;
import com.example.sporteam.model.Location;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LocationService {

    private static List<Location> locations = new ArrayList<>();
    private static LocationService instance = null;

    private LocationService(){

        Location location1 = new Location();
        location1.setName("Sală sportivă");
        location1.setAddress("Str. Cimitirului, nr.22");
        location1.setOwnerName("Pop Alexandru");
        location1.setPhoneNumber("0735 634 266");
        location1.setAvailableFrom(LocalDateTime.now());
        location1.setAvailableTo(LocalDateTime.now());
        location1.setImage(R.drawable.location);

        Location location2 = new Location();
        location2.setName("Teren tenis");
        location2.setAddress("Str. Trandafirul Negru, nr.16");
        location2.setOwnerName("Grigorescu Ioan Răzvan");
        location2.setPhoneNumber("0745 111 111");
        location2.setAvailableFrom(LocalDateTime.now());
        location2.setAvailableTo(LocalDateTime.now());
        location2.setImage(R.drawable.location);

        Location location3 = new Location();
        location3.setName("Teren fotbal");
        location3.setAddress("Str. Valea Primăverii, nr.102A");
        location3.setOwnerName("Mihălescu Ana");
        location3.setPhoneNumber("0755 111 478");
        location3.setAvailableFrom(LocalDateTime.now());
        location3.setAvailableTo(LocalDateTime.now());
        location3.setImage(R.drawable.location);

        locations.add(location1);
        locations.add(location2);
        locations.add(location3);

    }

    public static LocationService getInstance(){
        if(instance == null){
            instance = new LocationService();
        }

        return instance;
    }

    public List<Location> getLocations(){
        return locations;
    }

    public void addLocation(Location location){
        locations.add(location);
    }

    public static void removeLocation(Location location){
        Iterator<Location> iterator = locations.iterator();
        while(iterator.hasNext()){
            Location loc = iterator.next();
            if(loc.getName().equals(location.getName()) && loc.getAddress().equals(location.getAddress())){
                locations.remove(loc);
                break;
            }
        }
    }

}
