/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexionDBA;

import EntidadModelo.EntidadCongreso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alejandro
 */
public class CongresoDB {

    private static final String CREAR_COGRESO_QUERY = "INSERT INTO congreso (nombre_congreso, descripcion, fecha_inicio,"
            + "precio, ubicacion, id_institucion, fecha_apertura_conv, fecha_cierre_conv) "
            + "VALUES (?,?,?,?,?,?,?,?)";

    private static final String ENCONTRAR_CONGRESO_QUERY = "SELECT * FROM congreso WHERE nombre_congreso = ?";
    private static final String LISTAR_CONGRESOS_QUERY = "SELECT * FROM congreso";

    public void agregarCongreso(EntidadCongreso entidadCongreso) {

        Connection connection = Conexion.getInstance().getConnect();

        try (PreparedStatement insert = connection.prepareStatement(CREAR_COGRESO_QUERY)) {

            insert.setString(1, entidadCongreso.getNombre());
            insert.setString(2, entidadCongreso.getDescripcion());
            insert.setString(3, entidadCongreso.getFechaInicio().toString());
            insert.setDouble(4, entidadCongreso.getPrecioCongreso());
            insert.setString(5, entidadCongreso.getUbicacion());
            insert.setInt(6, entidadCongreso.getId_institucion());
            insert.setString(7, entidadCongreso.getFechaInicioConvocatoria().toString());
            insert.setString(8, entidadCongreso.getFechaCierreConvocatoria().toString());

            insert.executeUpdate();
            System.out.println("inserto " + insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existeCongreso(String nombre) {
        Connection connection = Conexion.getInstance().getConnect();

        try (PreparedStatement query = connection.prepareStatement(ENCONTRAR_CONGRESO_QUERY)) {
            query.setString(1, nombre);
            ResultSet result = query.executeQuery();
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<EntidadCongreso> listarCongreso() {

        List<EntidadCongreso> lista = new ArrayList<>();
        Connection connection = Conexion.getInstance().getConnect();

        try (PreparedStatement ps = connection.prepareStatement(LISTAR_CONGRESOS_QUERY); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_congreso");
                String nombre = rs.getString("nombre_congreso");
                String descripcion = rs.getString("descripcion");
                LocalDate fechaInicio = rs.getDate("fecha_inicio").toLocalDate();
                double precio = rs.getDouble("precio");
                String ubicacion = rs.getString("ubicacion");
                int idInstitucion = rs.getInt("id_institucion");
                LocalDate fechaAperturaConv = rs.getDate("fecha_apertura_conv").toLocalDate();
                LocalDate fechaCierreConv = rs.getDate("fecha_cierre_conv").toLocalDate();

                EntidadCongreso congreso = new EntidadCongreso(
                        id,
                        nombre, 
                        descripcion, 
                        fechaInicio, 
                        precio, 
                        ubicacion,
                        idInstitucion, 
                        fechaAperturaConv, 
                        fechaCierreConv
                );
                lista.add(congreso);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Congresos: " + lista.size());
        return lista;
    }

}
