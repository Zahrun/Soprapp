package fr.insa.gei.soprapp.dto;

import java.util.Date;

/**
 * Created by Alexandre on 10/12/2015.
 */
public class Request {
    private Date dateMin ;
    private Date dateMax ;
    private int duration ;
    private int nbrPeople ;
    private roomEquipements equipements ;
    private Site site ;

    public Request(int dateMin, int dateMax, int duration, int nbrPeople, roomEquipements equipements, Site site) {
        this.dateMin = dateMin;
        this.dateMax = dateMax;
        this.duration = duration;
        this.nbrPeople = nbrPeople;
        this.equipements = equipements;
        this.site = site;
    }

    public int getDateMin() {
        return dateMin;
    }

    public void setDateMin(int dateMin) {
        this.dateMin = dateMin;
    }

    public int getDateMax() {
        return dateMax;
    }

    public void setDateMax(int dateMax) {
        this.dateMax = dateMax;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getNbrPeople() {
        return nbrPeople;
    }

    public void setNbrPeople(int nbrPeople) {
        this.nbrPeople = nbrPeople;
    }

    public roomEquipements getEquipements() {
        return equipements;
    }

    public void setEquipements(roomEquipements equipements) {
        this.equipements = equipements;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
}
