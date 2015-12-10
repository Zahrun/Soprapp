package fr.insa.gei.soprapp.dto;

/**
 * Created by Alexandre on 07/12/2015.
 */
public class Site {
    private Integer siteID      ;
    private String  name        ;
    private String  address     ;
    private String  description ;

    public Site(Integer siteID, String  name, String  address, String  description){
        this.siteID = siteID ;
        this.name = name ;
        this.address = address ;
        this.description = description ;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
