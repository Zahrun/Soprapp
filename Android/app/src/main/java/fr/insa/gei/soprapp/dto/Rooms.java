package fr.insa.gei.soprapp.dto;

import java.util.ArrayList;

/**
 * Created by Alexandre on 10/12/2015.
 */
public class Rooms {
    private ArrayList<Room> listRooms ;

    public Rooms(){
        this.listRooms = new ArrayList<>();
    }

    public ArrayList<Room> getListRooms() {
        return listRooms;
    }

    public void setListRooms(ArrayList<Room> listRooms) {
        this.listRooms = listRooms;
    }
}
