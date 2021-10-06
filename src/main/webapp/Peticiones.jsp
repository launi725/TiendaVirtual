<%--
Document : Archivo de peticiones
Created on : 09 / 2021 , 03:40 :PM
Author : neisalg
--%>

<%@page import="java.util.logging.Logger"%>
<%@page import="java.sql.SQLException"%>
<%@page import="Logica.Producto"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Arrays"%>
<%@page import=" java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="application/json;charset=iso-8859-1" language ="java" pageEncoding ="iso-8859-1" session ="true"%>

<%   
    String respuesta = "{";

    List<String> tareas = Arrays.asList(new String []{

        "listarproducto",
        "guardarproducto",
        "borrarproducto",
        "actualizarproducto"
    });
    
    String proceso = "" + request.getParameter("proceso");
    Producto p = new Producto();
    
    if(tareas.contains(proceso)){        
  
        respuesta += "\"OK\": true,";
        if(proceso.equals("listarproducto")){
           try {
               List<Producto> listaProductos = p.listarProductos();
               respuesta += "\"" + proceso + "\": true,\"Producto\":" + new Gson().toJson(listaProductos);
           } catch (SQLException ex){
               respuesta +="\"" + proceso + "\": true,\"Producto\":[]";
               Logger.getLogger(Producto.class.getName());
           }            
        }
    } else if{ // MODIFICAR METODOS
        (proceso.equals("guardarProducto")){
            int id_Productos = Integer.parseInt(request.getParameter("idProductos"));            
            String NombreProducto = request.getParameter("NombreProducto");
            int CantidadStock = Integer.parseInt(request.getParameter("CantidadStock"));
            String DescripcionProducto = request.getParameter("DescripcionProducto");
            int PrecioUnidad = Integer.parseInt(request.getParameter("PrecioUnidad"));
            String Peso = request.getParameter("Peso");
            
            //c.llenarProducto(id_Productos, NombreProducto, CantidadStock,DescripcionProducto,PrecioUnidad,Peso);
            if (c.guardarProducto()){
                respuesta += "\"" + proceso + "\": true";
            } else {
                respuesta += "\"" + proceso + "\": false";
            }        
    }
    else{
        
        respuesta +="\"OK\": false,";
        respuesta +="\"ERROR\": \"INVALID\",";
        respuesta +="\"ERRORMSG\": \"Lo sentimos, los datos que ha enviado,"
        + " son invalidos. Corrijalos y vuelva a intentar por favor.\"";
        
    }
    
    
    
    respuesta += "}";
    response.setContentType("application/json;charset=iso-8859-1");
    out.print(respuesta);
%>