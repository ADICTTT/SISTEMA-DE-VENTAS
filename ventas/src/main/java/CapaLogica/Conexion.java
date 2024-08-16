/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogica;
import java.sql.DriverManager;
import java.sql.Connection;
import javax.swing.JOptionPane;
/**
 *
 * @author ADRIANA
 */
public class Conexion {
    Connection conectar = null;
    
    String usuario = "user";
    String contrasenia = "1234";
    String bd = "db_ventas";
    String ip = "localhost";
    String puerto = "1433";
    
    String cadena = "jdbc:sqlserver://"+ip+":"+puerto+"/"+bd;
    
    public Connection establecerConexion(){
        try{
            String cadena = "jdbc:sqlserver://localhost:"+puerto+";"+"databaseName"+bd;
            conectar= DriverManager.getConnection(cadena,usuario,contrasenia);
            JOptionPane.showMessageDialog(null, "se conecto correctamente a la base de datos");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos, error: "+e.toString());
        }
        return conectar;
    }
}
