/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexionDBA;

import ConexionDBA.Conexion;
import EntidadModelo.EntidadUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author alejandro
 */
public class UsuarioDB {

    private static final String CREAR_USUARIO_QUERY = "INSERT INTO usuario (nombre_usuario, correo_usuario, identificacion, institucion, no_celular, foto_usuario, estado_usuario, saldo_usuario) VALUES (?,?,?,?,?,?,?,?)";
    private static final String ENCONTRAR_USUARIO_QUERY = "SELECT * FROM usuario WHERE identificacion = ?";

    public void agregarUsuario(EntidadUsuario usuario) {
        Connection connection = Conexion.getInstance().getConnect();

        try (PreparedStatement insert = connection.prepareStatement(CREAR_USUARIO_QUERY)) {
            insert.setString(1, usuario.getNombre());
            insert.setString(2, usuario.getCorreo());
            insert.setString(3, usuario.getIdentificador());
            insert.setString(4, usuario.getInstitucion());
            insert.setString(5, usuario.getCelular());
            insert.setBytes(6, usuario.getFoto());
            insert.setBoolean(7, usuario.isEstado());
            insert.setDouble(8, usuario.getSaldo());
            insert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existeUsuario(String identificacion) {
        Connection connection = Conexion.getInstance().getConnect();
      
        try (PreparedStatement query = connection.prepareStatement(ENCONTRAR_USUARIO_QUERY)) {
            query.setString(1, identificacion);
            ResultSet result = query.executeQuery();
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
