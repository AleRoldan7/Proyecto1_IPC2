/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import ConexionDBA.Conexion;
import ConexionDBA.AdminDB;
import EntidadModelo.EntidadAdmin;
import OpcionesENUM.RolAdmin;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author alejandro
 */
public class LoginServlet extends HttpServlet {

    
    private AdminDB controladorAdmin = new AdminDB();

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
        

    }
    
    private void crearInicio(HttpServletRequest request, HttpServletResponse response)
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
        
        iniciarSesion(request, response);
        
    }

    private void iniciarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        String rol = request.getParameter("rol");

        RolAdmin roladmin = RolAdmin.valueOf(rol);

        EntidadAdmin entidadAdmin = new EntidadAdmin();
        entidadAdmin.setUsuario(usuario);
        entidadAdmin.setPassword(password);
        entidadAdmin.setRolAdmin(roladmin);

        try {
            if (controladorAdmin.verificarAdmin(entidadAdmin)) {
                
                response.sendRedirect(request.getContextPath() + "/VistaGeneral/vista.jsp");

            } else {
                HttpSession sesion = request.getSession();
                sesion.setAttribute("usuario", usuario);
                response.sendRedirect(request.getContextPath() + "/Inicio/index.jsp");
                

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
