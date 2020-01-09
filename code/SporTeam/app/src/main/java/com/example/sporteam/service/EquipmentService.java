package com.example.sporteam.service;

import com.example.sporteam.R;
import com.example.sporteam.model.Equipment;

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
        eq1.setStock(5);

        Equipment eq2 = new Equipment();
        eq2.setName("Minge baschet");
        eq2.setDescription("Minge baschet, diametru 25 cm.");
        eq2.setPrice(39.99);
        eq2.setImage(R.drawable.basketball);
        eq2.setStock(10);

        Equipment eq3 = new Equipment();
        eq3.setName("Minge handbal");
        eq3.setDescription("Minge handbal, diametru 17cm, greutate 400g.");
        eq3.setPrice(24.99);
        eq3.setImage(R.drawable.handball_eq);
        eq3.setStock(40);

        Equipment eq4 = new Equipment();
        eq4.setName("Sac de box");
        eq4.setDescription("Sac pentru box: înălțime 120cm, greutate 40kg, diametru 35cm. Lanțul și cârligul sunt incluse.");
        eq4.setPrice(100);
        eq4.setImage(R.drawable.boxing_bag);
        eq4.setStock(10);

        Equipment eq5 = new Equipment();
        eq5.setName("Palete ping pong");
        eq5.setDescription("Palete tenis de masă/ping pong. Cadou: 3 mingi.");
        eq5.setPrice(19.99);
        eq5.setImage(R.drawable.ping_pong_eq);
        eq5.setStock(30);

        equipments.add(eq1);
        equipments.add(eq2);
        equipments.add(eq3);
        equipments.add(eq4);
        equipments.add(eq5);

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

    public int checkStock(Equipment eq){
        for(Equipment e: equipments){
            if(e.getName().equals(eq.getName())){
                return e.getStock();
            }
        }
        return -1;
    }

    public void updateStock(int quantity, Equipment eq){
        for(Equipment e: equipments){
            if(e.getName().equals(eq.getName())){
                e.setStock(e.getStock() - quantity);
                return;
            }
        }
    }

}
