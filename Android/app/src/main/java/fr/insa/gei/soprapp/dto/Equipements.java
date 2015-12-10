package fr.insa.gei.soprapp.dto;

/**
 * Created by Alexandre on 10/12/2015.
 */
public class Equipements {
    private int equipementId ;
    private String description ;

    public Equipements(int equipementId, String description) {
        this.equipementId = equipementId;
        this.description = description;
    }

    public int getEquipementId() {
        return equipementId;
    }

    public void setEquipementId(int equipementId) {
        this.equipementId = equipementId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


