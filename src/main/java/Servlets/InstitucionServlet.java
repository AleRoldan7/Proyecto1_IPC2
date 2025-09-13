/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Controlador.CrearInstitucion;
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


/**
 *
 * @author alejandro
 */
@WebServlet(name = "InstitucionServlet", urlPatterns = {"/formularios/InstitucionServlet"})
public class InstitucionServlet extends HttpServlet {


    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        CrearInstitucion crearInstitucion = new CrearInstitucion();
        
         try {
            
             EntidadInstitucion institucionCreada = crearInstitucion.crearInstitucion(request);
            
            request.setAttribute("institucionCreada", institucionCreada);
            
        } catch (DatosInvalidos | EntityExists e) {
            request.setAttribute("error", e.getMessage());
        }
        
         RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/formularios/agregar-institucion.jsp");
        dispatcher.forward(request, response);
    }

    
}
