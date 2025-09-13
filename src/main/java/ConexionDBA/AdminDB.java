/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexionDBA;

import ConexionDBA.Conexion;
import EntidadModelo.EntidadAdmin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author alejandro
 */
public class AdminDB {

    private static final String ASIGNAR_ADMIN_QUERY = "INSERT INTO administrador (user_name, password, rol_admin) VALUES (?,?,?)";
    private static final String ENCONTRAR_ADMIN_QUERY = "SELECT COUNT(*) FROM administrador WHERE user_name = ? AND password = ? AND rol_admin = ?";

    public void agregarAdmin(EntidadAdmin entidadAdmin) {

        Connection connection = Conexion.getInstance().getConnect();

        try (PreparedStatement insert = connection.prepareStatement(ASIGNAR_ADMIN_QUERY)) {
            insert.setString(1, entidadAdmin.getUsuario());
            insert.setString(2, entidadAdmin.getPassword());
            insert.setString(3, entidadAdmin.getRolAdmin().name());

            insert.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public boolean verificarAdmin(EntidadAdmin entidadAdmin) {
        Connection connection = Conexion.getInstance().getConnect();

        try (PreparedStatement pstm = connection.prepareStatement(ENCONTRAR_ADMIN_QUERY)) {
            pstm.setString(1, entidadAdmin.getUsuario());
            pstm.setString(2, entidadAdmin.getPassword());
            pstm.setString(3, entidadAdmin.getRolAdmin().name());

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
