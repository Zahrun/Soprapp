/* global Room, Rooms */

// this file is made to control all the interactions from the "Salles" section of the webpage

// REST api -> GET
function getRoomList() {
    var roomList;
    $.get(
        "http://localhost:8080/webapp/rest/entities.rooms",
        function (data) {
            drawRoomList(data);
        },
        "json"
    );
}

//var userImg;
//var adminImg; IMAGES spéciales à ajouter après par rapport aux types de salles?
function initRoomEdit() {

    // preload the images
    //userImg = new Image();
    //adminImg = new Image();

    //userImg.src = "media/userIcon.png";
   // adminImg.src = "media/adminIcon.png";
    
    // load the initial list
    searchRooms();

}

function searchRooms() {
    var searchString = $("#searchString").val();
    var selectedOption = $("input[name='searchOption']:checked").attr("id");
    var url = "http://localhost:8080/webapp/rest/entities.users/filterUsers";
    switch (selectedOption) {
        case "all":
            if (searchString !== "")
                url += "/" + searchString + "/" + searchString + "/" + searchString;
            else
                url = "http://localhost:8080/webapp/rest/entities.rooms";
            break;
 // autres cas de recherche à préciser !
      /*  case "name":
            url += "Name/" + searchString;
            break;
        case "surname":
            url += "Surname/" + searchString;
            break;
        case "mail":
            url += "Mail/" + searchString;
            break;
        default:
            url += "";
            break;*/
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
    var iconImg;
    for (var i = 0; i < roomList.length; i++) {
        tmpData = roomList[i];
    /*    if (tmpData.admin)
            iconImg = adminImg.src;
        else
            iconImg = userImg.src; */

        resultDiv.append("<div id='" + tmpData.roomID + "' class='roomOverview' onclick='editRoom(" + tmpData.roomID + ")' >"
            + "<div class='informations'>"
            + "<div id='number'>" + tmpData.number + "</div>"
            + "<div id='capacity'>" + tmpData.capacity + "</div>"
            + "</div></div>");
    }
}

function createRoom(){
    // retrieve the parameters from the form
    var name = $('input[name = "name"]').val();
    var surname = $('input[name = "surname"]').val();
    var mail = $('input[name = "mail"]').val();
    var pwd = $('input[name = "pwd"]').val();
    var admin = $('input[name = "admin"]').is(":checked");
    
    // sending the put request with the parameters
    $.ajax({
       url: "http://localhost:8080/webapp/rest/entities.users/js",
       type: 'POST',
       data: {
           admin: admin,
           name: name,
           surname: surname,
           pwd: pwd,
           mail: mail   
       },
       success: function (){
           loadPage('searchUsers.html','Utilisateurs');
       },
       error: function(error){
           alert(error);
       }
    });
    
    // disable redirection
    return false;
}

function editRoom(id){
    loadPage("http://localhost:8080/webapp/editRooms.jsp?id=" + id, "Salles");
}

function updateRoom(id){
    
    // retrieve the parameters from the form
    var name = $('input[name = "name"]').val();
    var surname = $('input[name = "surname"]').val();
    var mail = $('input[name = "mail"]').val();
    var pwd = $('input[name = "pwd"]').val();
    var admin = $('input[name = "admin"]').is(":checked");
    
    $.ajax({
       url: "http://localhost:8080/webapp/rest/entities.users/js/" + id,
       type: 'PUT',
       data: {
           name: name,
           surname: surname,
           mail: mail,
           pwd: pwd,
           admin: admin
       },
       success: function (){
           loadPage('searchUsers.html','Utilisateurs');
       },
       error: function (result){
           //alert(result);
       }
    });
    loadPage('searchUsers.html','Utilisateurs');
}

function deleteRoom(id){
    $.ajax({
       url: "http://localhost:8080/webapp/rest/entities.rooms/" + id,
       type: 'DELETE',
       success: function (result){
           // redirect to the search page
           alert("Deletion is a success!");
           loadPage('roomsEdit.html','Salles');
       }
   });
}