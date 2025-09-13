/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Controlador.AgregarRolAdmin;
import EntidadModelo.EntidadAdmin;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alejandro
 */
@WebServlet(name = "RolUsuario", urlPatterns = {"/formularios/RolUsuario"})
public class RolUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AgregarRolAdmin crearAdmin = new AgregarRolAdmin();

        try {
            EntidadAdmin adminCreado = crearAdmin.crearAdmin(request);
            
            request.setAttribute("mensaje", "Usuario de acceso creado correctamente");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/formularios/crear-usuario.jsp");
            dispatcher.forward(request, response);
        } catch (DatosInvalidos e) {
            request.setAttribute("error", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/formularios/crear-usuario.jsp");
            dispatcher.forward(request, response);
        } catch (EntityExists ex) {
            Logger.getLogger(RolUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
