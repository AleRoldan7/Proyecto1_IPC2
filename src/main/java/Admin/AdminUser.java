/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author alejandro
 */
public class AdminUser extends ConectarUsuarios{

   
     public boolean agregarUsuario(String usuario, String contrasena, String rol) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean resultado = false;
        
        try {
            conn = conectar();
            String query = "INSERT INTO usuario (userName, pass, rolTrabajador) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);
            stmt.setString(3, rol);

            int filasAfectadas = stmt.executeUpdate();
            resultado = filasAfectadas > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion(conn, stmt, null); 
        }
        
        return resultado;
    }
    
    
}
