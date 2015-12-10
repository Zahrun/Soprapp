package fr.insa.gei.soprapp.dto;

import java.util.ArrayList;

/**
 * Created by Alexandre on 10/12/2015.
 */
public class roomEquipements {

    private ArrayList<Equipements> listEquipements ;

    public roomEquipements() {
        this.listEquipements = new ArrayList<>();
    }

    public ArrayList<Equipements> getListEquipements() {
        return listEquipements;
    }

    public void setListEquipements(ArrayList<Equipements> listEquipements) {
        this.listEquipements = listEquipements;
    }
}
