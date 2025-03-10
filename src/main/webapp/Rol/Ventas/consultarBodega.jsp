<%-- 
    Document   : consultarBodega
    Created on : 10 mar 2025, 0:51:15
    Author     : alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="Admin.ConectarUsuarios"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Computadoras Ensambladas</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">



    </head>
    <body>

    <div class="container mt-5">
        <h1 class="text-center mb-4">Computadoras Ensambladas</h1>

        <!-- Tabla con clases de Bootstrap -->
        <table class="table table-striped table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>ID Unión</th>
                    <th>ID Computadora</th>
                    <th>ID Molde</th>
                    <th>Fecha Ensamblaje</th>
                    <th>Usuario</th>
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
                        String query = "SELECT * FROM computadora_molde_componente";
                        stmt = conn.prepareStatement(query);
                        rs = stmt.executeQuery();

                        if (!rs.next()) {
                            out.println("<tr><td colspan='6' class='text-center'>No hay computadoras ensambladas disponibles.</td></tr>");
                        } else {
                            do {
                                int idUnion = rs.getInt("idUnion");
                                int idComputadora = rs.getInt("idComputadora");
                                int idMolde = rs.getInt("idMolde");
                                Timestamp fechaEns = rs.getTimestamp("fechaEnsamblaje");
                                String nombreUsuario = rs.getString("nombreUsuario");
                                double precioTotal = rs.getDouble("precioTotal");
                %>
                <tr>
                    <td><%= idUnion %></td>
                    <td><%= idComputadora %></td>
                    <td><%= idMolde %></td>
                    <td><%= fechaEns %></td>
                    <td><%= nombreUsuario %></td>
                    <td><%= precioTotal %></td>
                </tr>
                <%
                            } while (rs.next());
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        out.println("Error en la consulta SQL: " + e.getMessage());
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

        <!-- Botón para regresar con estilo Bootstrap -->
        <form action="venta.jsp" method="get">
            <button type="submit" class="btn btn-primary">Regresar</button>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz4fnFO9gybY6tQVoC5hqucT6wIS5gFeT5r5TjN0r9a1sAqgbF0ktG49jmV" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-pzjw8f+ua7Kw1TIq0kbwbltt6XqLwvR0gjmsh8k1KkxEqzUqkn8ZHGwW3PY4iA5hQ" crossorigin="anonymous"></script>

    </body>
</html>
