<%-- 
    Document   : createRoom
    Created on : 9 janv. 2016, 23:49:23
    Author     : alexis
--%>

<!DOCTYPE html>

<!-- action="http://localhost:8080/webapp/rest/entities.sites/createRoom"  method="POST" -->
<div id="createUserForm">
    
    <div class="form-group">
        <label for="number">Nom/Numéro:</label>
        <input type="text" class="form-control" required name="number" placeholder="Nom de la salle" />
    </div>
    <div class="form-group">
        <label for="capacity">Capacité:</label>
        <input type="text" class="form-control" required name="capacity" placeholder="Capacité de la salle"/>
        <!-- TODO : le faire passer en integer !! -->
    </div>

    
    <div class="form-group">
        <button onclick="createRoom()">Submit</button>
    </div>
</div>


