/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Users;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Aroun
 */
@Stateless
@Path("entities.users")
public class UsersFacadeREST extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "webappPU")
    private EntityManager em;

    public UsersFacadeREST() {
        super(Users.class);
    }
    
    public UsersFacadeREST(EntityManager em) {
        super(Users.class);
        this.em = em;
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Users entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Users entity) {
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
    public Users find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    
    // will redirect to a admin web page
    @POST
    @Path("loginAdmin")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public Response loginAdmin(@FormParam("mail") String mail, @FormParam("pwd") String pwd){
        Users user = findByMailPass(mail, pwd);
        
        java.net.URI location;
        try {
            
            if (user != null && user.getAdmin())             
                location = new URI("http://localhost:8080/webapp/Logged.jsp");
            else
                location = new URI("http://localhost:8080/webapp/index.html");
            
            return Response.temporaryRedirect(location).build();
        } catch (URISyntaxException ex) {
            Logger.getLogger(UsersFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    // will return a boolean
    @POST
    @Path("loginUser")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public boolean loginUser(@FormParam("mail") String mail, @FormParam("pwd") String pwd){ 
        Users user = findByMailPass(mail, pwd);
        if (user != null)
            return true;
        else
            return false;
    }
    
    public Users findByMailPass(String mail, String pwd){
        Users user = null;
        try{
            user = (Users) em.createNamedQuery("Users.findByMailPass")
                .setParameter("mailAddress", mail)
                .setParameter("password", pwd)
                .getSingleResult();
        }catch (NoResultException e){}
        
        return user;
    }
    
    // methods for filtering
    @POST
    @Path("filterUser")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public List<Users> filterUsers(@FormParam("name") String name, @FormParam("surname") String surname, @FormParam("mail") String mail){
        
        return filterByNameSurnameMail(name, surname, mail);
    }
    
    public List<Users> filterByNameSurnameMail(String name, String surname, String mail){
        List<Users> listUsers = null;
        System.out.println("name: " + name);
        System.out.println("surname: " + surname);
        System.out.println("mail: " + mail);
        listUsers = (List<Users>) em.createNamedQuery("Users.filterByNameSurnameMail")
                .setParameter("name", "%" + name + "%")
                .setParameter("surname", "%" + surname + "%")
                .setParameter("mail", "%" + mail + "%")
                .getResultList();
        return listUsers;
    }
}
