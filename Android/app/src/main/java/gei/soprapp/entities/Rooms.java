/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gei.soprapp.entities;

import java.io.Serializable;
import java.util.Collection;


/**
 *
 * @author Aroun
 */
public class Rooms implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer roomID;
    private String number;
    private short capacity;
    private Collection<RoomEquipments> roomEquipmentsCollection;
    private Collection<Reservations> reservationsCollection;
    private Collection<OldReservations> oldReservationsCollection;
    private Sites siteRef;

    public Rooms() {
    }

    public Rooms(Integer roomID) {
        this.roomID = roomID;
    }

    public Rooms(Integer roomID, String number, short capacity) {
        this.roomID = roomID;
        this.number = number;
        this.capacity = capacity;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public short getCapacity() {
        return capacity;
    }

    public void setCapacity(short capacity) {
        this.capacity = capacity;
    }

    public Collection<RoomEquipments> getRoomEquipmentsCollection() {
        return roomEquipmentsCollection;
    }

    public void setRoomEquipmentsCollection(Collection<RoomEquipments> roomEquipmentsCollection) {
        this.roomEquipmentsCollection = roomEquipmentsCollection;
    }

    public Collection<Reservations> getReservationsCollection() {
        return reservationsCollection;
    }

    public void setReservationsCollection(Collection<Reservations> reservationsCollection) {
        this.reservationsCollection = reservationsCollection;
    }

    public Collection<OldReservations> getOldReservationsCollection() {
        return oldReservationsCollection;
    }

    public void setOldReservationsCollection(Collection<OldReservations> oldReservationsCollection) {
        this.oldReservationsCollection = oldReservationsCollection;
    }

    public Sites getSiteRef() {
        return siteRef;
    }

    public void setSiteRef(Sites siteRef) {
        this.siteRef = siteRef;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomID != null ? roomID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rooms)) {
            return false;
        }
        Rooms other = (Rooms) object;
        if ((this.roomID == null && other.roomID != null) || (this.roomID != null && !this.roomID.equals(other.roomID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Rooms[ roomID=" + roomID + " ]";
    }
    
}
