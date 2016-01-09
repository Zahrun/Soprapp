<%-- 
    Document   : sites
    Created on : 14 déc. 2015, 10:37:45
    Author     : gautierenaud
--%>

<%@page import="entities.Sites"%>
<%@page import="java.util.List"%>
<%@page import="service.SitesFacadeREST"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>List of sites</h1>
        <%
            EntityManagerFactory ef = Persistence.createEntityManagerFactory("webappPU");
            EntityManager em = ef.createEntityManager();
            
            SitesFacadeREST sitesREST = new SitesFacadeREST(em);
            List<Sites> listSites = sitesREST.findAll();
            for (Sites s : listSites){
                %><p><a href="#"><%out.println(s.getName());%></a></p><% // link to complete => link to listOfRooms ?
            }
        %>
    </body>
</html>
