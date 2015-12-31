/* global User, Users */

// this file is made to control all the interactions from the "Utilisateurs" section of the webpage

// REST api -> GET
function getUserList() {
    var userList;
    $.get(
            "http://localhost:8080/webapp/rest/entities.users",
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
    var url = "http://localhost:8080/webapp/rest/entities.users/filterUsers";
    switch (selectedOption) {
        case "all":
            if (searchString !== "")
                url += "/" + searchString + "/" + searchString + "/" + searchString;
            else
                url = "http://localhost:8080/webapp/rest/entities.users";
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

        resultDiv.append("<div id='" + tmpData.userID + "' class='userOverview' onclick='editUser(" + tmpData.userID + ")' >"
            + "<div><img src='" + iconImg + "'/></div>"
            + "<div class='informations'>"
            + "<div id='name'>" + tmpData.name + "</div><div id='surname'>" + tmpData.surname + "</div>"
            + "<div id='mailAddress'>" + tmpData.mailAddress + "</div>"
            + "</div></div>");
    }
}

function createUser(){
    // retrieve the parameters from the form
    var name = $('[name = "name"]').val();
    var surname = $('[name = "surname"]').val();
    var mail = $('[name = "mail"]').val();
    var pwd = $('[name = "password"]').val();
    var admin = $(".admin").is(":checked");
    
    var user = {
        userID:null,
        name:$("input[name=name]").val(),
        surname:$("input[name=surname]").val(),
        mailAddress:$("input[name=mail]").val(),
        password:$("input[name=pwd]").val(),
        admin:$("input[name=admin]").is(":checked")
    };
    
    // sending the put request with the parameters
    $.ajax({
       url: "http://localhost:8080/webapp/rest/entities.users",
       type: 'POST',
       data: JSON.stringify(user),
       success: function (result){
           // do something
           alert("ok");
       },
       error: function(){
           alert("something went wrong");
       }
    });
}

function editUser(id){
    loadPage("http://localhost:8080/webapp/editUsers.jsp?id=" + id, "Utilisateurs");
}

function updateUser(id){
    
    var user = {
        userID:parseInt(id),
        name:$("input[name=name]").val(),
        surname:$("input[name=surname]").val(),
        mailAddress:$("input[name=mail]").val(),
        password:$("input[name=pwd]").val(),
        admin:$("input[name=admin]").is(":checked")
    };
    alert(JSON.stringify(user));
    $.ajax({
       url: "http://localhost:8080/webapp/rest/entities.users/" + id,
       type: 'PUT',
       data: JSON.stringify(user),
       success: function (result){
           // redirect to the search page
           alert("Modifiation is a success!");
           loadPage('searchUsers.html','Utilisateurs');
       },
       error: function (result){
           alert(result);
       }
    });
}