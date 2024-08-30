/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADRIANA
 */
public class ControladorVenta {
    
    public void BuscarProducto(JTextField nombreProducto, JTable tablaproductos){
        Configuracion.CConexion objetoConexion = new Configuracion.CConexion();
        Modelos.ModeloProducto objetoProducto = new Modelos.ModeloProducto();
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("id");
        modelo.addColumn("NombreP");
        modelo.addColumn("PrecioProducto");
        modelo.addColumn("Stock");
        
        tablaproductos.setModel(modelo);
        
        try {
            String consulta = "select * from producto where producto.nombre like concat('%',?,'%'); ";
            PreparedStatement ps = objetoConexion.estableceConexion().prepareStatement(consulta);
            
            ps.setString(1, nombreProducto.getText());
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                objetoProducto.setIdProducto(rs.getInt("idproducto"));
                objetoProducto.setNombreProducto(rs.getString("nombre"));
                objetoProducto.setPrecioProducto(rs.getDouble("precioProducto"));
                objetoProducto.setStockProducto(rs.getInt("stock"));
                
                modelo.addRow(new Object[]{objetoProducto.getIdProducto(), objetoProducto.getNombreProducto(),objetoProducto.getPrecioProducto(),objetoProducto.getStockProducto()});   
            }
            tablaproductos.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar: "+e.toString());
        } finally {
            objetoConexion.cerrarConexion();
        }
        
        for(int colum = 0; colum<tablaproductos.getColumnCount();colum++){
            Class <?> ColumClass = tablaproductos.getColumnClass(colum);
            tablaproductos.setDefaultEditor(ColumClass, null);
        }
    }
    
    public void SeleccionarProductoVenta(JTable tablaProducto, JTextField id, JTextField nombres, JTextField precioProducto, JTextField stock, JTextField precioFinal){
        int fila = tablaProducto.getSelectedRow();
        
        try {
            if(fila>=0){
                id.setText(tablaProducto.getValueAt(fila, 0).toString());
                nombres.setText(tablaProducto.getValueAt(fila, 1).toString());
                precioProducto.setText(tablaProducto.getValueAt(fila, 2).toString());
                stock.setText(tablaProducto.getValueAt(fila, 3).toString());
                precioFinal.setText(tablaProducto.getValueAt(fila, 2).toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de seleccion: "+e.toString());
        }
    }
}
