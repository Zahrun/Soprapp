<%-- 
    Document   : editUsers
    Created on : 31 déc. 2015, 16:35:02
    Author     : GAUTIER Renaud
--%>

<%@page import="entities.Users"%>
<%@page import="service.UsersFacadeREST"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
   EntityManagerFactory ef = Persistence.createEntityManagerFactory("webappPU");
   EntityManager em = ef.createEntityManager();

   UsersFacadeREST userFacade = new UsersFacadeREST(em);
   int id = Integer.parseInt(request.getParameter("id"));
   Users user = userFacade.find(id);
%>

<button class="btn-danger" onclick="deleteUser(<% out.println(id); %>)">Delete</button>

<form id="createUserForm" onsubmit="updateUser(<% out.println(user.getUserID()); %>)">
    <div class="form-group">
        <label for="admin"><input name="admin" type="checkbox" <% if (user.getAdmin()) out.println("checked"); %>/>Admin</label>
    </div>
    <div class="form-group">
        <label for="name">Nom:</label>
        <input type="text" class="form-control" required name="name" value="<% out.println(user.getName()); %>" />
    </div>
    <div class="form-group">
        <label for="surname">Prenom:</label>
        <input type="text" class="form-control" required name="surname" value="<% out.println(user.getSurname()); %>"/>
    </div>
    <div class="form-group">
        <label for="mail">Email:</label>
        <input type="email" class="form-control" required name="mail" value="<% out.println(user.getMailAddress()); %>"/>
    </div>
    <div class="form-group">
        <label for="password">Mot de passe:</label>
        <input type="password" class="form-control" required name="pwd" value="<% out.println(user.getPassword()); %>"/>
    </div>
    <div class="form-group">
        <input type="Submit"/>
    </div>
</form>
