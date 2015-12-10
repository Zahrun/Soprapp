package fr.insa.gei.soprapp.dto;

/**
 * Created by Alexandre on 10/12/2015.
 */
public class User {
    private int userID ;
    private String name ;
    private String surname ;
    private Boolean admin ;
    private String pmailAdress ;
    private String password ;

    public User(int userID, String name, String surname, Boolean admin, String pmailAdress, String password) {
        this.userID = userID;
        this.name = name;
        this.surname = surname;
        this.admin = admin;
        this.pmailAdress = pmailAdress;
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
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

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getPmailAdress() {
        return pmailAdress;
    }

    public void setPmailAdress(String pmailAdress) {
        this.pmailAdress = pmailAdress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
