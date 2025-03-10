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
public class VentaRealizada extends Comprador{
    
    private int idVenta;
    private String fechaVenta;
    private double montoTotal;

    public VentaRealizada(int idVenta, String fechaVenta, double montoTotal, int idComprador, String nombreComprador, String nit, String direccion, String celularComprador, String emailComprador) {
        super(nombreComprador, nit, direccion, celularComprador, emailComprador);
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.montoTotal = montoTotal;
    }

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
    
    
}
