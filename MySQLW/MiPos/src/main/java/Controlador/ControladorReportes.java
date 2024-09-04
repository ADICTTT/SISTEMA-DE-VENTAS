/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author ADRIANA
 */
public class ControladorReportes {
    
    public void BuscarFacturaMostrarDatosCliente(JTextField numeroFactura, JLabel numeroFacturaEncontrado, JLabel fechaFacturaEncontrado, JLabel nombreCliente, JLabel appaterno, JLabel apmaterno){
        Configuracion.CConexion objetoCConexion = new Configuracion.CConexion();
        try {
            String consulta = "SELECT factura.idfactura, factura.fechaFactura, cliente.nombres, cliente.appaterno, cliente.apmaterno from factura INNER JOIN cliente ON cliente.idcliente = factura.fkcliente WHERE factura.idfactura = ?;";
            
            PreparedStatement ps = objetoCConexion.estableceConexion().prepareStatement(consulta);
            ps.setInt(1, Integer.parseInt(numeroFactura.getText()));
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                numeroFacturaEncontrado.setText(String.valueOf(rs.getInt("idfactura")));
                fechaFacturaEncontrado.setText(rs.getDate("fechaFactura").toString());
                nombreCliente.setText(rs.getString("nombres"));
                appaterno.setText(rs.getString("appaterno"));
                apmaterno.setText(rs.getString("apmaterno"));  
            }else{
                numeroFacturaEncontrado.setText("");
                fechaFacturaEncontrado.setText("");
                nombreCliente.setText("");
                apmaterno.setText("");
                apmaterno.setText(""); 
                
                JOptionPane.showMessageDialog(null, "No se encontro la factura");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar la factura: "+e.toString());
        } finally {
            objetoCConexion.cerrarConexion();
        }
    }
    
}
