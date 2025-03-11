/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AreaVentas;

/**
 *
 * @author alejandro
 */
public class Comprador {
   
    private int idComprador;
    private String nombreComprador;
    private String nit;
    private String direccion;
    private String celularComprador;
    private String emailComprador;

    public Comprador(String nombreComprador, String nit, String direccion, String celularComprador, String emailComprador) {
        this.nombreComprador = nombreComprador;
        this.nit = nit;
        this.direccion = direccion;
        this.celularComprador = celularComprador;
        this.emailComprador = emailComprador;
    }

    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelularComprador() {
        return celularComprador;
    }

    public void setCelularComprador(String celularComprador) {
        this.celularComprador = celularComprador;
    }

    public String getEmailComprador() {
        return emailComprador;
    }

    public void setEmailComprador(String emailComprador) {
        this.emailComprador = emailComprador;
    }

    public int getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(int idComprador) {
        this.idComprador = idComprador;
    }
    
    
}
