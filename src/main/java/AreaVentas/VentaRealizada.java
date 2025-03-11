/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AreaVentas;

import java.sql.*;
import Admin.ConectarUsuarios;
/**
 *
 * @author alejandro
 */
public class VentaRealizada extends Comprador {

    private int idVenta;
    private String fechaVenta;
    private double montoTotal;

    public VentaRealizada(int idVenta, String fechaVenta, double montoTotal, int idComprador, String nombreComprador, String nit, String direccion, String celularComprador, String emailComprador) {
        super(nombreComprador, nit, direccion, celularComprador, emailComprador);
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.montoTotal = montoTotal;
    }

    // Getters y Setters
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    
    public boolean realizarCompra() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            
            conn = new ConectarUsuarios().conectar();

            if (conn != null) {
                String insertQuery = "INSERT INTO venta_comprador (idVenta, idComprador) VALUES (?, ?)";
                pst = conn.prepareStatement(insertQuery);
                pst.setInt(1, this.idVenta);  
                pst.setInt(2, this.getIdComprador()); 

                int filasAfectadas = pst.executeUpdate();
                if (filasAfectadas > 0) {
                    System.out.println("Venta realizada con éxito.");
                    return true;
                } else {
                    System.out.println("Error al realizar la venta.");
                    return false;
                }
            } else {
                System.out.println("No se pudo establecer conexión a la base de datos.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
   

}
