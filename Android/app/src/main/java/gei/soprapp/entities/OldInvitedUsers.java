/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gei.soprapp.entities;

import java.io.Serializable;

/**
 *
 * @author Aroun
 */
public class OldInvitedUsers implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer oldInvitedUserID;
    private OldReservations oldReservationRef;
    private Users userRef;

    public OldInvitedUsers() {
    }

    public OldInvitedUsers(Integer oldInvitedUserID) {
        this.oldInvitedUserID = oldInvitedUserID;
    }

    public Integer getOldInvitedUserID() {
        return oldInvitedUserID;
    }

    public void setOldInvitedUserID(Integer oldInvitedUserID) {
        this.oldInvitedUserID = oldInvitedUserID;
    }

    public OldReservations getOldReservationRef() {
        return oldReservationRef;
    }

    public void setOldReservationRef(OldReservations oldReservationRef) {
        this.oldReservationRef = oldReservationRef;
    }

    public Users getUserRef() {
        return userRef;
    }

    public void setUserRef(Users userRef) {
        this.userRef = userRef;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (oldInvitedUserID != null ? oldInvitedUserID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OldInvitedUsers)) {
            return false;
        }
        OldInvitedUsers other = (OldInvitedUsers) object;
        if ((this.oldInvitedUserID == null && other.oldInvitedUserID != null) || (this.oldInvitedUserID != null && !this.oldInvitedUserID.equals(other.oldInvitedUserID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.OldInvitedUsers[ oldInvitedUserID=" + oldInvitedUserID + " ]";
    }
    
}
