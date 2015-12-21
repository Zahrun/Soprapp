// this file is made to control all the interactions from the "Utilisateurs" section of the webpage

// REST api -> GET
function getUserList(){
    var userList;
    $.get(
       "http://localhost:8080/webapp/rest/entities.users",
       function(data){
           $(".mainContainer").html(data);
           alert("done");
       },
       "json"
    );
}

function initUserEdit(){
    alert("ok");
}
