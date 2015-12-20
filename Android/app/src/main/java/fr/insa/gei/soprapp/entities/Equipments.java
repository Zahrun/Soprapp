/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.gei.soprapp.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.validation.constraints.Size;

/**
 *
 * @author Aroun
 */
public class Equipments implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer equipmentID;
    @Size(max = 65535)
    private String description;
    private Collection<RoomEquipments> roomEquipmentsCollection;

    public Equipments() {
    }

    public Equipments(Integer equipmentID) {
        this.equipmentID = equipmentID;
    }

    public Integer getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(Integer equipmentID) {
        this.equipmentID = equipmentID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<RoomEquipments> getRoomEquipmentsCollection() {
        return roomEquipmentsCollection;
    }

    public void setRoomEquipmentsCollection(Collection<RoomEquipments> roomEquipmentsCollection) {
        this.roomEquipmentsCollection = roomEquipmentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (equipmentID != null ? equipmentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipments)) {
            return false;
        }
        Equipments other = (Equipments) object;
        if ((this.equipmentID == null && other.equipmentID != null) || (this.equipmentID != null && !this.equipmentID.equals(other.equipmentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Equipments[ equipmentID=" + equipmentID + " ]";
    }
    
}
