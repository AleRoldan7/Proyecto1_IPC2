/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import ConexionDBA.InstitucionDB;
import EntidadModelo.EntidadInstitucion;
import EntidadModelo.EntidadUsuario;
import Excepciones.DatosInvalidos;
import Excepciones.EntityExists;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author alejandro
 */
public class CrearInstitucion {

    public EntidadInstitucion crearInstitucion(HttpServletRequest request) throws EntityExists, DatosInvalidos {

        InstitucionDB institucionDB = new InstitucionDB();

        EntidadInstitucion entidadInstitucion = extraer(request);

        if (institucionDB.existeUsuario(entidadInstitucion.getNombreInstitucion())) {
            throw new EntityExists(
                    String.format("La Institucion con nombre %s ya existe", entidadInstitucion.getNombreInstitucion())
            );
        }

        institucionDB.agregarInstitucion(entidadInstitucion);
        System.out.println("si lo agrego " + institucionDB);
        return entidadInstitucion;

    }

    private EntidadInstitucion extraer(HttpServletRequest request) throws DatosInvalidos {
        try {
            String nombre = request.getParameter("nuevaInstitucion");
            String descripcion = request.getParameter("descripcion");

            EntidadInstitucion entidadInstitucion = new EntidadInstitucion(nombre);
            entidadInstitucion.setDescripcion(descripcion);

            if (!entidadInstitucion.valido()) {
                throw new DatosInvalidos("El nombre de la institución es obligatorio");
            }

            return entidadInstitucion;
        } catch (Exception e) {
            throw new DatosInvalidos("Error al procesar los datos: " + e.getMessage());
        }
    }
}
