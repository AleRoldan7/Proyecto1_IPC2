/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AreaEnsamblaje;

import Admin.ConectarUsuarios;
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
public class GestionComponentes extends Componentes {

    public GestionComponentes(String nombreComponente, String tipoComponente, int cantidadComponente, double precioComponente) {
        super(nombreComponente, tipoComponente, cantidadComponente, precioComponente);
    }

    public boolean agregarComponente() {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            ConectarUsuarios conexion = new ConectarUsuarios();
            conn = conexion.conectar();

            String query = "INSERT INTO componente (tipoComponente, precioComponente, cantidadStock, nombreComponente) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, tipoComponente);
            stmt.setDouble(2, precioComponente);
            stmt.setInt(3, cantidadComponente);
            stmt.setString(4, nombreComponente);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean verificarComponenteExistente(String nombreComponente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            ConectarUsuarios conexion = new ConectarUsuarios();
            conn = conexion.conectar();

            String query = "SELECT COUNT(*) FROM componente WHERE LOWER(nombreComponente) = LOWER(?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, nombreComponente);
            rs = stmt.executeQuery();

            return rs.next() && rs.getInt(1) > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean eliminarComponente(String nombreComponente, int cantidadEliminar) {
        Connection conn = null;
        PreparedStatement stmt = null;
        PreparedStatement updateStmt = null;
        ResultSet rs = null;

        try {
            ConectarUsuarios conexion = new ConectarUsuarios();
            conn = conexion.conectar();

            String consultaStock = "SELECT cantidadStock FROM componente WHERE nombreComponente = ?";
            stmt = conn.prepareStatement(consultaStock);
            stmt.setString(1, nombreComponente);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int cantidadActual = rs.getInt("cantidadStock");

                if (cantidadEliminar >= cantidadActual) {

                    String queryEliminar = "DELETE FROM componente WHERE nombreComponente = ?";
                    updateStmt = conn.prepareStatement(queryEliminar);
                    updateStmt.setString(1, nombreComponente);
                } else {

                    String queryActualizar = "UPDATE componente SET cantidadStock = cantidadStock - ? WHERE nombreComponente = ?";
                    updateStmt = conn.prepareStatement(queryActualizar);
                    updateStmt.setInt(1, cantidadEliminar);
                    updateStmt.setString(2, nombreComponente);
                }

                int rowsAffected = updateStmt.executeUpdate();
                return rowsAffected > 0;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (updateStmt != null) {
                    updateStmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean agregarCantidad(int cantidad) {

        String query = "UPDATE componente SET cantidadStock = cantidadStock + ? WHERE nombreComponente = ?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            ConectarUsuarios conexion = new ConectarUsuarios();
            conn = conexion.conectar();

            stmt = conn.prepareStatement(query);
            stmt.setInt(1, cantidad);
            stmt.setString(2, this.nombreComponente);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<GestionComponentes> obtenerComponentes() {
        List<GestionComponentes> listaComponentes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            ConectarUsuarios conexion = new ConectarUsuarios();
            conn = conexion.conectar();

            String query = "SELECT nombreComponente, tipoComponente, cantidadStock, precioComponente FROM componente";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String nombreComponente = rs.getString("nombreComponente");
                String tipoComponente = rs.getString("tipoComponente");
                int cantidadStock = rs.getInt("cantidadStock");
                double precioComponente = rs.getDouble("precioComponente");

                GestionComponentes componente = new GestionComponentes(nombreComponente, tipoComponente, cantidadStock, precioComponente);
                listaComponentes.add(componente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return listaComponentes;
    }

    public boolean actualizarPrecio(double nuevoPrecio) {
        String query = "UPDATE componente SET precioComponente = ? WHERE nombreComponente = ?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            ConectarUsuarios conexion = new ConectarUsuarios();
            conn = conexion.conectar();

            stmt = conn.prepareStatement(query);
            stmt.setDouble(1, nuevoPrecio);
            stmt.setString(2, this.nombreComponente);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
