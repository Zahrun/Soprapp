/* global Site, Sites */

// this file is made to control all the interactions from the "Sites" section of the webpage

// REST api -> GET
function getSiteList() {
    var siteList;
    $.get(
        "http://localhost:8080/webapp/rest/entities.sites",
        function (data) {
            drawSiteList(data);
        },
        "json"
    );
}

//var userImg;
//var adminImg; IMAGES spéciales à ajouter après par rapport aux types de salles?
function initSiteEdit() {

    // preload the images
    //userImg = new Image();
    //adminImg = new Image();

    //userImg.src = "media/userIcon.png";
   // adminImg.src = "media/adminIcon.png";
    
    // load the initial list
    searchSites();

}

function searchSites() {
    var searchString = $("#searchString").val();
    var selectedOption = $("input[name='searchOption']:checked").attr("id");
    var url = "http://localhost:8080/webapp/rest/entities.sites/filterSites";
    switch (selectedOption) {
        case "all":
            if (searchString !== "")
                url += "/" + searchString + "/" + searchString + "/" + searchString;
            else
                url = "http://localhost:8080/webapp/rest/entities.sites";
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
            drawSiteList(data);
        },
        "json"
    );
}

function drawSiteList(siteList){
    var resultDiv = $("#searchResult");
    resultDiv.html("");
    var tmpData;
    var iconImg;
    for (var i = 0; i < siteList.length; i++) {
        tmpData = siteList[i];
    /*    if (tmpData.admin)
            iconImg = adminImg.src;
        else
            iconImg = userImg.src; */

        resultDiv.append("<div id='" + tmpData.siteID + "' class='roomOverview' onclick='editSite(" + tmpData.siteID + ")' >"
            + "<div class='informations'>"
            + "<div id='name'>" + tmpData.name + "</div>"
            + "<div id='address'>" + tmpData.address + "</div>"
            + "<div id='description'>" + tmpData.description + "</div>"
            + "</div></div>");
    }
}

function createSite(){ 
    // retrieve the parameters from the form
    var name = $('input[name = "name"]').val();
    var address = $('input[name = "address"]').val();
    var description = $('input[name = "description"]').val();
    
    // sending the put request with the parameters
    $.ajax({
       url: "http://localhost:8080/webapp/rest/entities.sites/js",
       type: 'POST',
       data: {
           name: name,
           address: address,
           description: description  
       },
       success: function (){
           loadPage('sitesEdit.html','Sites');
       },
       error: function(error){
           alert(error);
       }
    });
    
    // disable redirection
    return false;
}

function editSite(id){ // TODO
    loadPage("http://localhost:8080/webapp/editRooms.jsp?id=" + id, "Salles");
}

function updateSite(id){ // TODO
    
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

function deleteSite(id){ // TODO
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