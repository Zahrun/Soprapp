package fr.insa.gei.soprapp.dto;

import java.util.ArrayList;

import fr.insa.gei.soprapp.dto.Site;

/**
 * Created by Alexandre on 07/12/2015.
 */
public class Sites {
    private ArrayList<Site> listSites ;

    public Sites(){
        this.listSites = new ArrayList<>();
    }

    public ArrayList<Site> getListSites() {
        return listSites;
    }

    public void setListSites(ArrayList<Site> listSites) {
        this.listSites = listSites;
    }
}
