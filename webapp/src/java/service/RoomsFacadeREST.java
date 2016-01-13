/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Reservations;
import entities.Rooms;
import entities.Sites;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.NoResultException;
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
@Path("entities.rooms")
public class RoomsFacadeREST extends AbstractFacade<Rooms> {

    @PersistenceContext(unitName = "webappPU")
    private EntityManager em;

    public RoomsFacadeREST() {
        super(Rooms.class);
    }
    
    public RoomsFacadeREST(EntityManager em) {
        super(Rooms.class);
        this.em = em;
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Rooms entity) {
        super.create(entity);
    }

    @POST
    @Path(value = "js")
    @Consumes(value = {MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createRoomREST(@FormParam("number") String number, @FormParam("capacity") short capacity, @FormParam("siteRef_name") String siteRef_name ){     
        Rooms room = new Rooms();  
        
        room.setNumber(number);
        room.setCapacity(capacity);
        
        Sites site = null;
        
        try{
            site = (Sites) em.createNamedQuery("Sites.findByName")
                    .setParameter("name", siteRef_name)
                    .getSingleResult();
        }catch (NoResultException e){}
        
        room.setSiteRef(site); 

        super.create(room);
    }
    
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Rooms entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Rooms find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Rooms> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Rooms> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    
     // METHODS FOR FILTERING
    
    /* @GET
    @Path(value = "filterRooms/{number}/{capacity}")
    public List<Rooms> filterRoomsGET(@PathParam(value = "number") final String number, @PathParam(value = "capacity") final String capacity) {
        return filterByEverythingOR(number, capacity);
    } */
 
    
    @GET
    @Path(value = "filterRoomsNumber/{number}")
    public List<Rooms> filterRoomsNameGET(@PathParam(value = "number") final String number) {
        List<Rooms> listRooms;
        listRooms = (List<Rooms>) em.createNamedQuery("Rooms.filterByName")
                .setParameter("number", "%" + number + "%")           
                .getResultList();
        return listRooms;
    }
    
    
    @GET
    @Path(value = "filterRoomsSite/{siteRef_name}")
    public List<Rooms> filterRoomsSiteGET(@PathParam(value = "siteRef_name") final String siteRef_name) {
        List<Rooms> listRooms;
        listRooms = (List<Rooms>) em.createNamedQuery("Rooms.filterBySite")
                .setParameter("siteRef_name", "%" + siteRef_name + "%")           
                .getResultList();
        return listRooms;
    }
    
    @GET
    @Path(value = "filterRoomsCapacity/{capacity}")
    public List<Rooms> filterRoomsCapacityGET(@PathParam(value = "capacity") final String capacity) {
        List<Rooms> listRooms;
        listRooms = (List<Rooms>) em.createNamedQuery("Rooms.findByCapacity")
                .setParameter("capacity", Short.parseShort(capacity))            
                .getResultList();
        return listRooms;
    }
    
    @GET
    @Path(value = "mainSearch/{searchParams}")
    public List<Rooms> mainSearchGET(@PathParam("searchParams") final String searchParams) {
        
        String[] params = {"", ""};
        String[] splitResult = searchParams.split("&");
        System.arraycopy(splitResult, 0, params, 0, splitResult.length); 
                  
        
        List<Rooms> listRooms;
        
        listRooms = (List<Rooms>) em.createNamedQuery("Rooms.findAvailable")
                .setParameter("site", params[0])
                .setParameter("nombrePersonnes",Short.parseShort(params[1]))  
                .getResultList();
        
        listRooms = areWellEquiped(listRooms) ;
        listRooms = areAvailable(listRooms);
        return listRooms;
    }
    
<<<<<<< HEAD
    public List<Rooms> areWellEquiped( List<Rooms> listRooms) {
        List<Rooms> result = listRooms ;
        //TODO 
        
        return result ;
    }
    public List<Rooms> areAvailable(List<Rooms> listRooms){
        List<Rooms> result = listRooms ;
        //TODO 
        
        return result ;
=======
    @GET
    @Path(value = "badSearch/{searchParams}")
    public List<Rooms> badSearchGET(@PathParam("searchParams") String searchParams) {
        //site/date/durée/nombredepersonnes/equipements
        String[] params = {"", "", "", "", ""};
        String[] splitResult = searchParams.split("&");
        System.arraycopy(splitResult, 0, params, 0, splitResult.length);
        
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate = new Date(Long.valueOf(params[1]));
         
        Date endDate = new Date(startDate.getTime()+Long.valueOf(params[2])*60*1000);
        
        List<Rooms> listRooms = this.filterRoomsSiteGET(params[0]);
        List<Rooms> listRoomsCapacite = this.filterRoomsCapacityGET(params[3]);
        listRooms.retainAll(listRoomsCapacite);
        
        ReservationsFacadeREST rfREST = new ReservationsFacadeREST(em);
        List<Reservations> listReservations = rfREST.filterReservations("&&"+startDate.getTime()+"&"+endDate.getTime());
        
        for (Reservations res : listReservations){
            listRooms.remove(res.getRoomRef());
        }
        
        return listRooms;
>>>>>>> origin/master
    }
    
    
    
   /*  public List<Rooms> filterByEverythingAND(String number, String capacity){
        List<Rooms> listRooms;
        listRooms = (List<Rooms>) em.createNamedQuery("Rooms.filterByEverythingAND")
                .setParameter("number", "%" + number + "%")
                .setParameter("capacity", Short.parseShort(capacity))               
                .getResultList();
        return listRooms;
    } */
    
   /* public List<Rooms> filterByEverythingOR(String number, String capacity){
        List<Rooms> listRooms;

        listRooms = (List<Rooms>) em.createNamedQuery("Rooms.filterByName")
                .setParameter("number", "%" + number + "%")
             //   .setParameter("capacity", Short.parseShort(capacity))    // Quand on cherche autre chose que la capacité on a pas un short ici mais un int !!!          
                .getResultList();
        
        return listRooms;
    }*/         // TODO if needed
}
