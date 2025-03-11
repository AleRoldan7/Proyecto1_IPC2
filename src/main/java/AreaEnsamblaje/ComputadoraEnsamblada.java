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

public class ComputadoraEnsamblada extends Componentes {
    private int idComputadora;
    private Timestamp fechaEnsamblaje;
    private int idUsuario;

    public ComputadoraEnsamblada(String nombreComponenteString, String tipoComponenteString, int cantidadComponente, double precioComponente) {
        super(nombreComponenteString, tipoComponenteString, cantidadComponente, precioComponente);
    }

    public boolean guardarComputadora(String nombreComputadora, double precioTotal) {
        Connection conexion = null;
        PreparedStatement ps = null;
        try {
            ConectarUsuarios conn = new ConectarUsuarios();
            conexion = conn.conectar();

            String sql = "INSERT INTO computadora (nombreComputadora, precioTotal) VALUES (?, ?)";
            ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, nombreComputadora);
            ps.setDouble(2, precioTotal);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    this.idComputadora = generatedKeys.getInt(1);
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean registrarUnion(int idMolde, List<Integer> idsComponentes) {
        Connection conexion = null;
        PreparedStatement ps = null;
        try {
            ConectarUsuarios conn = new ConectarUsuarios();
            conexion = conn.conectar();

            String sql = "INSERT INTO computadora_molde_componente (idComputadora, idMolde, idComponente) VALUES (?, ?, ?)";
            ps = conexion.prepareStatement(sql);

            for (int idComponente : idsComponentes) {
                ps.setInt(1, this.idComputadora);
                ps.setInt(2, idMolde);
                ps.setInt(3, idComponente);
                ps.addBatch();
            }

            int[] resultados = ps.executeBatch();

            for (int resultado : resultados) {
                if (resultado == PreparedStatement.EXECUTE_FAILED) {
                    return false;
                }
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public double calcularPrecioTotal(List<Integer> idsComponentes) {
        Connection conexion = null;
        PreparedStatement ps = null;
        double precioTotal = 0.0;

        try {
            ConectarUsuarios conn = new ConectarUsuarios();
            conexion = conn.conectar();


            String sql = "SELECT precioComponente FROM componente WHERE idComponente = ?";
            ps = conexion.prepareStatement(sql);

            for (int idComponente : idsComponentes) {
                ps.setInt(1, idComponente);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    precioTotal += rs.getDouble("precioComponente");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return precioTotal;
    }

    public int getIdComputadora() {
        return idComputadora;
    }

    public void setIdComputadora(int idComputadora) {
        this.idComputadora = idComputadora;
    }

    public Timestamp getFechaEnsamblaje() {
        return fechaEnsamblaje;
    }

    public void setFechaEnsamblaje(Timestamp fechaEnsamblaje) {
        this.fechaEnsamblaje = fechaEnsamblaje;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
