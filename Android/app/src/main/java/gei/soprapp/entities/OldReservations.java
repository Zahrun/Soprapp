/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gei.soprapp.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Aroun
 */
public class OldReservations implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer oldReservationID;
    private Date start;
    private Date end;
    private Collection<OldInvitedUsers> oldInvitedUsersCollection;
    private Rooms roomRef;
    private Users ownerRef;

    public OldReservations() {
    }

    public OldReservations(Integer oldReservationID) {
        this.oldReservationID = oldReservationID;
    }

    public OldReservations(Integer oldReservationID, Date start, Date end) {
        this.oldReservationID = oldReservationID;
        this.start = start;
        this.end = end;
    }

    public Integer getOldReservationID() {
        return oldReservationID;
    }

    public void setOldReservationID(Integer oldReservationID) {
        this.oldReservationID = oldReservationID;
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

    public Collection<OldInvitedUsers> getOldInvitedUsersCollection() {
        return oldInvitedUsersCollection;
    }

    public void setOldInvitedUsersCollection(Collection<OldInvitedUsers> oldInvitedUsersCollection) {
        this.oldInvitedUsersCollection = oldInvitedUsersCollection;
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
        hash += (oldReservationID != null ? oldReservationID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OldReservations)) {
            return false;
        }
        OldReservations other = (OldReservations) object;
        if ((this.oldReservationID == null && other.oldReservationID != null) || (this.oldReservationID != null && !this.oldReservationID.equals(other.oldReservationID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.OldReservations[ oldReservationID=" + oldReservationID + " ]";
    }
    
}
