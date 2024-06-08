/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clientes;

import conexion.*;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import conexion.conexionmysql;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
/**
 *
 * @author alexa
 */
public class Cliente2 {
    
        Connection cn;
        
        
    public void CargarTabla(JTable tabla, String cadena){
        DefaultTableModel modelo;
        String [] titulo = {"USUARIO","NOMBRE(S)","APELLIDOS","EDAD","SEXO","CARGO"};
        modelo = new DefaultTableModel(null, titulo);
        
        String [] registros = new String[6];
        String sql = "SELECT * FROM usuarios WHERE CONCAT(Usuario,' ',Nombre,' ', Apellido,' ', Edad,' ',sexo,' ',Cargo) LIKE '%"+cadena+"%'";
        conexionmysql con = new conexionmysql();
        cn = con.conectar();
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                for(int i=0;i<6;i++)
                    registros[i]=rs.getString(i+1);
                    modelo.addRow(registros);
            }
            tabla.setModel(modelo);
         }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error: "+ex);
         }
    
    }
    
}
