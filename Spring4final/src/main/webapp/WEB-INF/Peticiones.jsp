<%-- 
    Document   : Peticiones
    Created on : 4/10/2021, 6:59:43 p. m.
    Author     : User
--%>

<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.sql.SQLException"%>
<%@page import="logica.Productos"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.sql.SQLException"%>
<%@page import="logica.Productos"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="application/json;charset=iso-8859-1" language="java" pageEncoding="iso-8859-1" session="true"%>

<%   
    String respuesta = "{";
    List<String> tareas =Arrays.asList (new String[]{
        "actualizaproducto",
        "eliminarproducto",
        "listarproducto",
        "guardarproducto"
    });
    
    String proceso = "" + request.getParameter ("proceso");
    Productos c = new Productos ();
    
    if (tareas.contains(proceso)){
        if (proceso.equals("guardarProducto")){
            int id_Productos = Integer.parseInt(request.getParameter("id_Productos"));            
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
        
        } else if (proceso.equals("eliminarProductos")){
            int id_Productos = Integer.parseInt(request.getParameter("id_Productos"));
            if (c.borrarProducto(id_Productos)){
            respuesta += "\"" + proceso + "\": true";
            } else {
                respuesta += "\"" + proceso + "\": false";
            }    
        } else if (proceso.equals("listarproducto")){
            try {
                List<Productos> lista = c.listarProductos();
                respuesta += "\"" + proceso + "\": true, \"Productos\":" + new Gson().toJson(lista);
            } catch (SQLException ex) {
                respuesta += "\"" + proceso + "\": true,\"Productos\":[]";
                Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (proceso.equals ("actualizarproducto")) {
            int id_Productos = Integer.parseInt(request.getParameter("id_Productos"));            
            String NombreProducto = request.getParameter("NombreProducto");
            int CantidadStock = Integer.parseInt(request.getParameter("CantidadStock"));
            String DescripcionProducto = request.getParameter("DescripcionProducto");
            int PrecioUnidad = Integer.parseInt(request.getParameter("PrecioUnidad"));
            String Peso = request.getParameter("Peso");
            //c.llenarProducto(id_Productos, NombreProducto, CantidadStock,DescripcionProducto,PrecioUnidad,Peso);
            
            if (c.actualizarProducto()) {
                respuesta += "\"" + proceso + "\": true";
            } else {
                respuesta += "\"" + proceso + "\": false";
            }
        }
        
    }else{
        
        respuesta += "\"ok\" false,";
        respuesta += "\"error\": \"INVALID\",";
        respuesta += "\"errorMsg\": \"Lo sentimos, los datos que ha enviado,"
                  + " son inválidos. corrijalos y vuelva a intentar por favor.\""; 
        
    }
  
    respuesta += "}";
    response.setContentType("application/json;charset=iso-8859-1");
    out.print(respuesta);
%>
       