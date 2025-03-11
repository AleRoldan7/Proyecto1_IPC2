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
public class RegistrarVentaServlet extends HttpServlet {

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

        if (request.getMethod().equals("POST")) {

            String[] productosSeleccionados = request.getParameterValues("productos");

            if (productosSeleccionados != null && productosSeleccionados.length > 0) {
                try {
                    Connection conn = new ConectarUsuarios().conectar();

                    // Verificar si el idComprador existe (usamos el NIT para encontrarlo)
                    String checkCompradorQuery = "SELECT idComprador FROM comprador WHERE nit = ?";
                    PreparedStatement stmtCheckComprador = conn.prepareStatement(checkCompradorQuery);
                    stmtCheckComprador.setString(1, nit); // Usamos el NIT del comprador
                    ResultSet rsCheckComprador = stmtCheckComprador.executeQuery();

                    if (rsCheckComprador.next()) {
                        int idComprador = rsCheckComprador.getInt("idComprador"); // Obtener el idComprador

                        // Obtener el idUsuario del usuario autenticado (asumimos que está en la sesión)
                        HttpSession session = request.getSession();
                        Integer idUsuario = (Integer) session.getAttribute("idUsuario");

                        if (idUsuario == null) {
                            // Manejar el caso cuando idUsuario no está en la sesión (por ejemplo, redirigir a la página de login)
                            request.setAttribute("mensajeError", "Usuario no autenticado.");
                            response.sendRedirect("index.jsp"); // o cualquier página de redirección
                            return; // Detener la ejecución del método
                        }

                        // Consulta para obtener el nombre del usuario que tiene el rol "ventas"
                        String getUserNameQuery
                                = "SELECT userName FROM usuario WHERE idUsuario = ? AND rolTrabajdor = 'Ventas'";
                        PreparedStatement stmtUserName = conn.prepareStatement(getUserNameQuery);
                        stmtUserName.setInt(1, idUsuario); // Establecemos el idUsuario
                        ResultSet rsUserName = stmtUserName.executeQuery();

                        String nombreUsuario = "";
                        if (rsUserName.next()) {
                            nombreUsuario = rsUserName.getString("nombreUsuario");
                        }

                        // Calcular el precio total de los productos seleccionados
                        double precioTotal = 0.0;

                        // Obtener el precio de los componentes asociados a la computadora
                        for (String producto : productosSeleccionados) {
                            // Consulta para obtener el precio de cada producto en la tabla computadora_molde_componente
                            String obtenerPrecioProductoQuery
                                    = "SELECT SUM(cm.precio) AS precioTotal "
                                    + "FROM computadora_molde_componente cm "
                                    + "JOIN computadora c ON cm.idComputadora = c.idComputadora "
                                    + "WHERE cm.idComputadora = ?";

                            PreparedStatement stmtPrecioProducto = conn.prepareStatement(obtenerPrecioProductoQuery);
                            stmtPrecioProducto.setString(1, producto);  // Suponiendo que 'producto' es el id de la computadora
                            ResultSet rsPrecioProducto = stmtPrecioProducto.executeQuery();

                            if (rsPrecioProducto.next()) {
                                precioTotal += rsPrecioProducto.getDouble("precioTotal");
                            }
                            stmtPrecioProducto.close();
                        }

                        // Insertar la venta sin especificar idComprador
                        String insertVentaQuery = "INSERT INTO venta (idComprador, precioTotal, nombreUsuario, fechaVenta) VALUES (?, ?, ?, NOW())";
                        PreparedStatement stmtVenta = conn.prepareStatement(insertVentaQuery);
                        stmtVenta.setInt(1, idComprador);  // Insertamos el idComprador que ya obtuvimos
                        stmtVenta.setDouble(2, precioTotal);
                        stmtVenta.setString(3, nombreUsuario); // Nombre del usuario que hace la venta
                        stmtVenta.executeUpdate();

                        // Maneja los productos en la venta, actualiza el stock, etc.
                        request.setAttribute("mensajeExito", "Venta registrada exitosamente.");
                        stmtVenta.close();
                        conn.close();
                    } else {
                        request.setAttribute("mensajeError", "El comprador no existe en la base de datos.");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                    request.setAttribute("mensajeError", "Hubo un error al registrar la venta.");
                }
            } else {
                request.setAttribute("mensajeError", "No se seleccionaron productos para la venta.");
            }
        }

        request.getRequestDispatcher("Rol/Ventas/registrarVenta.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        System.out.println("Si funcionaaaaaa");
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
