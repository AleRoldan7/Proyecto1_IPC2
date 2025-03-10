/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AreaEnsamblaje;

import Admin.ConectarUsuarios;
import java.util.List;
import java.sql.*;
/**
 *
 * @author alejandro
 */
public class Molde extends Componentes{
    
    private List<Componentes> componentesMolde; 
    
    public Molde(String nombreMolde, String tipoMolde, int cantidad, double precio, List<Componentes> componentesMolde) {
        super(nombreMolde, tipoMolde, cantidad, precio);
        this.componentesMolde = componentesMolde;
    }
    
    public List<Componentes> getComponentesMolde() {
        return componentesMolde;
    }

    public void setComponentesMolde(List<Componentes> componentesMolde) {
        this.componentesMolde = componentesMolde;
    }

    public boolean guardarMoldeEnBD() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int idMolde = -1;

        try {
            conn = new ConectarUsuarios().conectar();
            conn.setAutoCommit(false); 

            
            String insertMolde = "INSERT INTO molde (nombreMolde) VALUES (?) RETURNING idMolde";
            stmt = conn.prepareStatement(insertMolde);
            stmt.setString(1, nombreComponente);
            stmt.setString(2, tipoComponente);
            stmt.setInt(3, cantidadComponente);
            stmt.setDouble(4, precioComponente);

            rs = stmt.executeQuery();
            if (rs.next()) {
                idMolde = rs.getInt("idMolde"); 
            }

            
            if (idMolde != -1) {
                if (guardarComponentesMolde(conn, idMolde)) {
                    conn.commit(); 
                    return true;
                } else {
                    conn.rollback();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback(); 
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.setAutoCommit(true); conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean guardarComponentesMolde(Connection conn, int idMolde) {
        PreparedStatement stmt = null;
        try {
            String insertRelacion = "INSERT INTO molde_componente (idMolde, idComponente) VALUES (?, ?)";
            stmt = conn.prepareStatement(insertRelacion);

            for (Componentes componente : componentesMolde) {
                stmt.setInt(1, idMolde);
                stmt.setInt(2, obtenerIdComponente(conn, componente.getNombreComponenteString()));
                stmt.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private int obtenerIdComponente(Connection conn, String nombreComponente) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String query = "SELECT idComponente FROM componente WHERE nombreComponente = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, nombreComponente);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("idComponente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }
}
