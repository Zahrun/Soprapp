/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.gei.soprapp.entities;

import java.io.Serializable;

/**
 *
 * @author Aroun
 */
public class RoomEquipments implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer roomEquipmentID;
    private Rooms roomRef;
    private Equipments equipmentRef;

    public RoomEquipments() {
    }

    public RoomEquipments(Integer roomEquipmentID) {
        this.roomEquipmentID = roomEquipmentID;
    }

    public Integer getRoomEquipmentID() {
        return roomEquipmentID;
    }

    public void setRoomEquipmentID(Integer roomEquipmentID) {
        this.roomEquipmentID = roomEquipmentID;
    }

    public Rooms getRoomRef() {
        return roomRef;
    }

    public void setRoomRef(Rooms roomRef) {
        this.roomRef = roomRef;
    }

    public Equipments getEquipmentRef() {
        return equipmentRef;
    }

    public void setEquipmentRef(Equipments equipmentRef) {
        this.equipmentRef = equipmentRef;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomEquipmentID != null ? roomEquipmentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoomEquipments)) {
            return false;
        }
        RoomEquipments other = (RoomEquipments) object;
        if ((this.roomEquipmentID == null && other.roomEquipmentID != null) || (this.roomEquipmentID != null && !this.roomEquipmentID.equals(other.roomEquipmentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RoomEquipments[ roomEquipmentID=" + roomEquipmentID + " ]";
    }
    
}
