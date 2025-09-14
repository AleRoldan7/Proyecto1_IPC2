/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import ConexionDBA.CongresoDB;
import EntidadModelo.EntidadCongreso;
import Excepciones.DatosInvalidos;
import Excepciones.EntityExists;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author alejandro
 */
public class CrearCongreso {

    private  DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public EntidadCongreso crearCongreso(HttpServletRequest request) throws EntityExists, DatosInvalidos {
        CongresoDB congresoDB = new CongresoDB();

        EntidadCongreso entidadCongreso = extraer(request);

        if (congresoDB.existeCongreso(entidadCongreso.getNombre())) {
            throw new EntityExists(
                    String.format("El congreso con nombre %s ya existe", entidadCongreso.getNombre())
            );
        }

        congresoDB.agregarCongreso(entidadCongreso);

        return entidadCongreso;
    }

    private EntidadCongreso extraer(HttpServletRequest request) throws DatosInvalidos {

        try {
            EntidadCongreso entidadCongreso = new EntidadCongreso(
                    request.getParameter("nombre_congreso"),
                    request.getParameter("descripcion"),
                    LocalDate.parse(request.getParameter("fecha_inicio"), formato),
                    Double.parseDouble(request.getParameter("precio")),
                    request.getParameter("ubicacion"),
                    Integer.parseInt(request.getParameter("id_institucion")),
                    LocalDate.parse(request.getParameter("fecha_apertura_conv"), formato),
                    LocalDate.parse(request.getParameter("fecha_cierre_conv"), formato)
                   
            );

            if (!entidadCongreso.esValido()) {
                throw new DatosInvalidos("Error en los datos enviados");
            }

            return entidadCongreso;
        } catch (Exception e) {
            throw new DatosInvalidos("Error en los datos enviados" + e.getMessage());
        }
    }
}
