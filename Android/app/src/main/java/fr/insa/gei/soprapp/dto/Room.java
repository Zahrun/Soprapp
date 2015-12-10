package fr.insa.gei.soprapp.dto;

/**
 * Created by Alexandre on 10/12/2015.
 */
public class Room {
    private int roomID ;
    private Site siteRef;
    private String number;
    private int capacity;

    public Room(int capacity, int roomID, Site siteRef, String number) {
        this.capacity = capacity;
        this.roomID = roomID;
        this.siteRef = siteRef;
        this.number = number;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public Site getSiteRef() {
        return siteRef;
    }

    public void setSiteRef(Site siteRef) {
        this.siteRef = siteRef;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
