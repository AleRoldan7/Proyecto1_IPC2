/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AreaVentas;

import Admin.ConectarUsuarios;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alejandro
 */
@WebServlet(name = "RegistrarVentaCompra", urlPatterns = {"/RegistrarVentaCompra"})
public class RegistrarVenta extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        request.getRequestDispatcher("Rol/Ventas/registrarVenta.jsp").forward(request, response);

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
        PreparedStatement stmt = null;
        String[] productosSeleccionados = request.getParameterValues("productos");
        String usuario = request.getParameter("nit");
        Connection conn = new ConectarUsuarios().conectar();
        String query1 = "SELECT idComprador,nombreComprador, direccion, celularComprador, emailComprador FROM comprador WHERE nit = ?";
                
        PreparedStatement stmt2;
        try {
            stmt2 = conn.prepareStatement(query1);
            stmt2.setString(1,usuario);
            ResultSet rst = stmt2.executeQuery();
                if (rst.next()) {
                    System.out.println("CAMBIANDO ID COMPRADOR");
                
                    usuario = rst.getString("idComprador");
                    System.out.println("USUARIO: " + usuario);
                } else {
                    request.setAttribute("mensajeError", "Cliente no encontrado con ese NIT.");
                }

        } catch (SQLException ex) {
            Logger.getLogger(RegistrarVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (productosSeleccionados != null) {
            System.out.println("ID DEL COMPRADOR: " + usuario);
            for (String id : productosSeleccionados) {
                
            String query = "INSERT INTO venta (idComprador, idProducto) VALUES (?,?)";
                try {
                    stmt = conn.prepareStatement(query);
                    stmt.setString(1, usuario);
                    stmt.setString(2, id);
                    stmt.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(RegistrarVenta.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
          
        }

        processRequest(request, response);

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
