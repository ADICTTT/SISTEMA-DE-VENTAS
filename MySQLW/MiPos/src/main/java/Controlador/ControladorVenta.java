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

            public void BuscarCliente(JTextField nombreCliente, JTable tablaCliente){
            Configuracion.CConexion objetoConexion = new Configuracion.CConexion();
            Modelos.ModeloCliente objetoCliente = new Modelos.ModeloCliente();

            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("id");
            modelo.addColumn("Nombres");
            modelo.addColumn("ApPaterno");
            modelo.addColumn("ApMaterno");

            tablaCliente.setModel(modelo);

            try {
                String consulta = "select * from cliente where cliente.nombres like concat('%',?,'%'); ";
                PreparedStatement ps = objetoConexion.estableceConexion().prepareStatement(consulta);

                ps.setString(1, nombreCliente.getText());

                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    objetoCliente.setIdCliente(rs.getInt("idcliente"));
                    objetoCliente.setNombres(rs.getString("nombres"));
                    objetoCliente.setApPaterno(rs.getString("appaterno"));
                    objetoCliente.setApMaterno(rs.getString("apmaterno"));

                    modelo.addRow(new Object[]{objetoCliente.getIdCliente(), objetoCliente.getNombres(),objetoCliente.getApPaterno(),objetoCliente.getApMaterno()});   
                }
                tablaCliente.setModel(modelo);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al buscar: "+e.toString());
            } finally {
                objetoConexion.cerrarConexion();
            }

            for(int colum = 0; colum<tablaCliente.getColumnCount();colum++){
                Class <?> ColumClass = tablaCliente.getColumnClass(colum);
                tablaCliente.setDefaultEditor(ColumClass, null);
            }
        }
        
        public void SeleccionarClienteVenta(JTable tablaCliente, JTextField id, JTextField nombres, JTextField appaterno, JTextField apmaterno){
            int fila = tablaCliente.getSelectedRow();

            try {
                if(fila>=0){
                    id.setText(tablaCliente.getValueAt(fila, 0).toString());
                    nombres.setText(tablaCliente.getValueAt(fila, 1).toString());
                    appaterno.setText(tablaCliente.getValueAt(fila, 2).toString());
                    apmaterno.setText(tablaCliente.getValueAt(fila, 3).toString());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error de seleccion: "+e.toString());
            }   
        }
        
        public void pasarProductosVenta(JTable tablaResumen,JTextField idproducto, JTextField nombreProducto, JTextField precioProducto,JTextField cantidadVenta ,JTextField stock){
            DefaultTableModel modelo = (DefaultTableModel) tablaResumen.getModel();
            
            int stockDisponible = Integer.parseInt(stock.getText());
            
            String idProducto = idproducto.getText();
            
            for(int i=0;i<modelo.getRowCount();i++){
                String idExistente  = (String) modelo.getValueAt(i, 0);
                if(idExistente.equals(idProducto)){
                    JOptionPane.showMessageDialog(null, "El producto ya esta registrado.");
                    return;
                }
            }
        
            String nProducto = nombreProducto.getText();
            Double precioUnitario = Double.parseDouble(precioProducto.getText());
            int cantidad = Integer.parseInt(cantidadVenta.getText());
            
            if(cantidad > stockDisponible){
                JOptionPane.showMessageDialog(null, "La cantidad de venta no puede ser mayor al stock disponible.");
                return;
            }
            
            double subtotal = precioUnitario* cantidad;
            
            modelo.addRow(new Object[] {idProducto,nProducto,precioUnitario,cantidad,subtotal});
        }
        
        public void eliminarProductosSeleccionadosResumenVenta(JTable tablaResumen){
            try {
                DefaultTableModel modelo = (DefaultTableModel) tablaResumen.getModel();
            
            int indiceSeleccionado = tablaResumen.getSelectedRow();
            
            if(indiceSeleccionado!=-1){
                modelo.removeRow(indiceSeleccionado);
            }else{
                JOptionPane.showMessageDialog(null, "seleccione una fila para eliminar.");
            }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al seleccionar: "+e.toString());
            }
        }
        
}
