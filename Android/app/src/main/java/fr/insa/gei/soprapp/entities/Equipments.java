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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Aroun
 */
@Entity
@Table(name = "Equipments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipments.findAll", query = "SELECT e FROM Equipments e"),
    @NamedQuery(name = "Equipments.findByEquipmentID", query = "SELECT e FROM Equipments e WHERE e.equipmentID = :equipmentID")})
public class Equipments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "equipmentID")
    private Integer equipmentID;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipmentRef")
    private Collection<RoomEquipments> roomEquipmentsCollection;

    public Equipments() {
    }

    public Equipments(Integer equipmentID) {
        this.equipmentID = equipmentID;
    }

    public Integer getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(Integer equipmentID) {
        this.equipmentID = equipmentID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<RoomEquipments> getRoomEquipmentsCollection() {
        return roomEquipmentsCollection;
    }

    public void setRoomEquipmentsCollection(Collection<RoomEquipments> roomEquipmentsCollection) {
        this.roomEquipmentsCollection = roomEquipmentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (equipmentID != null ? equipmentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipments)) {
            return false;
        }
        Equipments other = (Equipments) object;
        if ((this.equipmentID == null && other.equipmentID != null) || (this.equipmentID != null && !this.equipmentID.equals(other.equipmentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Equipments[ equipmentID=" + equipmentID + " ]";
    }
    
}
