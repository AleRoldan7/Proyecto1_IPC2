<%-- 
    Document   : cambiarRol
    Created on : 11 mar 2025, 1:40:58
    Author     : alejandro
--%>

<%@page import="Admin.ConectarUsuarios"%>
<%@page import="java.sql.*" %>
<%@page import="Admin.AdminUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String mensaje = (String) request.getAttribute("mensaje");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cambiar Rol de Usuario</title>
        <link href="AdministradorCSS/styleRol.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="form-container">
            <h1>Cambiar Rol de Usuario</h1>

            <form action="procesarCambioRol.jsp" method="post">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Nombre de Usuario</th>
                            <th>Rol Actual</th>
                            <th>Nuevo Rol</th>
                            <th>Acción</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ConectarUsuarios conexionBD = new ConectarUsuarios();
                            Connection conn = null;
                            PreparedStatement pstmt = null;
                            ResultSet rs = null;

                            try {
                                conn = conexionBD.conectar();
                                if (conn == null) {
                                    out.println("<tr><td colspan='4'>Error al conectar con la base de datos</td></tr>");
                                } else {
                                    String sql = "SELECT userName, rolTrabajador, idUsuario FROM usuario";
                                    pstmt = conn.prepareStatement(sql);
                                    rs = pstmt.executeQuery();

                                    if (!rs.isBeforeFirst()) {
                                        out.println("<tr><td colspan='4'>No se encontraron usuarios</td></tr>");
                                    } else {
                                        while (rs.next()) {
                                            String userName = rs.getString("userName");
                                            String rolTrabajador = rs.getString("rolTrabajador");
                                            int idUsuario = rs.getInt("idUsuario");
                        %>
                        <tr>
                            <td><%= userName%></td>
                            <td><%= rolTrabajador%></td>
                            <td>
                                <select name="nuevoRol_<%= idUsuario%>">
                                    <option value="Administrador" <%= "Administrador".equals(rolTrabajador) ? "selected" : ""%>>Administrador</option>
                                    <option value="Ensamblaje" <%= "Ensamblaje".equals(rolTrabajador) ? "selected" : ""%>>Encargado Ensamblaje</option>
                                    <option value="Ventas" <%= "Ventas".equals(rolTrabajador) ? "selected" : ""%>>Encargado Ventas</option>
                                </select>
                            </td>
                            <td>
                                <input type="hidden" name="idUsuario_<%= idUsuario%>" value="<%= idUsuario%>">
                                <input type="checkbox" name="cambiarRol_<%= idUsuario%>" value="true">
                            </td>
                        </tr>
                        <%
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                out.println("<tr><td colspan='4'>Error: " + e.getMessage() + "</td></tr>");
                                e.printStackTrace();
                            } finally {
                                conexionBD.cerrarConexion(conn, pstmt, rs);
                            }
                        %>
                    </tbody>
                </table>

                <br>

                <input type="submit" value="Cambiar Roles Seleccionados">
            </form>

            <form action="admin.jsp" method="get">
                <input type="submit" value="Regresar a la Administración">
            </form>


        </div>



        <div style="color: <% if (mensaje != null && mensaje.contains("correctamente")) { %>green<% } else { %>red<% } %>; font-weight: bold; text-align: center; margin-top: 20px;">
            <% if (mensaje != null && !mensaje.isEmpty()) {%>
            <%= mensaje%>
            <% }%>
        </div>

    </body>
</html>
