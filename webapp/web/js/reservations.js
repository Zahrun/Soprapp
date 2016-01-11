/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
function initReservationEdit(){
    getReservationList();
    
    var startDate = $('input[name="dateRangeStart"').datepicker({
        format: "dd-mm-yyyy"
    });
    var endDate = $('input[name="dateRangeEnd"').datepicker({
        format: "dd-mm-yyyy"
    });
}


// get the list of existing reservations
function getReservationList() {
    $.get(
        "http://localhost:8080/webapp/rest/entities.reservations",
        function (data) {
            drawReservationList(data);
        },
        "json"
    );
}

function drawReservationList(reservationList){
    var resultDiv = $("#searchResult");
    $("#reservationCount").html(reservationList.length);
    resultDiv.html("");
    var tmpData;
    for (var i = 0; i < reservationList.length; i++) {
        tmpData = reservationList[i];
       
        resultDiv.append("<div id='" + tmpData.reservationID + "' class='objectOverview' >"
            + "<div class='informations'>"
            + "<div id='roomName'>" + tmpData.roomRef.number + "</div>"
            + "<div id='owner'>" + tmpData.ownerRef.name + " " + tmpData.ownerRef.surname + "</div>"
            + "<div id='start'>Début: " + tmpData.start.split("+", 1) + "</div>"
            + "<div id='end'>Fin: " + tmpData.end.split("+", 1) + "</div>"
            + "</div>"
            + "<button class='btn btn-danger small-btn' onclick='clearReservation(" + tmpData.reservationID + ")'>Effacer</button></div>");
    }
}

function searchReservations(){
    var ownerName = $("input[name='ownerName']").val();
    var roomName = $("input[name='roomName']").val();
    var dateRangeStart = $("input[name='dateRangeStart']").val();
    var dateRangeEnd = $("input[name='dateRangeEnd']").val();
    
    // on ajoute des %% pour que les chaines ne soient pas vides
    // elles sont équivalentes à des jokers lors des requetes SQL
    $.ajax({
       url: "http://localhost:8080/webapp/rest/entities.reservations/filter/"
        + ownerName + "&"
        + roomName + "&"
        + dateRangeStart + "&"
        + dateRangeEnd,
       type: 'GET',
       success: function (reservationList){
            drawReservationList(reservationList);
       }
    });
}

function clearReservation(id){
    $.ajax({
       url: "http://localhost:8080/webapp/rest/entities.reservations/" + id,
       type: 'DELETE',
       success: function (result){
           // redirect to the search page
           alert("Deletion is a success!");
           getReservationList();
       }
   });
}