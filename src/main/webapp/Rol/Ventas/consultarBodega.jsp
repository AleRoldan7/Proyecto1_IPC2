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
        <link href="VentaCSS/styleBodega.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <div class="container">
            <h1>Computadoras Ensambladas</h1>

            <table>
                <thead>
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
                                out.println("<tr><td colspan='6' class='alert alert-danger'>No hay computadoras ensambladas disponibles.</td></tr>");
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
                        <td><%= idUnion%></td>
                        <td><%= idComputadora%></td>
                        <td><%= idMolde%></td>
                        <td><%= fechaEns%></td>
                        <td><%= nombreUsuario%></td>
                        <td><%= precioTotal%></td>
                    </tr>
                    <%
                                } while (rs.next());
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                            out.println("<tr><td colspan='6' class='alert alert-danger'>Error en la consulta SQL: " + e.getMessage() + "</td></tr>");
                        } finally {
                            try {
                                if (rs != null) {
                                    rs.close();
                                }
                                if (stmt != null) {
                                    stmt.close();
                                }
                                if (conn != null) {
                                    conn.close();
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    %>
                </tbody>
            </table>

            <form action="venta.jsp" method="get">
                <button type="submit">Regresar</button>
            </form>
        </div>

    </body>
</html>
