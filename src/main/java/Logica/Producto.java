package Logica;

/**
 *
 * @author neisalg
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Persistencia.ConexionDB;


public class Producto {
    private int idProducto;
    public String nombre;
    public String descripcion;
    public int stock;
    public int valor;
    public String peso;

    public Producto() {
        
    }

    public Producto getProducto(int idProducto) throws SQLException{
        this.idProducto = idProducto;
        return this.getProducto(idProducto);
    }

    public int getidProducto() {
        return idProducto;
    }

    public void setidProducto(int idProducto) {
        this.idProducto = idProducto;
    }   
        
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }
    
    public boolean guardarProducto(String nombre, String descripcion, int stock, int valor, String peso){
        ConexionDB conexion = new ConexionDB();
        
        String sentencia = "INSERT INTO producto (nombre, descripcion, stock, valor, peso) "
                + " VALUES ('" + this.nombre + "','" + this.descripcion + "','" + this.stock + "','"
                + this.valor + "','" + this.peso + "'); ";
        if (conexion.setAutoCommitDB(false)) {
            if (conexion.insertarDB(sentencia)) {
                conexion.commitDB();
                conexion.cerrarConexion();
                return true;
            } else {
                conexion.rollbackDB();
                conexion.cerrarConexion();
                return false;
            }
        } else {
            conexion.cerrarConexion();
            return false;
        }                
    }
    
    public boolean borrarProducto (int id_producto){
        
        String sentencia = "DELETE FROM producto WHERE idProducto = '"+ this.idProducto + "'"; 
        ConexionDB conexion = new ConexionDB();
        if (conexion.setAutoCommitDB(false)) {
            if (conexion.actualizarDB(sentencia)) {
                conexion.commitDB();
                conexion.cerrarConexion();
                return true;
            } else {
                conexion.rollbackDB();
                conexion.cerrarConexion();
                return false;
            }
        } else {
            conexion.cerrarConexion();
            return false;
        }
    }
    
    public boolean actualizarProducto(int idProducto){
        ConexionDB conexion = new ConexionDB();
        String sentencia = "UPDATE producto SET nombre='" +this.nombre+ "',descripcion='" +this.descripcion+ 
                "',stock='" +this.stock+ "',valor='" + this.valor + "',peso='" + this.peso +
                "' WHERE idProducto =" + this.idProducto + ";";
        if (conexion.setAutoCommitDB(false)) {
            if (conexion.actualizarDB(sentencia)) {
                conexion.commitDB();
                conexion.cerrarConexion();
                return true;
            } else {
                conexion.rollbackDB();
                conexion.cerrarConexion();
                return false;
            }
        } else {
            conexion.cerrarConexion();
            return false;
        }
    }
    
    public List<Producto> listarProductos() throws SQLException{
        
        ConexionDB conexion = new ConexionDB();
        List<Producto> listaProductos = new ArrayList<>();
        String sql = "SELECT * FROM producto order by idProducto asc";
        ResultSet rs = conexion.consultarDB(sql);
        Producto p;
        while (rs.next()){
            p = new Producto();
            p.setidProducto(rs.getInt("idProducto"));
            p.setNombre(rs.getString("nombre"));
            p.setDescripcion(rs.getString("descripcion"));
            p.setStock(rs.getInt("stock"));
            p.setValor(rs.getInt("valor"));
            p.setPeso(rs.getString("peso"));
            listaProductos.add(p);
                        
        }
        conexion.cerrarConexion();
        return listaProductos;
    }
    
    public Producto getProducto() throws SQLException {
        
        ConexionDB conexion = new ConexionDB();
        String sql = "SELECT * FROM producto WHERE idProducto='" + this.idProducto + "'";
        ResultSet rs = conexion.consultarDB(sql);
        if(rs.next()){
            this.idProducto = rs.getInt("idProducto");
            this.nombre = rs.getString("nombre");
            this.descripcion = rs.getString("descripcion");
            this.stock = rs.getInt("stock");
            this.valor = rs.getInt("valor");
            this.peso = rs.getString("peso");
            conexion.cerrarConexion();
            return this;            
        } else {
            
            conexion.cerrarConexion();
            return null;
        }  
    }
    
    @Override
    public String toString(){
        return "Producto{" + "idProducto=" + idProducto + ", nombre=" + nombre + ", descripcion=" + descripcion + ", stock=" + stock + ", valor=" + valor + ", peso=" + peso + '}';        
    }
}
