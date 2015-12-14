/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.gei.soprapp.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Aroun
 */
@Entity
@Table(name = "OldReservations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OldReservations.findAll", query = "SELECT o FROM OldReservations o"),
    @NamedQuery(name = "OldReservations.findByOldReservationID", query = "SELECT o FROM OldReservations o WHERE o.oldReservationID = :oldReservationID"),
    @NamedQuery(name = "OldReservations.findByStart", query = "SELECT o FROM OldReservations o WHERE o.start = :start"),
    @NamedQuery(name = "OldReservations.findByEnd", query = "SELECT o FROM OldReservations o WHERE o.end = :end")})
public class OldReservations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "oldReservationID")
    private Integer oldReservationID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date end;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "oldReservationRef")
    private Collection<OldInvitedUsers> oldInvitedUsersCollection;
    @JoinColumn(name = "roomRef", referencedColumnName = "roomID")
    @ManyToOne(optional = false)
    private Rooms roomRef;
    @JoinColumn(name = "ownerRef", referencedColumnName = "userID")
    @ManyToOne(optional = false)
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

    @XmlTransient
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
