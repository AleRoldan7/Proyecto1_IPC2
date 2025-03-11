/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

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
    
    public boolean actualizarRol(int idUsuario, String nuevoRol) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean actualizado = false;

        try {
            conn = conectar();
            String query = "UPDATE usuario SET rolTrabajador = ? WHERE idUsuario = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, nuevoRol);
            stmt.setInt(2, idUsuario);

            int filasAfectadas = stmt.executeUpdate();
            actualizado = filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarConexion(conn, stmt, null);
        }

        return actualizado;
    }

    public boolean actualizarRoles(Map<Integer, String> cambiosRoles) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean exito = true;

        try {
            conn = conectar();

            for (Map.Entry<Integer, String> entry : cambiosRoles.entrySet()) {
                int idUsuario = entry.getKey();
                String nuevoRol = entry.getValue();

                String query = "UPDATE usuario SET rolTrabajador = ? WHERE idUsuario = ?";
                stmt = conn.prepareStatement(query);
                stmt.setString(1, nuevoRol);
                stmt.setInt(2, idUsuario);

                int filasAfectadas = stmt.executeUpdate();
                if (filasAfectadas == 0) {
                    exito = false; 
                }
            }

        } catch (SQLException e) {
            exito = false;
            e.printStackTrace();
        } finally {
            cerrarConexion(conn, stmt, null);
        }

        return exito;
    }
      
}
