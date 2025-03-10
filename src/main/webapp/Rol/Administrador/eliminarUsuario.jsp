<%-- 
    Document   : eliminarUsuario
    Created on : 21 feb 2025, 1:26:43
    Author     : alejandro
--%>

<%@ page import="Admin.EliminarUsuarios" %>
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
        <title>Eliminar Usuario</title>
    </head>
    <body>
        <h1>Lista de Usuarios</h1>

        <table border="1">
            <thead>
                <tr>
                    <th>Usuario</th>
                    <th>Rol</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    
                    ConectarUsuarios conexion = new ConectarUsuarios();
                    Connection conn = conexion.conectar();
                    String query = "SELECT userName, rolTrabajador FROM usuario";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    ResultSet rs = stmt.executeQuery();

                    
                    while (rs.next()) {
                        String userName = rs.getString("userName");
                        String rol = rs.getString("rolTrabajador");

                %>
                <tr>
                    <td><%= userName %></td>
                    <td><%= rol %></td>
                    <td>
                       
                        <form action="eliminarUsuario.jsp" method="post">
                            <input type="hidden" name="usuario" value="<%= userName %>" />
                            <button type="submit" name="eliminarUsuario">Eliminar</button>
                        </form>
                    </td>
                </tr>
                <form action="admin.jsp" method="get">
                    <button type="submit">Regresar a Administración</button>
                </form>
                <% 
                    }
                    
                    conexion.cerrarConexion(conn, stmt, rs);
                %>
            </tbody>
        </table>

        <%
            
            String usuarioAEliminar = request.getParameter("usuario");
            if (usuarioAEliminar != null && request.getParameter("eliminarUsuario") != null) {
                EliminarUsuarios eliminar = new EliminarUsuarios();
                boolean eliminado = eliminar.eliminarUsuario(usuarioAEliminar);
                
                if (eliminado) {
                    out.println("<p style='color:green;'>Usuario eliminado correctamente.</p>");
                } else {
                    out.println("<p style='color:red;'>Error al eliminar el usuario.</p>");
                }
            }
        %>
    </body>
</html>
