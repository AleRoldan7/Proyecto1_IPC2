/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AreaEnsamblaje;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
/**
 *
 * @author alejandro
 */
@WebServlet(name = "MostrarComponentesServlet", urlPatterns = {"/MostrarComponentesServlet"})
public class MostrarComponentesServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        request.getRequestDispatcher("Rol/Ensamblaje/crearComponente.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);

        List<GestionComponentes> listaComponentes = GestionComponentes.obtenerComponentes();  // O el método adecuado para obtener los componentes
        request.setAttribute("listaComponentes", listaComponentes);
        RequestDispatcher dispatcher = (RequestDispatcher) request.getRequestDispatcher("crearComponente.jsp");
       

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
        String nombreComponente = request.getParameter("nombreComponente");
        String tipoComponente = request.getParameter("tipoComponente");
        String precioComponenteStr = request.getParameter("precioComponente");
        String cantidadComponenteStr = request.getParameter("cantidadComponente");

        String nombreComponenteExistente = request.getParameter("nombreComponenteExistente");
        String cantidadAgregarStr = request.getParameter("cantidadAgregar");

        if (nombreComponente != null && tipoComponente != null && precioComponenteStr != null && cantidadComponenteStr != null) {
            try {
                double precioComponente = Double.parseDouble(precioComponenteStr);
                int cantidadComponente = Integer.parseInt(cantidadComponenteStr);

                GestionComponentes nuevoComponente = new GestionComponentes(nombreComponente, tipoComponente, cantidadComponente, precioComponente);

                if (nuevoComponente.verificarComponenteExistente(nombreComponente)) {
                    response.getWriter().println("<p style='color:red;'>El componente ya existe en la base de datos.</p>");
                } else {
                    boolean componenteAgregado = nuevoComponente.agregarComponente();
                    if (componenteAgregado) {
                        response.getWriter().println("<p style='color:green;'>Componente agregado correctamente.</p>");
                    } else {
                        response.getWriter().println("<p style='color:red;'>Error al agregar el componente.</p>");
                    }
                }
            } catch (NumberFormatException e) {
                response.getWriter().println("<p style='color:red;'>Por favor ingrese valores válidos para precio y cantidad.</p>");
            }
        }

        if (nombreComponenteExistente != null && cantidadAgregarStr != null) {
            try {
                int cantidadAgregar = Integer.parseInt(cantidadAgregarStr);
                GestionComponentes gestionExistente = new GestionComponentes(nombreComponenteExistente, "", 0, 0);

                if (gestionExistente.verificarComponenteExistente(nombreComponenteExistente)) {
                    boolean cantidadAgregada = gestionExistente.agregarCantidad(cantidadAgregar);
                    if (cantidadAgregada) {
                        response.getWriter().println("<p style='color:green;'>Cantidad agregada correctamente al componente.</p>");
                    } else {
                        response.getWriter().println("<p style='color:red;'>Error al agregar cantidad al componente.</p>");
                    }
                } else {
                    response.getWriter().println("<p style='color:red;'>El componente no existe en la base de datos.</p>");
                }
            } catch (NumberFormatException e) {
                response.getWriter().println("<p style='color:red;'>Por favor ingrese un valor válido para la cantidad a agregar.</p>");
            }
        }
        
        response.setContentType("text/html");
        request.getRequestDispatcher("Rol/Ensamblaje/crearComponente.jsp").forward(request, response);
    
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
