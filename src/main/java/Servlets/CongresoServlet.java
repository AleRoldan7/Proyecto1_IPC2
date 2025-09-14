/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import Controlador.CrearCongreso;
import EntidadModelo.EntidadCongreso;
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
        
        CrearCongreso crearCongreso = new CrearCongreso();
        
         try {
             EntidadCongreso congresoCreado = crearCongreso.crearCongreso(request);

            request.setAttribute("congresoCreado", congresoCreado);
            
        } catch (DatosInvalidos | EntityExists e) {
            request.setAttribute("error", e.getMessage());
        }

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/formularios/agreagar-congreso.jsp");
        dispatcher.forward(request, response);
    }

   
}
