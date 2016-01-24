/* global Room, Rooms */

// this file is made to control all the interactions from the "Salles" section of the webpage

    var serverURL="176.31.1.146:8080/webapp";
    
// REST api -> GET
function getRoomList() {
    var roomList;
    $.get(
        "http://"+serverURL+"/rest/entities.rooms",
        function (data) {
            drawRoomList(data);
        },
        "json"
    );
}


function initRoomEdit() {
    
    // load the initial list
    searchRooms();

}

function searchRooms() {
    var searchString = $("#searchString").val();
    var selectedOption = $("input[name='searchOption']:checked").attr("id");
    var url = "http://"+serverURL+"/rest/entities.rooms/filterRooms";
    switch (selectedOption) {
        case "all":
        //    if (searchString !== "")
        //        url += "/" + searchString + "/" + searchString ;
        //    else
                url = "http://"+serverURL+"/rest/entities.rooms";
            break;
        case "number":
            url += "Number/" + searchString;
            break;
       
        case "capacity":
            url += "Capacity/" + searchString;
            break;
            
        case "site":
            url += "Site/" + searchString;
            break;

        default:
            url += "";
            break;
    }

    $.get(
        url,
        function (data) {
            drawRoomList(data);
        },
        "json"
    );
}

function drawRoomList(roomList){
    var resultDiv = $("#searchResult");
    resultDiv.html("");
    var tmpData;
    for (var i = 0; i < roomList.length; i++) {
        tmpData = roomList[i];


        resultDiv.append("<div id='" + tmpData.roomID + "' class='objectOverview roomOverview' onclick='editRoom(" + tmpData.roomID + ")' >"
            + "<div class='informations'>"
            + "<div id='number'><b>" + tmpData.number + "</b></div>"
            + "<div id='capacity'>Capacité: " + tmpData.capacity + "</div>"
            + "<div id='siteRef.name'>Site: " + tmpData.siteRef.name + "</div>"
            + "</div></div>");
    }
}

function createRoom(){ 
    
    var siteRef_name = $('input[name="siteRef_name"]:checked').val();
    var number = $('input[name="number"]').val();
    var capacity = $('input[name="capacity"]').val();
    
    // sending the put request with the parameters
    $.ajax({
       url: "http://"+serverURL+"/rest/entities.rooms/js",
       type: 'POST',
       data: {
           siteRef_name: siteRef_name,
           number: number,
           capacity: capacity  
       },
       success: function (){
           loadPage('roomsEdit.html','Salles');
       },
       error: function(error){
           alert(error);
       }
    });
    
    // disable redirection
    return false;
}

function editRoom(id){ // TODO
    loadPage("http://"+serverURL+"/editRooms.jsp?id=" + id, "Salles");
}

function updateRoom(id){ // TODO
    
    // retrieve the parameters from the form
    var number = $('input[name = "number"]').val();
    var capacity = $('input[name = "capacity"]').val();
    
    $.ajax({
       url: "http://"+serverURL+"/rest/entities.rooms/js/" + id,
       type: 'PUT',
       data: {
           number: number,
           capacity: capacity 
       },
       success: function (){
         loadPage('roomsEdit.html','Salles');
       },
       error: function (result){
           //alert(result);
       }
    });
    loadPage('roomsEdit.html','Salles');
}

function deleteRoom(id){ 
    $.ajax({
       url: "http://"+serverURL+"/rest/entities.rooms/" + id,
       type: 'DELETE',
       success: function (result){
           // redirect to the search page
           alert("Deletion is a success!");
           loadPage('roomsEdit.html','Salles');
       }
   });
}