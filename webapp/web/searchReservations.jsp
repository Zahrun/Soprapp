<%-- 
    Document   : searchReservations
    Created on : 8 janv. 2016, 22:06:11
    Author     : GAUTIER Renaud
--%>


<%@page import="entities.Reservations"%>
<%@page import="java.util.List"%>
<%@page import="service.ReservationsFacadeREST"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%
            EntityManagerFactory ef = Persistence.createEntityManagerFactory("webappPU");
            EntityManager em = ef.createEntityManager();
            
            ReservationsFacadeREST reservationsREST = new ReservationsFacadeREST(em);
%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div id="search-bar"> <!-- research bar -->
    <div class="row">
        <div class="col-xs-4 reservationInput">
            <input id="searchEverything" placeholder="Chercher ..." />
        </div>
        <div class="col-xs-4 reservationInput">
            <input name="dateRange" placeholder="Intervalle de temps"/>
        </div>
        <div class="col-xs-4 reservationInput">
            <input name="lala" placeholder="Intervalle de temps"/>
        </div>
    </div> <!-- end of the row -->
    <div>
        <span class="col-xs-8"/>
        <span class="col-xs-4">Réservations: <% out.println(reservationsREST.countREST()); %></span>
    </div>
</div><!-- end of the research bar -->

<div id="searchResult"> <!-- to put the result of the research -->

</div> <!-- end of the research div -->
            
<script>
    initReservationEdit();
</script>
