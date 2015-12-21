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
    </head>
    <body>
        <!-- navigation bar for all the edit sections -->
        <nav class="navbar navbar-inverse navbar-fixed-top" >
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">
                        Sopradmin
                    </a>
                </div>
                <div id="navbar" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="active" id="Sites"><a href="#">Sites</a></li>
                        <li id="Salles"><a href="#">Salles</a></li>
                        <li id="Utilisateurs"><a href="usersEdit.html">Utilisateurs</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </nav> <!-- fin de la barre de navigation -->
        
        <div class="mainContainer"> <!-- here we put the content of the site -->
            
        </div>
        
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    </body>
</html>
