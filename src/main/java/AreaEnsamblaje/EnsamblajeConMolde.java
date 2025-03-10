/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AreaEnsamblaje;

import Admin.ConectarUsuarios;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author alejandro
 */

public class EnsamblajeConMolde {

    public boolean ensamblarComputadora(int idMolde, String[] componentesSeleccionados, int[] cantidades) {
        Connection conn = null;
        try {
            conn = new ConectarUsuarios().conectar();
            conn.setAutoCommit(false);

            // Verificar stock
            for (int i = 0; i < componentesSeleccionados.length; i++) {
                int idComponente = Integer.parseInt(componentesSeleccionados[i]);
                int cantidad = cantidades[i];

                String query = "SELECT cantidadStock FROM componente WHERE idComponente = ?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setInt(1, idComponente);
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        int stockDisponible = rs.getInt("cantidadStock");
                        if (stockDisponible < cantidad) {
                            conn.rollback();
                            return false; // No hay suficiente stock
                        }
                    } else {
                        conn.rollback();
                        return false; // Componente no encontrado
                    }
                    rs.close();
                }
            }

            // Descontar stock
            for (int i = 0; i < componentesSeleccionados.length; i++) {
                int idComponente = Integer.parseInt(componentesSeleccionados[i]);
                int cantidad = cantidades[i];

                String update = "UPDATE componente SET cantidadStock = cantidadStock - ? WHERE idComponente = ?";
                try (PreparedStatement stmt = conn.prepareStatement(update)) {
                    stmt.setInt(1, cantidad);
                    stmt.setInt(2, idComponente);
                    stmt.executeUpdate();
                }
            }

            conn.commit();
            return true; // Ensamblaje exitoso
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void descontarStock(int idComponente, int cantidad, Connection conn) throws SQLException {
        String query = "UPDATE componente SET cantidadStock = cantidadStock - ? WHERE idComponente = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, cantidad);
            stmt.setInt(2, idComponente);
            stmt.executeUpdate();
        }
    }

    // Método para calcular el costo total de los componentes seleccionados
    public double calcularCosto(String[] componentesSeleccionados, int[] cantidades, Connection conn) throws SQLException {
        double costoTotal = 0.0;

        for (int i = 0; i < componentesSeleccionados.length; i++) {
            int idComponente = Integer.parseInt(componentesSeleccionados[i]);
            int cantidad = cantidades[i];

            // Consultar el precio del componente
            String query = "SELECT precio FROM componente WHERE idComponente = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, idComponente);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    double precio = rs.getDouble("precio");
                    costoTotal += precio * cantidad;
                }
                rs.close();
            }
        }

        return costoTotal;
    }
}
