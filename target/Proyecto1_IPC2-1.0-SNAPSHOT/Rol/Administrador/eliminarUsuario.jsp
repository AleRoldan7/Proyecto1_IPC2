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
        <link href="AdministradorCSS/styleEliminar.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="form-container">
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

                        String currentUser = (String) session.getAttribute("currentUser");

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
                                <% 
                                    if (currentUser != null && currentUser.equals(userName)) {
                                        out.println("<input type='submit' name='eliminarUsuario' value='Eliminar' disabled title='No puedes eliminarte a ti mismo.'/>");
                                    } else {
                                        out.println("<input type='submit' name='eliminarUsuario' value='Eliminar'/>");
                                    }
                                %>
                            </form>
                        </td>
                    </tr>
                    <% 
                        }
                        conexion.cerrarConexion(conn, stmt, rs);
                    %>
                </tbody>
            </table>

            <form action="admin.jsp" method="get">
                <input type="submit" value="Regresar a Administración"/>
            </form>

            <%
                String usuarioAEliminar = request.getParameter("usuario");
                if (usuarioAEliminar != null && request.getParameter("eliminarUsuario") != null) {
                    String currentUserCheck = (String) session.getAttribute("currentUser");

                    if (currentUserCheck != null && currentUserCheck.equals(usuarioAEliminar)) {
                        out.println("<p class='error'>No puedes eliminarte a ti mismo.</p>");
                    } else {
                        EliminarUsuarios eliminar = new EliminarUsuarios();
                        boolean eliminado = eliminar.eliminarUsuario(usuarioAEliminar);
                        
                        if (eliminado) {
                            out.println("<p class='success'>Usuario eliminado correctamente.</p>");
                        } else {
                            out.println("<p class='error'>Error al eliminar el usuario.</p>");
                        }
                    }
                }
            %>
        </div>
    </body>
</html>