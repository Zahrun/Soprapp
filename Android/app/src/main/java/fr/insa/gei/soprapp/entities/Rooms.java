/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.gei.soprapp.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Aroun
 */
@Entity
@Table(name = "Rooms")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rooms.findAll", query = "SELECT r FROM Rooms r"),
    @NamedQuery(name = "Rooms.findByRoomID", query = "SELECT r FROM Rooms r WHERE r.roomID = :roomID"),
    @NamedQuery(name = "Rooms.findByCapacity", query = "SELECT r FROM Rooms r WHERE r.capacity = :capacity")})
public class Rooms implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "roomID")
    private Integer roomID;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "number")
    private String number;
    @Basic(optional = false)
    @NotNull
    @Column(name = "capacity")
    private short capacity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomRef")
    private Collection<RoomEquipments> roomEquipmentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomRef")
    private Collection<Reservations> reservationsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomRef")
    private Collection<OldReservations> oldReservationsCollection;
    @JoinColumn(name = "siteRef", referencedColumnName = "siteID")
    @ManyToOne(optional = false)
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

    @XmlTransient
    public Collection<RoomEquipments> getRoomEquipmentsCollection() {
        return roomEquipmentsCollection;
    }

    public void setRoomEquipmentsCollection(Collection<RoomEquipments> roomEquipmentsCollection) {
        this.roomEquipmentsCollection = roomEquipmentsCollection;
    }

    @XmlTransient
    public Collection<Reservations> getReservationsCollection() {
        return reservationsCollection;
    }

    public void setReservationsCollection(Collection<Reservations> reservationsCollection) {
        this.reservationsCollection = reservationsCollection;
    }

    @XmlTransient
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
