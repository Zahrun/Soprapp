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

        resultDiv.append("<div id='" + tmpData.userID + "' class='userOverview'>"
            + "<div><img src='" + iconImg + "'/></div>"
            + "<div class='informations'>"
            + "<div id='name'>" + tmpData.name + "</div><div id='surname'>" + tmpData.surname + "</div>"
            + "<div id='mailAddress'>" + tmpData.mailAddress + "</div>"
            + "</div></div>");
    }
}
