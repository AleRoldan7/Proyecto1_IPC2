/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexionDBA;

import ConexionDBA.Conexion;
import EntidadModelo.EntidadInstitucion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alejandro
 */
public class InstitucionDB {

    private static final String CREAR_INSTITUCION_QUERY = "INSERT INTO institucion (nombre_institucion, descripcion) VALUES (?,?)";
    private static final String ENCONTRAR_INSTITUCION_QUERY = "SELECT * FROM institucion WHERE nombre_institucion = ?";

    public void agregarInstitucion(EntidadInstitucion entidadInstitucion) {

        Connection connection = Conexion.getInstance().getConnect();

        try (PreparedStatement insert = connection.prepareStatement(CREAR_INSTITUCION_QUERY)) {

            insert.setString(1, entidadInstitucion.getNombreInstitucion());
            insert.setString(2, entidadInstitucion.getDescripcion());
            insert.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean existeUsuario(String nombre) {

        Connection connection = Conexion.getInstance().getConnect();
        try (PreparedStatement query = connection.prepareStatement(ENCONTRAR_INSTITUCION_QUERY)) {
            query.setString(1, nombre);
            ResultSet result = query.executeQuery();
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<String> obtenerNombresInstituciones() {
        List<String> nombres = new ArrayList<>();
        Connection connection = Conexion.getInstance().getConnect();
        String query = "SELECT nombre_institucion FROM institucion";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nombres.add(rs.getString("nombre_institucion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombres;
    }
}
