/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AreaVentas;

import Admin.ConectarUsuarios;
import java.sql.*;


/**
 *
 * @author alejandro
 */
public class AgregarComprador extends Comprador{
    
    public AgregarComprador(String nombreComprador, String nit, String direccion, String celularComprador, String emailComprador) {
        super(nombreComprador, nit, direccion, celularComprador, emailComprador);
    }

    public boolean agregarComprador() {
        boolean success = false;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = new ConectarUsuarios().conectar();

            String query = "INSERT INTO comprador (nombreComprador, nit, direccion, celularComprador, emailComprador) "
                         + "VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);

            stmt.setString(1, this.getNombreComprador());
            stmt.setString(2, this.getNit());
            stmt.setString(3, this.getDireccion());
            stmt.setString(4, this.getCelularComprador());
            stmt.setString(5, this.getEmailComprador());

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


