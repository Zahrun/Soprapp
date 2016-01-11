/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.IntegerDummy;
import entities.Reservations;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.persistence.ElementCollection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author GAUTIER Renaud
 */

@Stateless
@Path("entities.stats")
public class StatistiquesREST {
    
    @PersistenceContext(unitName = "webappPU")
    private EntityManager em;
    
    public StatistiquesREST(){
    }
    
    @GET
    @Path("reservationWeekdate")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<IntegerDummy> reservationWeekdateREST(){
        List<IntegerDummy> result = new ArrayList<>();
        for (int i = 0; i < 7; i++)
            result.add(new IntegerDummy(0));
        
        ReservationsFacadeREST reservFacade = new ReservationsFacadeREST(em);
        List<Reservations> reservList = reservFacade.findAll();
        for (Reservations tmpReserv : reservList){
            Date startDate = tmpReserv.getStart();
            Calendar ca = Calendar.getInstance();
            ca.setTime(startDate);
            IntegerDummy tmpInteger = result.get(ca.get(Calendar.DAY_OF_WEEK));
            tmpInteger.setValue(tmpInteger.getValue()+1);
        }
        
        // mettre les valeurs correspondants à Samedi et Dimanche en fin de liste
        IntegerDummy tmp = result.remove(0);
        result.add(tmp);
        tmp = result.remove(0);
        result.add(tmp);
        
        return result;
    }
    
    @GET
    @Path("reservationMonth")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<IntegerDummy> reservationMonthREST(){
        List<IntegerDummy> result = new ArrayList<>();
        for (int i = 0; i < 12; i++)
            result.add(new IntegerDummy(0));
        
        ReservationsFacadeREST reservFacade = new ReservationsFacadeREST(em);
        List<Reservations> reservList = reservFacade.findAll();
        for (Reservations tmpReserv : reservList){
            Date startDate = tmpReserv.getStart();
            Calendar ca = Calendar.getInstance();
            ca.setTime(startDate);
            IntegerDummy tmpInteger = result.get(ca.get(Calendar.MONTH));
            tmpInteger.setValue(tmpInteger.getValue()+1);
        }
        
        return result;
    }
    
    @GET
    @Path("room/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public IntegerDummy roomStatREST(@PathParam("id") String roomID){
        ReservationsFacadeREST reservFacade = new ReservationsFacadeREST(em);
        List<Reservations> reservList = reservFacade.findAll();
        
        IntegerDummy count = new IntegerDummy(0);
        
        for(Reservations tmpReserv : reservList){
            if (tmpReserv.getRoomRef().getRoomID() == Integer.parseInt(roomID))
                count.succ();
        }
        
        return count;
    }
}
