<%-- 
    Document   : index
    Created on : 14 déc. 2015, 08:40:37
    Author     : gautierenaud
--%>

<%@page import="javax.persistence.Persistence"%>
<%@page import="org.osgi.service.jpa.EntityManagerFactoryBuilder"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="entities.Sites"%>
<%@page import="java.util.List"%>
<%@page import="service.SitesFacadeREST"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <%
            EntityManagerFactory ef = Persistence.createEntityManagerFactory("webappPU");
            EntityManager em = ef.createEntityManager();
            
            SitesFacadeREST sitesREST = new SitesFacadeREST(em);
            List<Sites> listSites = sitesREST.findAll();
            for (Sites s : listSites){
                %><p>Name: <%out.println(s.getName());%></p><%
            }
        %>

    </body>
</html>
 