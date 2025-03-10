<%-- 
    Document   : registrarVenta
    Created on : 10 mar 2025, 0:50:46
    Author     : alejandro
--%>
<%@page import="Admin.ConectarUsuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, javax.sql.DataSource"%>
<%@page import="java.util.regex.*"%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Venta</title>
        <!-- Incluir Bootstrap desde un CDN -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

    <div class="container mt-5">
        <h2>Formulario de Venta</h2>

        <!-- Formulario para ingresar NIT del cliente -->
        <form action="registrarVenta.jsp" method="post">
            <div class="mb-3">
                <label for="nit" class="form-label">NIT del Cliente</label>
                <input type="text" class="form-control" id="nit" name="nit" required>
            </div>

            <%
                String nit = request.getParameter("nit");
                String nombreCliente = "";
                String direccionCliente = "";
                String celularCliente = "";
                String emailCliente = "";
                String mensajeError = "";

                if (nit != null && !nit.isEmpty()) {
                    // Realizar consulta para obtener los datos del cliente por NIT
                    Connection conn = null;
                    PreparedStatement stmt = null;
                    ResultSet rs = null;

                    try {
                        conn = new ConectarUsuarios().conectar();
                        String query = "SELECT nombreComprador, direccion, celularComprador, emailComprador FROM comprador WHERE nit = ?";
                        stmt = conn.prepareStatement(query);
                        stmt.setString(1, nit);
                        rs = stmt.executeQuery();

                        if (rs.next()) {
                            // Si se encuentra el cliente, asignamos los valores
                            nombreCliente = rs.getString("nombreComprador");
                            direccionCliente = rs.getString("direccion");
                            celularCliente = rs.getString("celularComprador");
                            emailCliente = rs.getString("emailComprador");
                        } else {
                            // Si no se encuentra el cliente, mostramos un mensaje de error
                            mensajeError = "Cliente no encontrado con ese NIT.";
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        mensajeError = "Hubo un error al buscar al cliente.";
                    } finally {
                        try {
                            if (rs != null) rs.close();
                            if (stmt != null) stmt.close();
                            if (conn != null) conn.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }

                // Mostrar mensaje de error, si lo hay
                if (!mensajeError.isEmpty()) {
                    out.print("<div class='alert alert-danger'>" + mensajeError + "</div>");
                }
            %>

            <!-- Si el cliente existe, mostramos sus datos -->
            <%
                if (!nombreCliente.isEmpty()) {
            %>
            <h4>Datos del Cliente</h4>
            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre</label>
                <input type="text" class="form-control" id="nombre" name="nombre" value="<%= nombreCliente %>" readonly>
            </div>
            <div class="mb-3">
                <label for="direccion" class="form-label">Dirección</label>
                <input type="text" class="form-control" id="direccion" name="direccion" value="<%= direccionCliente %>" readonly>
            </div>
            <div class="mb-3">
                <label for="celular" class="form-label">Celular</label>
                <input type="text" class="form-control" id="celular" name="celular" value="<%= celularCliente %>" readonly>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" value="<%= emailCliente %>" readonly>
            </div>
            <%
                }
            %>

            <!-- Productos para comprar -->
         <h4>Productos a Comprar</h4>
<table class="table table-bordered">
    <thead>
        <tr>
            <th>Seleccionar</th>
            <th>Nombre de la Computadora</th>
            <th>Nombre del Molde</th>
            <th>Componente</th>
            <th>Precio Total</th>
        </tr>
    </thead>
    <tbody>
        <%
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                conn = new ConectarUsuarios().conectar();
                // Consulta ajustada para obtener los datos con INNER JOIN
                String query = "SELECT cmc.idUnion, c.nombreComputadora, m.nombreMolde, com.nombreComponente, cmc.precioTotal "
                             + "FROM computadora_molde_componente cmc "
                             + "INNER JOIN computadora c ON cmc.idComputadora = c.idComputadora "
                             + "INNER JOIN molde m ON cmc.idMolde = m.idMolde "
                             + "LEFT JOIN componente com ON cmc.idComponente = com.idComponente";
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
                
                while (rs.next()) {
                    String nombreComputadora = rs.getString("nombreComputadora");
                    String nombreMolde = rs.getString("nombreMolde");
                    String nombreComponente = rs.getString("nombreComponente");
                    double precioTotal = rs.getDouble("precioTotal");
        %>
                    <tr>
                        <td>
                            <input type="checkbox" name="productos" value="<%= nombreComputadora %>">
                        </td>
                        <td><%= nombreComputadora %></td>
                        <td><%= nombreMolde %></td>
                        <td><%= (nombreComponente != null ? nombreComponente : "Sin componente") %></td>
                        <td><%= precioTotal %></td>
                    </tr>
        <%
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (stmt != null) stmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        %>
    </tbody>
</table>


            <!-- Botón para confirmar -->
            <button type="submit" class="btn btn-primary">Confirmar Compra</button>
        </form>
    </div>

    <!-- Incluir JS de Bootstrap desde un CDN -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

    </body>
</html>
