/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AreaEnsamblaje;

import java.sql.*;
import java.util.List;
import Admin.ConectarUsuarios;

/**
 *
 * @author alejandro
 */

public class EnsamblajeComputadora {

    public boolean registrarEnsamblaje(int idComputadora, int idMolde, String nombreUsuario, List<Integer> componentesSeleccionados) {
        boolean exito = false;
        double precioTotal = calcularPrecioTotal(componentesSeleccionados);

        try (Connection conexion = new ConectarUsuarios().conectar()) {

            String sql = "INSERT INTO computadora_molde_componente (idComputadora, idMolde, nombreUsuario, precioTotal, stock) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setInt(1, idComputadora);
                ps.setInt(2, idMolde);
                ps.setString(3, nombreUsuario);
                ps.setDouble(4, precioTotal);
                ps.setInt(5, 1);

                int resultado = ps.executeUpdate();
                if (resultado > 0) {

                    for (Integer idComponente : componentesSeleccionados) {
                        String updateStockSql = "UPDATE componente SET cantidadStock = cantidadStock - 1 WHERE idComponente = ?";
                        try (PreparedStatement updatePs = conexion.prepareStatement(updateStockSql)) {
                            updatePs.setInt(1, idComponente);
                            updatePs.executeUpdate();
                        }
                    }
                    exito = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exito;
    }

    private double calcularPrecioTotal(List<Integer> componentesSeleccionados) {
        double precioTotal = 0.0;
        try (Connection conexion = new ConectarUsuarios().conectar()) {
            for (Integer idComponente : componentesSeleccionados) {
                String sql = "SELECT precioComponente FROM componente WHERE idComponente = ?";
                try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                    ps.setInt(1, idComponente);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            precioTotal += rs.getDouble("precioComponente");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return precioTotal;
    }
}
