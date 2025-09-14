/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Controlador.CrearUsuarios;
import EntidadModelo.EntidadUsuario;
import Excepciones.ConversionNotFound;
import Excepciones.DatosInvalidos;
import Excepciones.EntityExists;
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
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CrearUsuarios crearUsuarios = new CrearUsuarios();

        Part partFoto = request.getPart("foto");

        if (partFoto != null && partFoto.getSize() > 0) {

            String nombreFoto = System.getProperty("java.io.tmpdir") + "/" + partFoto.getSubmittedFileName();
            File fototmp = new File(nombreFoto);
            partFoto.write(nombreFoto);

            request.setAttribute("fototmp", fototmp);
        }

        try {

            EntidadUsuario usuarioCreado = crearUsuarios.crearUsuario(request);
            request.setAttribute("usuarioCreado", usuarioCreado);

        } catch (EntityExists | DatosInvalidos | FileNotFoundException | ConversionNotFound e) {
            request.setAttribute("error", e.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/formularios/agregar-usuario.jsp");
        dispatcher.forward(request, response);
    }

}
