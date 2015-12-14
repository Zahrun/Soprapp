/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.gei.soprapp.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Aroun
 */
@Entity
@Table(name = "OldInvitedUsers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OldInvitedUsers.findAll", query = "SELECT o FROM OldInvitedUsers o"),
    @NamedQuery(name = "OldInvitedUsers.findByOldInvitedUserID", query = "SELECT o FROM OldInvitedUsers o WHERE o.oldInvitedUserID = :oldInvitedUserID")})
public class OldInvitedUsers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "oldInvitedUserID")
    private Integer oldInvitedUserID;
    @JoinColumn(name = "oldReservationRef", referencedColumnName = "oldReservationID")
    @ManyToOne(optional = false)
    private OldReservations oldReservationRef;
    @JoinColumn(name = "userRef", referencedColumnName = "userID")
    @ManyToOne(optional = false)
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
