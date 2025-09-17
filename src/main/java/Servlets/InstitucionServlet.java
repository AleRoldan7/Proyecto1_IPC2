/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import ConexionDBA.InstitucionDB;
import Controlador.Institucion.CrearInstitucion;
import Controlador.Institucion.ObtenerInstituciones;
import EntidadModelo.EntidadInstitucion;
import Excepciones.DatosInvalidos;
import Excepciones.EntidadNotFound;
import Excepciones.EntityExists;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author alejandro
 */
@WebServlet(name = "InstitucionServlet", urlPatterns = {"/institucion/InstitucionServlet"})
public class InstitucionServlet extends HttpServlet {

    private InstitucionDB institucionDB = new InstitucionDB();

     /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<EntidadInstitucion> instituciones = institucionDB.obtenerInstituciones();

        request.setAttribute("instituciones", instituciones);

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/formularios/agregar-institucion.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre_institucion");
        String descripcion = request.getParameter("descripcion");

        if (nombre == null || nombre.isBlank()) {
            request.setAttribute("error", "El nombre de la institución es obligatorio.");
        } else if (institucionDB.existeInstitucion(nombre)) {
            request.setAttribute("error", "Ya existe una institución con ese nombre.");
        } else {
            EntidadInstitucion institucion = new EntidadInstitucion(nombre, descripcion);
            institucionDB.agregarInstitucion(institucion);

            request.setAttribute("mensajeExito", "Institución registrada correctamente.");
        }

        List<EntidadInstitucion> instituciones = institucionDB.obtenerInstituciones();
        request.setAttribute("instituciones", instituciones);

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/formularios/agregar-institucion.jsp");
        dispatcher.forward(request, response);
    }

    private boolean obtenerTodos(HttpServletRequest request) {
        return StringUtils.isBlank(request.getParameter("nombre_institucion"));
    }

}
