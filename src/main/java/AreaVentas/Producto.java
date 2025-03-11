/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AreaVentas;

/**
 *
 * @author alejandro
 */

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
}