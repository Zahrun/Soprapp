/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Sites;
import java.util.List;
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
@Path("entities.sites")
public class SitesFacadeREST extends AbstractFacade<Sites> {

    @PersistenceContext(unitName = "webappPU")
    private EntityManager em;

    public SitesFacadeREST() {
        super(Sites.class);
    }
    
    public SitesFacadeREST(EntityManager e) {
        super(Sites.class);
        em = e;
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Sites entity) {
        super.create(entity);
    }
    
    /* Fait de la même façon que ce cher Gautier, car j'avais la flemme de me pencher sur les objets java en js :p */
    @POST
    @Path(value = "js")
    @Consumes(value = {MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createUserREST(@FormParam("name") String name, @FormParam("address") String address, @FormParam("description") String description){     
        Sites site = new Sites();      
        site.setName(name);
        site.setAddress(address);
        site.setDescription(description);        
        super.create(site);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Sites entity) {
        super.edit(entity);
    }
    
     @PUT
    @Path("js/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED})
    public void editTest(@PathParam("id") Integer id, @FormParam("name") String name, @FormParam("address") String address, @FormParam("description") String description) {
       
        Sites entity = new Sites(id, name, address, description);
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
    public Sites find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Sites> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Sites> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    
    // methods for filtering
    @GET
    @Path(value = "filterSites/{name}/{address}/{description}")
    public List<Sites> filterSitesGET(@PathParam(value = "name") final String name, @PathParam(value = "address") final String address, @PathParam(value = "description") final String description) {
        return filterByEverythingOR(name, address, description);
    }
    
    @GET
    @Path(value = "filterSitesName/{name}")
    public List<Sites> filterSitesNameGET(@PathParam(value = "name") final String name) {
        return filterByEverythingAND(name, "", "");
    }
    
     public List<Sites> filterByEverythingAND(String name, String address, String description){
        List<Sites> listSites;
        listSites = (List<Sites>) em.createNamedQuery("Sites.filterByEverythingAND")
                .setParameter("name", "%" + name + "%")
                .setParameter("address", "%" + address + "%")
                .setParameter("description", "%" + description + "%")
                .getResultList();
        return listSites;
    }
    
    public List<Sites> filterByEverythingOR(String name, String address, String description){
        List<Sites> listSites;
        listSites = (List<Sites>) em.createNamedQuery("Sites.filterByEverythingOR")
                .setParameter("name", "%" + name + "%")
                .setParameter("surname", "%" + address + "%")
                .setParameter("mail", "%" + description + "%")
                .getResultList();
        return listSites;
    }
}
