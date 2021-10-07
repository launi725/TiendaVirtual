<%-- 
    Document   : index
    Created on : 30/09/2021, 12:40:49 p. m.
    Author     : neisalg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <title>JSP Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">        
        <link rel ="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">        
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
        <style>            
        </style>
    </head>
    
    <style type = "text/css">
        .message {
            color: #FA787E;
        }
        form.ng-submitted input.ng-invalid{
            border-color: #FA787E;
        }
        form input.ng-invalid.ng-touched{
            border-color: #FA787E;
        }       
    </style>
        
    <body>        
        <div class="container-fluid" ng-app="TestPro" ng-controller="productosController as pn">            
            <form name="userForm" novalidate="">                
            
                <div class="row">
                    <div class="col-12">
                        <center><h1>Tienda Virtual - mibarrio.com</h1></center>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <h3>Sección 1 - Ingreso de Datos</h3>
                        <div class="row">
                            <div class="col-6">
                                <label> Id. de Producto </label>
                                <input name="idProducto" class="form-control" type="number" min="0" ng-model="pn.idproducto" ng-model-options="{updateOn: 'blur'}" required>
                            </div>
                            <div class="col-6">
                                <label> Nombre </label>
                                <input name="nombre" class="form-control" type="text" min="0" ng-model="pn.nombre" ng-model-options="{updateOn: 'blur'}" required>
                            </div>
                            <div class="col-6">
                                <label> Descripción </label>
                                <input name="descripcion" class="form-control" type="text" min="0" ng-model="pn.descripcion" ng-model-options="{updateOn: 'blur'}" required>
                            </div>
                            <div class="col-6">
                                <label> Stock </label>
                                <input name="stock" class="form-control" type="number" min="0" ng-model="pn.stock" ng-model-options="{updateOn: 'blur'}" required>
                            </div>
                            <div class="col-6">
                                <label> Valor del Producto </label>
                                <input name="valor" class="form-control" type="number" min="0" ng-model="pn.valor" ng-model-options="{updateOn: 'blur'}" required>
                            </div>
                            <div class="col-6">
                                <label> Peso </label>
                                <input name="peso" class="form-control" type="text" min="0" ng-model="pn.peso" ng-model-options="{updateOn: 'blur'}" required>
                            </div>
                        </div>
                        <div><br></div>
                        <h3>Sección 2 - Botones</h3>
                        <div class="row">
                            <div class="col-3">
                                <input class="btn btn-success" type="submit" ng-click="pn.guardarProducto" value="Guardar Producto" ng-disabled="" />
                            </div>
                            <div class="col-3">
                                <button class="btn btn-primary" ng-click="pn.listarProducto">Listar producto</button>
                            </div>
                            <div class="col-3">
                                <button class="btn btn-danger" ng-click="pn.borrarProducto">Eliminar producto</button>
                            </div>
                            <div class="col-3">
                                <button class="btn btn-warning" ng-click="pn.actualizarProducto">Actualizar producto</button>
                            </div>
                        </div>
                        <div><br></div>
                        <div class="row">
                            <div class="col-12 table-responsive-x1">
                                <h3>Sección 3 - Listado de Productos</h3>
                                <table class="table table-striped table-hover">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th>Id. de Producto</th>
                                            <th>Nombre</th>
                                            <th>Descripcion</th>
                                            <th>Stock</th>
                                            <th>Valor</th>
                                            <th>Peso</th>
                                        </tr>
                                    </thead>
                                    <tr ng-repeat="producto in pn.producto">
                                        <td>{{producto.idproducto}}</td>
                                        <td>{{producto.nombre}}</td>
                                        <td>{{producto.descripcion}}</td>
                                        <td>{{producto.stock}}</td>
                                        <td>{{producto.valor}}</td>
                                        <td>{{producto.peso}}</td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                
                </div>
            </form>
    </body>
    <script>
        angular.module('testPro', []).controller('productosController', ['$scope', function ($scope){
                
            $scope.user = {};
            
            $scope.update = function () {
                console.log($scope.user);
            };
            
            $scope.reset = function (form) {
                $scope.user = {};
                if (form) {
                    form.$setPristine();
                    form.$setUntouched();
                }
            };
            
            $scope.reset();            
        }]);
        
        var app = angular.module('testPro', []);
        app.controller('productosController', ['$http', controladorProductos]);
        
        function validar(){
            return true;
        }
        
        function controladorProductos ($http){
            
            var pn = this;
            
            pn.listarProducto = function () {
                var url = "Peticiones.jsp";
                var params = {
                    proceso: "listarproducto"
                };
                $http({
                    method: 'POST',
                    url: 'Peticiones.jsp',
                    params: params
                }).then(function (res) {
                   pn.productos = res.data.Producto;
                    });
                };
            
            pn.guardarProducto = function () {
                
                var producto = {
                    proceso: "guardarproducto",
                    idproducto: pn.idProducto,
                    nombre: pn.nombre,
                    descripcion: pn.descripcion,
                    stock: pn.stock,
                    valor: pn.valor,
                    peso: pn.peso
                    };
                    $http({
                        method: 'POST',
                        url: 'Peticiones.jsp',
                        params: producto
                    }).then(function (res) {
                        if (res.data.ok === true) {
                            if (res.data[producto.proceso] === true) {
                                alert("Producto guardado con exito");
                            } else {
                                alert("Por favor verifique sus datos");
                            }
                        } else {
                            alert(res.data.errorMsg);
                        }
                    });
                };
                
                pn.borrarProducto = function () {
                    var producto = {
                        proceso: "borrarproducto",
                        idproducto: pn.idProducto
                    };
                    $http({
                        method: 'POST',
                        url: 'Peticiones.jsp',
                        params: producto
                        
                    }).then(function (res){
                        if (res.data.ok === true) {
                            if (res.data[producto.proceso] === true) {
                                alert("Producto eliminado con éxito");
                            } else {
                                alert("Por favor verifique sus datos");
                            }
                        } else {
                            alert(res.data.errorMsg);
                        }    
                    });
                };
                
                pn.actualizarProducto = function () {
                    var producto = {
                        proceso: "actualizarproducto",
                        idproducto: pn.idProducto,
                        nombre: pn.nombre,
                        descripcion: pn.descripcion,
                        stock: pn.stock,
                        valor: pn.valor,
                        peso: pn.peso
                    };
                    $http({
                        method: 'POST',
                        url: 'Peticiones.jsp',
                        params: producto
                    }).then(function (res) {
                        if (res.data.ok === true) {
                            if (res.data[producto.proceso] === true) {
                                alert("Producto actualizado con éxito");
                            } else {
                                alert("Por favor verifique sus datos");
                            }
                        } else {
                            alert (res.data.errorMsg);
                        }                        
                    });      
                };                
                
            };                             
    </script>        
</html>
