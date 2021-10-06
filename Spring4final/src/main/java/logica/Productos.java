/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.sql.ResultSet;
import persistencia.ConnectionBD;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;

public class Productos {
    private int id_productos;
    private String NombreProducto;
    private int CantidadStock;
    private String DescripcionProducto;
    private int PrecioUnidad;
    private String Peso; 

    public Productos(int id_productos, String NombreProducto, int CantidadStock, String DescripcionProducto, int PrecioUnidad, String Peso) {
        this.id_productos = id_productos;
        this.NombreProducto = NombreProducto;
        this.CantidadStock = CantidadStock;
        this.DescripcionProducto = DescripcionProducto;
        this.PrecioUnidad = PrecioUnidad;
        this.Peso = Peso;
    }

    public Productos() {
    }

    public int getId_productos() {
        return id_productos;
    }

    public void setId_productos(int id_productos) {
        this.id_productos = id_productos;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String NombreProducto) {
        this.NombreProducto = NombreProducto;
    }

    public int getCantidadStock() {
        return CantidadStock;
    }

    public void setCantidadStock(int CantidadStock) {
        this.CantidadStock = CantidadStock;
    }

    public String getDescripcionProducto() {
        return DescripcionProducto;
    }

    public void setDescripcionProducto(String DescripcionProducto) {
        this.DescripcionProducto = DescripcionProducto;
    }

    public int getPrecioUnidad() {
        return PrecioUnidad;
    }

    public void setPrecioUnidad(int PrecioUnidad) {
        this.PrecioUnidad = PrecioUnidad;
    }

    public String getPeso() {
        return Peso;
    }

    public void setPeso(String Peso) {
        this.Peso = Peso;
    }
    public boolean guardarProducto(){
        ConnectionBD conexion = new ConnectionBD();     
    String sentencia = "INSERT INTO productosstock(id_Productos,NombreProdducto,CantidadStock,DescripciónProducto,PrecioUnidad,Peso) VALUES('"+this.id_productos+"',"+this.NombreProducto+",'"+this.CantidadStock+"',"+this.DescripcionProducto+","+this.PrecioUnidad+","+this.Peso+");"; 
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.insertarBD(sentencia)) {
                conexion.commitBD();
                conexion.cerrarConexion();
                return true;
            } else {
                conexion.rollbackBD();
                conexion.cerrarConexion();
                return false;
            }
        } else {
            conexion.cerrarConexion();
            return false;
        }
    }
public boolean borrarProducto(int id_Productos) {
        String Sentencia = "DELETE FROM `productosstock` WHERE `id_Productos`='" + id_Productos + "'";
        ConnectionBD conexion = new ConnectionBD();
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.actualizarBD(Sentencia)) {
                conexion.commitBD();
                conexion.cerrarConexion();
                return true;
            } else {
                conexion.rollbackBD();
                conexion.cerrarConexion();
                return false;
            }
        } else {
            conexion.cerrarConexion();
            return false;
        }
    }
    public boolean actualizarProducto() {
        ConnectionBD conexion = new ConnectionBD();
        String Sentencia = "UPDATE `productosstock` SET NombreProducto='" + this.NombreProducto + "',CantidadStock='" + this.CantidadStock
                + "',DescripcionProducto='" + this.DescripcionProducto + "',PrecioUnidad='" + this.PrecioUnidad + "',Peso='" + this.Peso +  "' WHERE id_Productos=" + this.id_productos + ";";
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.actualizarBD(Sentencia)) {
                conexion.commitBD();
                conexion.cerrarConexion();
                return true;
            } else {
                conexion.rollbackBD();
                conexion.cerrarConexion();
                return false;
            }
        } else {
            conexion.cerrarConexion();
            return false;
        }
    }
    public List<Productos> listarProductos() throws SQLException {
        ConnectionBD conexion = new ConnectionBD();
        List<Productos> listaProductos;
        listaProductos = new ArrayList<>();
        String sql = "select * from productosstock order by id_producto asc";
        ResultSet rs = conexion.consultarBD(sql);
        Productos c;
        while (rs.next()) {
            c = new Productos();
            c.setId_productos(rs.getInt("id_Productos"));
            c.setNombreProducto(rs.getString("NombreProducto"));
            c.setCantidadStock(rs.getInt("CantidadStock"));
            c.setDescripcionProducto(rs.getString("DescripcionProducto"));
            c.setPrecioUnidad(rs.getInt("PrecioUnidad"));
            c.setPeso(rs.getString("Peso"));
            listaProductos.add(c);
        }
        conexion.cerrarConexion();
        return listaProductos;
    }
    public Productos getProducto() throws SQLException {
        ConnectionBD conexion = new ConnectionBD();
        String sql = "select * from productosstock where id_Productos='" + this.id_productos + "'";
        ResultSet rs = conexion.consultarBD(sql);
        if (rs.next()) {
            this.id_productos = rs.getInt("id_Productos");
            this.NombreProducto = rs.getString("NombreProducto");
            this.CantidadStock = rs.getInt("CantidadStock");
            this.DescripcionProducto = rs.getString("DescripcionProducto");
            this.PrecioUnidad = rs.getInt("PrecioUnidad");
            this.Peso = rs.getString("Peso");
            conexion.cerrarConexion();
            return this;

        } else {
            conexion.cerrarConexion();
            return null;
        }
}

    @Override
    public String toString() {
        return "Productos{" + "id_productos=" + id_productos + ", NombreProducto=" + NombreProducto + ", CantidadStock=" + CantidadStock + ", DescripcionProducto=" + DescripcionProducto + ", PrecioUnidad=" + PrecioUnidad + ", Peso=" + Peso + '}';
    }
    
    
}