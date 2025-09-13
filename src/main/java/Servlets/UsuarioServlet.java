/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import ConexionDBA.InstitucionDB;
import Controlador.CrearUsuarios;
import EntidadModelo.EntidadInstitucion;
import EntidadModelo.EntidadUsuario;
import Excepciones.DatosInvalidos;
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

/**
 *
 * @author alejandro
 */
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/formularios/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        InstitucionDB institucionDB = new InstitucionDB();
        List<String> instituciones = institucionDB.obtenerNombresInstituciones();
        request.setAttribute("instituciones", instituciones);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/formularios/agregar-usuario.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CrearUsuarios crearUsuarios = new CrearUsuarios();
        String institucionSeleccionada = request.getParameter("institucionSeleccionada");
        String nuevaInstitucionNombre = request.getParameter("nuevaInstitucion");
        String nuevaInstitucionDescripcion = request.getParameter("descripcion");

        try {
            if ("nueva".equals(institucionSeleccionada) && nuevaInstitucionNombre != null && !nuevaInstitucionNombre.isEmpty()) {
                EntidadInstitucion nuevaInst = new EntidadInstitucion(nuevaInstitucionNombre);
                nuevaInst.setDescripcion(nuevaInstitucionDescripcion);

                if (nuevaInst.valido()) {
                    InstitucionDB instDB = new InstitucionDB();
                    instDB.agregarInstitucion(nuevaInst);

                    request.setAttribute("institucionUsuario", nuevaInstitucionNombre);
                }
            } else {
                request.setAttribute("institucionUsuario", institucionSeleccionada);
            }

            EntidadUsuario usuarioCreado = crearUsuarios.crearUsuario(request);

            request.setAttribute("usuarioCreado", usuarioCreado);

        } catch (DatosInvalidos | EntityExists e) {
            request.setAttribute("error", e.getMessage());
        }

        InstitucionDB instDB = new InstitucionDB();
        request.setAttribute("instituciones", instDB.obtenerNombresInstituciones());

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/formularios/agregar-usuario.jsp");
        dispatcher.forward(request, response);

    }

}
