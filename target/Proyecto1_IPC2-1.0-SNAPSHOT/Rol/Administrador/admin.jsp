<%-- 
    Document   : bienvenido
    Created on : 17 feb 2025, 0:43:27
    Author     : alejandro
--%>

<%@ page import="java.sql.*" %>
<%@ page import="Admin.ConectarUsuarios" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrador</title>
        <link href="AdministradorCSS/styleAdmin.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="contenedor-admin">
            <h1>Bienvenido, Administrador</h1>
            <h2>Gestión de Usuarios</h2>

            <form action="eliminarUsuario.jsp" method="get">
                <button type="submit">Dar de baja</button>
            </form>


            <form action="crearUsuario.jsp" method="get">
                <button type="submit">Crear Usuario</button>
            </form>

            <form action="cambiarRol.jsp" method="get">
                <button>Cambiar rol</button>
            </form>


            <form action="../../Frontend/index.jsp" method="get">
                <button>Inicio</button>
            </form>

            <form action="cargarArchivos.jsp" method="get">
                <button>Cargar Archivo</button>
            </form>

            <form action="../Ensamblaje/ensamblaje.jsp" method="get">
                <button type="submit">Ensamblaje</button>
            </form>
            
            <form action="../Ventas/venta.jsp" method="get">
                <button type="submit">Venta</button>
            </form>

            <table>
                <thead>
                    <tr>
                        <th>Usuario</th>
                        <th>Rol</th>
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

                    </tr>

                    <% 
                        }
                        
                        conexion.cerrarConexion(conn, stmt, rs);
                    %>
                </tbody>
            </table>
        </div>
    </body>
</html>
