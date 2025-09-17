/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validaciones;

import ConexionDBA.InstitucionDB;
import EntidadModelo.EntidadInstitucion;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 * @author alejandro
 */
public class ValidacionCongreso {

    private InstitucionDB institucionDB = new InstitucionDB();

    public ValidacionCongreso() {
        this.institucionDB = institucionDB;
    }

    public String validarFormularioCongreso(HttpServletRequest request) {

        String nombre = request.getParameter("nombre_congreso");
        String descripcion = request.getParameter("descripcion");
        String fechaInicio = request.getParameter("fecha_inicio");
        String fechaApertura = request.getParameter("fecha_apertura_conv");
        String fechaCierre = request.getParameter("fecha_cierre_conv");
        String precio = request.getParameter("precio");
        String ubicacion = request.getParameter("ubicacion");
        String idIntitucion = request.getParameter("institucion");

        if (nombre == null || nombre.trim().isEmpty()) {
            return "EL nombre es obligatorio";
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
            return "La descripción es obligatoria";
        }
        if (fechaInicio == null || fechaApertura == null || fechaCierre == null) {
            return "Fecha Obligatoria";
        }
        if (ubicacion == null || ubicacion.trim().isEmpty()) {
            return "Ubicacion Obligatoria";
        }
        if (idIntitucion == null || idIntitucion.isEmpty()) {
            return "Elegir Institucion";
        }

        double precioCongreso;
        try {
            precioCongreso = Double.parseDouble(precio);
            if (precioCongreso < 35) {
                return "El precio no puede ser menor a 35";
            }
        } catch (NumberFormatException e) {
            return "EL precio debe ser una cantidad valida";
        }

        if (fechaInicio.compareTo(fechaApertura) < 0) {
            return "La fecha de inicio no puede ser anterior a la apertura de convocatoria.";
        }
        if (fechaCierre.compareTo(fechaApertura) < 0) {
            return "La fecha de cierre no puede ser anterior a la apertura de convocatoria.";
        }

        int idInstitucion = Integer.parseInt(idIntitucion);
        List<EntidadInstitucion> instituciones = institucionDB.listarInstituciones();
        boolean existe = false;

        for (EntidadInstitucion inst : instituciones) {
            if (inst.getId_institucion() == idInstitucion) {
                existe = true;
                break; 
            }
        }

        if (!existe) {
            return "La institucion no existe";
        }

        return null; 

    }

}
