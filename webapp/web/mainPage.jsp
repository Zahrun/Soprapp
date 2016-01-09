<%-- 
    Document   : mainPage
    Created on : 21 déc. 2015, 18:36:49
    Author     : gautierenaud
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sopra Admin Page</title>

        <link href="css/bootstrap.min.css" rel="stylesheet" />
        <link href="css/starter-template.css" rel="stylesheet" />
        <link href="css/simple-sidebar.css" rel="stylesheet" />
        <link href="css/main.css" rel="stylesheet" />
    </head>
    <body onload="loadPage('sitesEdit.html','Sites')">
        <!-- navigation bar for all the edit sections -->
        <nav class="navbar navbar-inverse navbar-fixed-top" >
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#" onclick="toggleMenu()">
                        <span id="navIcon" class="glyphicon glyphicon-menu-hamburger" aria-hidden="true"></span>
                        Sopradmin
                    </a>
                </div>
                <div id="navbar" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="active" id="Sites" onclick="loadPage('sitesEdit.html','Sites')"><a href="#">Sites</a></li>
                        <li id="Salles" onclick="loadPage('roomsEdit.html','Salles')"><a href="#">Salles</a></li>
                        <li id="Utilisateurs" onclick="loadPage('searchUsers.html','Utilisateurs')"><a href="#">Utilisateurs</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </nav> <!-- fin de la barre de navigation -->
        
        <div id="wrapper">
            <!-- Sidebar -->
            <div id="sidebar-wrapper">
                <ul class="sidebar-nav">
                    <!-- list of options will come here -->
                </ul>
            </div>
            <!-- /#sidebar-wrapper -->
            
            <!-- Content -->
            <div id="page-content-wrapper">
                <div class="container-fluid"> <!-- pour que le contenu s'adapte -->
                    <div class="mainContainer container"> <!-- here we put the content of the site -->

                    </div>
                </div>
            </div>
        </div>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/variables.js"></script>
        <script src="js/main.js"></script>
        <script src="js/users.js"></script>
        <script src="js/rooms.js"></script> 
        <script src="js/sites.js"></script>
    </body>
</html>
