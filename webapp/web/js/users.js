/* global User, Users */

// this file is made to control all the interactions from the "Utilisateurs" section of the webpage
 
    var serverURL="176.31.1.146:8080/webapp";
    
// REST api -> GET
function getUserList() {
    $.get(
        "http://"+serverURL+"/rest/entities.users",
        function (data) {
            drawUserList(data);
        },
        "json"
    );
}

var userImg;
var adminImg;
function initUserEdit() {

    // preload the images
    userImg = new Image();
    adminImg = new Image();

    userImg.src = "media/userIcon.png";
    adminImg.src = "media/adminIcon.png";
    
    // load the initial list
    searchUsers();

}

function searchUsers() {
    var searchString = $("#searchString").val();
    var selectedOption = $("input[name='searchOption']:checked").attr("id");
    var url = "http://"+serverURL+"/rest/entities.users/filterUsers";
    switch (selectedOption) {
        case "all":
            if (searchString !== "")
                url += "/" + searchString + "/" + searchString + "/" + searchString;
            else
                url = "http://"+serverURL+"/rest/entities.users";
            break;
        case "name":
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
            break;
    }

    $.get(
        url,
        function (data) {
            drawUserList(data);
        },
        "json"
    );
}

function drawUserList(userList){
    var resultDiv = $("#searchResult");
    resultDiv.html("");
    var tmpData;
    var iconImg;
    for (var i = 0; i < userList.length; i++) {
        tmpData = userList[i];
        if (tmpData.admin)
            iconImg = adminImg.src;
        else
            iconImg = userImg.src;

        resultDiv.append("<div id='" + tmpData.userID + "' class='objectOverview userOverview' onclick='editUser(" + tmpData.userID + ")' >"
            + "<div><img src='" + iconImg + "'/></div>"
            + "<div class='informations'>"
            + "<div id='name'>" + tmpData.name + "</div><div id='surname'>" + tmpData.surname + "</div>"
            + "<div id='mailAddress'>" + tmpData.mailAddress + "</div>"
            + "</div></div>");
    }
}

function createUser(){
    // retrieve the parameters from the form
    var name = $('input[name = "name"]').val();
    var surname = $('input[name = "surname"]').val();
    var mail = $('input[name = "mail"]').val();
    var pwd = $('input[name = "pwd"]').val();
    var admin = $('input[name = "admin"]').is(":checked");
    
    // sending the put request with the parameters
    $.ajax({
       url: "http://"+serverURL+"/rest/entities.users/js",
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

function editUser(id){
    loadPage("http://"+serverURL+"/editUsers.jsp?id=" + id, "Utilisateurs");
}

function updateUser(id){
    
    // retrieve the parameters from the form
    var name = $('input[name = "name"]').val();
    var surname = $('input[name = "surname"]').val();
    var mail = $('input[name = "mail"]').val();
    var pwd = $('input[name = "pwd"]').val();
    var admin = $('input[name = "admin"]').is(":checked");
    
    $.ajax({
       url: "http://"+serverURL+"/rest/entities.users/js/" + id,
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

function deleteUser(id){
    $.ajax({
       url: "http://"+serverURL+"/rest/entities.users/" + id,
       type: 'DELETE',
       success: function (result){
           // redirect to the search page
           alert("Deletion is a success!");
           loadPage('searchUsers.html','Utilisateurs');
       }
   });
}