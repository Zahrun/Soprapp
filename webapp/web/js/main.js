/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// default selection
var selectedNavItem = "Sites";
function loadPage(url, newActiveNavItem){
    //charge le contenu de l'url (html ou jsp) dans la div correspondante
    $(".mainContainer").load(url);
    
    // change le menu activé
    if (selectedNavItem !== newActiveNavItem){
        $("#" + selectedNavItem).toggleClass("active");
        $("#" + newActiveNavItem).toggleClass("active");
        selectedNavItem = newActiveNavItem;
        
        // charge les options du side bar
        loadSideBar(newActiveNavItem);
    }
}

var sideBar = $(".sidebar-nav");
function loadSideBar(id){
    sideBar.empty();
    var tmpList;
    var tmpLinkList;
    switch (id){
        case "Utilisateurs":
            tmpList = userOptions;
            tmpLinkList = userLinks;
            break;
        default:
            alert(id + " not defined yet");
            break;
    }

    // title of the Side Bar
    sideBar.append("<li class='sidebar-brand'><a>" + id + "</a></li>");
    // load the content of the list
    for (var i in tmpList){
        sideBar.append("<li><a href='#' onclick=\"loadPage('" + tmpLinkList[i] + "','" + id + "')\">" + tmpList[i] + "</a></li>");
    }
}

function toggleMenu(){
    $("#wrapper").toggleClass("toggled");
}

// sample for put request (not tested!)
function put(url){
    $.ajax({
       url: url,
       type: 'PUT',
       success: function (result){
           // do something
       }
    });
}