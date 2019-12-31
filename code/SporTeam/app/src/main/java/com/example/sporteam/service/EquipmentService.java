package com.example.sporteam.service;

import com.example.sporteam.R;
import com.example.sporteam.model.Equipment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EquipmentService {

    private static List<Equipment> equipments = new ArrayList<>();
    private static EquipmentService instance = null;

    private EquipmentService(){

        Equipment eq1 = new Equipment();
        eq1.setName("Minge fotbal");
        eq1.setDescription("Minge de fotbal, diametru 30 cm.");
        eq1.setPrice(29.99);
        eq1.setImage(R.drawable.football);

        Equipment eq2 = new Equipment();
        eq2.setName("Minge baschet");
        eq2.setDescription("Minge baschet, diametru 25 cm.");
        eq2.setPrice(39.99);
        eq2.setImage(R.drawable.basketball);

        Equipment eq3 = new Equipment();
        eq3.setName("Unknown");
        eq3.setDescription("");
        eq3.setPrice(59.99);
        eq3.setImage(R.drawable.location);

        equipments.add(eq1);
        equipments.add(eq2);
        equipments.add(eq3);

    }

    public static EquipmentService getInstance(){
        if(instance == null){
            instance = new EquipmentService();
        }

        return instance;
    }

    public List<Equipment> getEquipments(){
        return equipments;
    }

    public void addEquipment(Equipment eq){
        equipments.add(eq);
    }

}
