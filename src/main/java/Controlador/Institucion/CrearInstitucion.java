/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.Institucion;

import ConexionDBA.InstitucionDB;
import EntidadModelo.EntidadInstitucion;
import Excepciones.DatosInvalidos;
import Excepciones.EntityExists;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author alejandro
 */
public class CrearInstitucion {

    public EntidadInstitucion crearInstitucion(HttpServletRequest request) throws EntityExists, DatosInvalidos {

        InstitucionDB institucionDB = new InstitucionDB();

        EntidadInstitucion entidadInstitucion = extraer(request);

        if (institucionDB.existeInstitucion(entidadInstitucion.getNombre())) {
            throw new EntityExists(
                    String.format("La Institucion con nombre %s ya existe", entidadInstitucion.getNombre())
            );
        }

        institucionDB.agregarInstitucion(entidadInstitucion);

        return entidadInstitucion;
    }

    private EntidadInstitucion extraer(HttpServletRequest request) throws DatosInvalidos {

        try {
            EntidadInstitucion entidadInstitucion = new EntidadInstitucion(
                    request.getParameter("nombre_institucion"), 
                    request.getParameter("descripcion")
                    
            );

            if (!entidadInstitucion.esValido()) {
                throw new DatosInvalidos("Error en los datos enviados");
            }

            return entidadInstitucion;
        } catch (Exception e) {
            throw new DatosInvalidos("Error en los datos enviados" + e.getMessage());
        }
    }
}
