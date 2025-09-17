/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import ConexionDBA.InstitucionDB;
import Controlador.Usuario.CrearUsuarios;
import EntidadModelo.EntidadInstitucion;
import EntidadModelo.EntidadUsuario;
import Excepciones.ConversionNotFound;
import Excepciones.DatosInvalidos;
import Excepciones.EntityExists;
import Validaciones.ValidacionUsuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alejandro
 */
@MultipartConfig
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/formularios/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        InstitucionDB idb = new InstitucionDB();
        List<EntidadInstitucion> instituciones = idb.listarInstituciones();
        request.setAttribute("instituciones", instituciones);

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/formularios/agregar-usuario.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ValidacionUsuario validar = new ValidacionUsuario();
        String error = validar.validarFormularioUsuario(request);

        InstitucionDB idb = new InstitucionDB();
        List<EntidadInstitucion> instituciones = idb.listarInstituciones();
        request.setAttribute("instituciones", instituciones);
        
        if (error != null) {
            request.setAttribute("error", error);
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/formularios/agregar-usuario.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try {

            CrearUsuarios crearUsuarios = new CrearUsuarios();

            byte[] fotoBytes = null;
            Part partFoto = request.getPart("foto");
            if (partFoto != null && partFoto.getSize() > 0) {
                try (InputStream is = partFoto.getInputStream()) {
                    fotoBytes = is.readAllBytes();
                }
            }

            EntidadUsuario usuarioCreado = crearUsuarios.crearUsuario(request, fotoBytes);
            request.setAttribute("usuarioCreado", "Usuario Creado  ' " + usuarioCreado.getNombre() + "' correctamente");

        } catch (EntityExists | DatosInvalidos | FileNotFoundException | ConversionNotFound e) {
            request.setAttribute("error", e.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/formularios/agregar-usuario.jsp");
        dispatcher.forward(request, response);
    }

}
