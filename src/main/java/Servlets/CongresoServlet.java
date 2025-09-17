/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import ConexionDBA.CongresoDB;
import ConexionDBA.InstitucionDB;
import Controlador.Congreso.CrearCongreso;
import Controlador.Institucion.ObtenerInstituciones;
import EntidadModelo.EntidadCongreso;
import EntidadModelo.EntidadInstitucion;
import Excepciones.DatosInvalidos;
import Excepciones.EntityExists;
import Validaciones.ValidacionCongreso;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author alejandro
 */
@WebServlet(name = "CongresoServlet", urlPatterns = {"/congreso/CongresoServlet"})
public class CongresoServlet extends HttpServlet {

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

        String accion = request.getParameter("accion");

        if ("listar".equalsIgnoreCase(accion)) {
            CongresoDB congresoDB = new CongresoDB();
            List<EntidadCongreso> congresos = congresoDB.listarCongreso();
            request.setAttribute("congresos", congresos);

            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/congreso/listar-congresos.jsp");
            dispatcher.forward(request, response);

        } else {
            InstitucionDB idb = new InstitucionDB();
            List<EntidadInstitucion> instituciones = idb.listarInstituciones();
            request.setAttribute("instituciones", instituciones);

            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/formularios/agregar-congreso.jsp");
            dispatcher.forward(request, response);
        }
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

        ValidacionCongreso validador = new ValidacionCongreso();
        String error = validador.validarFormularioCongreso(request);

        InstitucionDB idb = new InstitucionDB();
        List<EntidadInstitucion> instituciones = idb.listarInstituciones();
        request.setAttribute("instituciones", instituciones);

        if (error != null) {
            request.setAttribute("error", error);
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/formularios/agregar-congreso.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try {

            CrearCongreso crearCongreso = new CrearCongreso();
            EntidadCongreso congresoCreado = crearCongreso.crearCongreso(request);
            request.setAttribute("mensajeExito", "El congreso '" + congresoCreado.getNombre() + "' se ha creado correctamente.");

        } catch (DatosInvalidos | EntityExists e) {
            request.setAttribute("error", e.getMessage());
        }

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/formularios/agregar-congreso.jsp");
        dispatcher.forward(request, response);
    }

}
