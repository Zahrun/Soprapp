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


function initSiteEdit() {


    
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

        resultDiv.append("<div id='" + tmpData.siteID + "' class='siteOverview' onclick='editSite(" + tmpData.siteID + ")' >"
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

function editSite(id){ 
    loadPage("http://localhost:8080/webapp/editSites.jsp?id=" + id, "Sites");
}

function updateSite(id){ 
    
    // retrieve the parameters from the form
    var name = $('input[name = "name"]').val();
    var address = $('input[name = "address"]').val();
    var description = $('input[name = "description"]').val();
    
    $.ajax({
       url: "http://localhost:8080/webapp/rest/entities.sites/js/" + id,
       type: 'PUT',
       data: {
           name: name,
           address: address,
           description: description
       },
       success: function (){
           loadPage('editSites.html','Sites');
       },
       error: function (result){
           //alert(result);
       }
    });
    loadPage('editSites.html','Sites');
}

function deleteSite(id){
    $.ajax({
       url: "http://localhost:8080/webapp/rest/entities.sites/" + id,
       type: 'DELETE',
       success: function (result){
           // redirect to the search page
           alert("Deletion is a success!");
           loadPage('sitesEdit.html','Sites');
       }
   });
}