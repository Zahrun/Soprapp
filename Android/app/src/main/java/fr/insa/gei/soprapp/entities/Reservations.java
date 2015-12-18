/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.gei.soprapp.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Aroun
 */
public class Reservations implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer reservationID;
    @NotNull
    private Date start;
    @NotNull
    private Date end;
    private Collection<InvitedUsers> invitedUsersCollection;
    private Rooms roomRef;
    private Users ownerRef;

    public Reservations() {
    }

    public Reservations(Integer reservationID) {
        this.reservationID = reservationID;
    }

    public Reservations(Integer reservationID, Date start, Date end) {
        this.reservationID = reservationID;
        this.start = start;
        this.end = end;
    }

    public Integer getReservationID() {
        return reservationID;
    }

    public void setReservationID(Integer reservationID) {
        this.reservationID = reservationID;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Collection<InvitedUsers> getInvitedUsersCollection() {
        return invitedUsersCollection;
    }

    public void setInvitedUsersCollection(Collection<InvitedUsers> invitedUsersCollection) {
        this.invitedUsersCollection = invitedUsersCollection;
    }

    public Rooms getRoomRef() {
        return roomRef;
    }

    public void setRoomRef(Rooms roomRef) {
        this.roomRef = roomRef;
    }

    public Users getOwnerRef() {
        return ownerRef;
    }

    public void setOwnerRef(Users ownerRef) {
        this.ownerRef = ownerRef;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservationID != null ? reservationID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservations)) {
            return false;
        }
        Reservations other = (Reservations) object;
        if ((this.reservationID == null && other.reservationID != null) || (this.reservationID != null && !this.reservationID.equals(other.reservationID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Reservations[ reservationID=" + reservationID + " ]";
    }
    
}
