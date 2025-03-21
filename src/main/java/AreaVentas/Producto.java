package AreaVentas;

import Admin.ConectarUsuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Producto {

    private int idUnion;
    private String nombreComputadora;
    private String nombreMolde;
    private String nombreComponente;
    private double precioTotal;
    private int idComputadora;
    private int idMolde;
    private int idComponente;

    public int getIdUnion() {
        return idUnion;
    }

    public void setIdUnion(int idUnion) {
        this.idUnion = idUnion;
    }

    public String getNombreComputadora() {
        return nombreComputadora;
    }

    public void setNombreComputadora(String nombreComputadora) {
        this.nombreComputadora = nombreComputadora;
    }

    public String getNombreMolde() {
        return nombreMolde;
    }

    public void setNombreMolde(String nombreMolde) {
        this.nombreMolde = nombreMolde;
    }

    public String getNombreComponente() {
        return nombreComponente;
    }

    public void setNombreComponente(String nombreComponente) {
        this.nombreComponente = nombreComponente;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public int getIdComputadora() {
        return idComputadora;
    }

    public void setIdComputadora(int idComputadora) {
        this.idComputadora = idComputadora;
    }

    public int getIdMolde() {
        return idMolde;
    }

    public void setIdMolde(int idMolde) {
        this.idMolde = idMolde;
    }

    public int getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(int idComponente) {
        this.idComponente = idComponente;
    }

    public static Producto obtenerProductoPorId(int idUnion) {
        Producto producto = null;
        Connection conexion = new ConectarUsuarios().conectar();
        String sql = "SELECT idComputadora, idMolde, precioTotal "
                + "FROM computadora_molde_componente "
                + "WHERE idUnion = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idUnion);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                producto = new Producto();
                producto.setIdUnion(idUnion);
                producto.setNombreComputadora(resultSet.getString("idComputadora"));
                producto.setNombreMolde(resultSet.getString("idMolde"));

                producto.setPrecioTotal(resultSet.getDouble("precioTotal"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    public static List<Producto> obtenerProductosPorCompradorId(int compradorId) {
        List<Producto> productos = new ArrayList<>();
        Connection conexion = new ConectarUsuarios().conectar();
        String sql = "SELECT p.idUnion, p.nombreComputadora, p.nombreMolde, p.nombreComponente, p.precioTotal "
                + "FROM productos p "
                + "JOIN ventas v ON p.id = v.id_producto "
                + "WHERE v.comprador_id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, compradorId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Producto producto = new Producto();
                producto.setIdUnion(resultSet.getInt("idUnion"));
                producto.setNombreComputadora(resultSet.getString("nombreComputadora"));
                producto.setNombreMolde(resultSet.getString("nombreMolde"));
                producto.setNombreComponente(resultSet.getString("nombreComponente"));
                producto.setPrecioTotal(resultSet.getDouble("precioTotal"));
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }

}
