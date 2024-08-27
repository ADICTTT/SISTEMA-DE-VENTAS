/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADRIANA
 */
public class ControladorCliente {
    public void MostrarClientes(JTable tablaTotalClientes){
        Configuracion.CConexion objetoConexion = new Configuracion.CConexion();
        
        Modelos.ModeloCliente  objetoCliente = new Modelos.ModeloCliente();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        String sql = "";
        
        modelo.addColumn("id");
        modelo.addColumn("Nombres");
        modelo.addColumn("ApPaterno");
        modelo.addColumn("ApMaterno");
        
        tablaTotalClientes.setModel(modelo);
        
        sql = "select cliente.idcliente,cliente.nombres,cliente.appaterno,cliente.apmaterno from cliente";
        
        try{
            Statement st = objetoConexion.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                objetoCliente.setIdCliente(rs.getInt("idcliente"));
                objetoCliente.setNombres(rs.getString("nombres"));
                objetoCliente.setApPaterno(rs.getString("appaterno"));
                objetoCliente.setApMaterno(rs.getString("apmaterno"));
                
                modelo.addRow(new Object[] {objetoCliente.getIdCliente(),objetoCliente.getNombres(),objetoCliente.getApPaterno(),objetoCliente.getApMaterno()});
            }
            tablaTotalClientes.setModel (modelo);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al mostrar usuarios, "+e.toString());
        }finally{
            objetoConexion.cerrarConexion();
        }
    }
    
    public void AgregarCliente(JTextField nombres, JTextField appaterno, JTextField apmaterno){
       Configuracion.CConexion objetoConexion = new Configuracion.CConexion();
       Modelos.ModeloCliente objetoCliente = new Modelos.ModeloCliente();
       
       String consulta = "insert into cliente (nombres,appaterno,apmaterno) values (?,?,?)";
       
       try{
           objetoCliente.setNombres(nombres.getText());
           objetoCliente.setApPaterno(appaterno.getText());
           objetoCliente.setApMaterno(apmaterno.getText());
           
           CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
           cs.setString(1, objetoCliente.getNombres());
           cs.setString(2, objetoCliente.getApPaterno());
           cs.setString(3, objetoCliente.getApMaterno());
           
           cs.execute();
           
           JOptionPane.showMessageDialog(null,"Se guardo correctamente");
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,"error al guardar"+e.toString());
       }finally{
           objetoConexion.cerrarConexion();
       }
    }
}
