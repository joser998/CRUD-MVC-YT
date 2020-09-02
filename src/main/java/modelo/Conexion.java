/*Esta clase es para hacer la conexion con nuestra base de datos
  Se encarga de todos los datos, consultas con nuestra base de datos, actualizaciones
  y busquedas*/
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexion {
    /* Variables de tipo global para conexion con Base de Datos */
    public static final String URL = "jdbc:mysql://localhost/escuela2?useSSL=false&serverTimezone=UTC";
    public static final String usuario = "root"; 
    public static final String contraseña = "admin";
    
    /*Metodo para hacer Conexion con Base de Datos */
    public Connection getConnection(){
        Connection conexion = null;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = (Connection) DriverManager.getConnection(URL, usuario, contraseña);
            JOptionPane.showMessageDialog(null, "Conexion Exitosa con DB");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return conexion;
    }
}