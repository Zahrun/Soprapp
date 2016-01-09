<%-- 
    Document   : searchReservations
    Created on : 8 janv. 2016, 22:06:11
    Author     : GAUTIER Renaud
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div id="search-bar"> <!-- research bar -->
    <div class="row">
        <div class="col-xs-12" id="inputCollection">
            <div class="form-group">
                <div class="input-group inputDefault">
                    <div class="input-group-addon">Utilisateur</div>
                    <input class="form-control" name="ownerName" type="text" placeholder="Personne ayant réservé" />
                </div>
                <div class="input-group inputDefault">
                    <div class="input-group-addon">Salle</div>
                    <input class="form-control" name="roomName" type="text" placeholder="Nom de la salle" />
                </div>
                <div class="input-group inputDefault">
                    <div class="input-group-addon">Début intervalle</div>
                    <input class="form-control datepicker" name="dateRangeStart" type="date" placeholder="Début de l'intervalle de recherche"/>
                </div>
                <div class="input-group inputDefault">
                    <div class="input-group-addon">Fin intervalle</div>
                    <input class="form-control datepicker" name="dateRangeEnd" type="date" placeholder="Fin de l'intervalle de recherche" />
                </div>
            </div>
        </div>
    </div> <!-- end of the row -->
    <div class="row">
        <div class="col-xs-8 defaultMargin">Réservations: <span id="reservationCount"></span></div>
        <div class="col-xs-3 defaultMargin" id="validation">
            <button type="button" class="btn btn-default" aria-label="Recherche" onclick="searchReservations()">
                <span class="glyphicon glyphicon-search" /> Rechercher
            </button>
        </div>
    </div>
</div><!-- end of the research bar -->

<div id="searchResult"> <!-- to put the result of the research -->

</div> <!-- end of the research div -->
            
<script>
    initReservationEdit();
</script>
