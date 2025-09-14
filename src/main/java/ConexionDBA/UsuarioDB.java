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

    private static final String CREAR_USUARIO_QUERY = "INSERT INTO usuario (nombre_completo, correo, identificacion, no_celular,"
            + "foto, estado_usuario, cuenta_usuario, user_name, password, rol_usuario) VALUES (?,?,?,?,?,?,?,?,?,?)";

    private static final String ENCONTRAR_USUARIO_QUERY = "SELECT * FROM usuario WHERE identificacion = ?";
    private static final String VERIFICAR_USUARIO_QUERY = "SELECT * FROM usuario WHERE user_name = ? AND password = ? AND rol_usuario = ?";

    public void agregarUsuario(EntidadUsuario usuario) throws SQLException {
        Connection connection = Conexion.getInstance().getConnect();

        try (PreparedStatement insert = connection.prepareStatement(CREAR_USUARIO_QUERY)) {
            insert.setString(1, usuario.getNombre());
            insert.setString(2, usuario.getCorreo());
            insert.setString(3, usuario.getIdentificacion());
            insert.setString(4, usuario.getCelular());
            insert.setBytes(5, usuario.getFoto());
            insert.setBoolean(6, usuario.isEstado());
            insert.setDouble(7, usuario.getCuenta());
            insert.setString(8, usuario.getUserName());
            insert.setString(9, usuario.getPassword());
            insert.setString(10, usuario.getRol().name());
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

    public boolean verificarUsuario(String user, String password, String rol) {
        Connection connection = Conexion.getInstance().getConnect();

        try (PreparedStatement verify = connection.prepareStatement(VERIFICAR_USUARIO_QUERY)) {
            verify.setString(1, user);
            verify.setString(2, password);
            verify.setString(3, rol);
            System.out.println("Ejecutando query de verificación:");
            System.out.println("User: " + user);
            System.out.println("Password: " + password);
            System.out.println("Rol: " + rol);

            ResultSet rs = verify.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
