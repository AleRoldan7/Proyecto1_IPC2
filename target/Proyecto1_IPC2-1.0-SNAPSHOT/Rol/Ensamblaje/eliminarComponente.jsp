<%-- 
    Document   : eliminarComponente
    Created on : 22 feb 2025, 23:58:39
    Author     : alejandro
--%>

<%@ page import="AreaEnsamblaje.GestionComponentes" %>
<%@ page import="Admin.ConectarUsuarios" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar Componente</title>
        <link href="EnsamblajeCSS/styleEliminar.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h1>Lista de Componentes</h1>

        <table border="1">
            <thead>
                <tr>
                    <th>Componente</th>
                    <th>Tipo</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    ConectarUsuarios conexion = new ConectarUsuarios();
                    Connection conn = conexion.conectar();
                    String query = "SELECT nombreComponente, tipoComponente, precioComponente, cantidadStock FROM componente";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    ResultSet rs = stmt.executeQuery();

                    while (rs.next()) {
                        String nombreComponente = rs.getString("nombreComponente");
                        String tipo = rs.getString("tipoComponente");
                        double precio = rs.getDouble("precioComponente");
                        int cantidad = rs.getInt("cantidadStock");
                %>
                <tr>
                    <td><%= nombreComponente %></td>
                    <td><%= tipo %></td>
                    <td><%= precio %></td>
                    <td><%= cantidad %></td>
                    <td>
                        <form action="eliminarComponente.jsp" method="post">
                            <input type="hidden" name="nombreComponente" value="<%= nombreComponente %>" />
                            <input type="number" name="cantidadEliminar" min="1" max="<%= cantidad %>" required />
                            <button type="submit" name="eliminarComponente">Eliminar</button>
                        </form>
                    </td>
                </tr>
                <% 
                    }
                    
                    conexion.cerrarConexion(conn, stmt, rs);
                %>
            </tbody>
        </table>

        <form action="../Ensamblaje/ensamblaje.jsp" method="get">
            <button type="submit">Regresar</button>
        </form>

        <%
            String nombreComponenteAEliminar = request.getParameter("nombreComponente");
            String cantidadEliminarStr = request.getParameter("cantidadEliminar");

            if (nombreComponenteAEliminar != null && cantidadEliminarStr != null) {
                try {
                    int cantidadEliminar = Integer.parseInt(cantidadEliminarStr);

                    GestionComponentes gestion = new GestionComponentes("", "", 0, 0);
                    boolean eliminado = gestion.eliminarComponente(nombreComponenteAEliminar, cantidadEliminar);

                    if (eliminado) {
                        out.println("<p style='color:green;'>Se eliminaron " + cantidadEliminar + " unidades de " + nombreComponenteAEliminar + ".</p>");
                    } else {
                        out.println("<p style='color:red;'>Error al eliminar el componente.</p>");
                    }
                } catch (NumberFormatException e) {
                    out.println("<p style='color:red;'>Cantidad inválida.</p>");
                }
            }
        %>

    </body>
</html>
