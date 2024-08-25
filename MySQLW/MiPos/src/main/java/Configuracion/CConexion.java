/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Configuracion;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author ADRIANA
 */
public class CConexion {
    Connection conectar = null;
    
    String usuario="root";
    String contrasenia="adrianacht_1";
    String bd="dbpos";
    String ip="localhost";
    String puerto="3306";
    
    String cadena="jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection estableceConexion(){
        try{
           Class.forName("com.mysql.jdbc.Driver");
           conectar = DriverManager.getConnection(cadena,usuario,contrasenia);
           JOptionPane.showMessageDialog(null,"Conexion correcta a la base de datos");
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,"NO SE CONECTO A LA BASE DE DATOS:"+e.toString());
        }
        return conectar; 
    }
   
    public void cerrarConexion(){
        try{
            if(conectar!=null && !conectar.isClosed()){
                conectar.close();
                JOptionPane.showMessageDialog(null, "La conexion fue cerrada");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "NO SE LOGRO CERRAR LA CONEXION: "+e.toString());
        }
    }

    public Object establecerConexion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }  
}
