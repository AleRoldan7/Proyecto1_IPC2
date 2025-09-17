/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexionDBA;

import EntidadModelo.EntidadInstitucion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author alejandro
 */
public class InstitucionDB {

    private static final String CREAR_INSTITUCION_QUERY = "INSERT INTO institucion (nombre_institucion, descripcion) VALUES (?,?)";
    private static final String ENCONTRAR_INSTITUCION_QUERY = "SELECT * FROM institucion WHERE nombre_institucion = ?";
    private static final String TODAS_LAS_INSTITUCIONES_QUERY = "SELECT * FROM institucion";

    public void agregarInstitucion(EntidadInstitucion entidadInstitucion) {
        Connection connection = Conexion.getInstance().getConnect();

        try (PreparedStatement insert = connection.prepareStatement(CREAR_INSTITUCION_QUERY)) {

            insert.setString(1, entidadInstitucion.getNombre());
            insert.setString(2, entidadInstitucion.getDescripcion());
            insert.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean existeInstitucion(String nombre) {
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

    public List<EntidadInstitucion> obtenerInstituciones() {

        List<EntidadInstitucion> instituciones = new ArrayList<>();
        Connection connection = Conexion.getInstance().getConnect();

        try (PreparedStatement query = connection.prepareStatement(TODAS_LAS_INSTITUCIONES_QUERY);) {
            ResultSet resultSet = query.executeQuery();

            while (resultSet.next()) {
                EntidadInstitucion institucion = new EntidadInstitucion(
                        resultSet.getInt("id_institucion"),
                        resultSet.getString("nombre_institucion"),
                        resultSet.getString("descripcion")
                );
                instituciones.add(institucion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instituciones;
    }

    public Optional<EntidadInstitucion> obtenerInstitucionNombre(String nombre) {

        Connection connection = Conexion.getInstance().getConnect();
        try (PreparedStatement query = connection.prepareStatement(ENCONTRAR_INSTITUCION_QUERY);) {

            query.setString(1, nombre);
            ResultSet resultSet = query.executeQuery();

            if (resultSet.next()) {
                EntidadInstitucion institucion = new EntidadInstitucion(
                        resultSet.getString("nombre_institucion"),
                        resultSet.getString("descripcion")
                );

                return Optional.of(institucion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public List<EntidadInstitucion> listarInstituciones() {
        List<EntidadInstitucion> lista = new ArrayList<>();
        String sql = "SELECT id_institucion, nombre_institucion, descripcion FROM institucion";

        Connection connection = Conexion.getInstance().getConnect();
       
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {


            while (rs.next()) {
                int id = rs.getInt("id_institucion");
                String nombre = rs.getString("nombre_institucion");
                String descripcion = rs.getString("descripcion");

                EntidadInstitucion inst = new EntidadInstitucion(id, nombre, descripcion);
                lista.add(inst);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Número total de instituciones en lista: " + lista.size());
        return lista;
    }

}
