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
public class EliminarUsuarios {
    
    private boolean delete = false;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    
    
    public boolean eliminarUsuario(String usuario) {
        
        try {
           
            ConectarUsuarios conexion = new ConectarUsuarios();
            conn = conexion.conectar();
           
            String query = "DELETE FROM usuario WHERE userName = ?";
            stmt = conn.prepareStatement(query); 
            stmt.setString(1, usuario);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                delete = true;
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

        return delete;
    }
       
}
