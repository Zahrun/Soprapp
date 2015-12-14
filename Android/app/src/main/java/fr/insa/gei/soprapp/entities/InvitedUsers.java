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
@Table(name = "InvitedUsers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InvitedUsers.findAll", query = "SELECT i FROM InvitedUsers i"),
    @NamedQuery(name = "InvitedUsers.findByInvitedUserID", query = "SELECT i FROM InvitedUsers i WHERE i.invitedUserID = :invitedUserID")})
public class InvitedUsers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "invitedUserID")
    private Integer invitedUserID;
    @JoinColumn(name = "reservationRef", referencedColumnName = "reservationID")
    @ManyToOne(optional = false)
    private Reservations reservationRef;
    @JoinColumn(name = "userRef", referencedColumnName = "userID")
    @ManyToOne(optional = false)
    private Users userRef;

    public InvitedUsers() {
    }

    public InvitedUsers(Integer invitedUserID) {
        this.invitedUserID = invitedUserID;
    }

    public Integer getInvitedUserID() {
        return invitedUserID;
    }

    public void setInvitedUserID(Integer invitedUserID) {
        this.invitedUserID = invitedUserID;
    }

    public Reservations getReservationRef() {
        return reservationRef;
    }

    public void setReservationRef(Reservations reservationRef) {
        this.reservationRef = reservationRef;
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
        hash += (invitedUserID != null ? invitedUserID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvitedUsers)) {
            return false;
        }
        InvitedUsers other = (InvitedUsers) object;
        if ((this.invitedUserID == null && other.invitedUserID != null) || (this.invitedUserID != null && !this.invitedUserID.equals(other.invitedUserID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.InvitedUsers[ invitedUserID=" + invitedUserID + " ]";
    }
    
}
