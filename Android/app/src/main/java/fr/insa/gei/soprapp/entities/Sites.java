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
public class Sites implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer siteID;

    @NotNull
    @Size(min = 1, max = 50)

    private String name;

    @NotNull

    @Size(min = 1, max = 65535)

    private String address;

    @Size(max = 65535)

    private String description;

    private Collection<Rooms> roomsCollection;

    public Sites() {
    }

    public Sites(Integer siteID) {
        this.siteID = siteID;
    }

    public Sites(Integer siteID, String name, String address) {
        this.siteID = siteID;
        this.name = name;
        this.address = address;
    }

    public Integer getSiteID() {
        return siteID;
    }

    public void setSiteID(Integer siteID) {
        this.siteID = siteID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Rooms> getRoomsCollection() {
        return roomsCollection;
    }

    public void setRoomsCollection(Collection<Rooms> roomsCollection) {
        this.roomsCollection = roomsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (siteID != null ? siteID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sites)) {
            return false;
        }
        Sites other = (Sites) object;
        if ((this.siteID == null && other.siteID != null) || (this.siteID != null && !this.siteID.equals(other.siteID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Sites[ siteID=" + siteID + " ]";
    }
    
}
