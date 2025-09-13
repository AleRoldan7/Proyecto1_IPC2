/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EntidadModelo;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author alejandro
 */
public class EntidadUsuario {

    private String nombre;
    private String correo;
    private String identificador;
    private String institucion;
    private String celular;
    private byte[] foto;
    private boolean estado;
    private double saldo;

    public EntidadUsuario(String nombre, String correo, String identificador, String institucion, String celular) {
        this.nombre = nombre;
        this.correo = correo;
        this.identificador = identificador;
        this.institucion = institucion;
        this.celular = celular;
        this.foto = null;
        this.estado = true;
        this.saldo = 0.0;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean esValido() {
       return StringUtils.isNotBlank(nombre)
        && StringUtils.isNotBlank(identificador);
    }

}
