<%-- 
    Document   : editRoom.jsp
    Created on : 8 janv. 2016, 23:16:05
    Author     : alexis
--%>

<%@page import="entities.Rooms"%>
<%@page import="service.RoomsFacadeREST"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
   EntityManagerFactory ef = Persistence.createEntityManagerFactory("webappPU");
   EntityManager em = ef.createEntityManager();

   RoomsFacadeREST roomFacade = new RoomsFacadeREST(em);
   int id = Integer.parseInt(request.getParameter("id"));
   Rooms room = roomFacade.find(id);
%>

<button class="btn-danger" onclick="deleteSite(<% out.println(id); %>)">Delete </button>

<form id="createUserForm" onsubmit="updateRoom(<% out.println(room.getRoomID()); %>)">

    <div class="form-group">
        <label for="number">Nom/numéro:</label>
        <input type="text" class="form-control" required name="number" value="<% out.println(room.getNumber()); %>" />
    </div>
    <div class="form-group">
        <label for="surname">Capacité:</label>
        <input type="text" class="form-control" required name="capacity" value="<% out.println(room.getCapacity()); %>"/>
    </div>
    
    <div class="form-group">
        <input type="Submit"/>
    </div>
</form>
