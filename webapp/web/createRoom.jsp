<%@page import="java.util.List"%>
<%@page import="entities.Sites"%>
<%@page import="service.SitesFacadeREST"%>
<%@page import="javax.persistence.NoResultException"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
   EntityManagerFactory ef = Persistence.createEntityManagerFactory("webappPU");
   EntityManager em = ef.createEntityManager();

   SitesFacadeREST siteFacade = new SitesFacadeREST(em);
%>

<!-- action="http://localhost:8080/webapp/rest/entities.sites/createRoom"  method="POST" -->
<div id="createUserForm">
    

    <!-- faire une liste des sites disponibles pour le choix -->

    <div class="form-group">
        <label for="siteRef_name">Site:</label> 

<%      List<Sites> listAllSites = siteFacade.findAll();
        int i = 0;
   
        for (Sites site : listAllSites) { %>           
        <input name="siteRef_name" type="radio" value="<%=listAllSites.get(i).getName()%>" /> <%out.println(site.getName());
            i++;   // j'ai tenté avec => listAllSites.get(i).getName() dans value au dessus, d'où le i qui se ballade !! sinon j'vais site.getName()
                   // mais je pensais que ça changeait à la fin...
        }
            %>
    </div>
  
    <div class="form-group">
        <label for="number">Nom/Numéro:</label>
        <input type="text" class="form-control" required name="number" placeholder="Nom de la salle" />
    </div>
    <div class="form-group">
        <label for="capacity">Capacité:</label>
        <input type="text" class="form-control" required name="capacity" placeholder="Capacité de la salle"/>
    </div>
    
    <div class="form-group">
        <button onclick="createRoom()">Submit</button>
    </div>
</div>



