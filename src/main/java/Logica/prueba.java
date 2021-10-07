package Logica;

import Persistencia.ConexionDB;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class prueba {
   
    public static void main(String[] args) {
      
        ConexionDB cn = new ConexionDB();      
        Producto p = new Producto();
        
        try {
            p.listarProductos();
            System.out.println(p.listarProductos());
            System.out.println("===DEBIO IMPRIMIRSE LA LISTA, SINO CORREGIR===");
        } catch (SQLException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR EN METODO LISTAR PRODUCTOS");
        }
        /*
        String nombretest = "PRUEBA nombre PRODUCTO";
        String descripciontest = "PRUEBA descripcion PRODUCTO";
        int stocktest = 1000;
        int valortest = 123456;
        String pesotest = "PRUEBA peso PRODUCTO";
        
        Producto ptest =  new Producto();
        p.guardarProducto(nombretest, descripciontest, stocktest, valortest, pesotest);
        */
        
        Statement st;
        ResultSet rs;
        try {
          st = cn.con.createStatement();
          rs = st.executeQuery("SELECT nombre, descripcion, stock, valor, peso FROM tiendadb.producto");
          while(rs.next()){
              System.out.println(rs.getString("nombre") +";"+rs.getString("descripcion")+";"+rs.getInt("stock")+";"+rs.getInt("valor")+";"+rs.getString("peso"));              
          }
          cn.con.close();
        } catch (Exception e) {
            System.err.println("Error - MAIN Listar: " + e);
        }
    }
    
    
}
