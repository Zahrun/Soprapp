/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.OldReservations;
import entities.Reservations;
import entities.Users;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Aroun
 */
@Stateless
@Path("entities.reservations")
public class ReservationsFacadeREST extends AbstractFacade<Reservations> {

    @PersistenceContext(unitName = "webappPU")
    private EntityManager em;

    public ReservationsFacadeREST() {
        super(Reservations.class);
    }
    
    public ReservationsFacadeREST(EntityManager em) {
        super(Reservations.class);
        this.em = em;
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Reservations entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Reservations entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        Reservations entity = super.find(id);
        OldReservationsFacadeREST oldReservFacade = new OldReservationsFacadeREST(em);
        oldReservFacade.create(new OldReservations(entity));
        super.remove(entity);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Reservations find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Reservations> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Reservations> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    @GET
    @Path("filter/{searchParams}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reservations> filterReservations(@PathParam("searchParams") String searchParams){
        String[] params = {"", "", "", ""};
        String[] splitResult = searchParams.split("&");
        System.arraycopy(splitResult, 0, params, 0, splitResult.length);

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate = new Date();
        Date endDate = new Date();
        
        // parse the date from the parameters
        try {
            if (!"".equals(params[2]))
                startDate = df.parse(params[2]);
            if(!"".equals(params[3]))
                endDate = df.parse(params[3]);
            
        } catch (ParseException ex) {
            System.out.println("Error from Date parsing: \n");
            Logger.getLogger(ReservationsFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        List<Reservations> result = new ArrayList<>();
        List<Reservations> tmp = super.findAll();
        
        for (Reservations tmpReserv : tmp){
            if (("".equals(params[2]) || tmpReserv.getStart().after(startDate)) && ("".equals(params[3]) || tmpReserv.getEnd().before(endDate)) && (tmpReserv.getOwnerRef().getName().contains(params[0]) || tmpReserv.getOwnerRef().getSurname().contains(params[0])) && (tmpReserv.getRoomRef().getNumber().contains(params[1]))){
                result.add(tmpReserv);
            }
        }
        
        return result;
    }

    
}
