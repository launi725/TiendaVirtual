package Persistencia;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;


/**
 *
 * @author neisalg
 */

public class ConexionDB {
    private String DB_driver = "";
    private String url = "";
    private String db = "";
    private String host = "";
    private String username = "";
    private String password = "";
    public Connection con = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public ConexionDB (){ 
        
        DB_driver = "com.mysql.cj.jdbc.Driver";
            
            host = "localhost:3306";
            db = "tiendadb";
            url = "jdbc:mysql://" + host + "/" + db;
            username = "root";
            password = "1413";
        
        try {
            Class.forName(DB_driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionDB.class.getName());
        }
        
        try {
            con = DriverManager.getConnection(url, username, password);
            con.setTransactionIsolation(8);
            System.out.println("CONECTADO A MYSQL: " + db);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName());
        }
    }
    
    public Connection getConnection(){ 
        return con;
    }
    
    public void closeConnection (Connection con){ 
        if (con != null){
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConexionDB.class.getName());
            }
        }
    }   
    
   public ResultSet consultarDB(String sentencia) {
        try {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sentencia);
        } catch (SQLException sqlex) {
        } catch (RuntimeException rex) {
        } catch (Exception ex) {
        }

        return rs;
    }
   
    public boolean insertarDB(String sentencia) {
        try {
            stmt = con.createStatement();
            stmt.execute(sentencia);
        } catch (SQLException | RuntimeException sqlex) {
            System.out.println("ERROR RUTINA: " + sqlex);
            return false;
        }
        return true;
    }

    public boolean borrarDB(String sentencia) {
        try {
            stmt = con.createStatement();
            stmt.execute(sentencia);
        } catch (SQLException | RuntimeException sqlex) {
            System.out.println("ERROR RUTINA: " + sqlex);
            return false;
        }
        return true;
    }
  
    public boolean actualizarDB(String sentencia) {
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(sentencia);
        } catch (SQLException | RuntimeException sqlex) {
            System.out.println("ERROR RUTINA: " + sqlex);
            return false;
        }
        return true;
    }

    public boolean setAutoCommitDB(boolean parametro) {
        try {
            con.setAutoCommit(parametro);
        } catch (SQLException sqlex) {
            System.out.println("Error al configurar el autoCommit " + sqlex.getMessage());
            return false;
        }
        return true;
    }

    public void cerrarConexion() {
        closeConnection(con);
    }

    public boolean commitDB() {
        try {
            con.commit();
            return true;
        } catch (SQLException sqlex) {
            System.out.println("Error al hacer commit " + sqlex.getMessage());
            return false;
        }
    }

    public boolean rollbackDB() {
        try {
            con.rollback();
            return true;
        } catch (SQLException sqlex) {
            System.out.println("Error al hacer rollback " + sqlex.getMessage());
            return false;
        }
    }

   /*public static void main(String[] args) {
        ConexionDB b = new ConexionDB();
        b.cerrarConexion();
    }*/
    
}
