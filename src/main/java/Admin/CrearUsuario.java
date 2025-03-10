/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin;

import java.sql.*;

/**
 *
 * @author alejandro
 */
public class CrearUsuario {
    
    public boolean agregarUsuario(String usuario, String contrasena, String rol) {
        boolean success = false;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
           
            ConectarUsuarios conexion = new ConectarUsuarios();
            conn = conexion.conectar();
            
           
            String query = "INSERT INTO usuario (userName, pass, rolTrabajador) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);
            stmt.setString(3, rol);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return success;
    }
}
