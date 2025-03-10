/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AreaEnsamblaje.UnionMolde;

import Admin.ConectarUsuarios;
import java.sql.*;

/**
 *
 * @author alejandro
 */
public class Molde {
    
    private String nombreMolde;

    public Molde(String nombreMolde) {
        this.nombreMolde = nombreMolde;
    }

    public String getNombreMolde() {
        return nombreMolde;
    }

    public void setNombreMolde(String nombreMolde) {
        this.nombreMolde = nombreMolde;
    }

    public boolean agregarMolde(){
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            ConectarUsuarios conexion = new ConectarUsuarios();
            conn = conexion.conectar();

            String query = "INSERT INTO componente (nombreMolde) VALUES (?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, nombreMolde);
            

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    
    
}
