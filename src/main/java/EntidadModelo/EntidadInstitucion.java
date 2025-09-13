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
    
    private String nombreInstitucion;
    private String descripcion;

    public EntidadInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
        this.descripcion = descripcion;
    }

   
    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    public boolean valido(){
        return StringUtils.isNotBlank(nombreInstitucion);
    }
    
}
