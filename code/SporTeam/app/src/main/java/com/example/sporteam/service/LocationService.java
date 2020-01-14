package com.example.sporteam.service;

import com.example.sporteam.R;
import com.example.sporteam.model.Location;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
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
        location1.setOwnerPhoneNumber("0735 634 266");
        location1.setAvailableFrom(LocalDate.of(2019, 12, 15));
        location1.setAvailableTo(LocalDate.of(2020, 1, 30));
        location1.setStartTime(LocalTime.of(0, 0));
        location1.setEndTime(LocalTime.of(1, 0));
        location1.setImage(R.drawable.location);
        location1.setRating("3.5/5");

        Location location2 = new Location();
        location2.setName("Teren tenis");
        location2.setAddress("Str. Trandafirul Negru, nr.16");
        location2.setOwnerName("Grigorescu Ioan Răzvan");
        location2.setOwnerPhoneNumber("0745 111 111");
        location2.setAvailableFrom(LocalDate.of(2020, 1, 10));
        location2.setAvailableTo(LocalDate.of(2020, 2, 10));
        location2.setStartTime(LocalTime.of(8, 0));
        location2.setEndTime(LocalTime.of(20, 0));
        location2.setImage(R.drawable.location);
        location2.setRating("4/5");

        Location location3 = new Location();
        location3.setName("Sală box");
        location3.setAddress("Str. Valea Primăverii, nr.102A");
        location3.setOwnerName("Mihălescu Ana");
        location3.setOwnerPhoneNumber("0755 111 478");
        location3.setAvailableFrom(LocalDate.of(2019, 12, 25));
        location3.setAvailableTo(LocalDate.of(2020, 2, 10));
        location3.setStartTime(LocalTime.of(6, 0));
        location3.setEndTime(LocalTime.of(22, 0));
        location3.setImage(R.drawable.location);
        location3.setRating("4.5/5");

        Location location4 = new Location();
        location4.setName("Teren fotbal");
        location4.setAddress("Str. Valea Mare, nr.10A");
        location4.setOwnerName("Popescu Cristian");
        location4.setOwnerPhoneNumber("0755 235 479");
        location4.setAvailableFrom(LocalDate.of(2019, 11, 19));
        location4.setAvailableTo(LocalDate.of(2020, 4, 20));
        location4.setStartTime(LocalTime.of(6, 0));
        location4.setEndTime(LocalTime.of(23, 0));
        location4.setImage(R.drawable.location);
        location4.setRating("5/5");

        locations.add(location1);
        locations.add(location2);
        locations.add(location3);
        locations.add(location4);

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

    public void addLocationReview(Location loc, String obs){
        Iterator<Location> iterator = locations.iterator();
        while(iterator.hasNext()){
            Location location = iterator.next();
            if(loc.getName().equals(location.getName()) && loc.getAddress().equals(location.getAddress())){
                location.setReviews(obs);
                break;
            }
        }
    }

    public void addLocationRating(Location loc, double stars){
        Iterator<Location> iterator = locations.iterator();
        while(iterator.hasNext()){
            Location location = iterator.next();
            if(loc.getName().equals(location.getName()) && loc.getAddress().equals(location.getAddress())){
                DecimalFormat df = new DecimalFormat("#.#");
                stars = Double.valueOf(df.format(stars));
                location.setRating(stars + "/5");
                break;
            }
        }
    }

}
