/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AreaVentas;

import Admin.ConectarUsuarios;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "RegistrarVentaServlet", urlPatterns = {"/registrarVenta"})
public class MostrarUsuarioServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nit = request.getParameter("nit");
       
        if (nit != null && !nit.isEmpty()) {
            try {
                Connection conn = new ConectarUsuarios().conectar();
                String query = "SELECT nombreComprador, direccion, celularComprador, emailComprador FROM comprador WHERE nit = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, nit);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    request.setAttribute("nombreCliente", rs.getString("nombreComprador"));
                    request.setAttribute("direccionCliente", rs.getString("direccion"));
                    request.setAttribute("celularCliente", rs.getString("celularComprador"));
                    request.setAttribute("emailCliente", rs.getString("emailComprador"));
                } else {
                    request.setAttribute("mensajeError", "Cliente no encontrado con ese NIT.");
                }

                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("mensajeError", "Hubo un error al buscar al cliente.");
            }
        }

        try {
            Connection conn = new ConectarUsuarios().conectar();
            String query = "SELECT cmc.idUnion, c.nombreComputadora, m.nombreMolde, com.nombreComponente, cmc.precioTotal "
                    + "FROM computadora_molde_componente cmc "
                    + "INNER JOIN computadora c ON cmc.idComputadora = c.idComputadora "
                    + "INNER JOIN molde m ON cmc.idMolde = m.idMolde "
                    + "LEFT JOIN componente com ON cmc.idComponente = com.idComponente";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            List<Producto> productos = new ArrayList<>();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdUnion(rs.getInt("idUnion"));
                producto.setNombreComputadora(rs.getString("nombreComputadora"));
                producto.setNombreMolde(rs.getString("nombreMolde"));
                producto.setNombreComponente(rs.getString("nombreComponente"));
                producto.setPrecioTotal(rs.getDouble("precioTotal"));
                productos.add(producto);
            }

            request.setAttribute("productos", productos);

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensajeError", "Hubo un error al obtener los productos.");
        }

        

        request.getRequestDispatcher("Rol/Ventas/registrarVenta.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        System.out.println("Si AAA");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para registrar ventas";
    }
}
