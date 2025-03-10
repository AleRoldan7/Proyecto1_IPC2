/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AreaEnsamblaje;

import Admin.ConectarUsuarios;
import java.sql.*;

/**
 *
 * @author alejandro
 */
public abstract class Componentes {
   
    protected String nombreComponente;
    protected String tipoComponente;
    protected int cantidadComponente;
    protected double precioComponente;

    protected Componentes(String nombreComponenteString, String tipoComponenteString, int cantidadComponente, double precioComponente) {
        this.nombreComponente = nombreComponenteString;
        this.tipoComponente = tipoComponenteString;
        this.cantidadComponente = cantidadComponente;
        this.precioComponente = precioComponente;
    }

    public String getNombreComponenteString() {
        return nombreComponente;
    }

    public void setNombreComponenteString(String nombreComponenteString) {
        this.nombreComponente = nombreComponenteString;
    }

    public String getTipoComponenteString() {
        return tipoComponente;
    }

    public void setTipoComponenteString(String tipoComponenteString) {
        this.tipoComponente = tipoComponenteString;
    }

    public int getCantidadComponente() {
        return cantidadComponente;
    }

    public void setCantidadComponente(int cantidadComponente) {
        this.cantidadComponente = cantidadComponente;
    }

    public double getPrecioComponente() {
        return precioComponente;
    }

    public void setPrecioComponente(double precioComponente) {
        this.precioComponente = precioComponente;
    }  
}
