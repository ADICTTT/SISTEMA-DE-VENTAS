/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogica;

import CapaDatos.M_Clientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import javax.swing.JOptionPane;
/**
 *
 * @author ADRIANA
 */
public class N_Cliente {
    private Conexion SQL = new Conexion();
    private Connection cn = SQL.establecerConexion();//creo que es conectar, revisar
    String sql="";
    
    
    public DefaultTableModel mostrar(String buscar){
        DefaultTableModel modelo;
        
        String[] titulos={"Codigo","Nombre","Apellidos","CI","Edad","Sexo","Telefono","Direccion"};
        modelo = new DefaultTableModel(null,titulos);
        String[] registro = new String [9];
        
        sql=("EXEC sp_buscar_cliente'"+buscar+"'");
        
        try{
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            
            while(rs.next()){
                registro[0]=rs.getString(1);
                registro[1]=rs.getString(2);
                registro[2]=rs.getString(3);
                registro[3]=rs.getString(4);
                registro[4]=rs.getString(5);
                registro[5]=rs.getString(6);
                registro[6]=rs.getString(7);
                registro[7]=rs.getString(8);
                registro[8]=rs.getString(9);
                modelo.addRow(registro);
            }
            return modelo;
        }catch(SQLException e){
            JOptionPane.showConfirmDialog(null,e);
            return null;
        }
    }
        
    public boolean insertar(M_Clientes dts){
        sql = ("{call sp_guardar_clientes (?,?,?,?,?,?,?,?)}");            
        
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, dts.getCodigo());
            pst.setString(2, dts.getNombre());
            pst.setString(3, dts.getApellidos());
            pst.setString(4, dts.getCi());
            pst.setInt(5, dts.getEdad());                
            pst.setString(6, dts.getSexo());
            pst.setString(7, dts.getTelefono());
            pst.setString(8, dts.getDireccion());
                
            int n = pst.executeUpdate();
                
            if(n!=0){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
        
        public boolean editar(M_Clientes dts){
            sql="{call sp_guardar_clientes (?,?,?,?,?,?,?,?)}";
            
            try{
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1,dts.getCodigo());
                pst.setString(2,dts.getNombre());
                pst.setString(3,dts.getApellidos());
                pst.setString(4,dts.getCi());
                pst.setInt(5,dts.getEdad());
                pst.setString(6,dts.getSexo());
                pst.setString(7,dts.getTelefono());
                pst.setString(8,dts.getDireccion());
                
                int n=pst.executeUpdate();
                
                if(n!=0){
                return true;
            }else{
                return false;
            }
            }catch(SQLException e){
                JOptionPane.showConfirmDialog(null, e);
                return false;
            }
            
        }
        
        public boolean eliminar(M_Clientes dts){
            sql= ("{call sp_eliminar_cliente (?,?,?,?,?,?,?,?)}");
            
            try{
                PreparedStatement pat = cn.prepareStatement(sql);
                pat.setString(1,dts.getCodigo());
                pat.setString(2,dts.getNombre());
                pat.setString(3,dts.getApellidos());
                pat.setString(4,dts.getCi());
                pat.setInt(5,dts.getEdad());
                pat.setString(6,dts.getSexo());
                pat.setString(7,dts.getTelefono());
                pat.setString(8,dts.getDireccion());
                
                int n=pat.executeUpdate();
                
                if (n!=0)
                    return true;
                else
                    return false;
               
            }catch(SQLException e){
                JOptionPane.showConfirmDialog(null, e);
                return false;
            }
            
        }
        
        public int generarIdCliente(){
            String sql = "SELECT MAX(idcliente) as id FROM cliente";
            int cod=0;
            
            try{
                Statement st=cn.createStatement();
                ResultSet rs=st.executeQuery(sql);
                if(rs.next()){
                    cod=rs.getInt("id");
                }
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            System.out.print(cod);
            return cod;
        }
}

