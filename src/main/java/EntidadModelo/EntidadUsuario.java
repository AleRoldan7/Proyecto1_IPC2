/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EntidadModelo;

import OpcionesENUM.RolAdmin;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author alejandro
 */
public class EntidadUsuario {

    private String nombre;
    private String correo;
    private String identificacion;
    private String celular;
    private byte[] foto;
    private boolean estado;
    private double cuenta;
    private String userName;
    private String password;
    private RolAdmin rol;

    public EntidadUsuario(String nombre, String correo, String identificacion, String celular, byte[] foto, boolean estado, double cuenta, 
            String userName, String password, RolAdmin rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.identificacion = identificacion;
        this.celular = celular;
        this.foto = foto;
        this.estado = estado;
        this.cuenta = cuenta;
        this.userName = userName;
        this.password = password;
        this.rol = rol;
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

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
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

    public double getCuenta() {
        return cuenta;
    }

    public void setCuenta(double cuenta) {
        this.cuenta = cuenta;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RolAdmin getRol() {
        return rol;
    }

    public void setRol(RolAdmin rol) {
        this.rol = rol;
    }
    
    

   
    public boolean esValido() {
       return StringUtils.isNotBlank(nombre)
        && StringUtils.isNotBlank(identificacion);
    }

}
