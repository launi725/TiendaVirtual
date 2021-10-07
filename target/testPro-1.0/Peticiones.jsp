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
    } else if (proceso.equals("guardarproducto")){
            //int idProducto = Integer.parseInt(request.getParameter("idProducto"));            
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            int stock = Integer.parseInt(request.getParameter("stock"));            
            int valor = Integer.parseInt(request.getParameter("valor"));
            String peso = request.getParameter("peso");

            boolean favorito = Boolean.parseBoolean(request.getParameter("favorito"));

            p.guardarProducto(nombre, descripcion, stock, valor, peso);
            if (p.guardarProducto(nombre, descripcion, stock, valor, peso)){
                respuesta += "\"" + proceso + "\": true";
            } else{
                respuesta += "\"" + proceso + "\": false";
            }        
    } else if (proceso.equals("borrarproducto")){
            int idProducto = Integer.parseInt(request.getParameter("idProducto"));
            if (p.borrarProducto(idProducto)){
                respuesta += "\"" + proceso + "\": true";
            } else{
                respuesta += "\"" + proceso + "\": false";       
            }
    } else if (proceso.equals("actualizarproducto")){
            int idProducto = Integer.parseInt(request.getParameter("idProducto"));            
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            int stock = Integer.parseInt(request.getParameter("stock"));            
            int valor = Integer.parseInt(request.getParameter("valor"));
            String peso = request.getParameter("peso");  

            boolean favorito = Boolean.parseBoolean(request.getParameter("favorito"));
            p.guardarProducto(nombre, descripcion, stock, valor, peso);
            
            if (p.actualizarProducto(idProducto)){
                respuesta += "\"" + proceso + "\": true";
            } else{
                respuesta += "\"" + proceso + "\": false";
            }                        
    } else {
        
        respuesta +="\"OK\": false,";
        respuesta +="\"ERROR\": \"INVALID\",";
        respuesta +="\"ERRORMSG\": \"Lo sentimos, los datos que ha enviado,"
        + " son invalidos. Corrijalos y vuelva a intentar por favor.\"";
        
    } 
    
        
    respuesta += "}";
    response.setContentType("application/json;charset=iso-8859-1");
    out.print(respuesta);
%>