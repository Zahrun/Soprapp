<%-- 
    Document   : editSites
    Created on : 9 janv. 2016, 23:07:33
    Author     : alexis
--%>

<%@page import="entities.Sites"%>
<%@page import="service.SitesFacadeREST"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
   EntityManagerFactory ef = Persistence.createEntityManagerFactory("webappPU");
   EntityManager em = ef.createEntityManager();

   SitesFacadeREST siteFacade = new SitesFacadeREST(em);
   int id = Integer.parseInt(request.getParameter("id"));
   Sites site = siteFacade.find(id);
%>

<button class="btn-danger" onclick="deleteSite(<% out.println(id); %>)">Delete</button>

<form id="createUserForm" onsubmit="updateSite(<% out.println(site.getSiteID()); %>)">

    <div class="form-group">
        <label for="name">Nom:</label>
        <input type="text" class="form-control" required name="name" value="<% out.println(site.getName()); %>" />
    </div>
    <div class="form-group">
        <label for="address">Adresse:</label>
        <input type="text" class="form-control" required name="address" value="<% out.println(site.getAddress()); %>"/>
    </div>
    <div class="form-group">
        <label for="description">Description:</label>
        <input type="text" class="form-control" required name="description" value="<% out.println(site.getDescription()); %>"/>
    </div>
    
    <div class="form-group">
        <input type="Submit"/>
    </div>
</form>
