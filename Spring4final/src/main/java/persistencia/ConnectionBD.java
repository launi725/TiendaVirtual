/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package persistencia;

/**
 *
 * @author User
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ConnectionBD {
        
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
    
    public ConnectionBD(){
        DB_driver = "com.mysql.cj.jdbc.Driver";
            host = "localhost:3306";
            db = "bdtiendavirtual";
            url = "jdbc:mysql://" + host + "/" + db;
            username = "root";
            password = "1234";
        
        try {
            Class.forName(DB_driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionBD.class.getName());
        }
        
        try {
            con = DriverManager.getConnection(url, username, password);
            con.setTransactionIsolation(8);
            System.out.println("CONECTADO A MYSQL: " + db);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionBD.class.getName());
        }
    }
   
    public Connection getConnection(String[] args) {
        return con;
    }
    public void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
        
    public ResultSet  consultarBD (String sentencia){
       try {
            stmt=con.createStatement();
            rs=stmt.executeQuery(sentencia);
        } catch (SQLException sqlex){
            System.out.println(sqlex.getMessage());

        } catch(RuntimeException rex){
            System.out.println(rex.getMessage());

        } catch (Exception ex){
            System.out.println(ex.getMessage ());
        }
       return rs;
    }
    public boolean insertarBD (String sentencia){
        try {
            stmt=con.createStatement();
            stmt.execute (sentencia);
    
        }catch (SQLException | RuntimeException sqlex){
            System.out.println("Error rutina: "+ sqlex.getMessage());
            return false;
        }
        return true;
     }
    public boolean actualizarBD (String sentencia){
        try {
            stmt=con.createStatement();
            stmt.executeUpdate(sentencia);
        }catch (SQLException | RuntimeException sqlex){
            System.out.println("Error rutina: "+ sqlex.getMessage());
            return false;
        }
        return true;
   }  
    public boolean borrarBD (String sentencia){
        try {
           stmt=con.createStatement();
           stmt.execute(sentencia);
        }catch (SQLException | RuntimeException sqlex){
            System.out.println("Error rutina: "+ sqlex.getMessage());
            return false;
        }
        return true;
   }
    public boolean setAutoCommitBD(boolean parametro) {
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

    public boolean commitBD() {
        try {
            con.commit();
            return true;
        } catch (SQLException sqlex) {
            System.out.println("Error al hacer commit " + sqlex.getMessage());
            return false;
        }
    }

    public boolean rollbackBD() {
        try {
            con.rollback();
            return true;
        } catch (SQLException sqlex) {
            System.out.println("Error al hacer rollback " + sqlex.getMessage());
            return false;
        }
    }

   /* public static void main(String[] args) {
        ConnectionBD.;
       b.cerrarConexion();
    }*/
}

