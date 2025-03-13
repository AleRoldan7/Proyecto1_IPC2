<%-- 
    Document   : computadoraEnsamblada
    Created on : 12 mar 2025, 0:54:35
    Author     : alejandro
--%>

<%@page import="Admin.ConectarUsuarios"%>
<%@ page import="java.sql.*, java.util.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Computadoras Ensambladas</title>
        <link rel="stylesheet" type="text/css" href="EnsamblajeCSS/styleCompu.css">
    </head>
    <body>
        <h1>Computadoras Ensambladas</h1>
        <div class="container">
            <table>
                <thead>
                    <tr>
                        <th>idUnion</th>
                        <th>idComputadora</th>
                        <th>Nombre Computadora</th>
                        <th>idMolde</th>
                        <th>idComponente</th>
                        <th>Fecha Ensamblaje</th>
                        <th>Nombre Usuario</th>
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
                            String query = "SELECT u.idUnion, u.idComputadora, u.idMolde, u.idComponente, u.fechaEnsamblaje, u.nombreUsuario, u.precioTotal, c.nombreComputadora "
                                    + "FROM computadora_union u "
                                    + "JOIN computadora c ON u.idComputadora = c.idComputadora";
                            stmt = conn.prepareStatement(query);
                            rs = stmt.executeQuery();
                            
                            while (rs.next()) {
                                int idUnion = rs.getInt("idUnion");
                                int idComputadora = rs.getInt("idComputadora");
                                String nombreComputadora = rs.getString("nombreComputadora");
                                int idMolde = rs.getInt("idMolde");
                                int idComponente = rs.getInt("idComponente");
                                Timestamp fechaEnsamblaje = rs.getTimestamp("fechaEnsamblaje");
                                String nombreUsuario = rs.getString("nombreUsuario");
                                double precioTotal = rs.getDouble("precioTotal");

                                // Imprimir datos para depurar
                                System.out.println("idUnion: " + idUnion + ", idComputadora: " + idComputadora + ", nombreComputadora: " + nombreComputadora);
                    %>

                    %>
                    <tr>
                        <td><%= idUnion%></td>
                        <td><%= idComputadora%></td>
                        <td><%= nombreComputadora%></td>
                        <td><%= idMolde%></td>
                        <td><%= idComponente%></td>
                        <td><%= fechaEnsamblaje%></td>
                        <td><%= nombreUsuario%></td>
                        <td><%= precioTotal%></td>
                    </tr>
                    <%
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
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
        </div>
    </body>
</html>
