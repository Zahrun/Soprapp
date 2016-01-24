/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
    var serverURL="176.31.1.146:8080/webapp2";
    
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
        "http://"+serverURL+"/rest/entities.reservations",
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
            + "<div class='informations col-xs-9'>"
                + "<div id='roomName'>" + tmpData.roomRef.number + "</div>"
                + "<div id='owner'>" + tmpData.ownerRef.name + " " + tmpData.ownerRef.surname + "</div>"
                + "<div id='start'>Début: " + tmpData.start.split("+", 1) + "</div>"
                + "<div id='end'>Fin: " + tmpData.end.split("+", 1) + "</div>"
            + "</div>"
            + "<div class='col-xs-2'><button class='btn btn-danger small-btn' onclick='clearReservation(" + tmpData.reservationID + ")'>Effacer</button></div></div>");
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
       url: "http://"+serverURL+"/rest/entities.reservations/filter/"
        + ownerName + "&"
        + roomName + "&"
        + dateRangeStart + "&"
        + dateRangeEnd,
       type: 'GET',
       success: function (reservationList){
            drawReservationList(reservationList);
       },
       dataType: "json"
    });
}

function clearReservation(id){
    $.ajax({
       url: "http://"+serverURL+"/rest/entities.reservations/" + id,
       type: 'DELETE',
       success: function (result){
           // redirect to the search page
           alert("Deletion is a success!");
           getReservationList();
       },
       dataType: "json"
   });
}

function initStats(){
    // get data for reservationWeekDate
    $.ajax({
       url: "http://"+serverURL+"/rest/entities.stats/reservationWeekdate",
       type: 'GET',
       success: function (result){
            //drawWeekReservation(result);
            var title = "Réservations par Jour";
            var legend = ['Jour', 'Nb Reservation'];
            var colName = ["Lun", "Mar", "Mer", "Jeu", "Ven", "Sam", "Dim"];
            var colColor = ["#d50000", "#aa00ff", "#1a237e", "#01579b", "#00bfa5", "#64dd17", "#ffd600"];
            //drawColumnChart(title, legend, result, colName, colColor, 'chart_div');
            drawWeekReservation(result);
       },
       dataType: "json"
   });
   
   // get data for reservationMonth
    $.ajax({
       url: "http://"+serverURL+"/rest/entities.stats/reservationMonth",
       type: 'GET',
       success: function (result){
            drawMonthReservation(result);
       },
       dataType: "json"
   });
   
    $("input[name='roomName']").on('input', inputChange);
   
   //get data for the room stats
   getRooms();
}
/*
function drawColumnChart(title, legend, colVal, colName, colColor, divID){
    var params = new Array;
    var tmpBase = new Array;
    for (var i = 0; i < legend.length; i++)
        tmpBase.push(legend[i]);
    
    tmpBase.push('{ role: "style" }');
    params.push(tmpBase);
    for (var i = 0; i < colVal.length; i++){
        tmpBase = new Array;
        tmpBase.push(colName[i]);
        tmpBase.push(colVal[i].value);
        tmpBase.push(colColor[i]);
        params.push(tmpBase);
        alert(tmpBase);
    }
    
    var data = google.visualization.arrayToDataTable(params);
    
     // Set chart options
    var options = {'title': title};
    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.ColumnChart(document.getElementById(divID));
    chart.draw(data, options);
}*/

function drawWeekReservation(weekRepartition){
    // Create the data table.
    var data = google.visualization.arrayToDataTable([
        ['Jour', "Nb Réservations", { role: "style" } ],
        ['Lun', weekRepartition[0].value, "#d50000" ],
        ['Mar', weekRepartition[1].value, "#aa00ff"],
        ['Mer', weekRepartition[2].value, "#1a237e"],
        ['Jeu', weekRepartition[3].value, "#01579b"],
        ['Ven', weekRepartition[4].value, "#00bfa5"],
        ['Sam', weekRepartition[5].value, "#64dd17"],
        ['Dim', weekRepartition[6].value, "#ffd600"]
      ]);
    
     // Set chart options
    var options = {'title':'Répartition des réservations par jour'};
    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
    chart.draw(data, options);
}

function drawMonthReservation(monthRepartition){
    // Create the data table.
    var data = google.visualization.arrayToDataTable([
        ['Mois', "Nb Réservations", { role: "style" } ],
        ['Jan', monthRepartition[0].value, "#d50000" ],
        ['Fev', monthRepartition[1].value, "#aa00ff"],
        ['Mar', monthRepartition[2].value, "#1a237e"],
        ['Avr', monthRepartition[3].value, "#01579b"],
        ['Mai', monthRepartition[4].value, "#00bfa5"],
        ['Jun', monthRepartition[5].value, "#64dd17"],
        ['Jui', monthRepartition[6].value, "#ffd600"],
        ['Aou', monthRepartition[7].value, "#ff6d00"],
        ['Sep', monthRepartition[8].value, "#dd2c00"],
        ['Oct', monthRepartition[9].value, "#795548"],
        ['Nov', monthRepartition[10].value, "#bdbdbd"],
        ['Dec', monthRepartition[11].value, "#607d8b"]
      ]);
    
     // Set chart options
    var options = {'title':'Répartition des réservations par Mois'};
    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.ColumnChart(document.getElementById('monthDiv'));
    chart.draw(data, options);
}

function inputChange(){
    if ($("input[name='roomName']").val() === "")
        getRooms();
    else
        loadRoomList();
}

function getRooms(){
    $.ajax({
       url: "http://"+serverURL+"/rest/entities.rooms/",
       type: 'GET',
       success: function (result){
           drawReservRoomList(result);               
       },
       dataType: "json"
   });
}

function loadRoomList(){
    var salleInput = $("input[name='roomName']").val();
    
    $.ajax({
       url: "http://"+serverURL+"/rest/entities.rooms/filterRoomsNumber/" + salleInput,
       type: 'GET',
       success: function (result){
            drawReservRoomList(result);              
       },
       dataType: "json"
   });
}

function drawReservRoomList(roomList){
    var salleList = $("#salleList");
    salleList.html("");
    for (var i = 0; i < roomList.length; i++){
        salleList.append("<li id='" + roomList[i].roomID + "' onclick='loadRoomStats(" + roomList[i].roomID + ",\"" + roomList[i].number + "\")'><a href='#'>" + roomList[i].number + "</a></li>");
    }
}

function loadRoomStats(id, number){
    $("input[name='roomName']").val(number);
    loadRoomList();
    
    // erase content of previous stats
    var roomStatDiv = $("#roomStatDiv");
    roomStatDiv.html("");
    
    $.ajax({
       url: "http://"+serverURL+"/rest/entities.stats/room/" + id,
       type: 'GET',
       success: function (result){
           roomStatDiv.append("<div>Il y a eu " + result.value + " réservations pour cette salle</div>");
       },
       dataType: "json"
   });
}