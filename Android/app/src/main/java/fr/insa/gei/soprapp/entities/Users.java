/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.gei.soprapp.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Aroun
 */
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer userID;
    @NotNull
    @Size(min = 1, max = 50)
    private String name;
    @NotNull
    @Size(min = 1, max = 50)
    private String surname;
    @NotNull
    @Size(min = 1, max = 50)
    private String mailAddress;
    @NotNull
    @Size(min = 1, max = 65535)
    private String password;
    @NotNull
    private boolean admin;
    private Collection<InvitedUsers> invitedUsersCollection;
    private Collection<OldInvitedUsers> oldInvitedUsersCollection;
    private Collection<Reservations> reservationsCollection;
    private Collection<OldReservations> oldReservationsCollection;

    public Users() {
    }

    public Users(Integer userID) {
        this.userID = userID;
    }

    public Users(Integer userID, String name, String surname, String mailAddress, String password, boolean admin) {
        this.userID = userID;
        this.name = name;
        this.surname = surname;
        this.mailAddress = mailAddress;
        this.password = password;
        this.admin = admin;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Collection<InvitedUsers> getInvitedUsersCollection() {
        return invitedUsersCollection;
    }

    public void setInvitedUsersCollection(Collection<InvitedUsers> invitedUsersCollection) {
        this.invitedUsersCollection = invitedUsersCollection;
    }

    public Collection<OldInvitedUsers> getOldInvitedUsersCollection() {
        return oldInvitedUsersCollection;
    }

    public void setOldInvitedUsersCollection(Collection<OldInvitedUsers> oldInvitedUsersCollection) {
        this.oldInvitedUsersCollection = oldInvitedUsersCollection;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Users[ userID=" + userID + " ]";
    }
    
}
