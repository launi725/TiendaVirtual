<%-- 
    Document   : index
    Created on : 4/10/2021, 8:05:43 a. m.
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP Page</title>
        <meta name="viewport" content="width=device-width; initial-scale=1.0">
        Bootstrap core CSS
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        Angular
        <script src = "https://ajax.googleapis.com/ajax/libs/angularjs/1.2.15/angular.min.js"></script> 
        <style>
            /*div {border-style: dotted;}*/
        </style>
    </head>
    <style type="text/css">
        .messages {
            color: #FA787E;
        }
        form.ng-submitted input.in-invalid{
            border-color: #FA787E;
        }
        form input.ng-invalid.ng-touched {
            border-color: #FA787E;
        }
    </style>
    <body>
        <div class="container-fluid" ng-app="Spring4final" ng-controller="productoController as cn">       
            <form name="userForm" novalidate>
                <div class="row">
                    <div class="col-12">
                        <center><h1>Registro de Stock</h1></center>
                    </div>
                <div class="row">
                    <div class="row">
                        <h3>Sección 1</h3>
                    </div>
                </div>
                <div class="row">
                        
                </div>
            </form>
        </div>      
    </body>
    <script>
        //codigo Angular
    </script>    
</html>
