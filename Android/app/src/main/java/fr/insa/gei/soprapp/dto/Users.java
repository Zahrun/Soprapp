package fr.insa.gei.soprapp.dto;

import java.util.ArrayList;

/**
 * Created by Alexandre on 10/12/2015.
 */
public class Users {
    private ArrayList<User> listUsers ;

    public Users(){
        this.listUsers = new ArrayList<>();
    }

    public ArrayList<User> getListUsers() {
        return listUsers;
    }

    public void setListUsers(ArrayList<User> listUsers) {
        this.listUsers = listUsers;
    }
}
