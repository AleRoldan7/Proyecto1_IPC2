/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EntidadModelo;

import java.time.LocalDate;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author alejandro
 */
public class EntidadCongreso {
    
    private int idCongreso;
    private String nombre;
    private String descripcion;
    private LocalDate fechaInicio;
    private double precioCongreso;
    private String ubicacion;
    private int idInstitucion;
    private LocalDate fechaInicioConvocatoria;
    private LocalDate fechaCierreConvocatoria;

    public EntidadCongreso(int idCongreso, String nombre, String descripcion, LocalDate fechaInicio, double precioCongreso, String ubicacion, 
            int id_institucion, LocalDate fechaInicioConvocatoria, LocalDate fechaCierreConvocatoria) {
        this.idCongreso = idCongreso;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.precioCongreso = precioCongreso;
        this.ubicacion = ubicacion;
        this.idInstitucion = id_institucion;
        this.fechaInicioConvocatoria = fechaInicioConvocatoria;
        this.fechaCierreConvocatoria = fechaCierreConvocatoria;
    }

    public EntidadCongreso(String nombre, String descripcion, LocalDate fechaInicio, double precioCongreso, 
            String ubicacion, int idInstitucion, LocalDate fechaInicioConvocatoria, LocalDate fechaCierreConvocatoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.precioCongreso = precioCongreso;
        this.ubicacion = ubicacion;
        this.idInstitucion = idInstitucion;
        this.fechaInicioConvocatoria = fechaInicioConvocatoria;
        this.fechaCierreConvocatoria = fechaCierreConvocatoria;
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public double getPrecioCongreso() {
        return precioCongreso;
    }

    public void setPrecioCongreso(double precioCongreso) {
        this.precioCongreso = precioCongreso;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getId_institucion() {
        return idInstitucion;
    }

    public void setId_institucion(int id_institucion) {
        this.idInstitucion = id_institucion;
    }

    public LocalDate getFechaInicioConvocatoria() {
        return fechaInicioConvocatoria;
    }

    public void setFechaInicioConvocatoria(LocalDate fechaInicioConvocatoria) {
        this.fechaInicioConvocatoria = fechaInicioConvocatoria;
    }

    public LocalDate getFechaCierreConvocatoria() {
        return fechaCierreConvocatoria;
    }

    public void setFechaCierreConvocatoria(LocalDate fechaCierreConvocatoria) {
        this.fechaCierreConvocatoria = fechaCierreConvocatoria;
    }

    
    
    public boolean esValido() {
       return StringUtils.isNotBlank(nombre);
    }
    
}
