package AreaVentas;

import Admin.ConectarUsuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Producto {

    private int idUnion;
    private String nombreComputadora;
    private String nombreMolde;
    private String nombreComponente;
    private double precioTotal;

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

  

}
