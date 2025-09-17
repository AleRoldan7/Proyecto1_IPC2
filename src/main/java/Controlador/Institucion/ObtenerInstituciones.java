/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Institucion;

import ConexionDBA.InstitucionDB;
import EntidadModelo.EntidadInstitucion;
import Excepciones.EntidadNotFound;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author alejandro
 */
public class ObtenerInstituciones {
    
    public List<EntidadInstitucion> obtenerDatosInstitucion(){
        
        InstitucionDB institucionDB = new InstitucionDB();
        
        return institucionDB.obtenerInstituciones();
    }
    
    public EntidadInstitucion obtenerInstitucionNombre(String nombre) throws EntidadNotFound {
        
        InstitucionDB institucionDB = new InstitucionDB();
        
        Optional<EntidadInstitucion> instiOptional = institucionDB.obtenerInstitucionNombre(nombre);
        
        if (instiOptional.isEmpty()) {
            throw new EntidadNotFound(
                    String.format("La institucion con nombre %s no existe", nombre)
            );
        }

        return instiOptional.get();
    }
}
