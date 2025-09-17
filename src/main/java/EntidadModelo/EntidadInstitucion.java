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
public class EntidadInstitucion {
    
    private int idInstitucion;
    private String nombre;
    private String descripcion;

    public EntidadInstitucion(int idInstitucion, String nombre, String descripcion) {
        this.idInstitucion = idInstitucion;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    
    public EntidadInstitucion(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId_institucion() {
        return idInstitucion;
    }

    public void setId_institucion(int id_institucion) {
        this.idInstitucion = id_institucion;
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public boolean esValido() {
       return StringUtils.isNotBlank(nombre);
    }
    
}
