/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function initReservationEdit(){
    getReservationList();
    $('input[name="dateRange"]').daterangepicker();
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
    resultDiv.html("");
    var tmpData;
    var iconImg;
    for (var i = 0; i < reservationList.length; i++) {
        tmpData = reservationList[i];
       

        resultDiv.append("<div id='" + tmpData.reservationID + "' class='objectOverview' >"
            + "<div class='informations'>"
            + "<div id='roomName'>" + tmpData.roomRef.number + "</div>"
            + "<div id='owner'>" + tmpData.ownerRef.name + " " + tmpData.ownerRef.surname + "</div>"
            + "<div id='start'>Début: " + tmpData.start.split("T", 1) + "</div>"
            + "<div id='end'>" + tmpData.end + "</div>"
            + "</div></div>");
    }
}