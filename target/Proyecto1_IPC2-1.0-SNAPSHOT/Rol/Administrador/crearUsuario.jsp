<%-- 
    Document   : crearUsuario
    Created on : 17 feb 2025, 23:55:07
    Author     : alejandro
--%>

<%@ page import="Admin.CrearUsuario" %>
<%@ page import="java.io.IOException" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Crear Usuario</title>
    <link href="AdministradorCSS/styleCrear.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <div class="form-container">
        <h1>Crear Nuevo Usuario</h1>

        <form action="crearUsuario.jsp" method="post">
            <label for="userName">Usuario:</label>
            <input type="text" id="userName" name="userName" required>
            <br>

            <label for="password">Contraseña:</label>
            <input type="password" id="pass" name="pass" required>
            <br>

            <label for="rolTrabajador">Rol de Usuario:</label>
            <select id="rolTrabajador" name="rolTrabajador" required>
                <option value="Administrador">Administrador</option>
                <option value="Ensamblaje">Encargado Ensamblaje</option>
                <option value="Ventas">Encargado Ventas</option>
            </select>
            <br><br>

            <input type="submit" value="Crear Usuario">
        </form>
        
        <form action="admin.jsp" method="get">
            <button type="submit">Regresar a Administración</button>
        </form>

        <%

            
            String usuario = request.getParameter("userName");
            String contrasena = request.getParameter("pass");
            String rol = request.getParameter("rolTrabajador");

            if (usuario != null && contrasena != null && rol != null) {
               
                CrearUsuario crearUsuario = new CrearUsuario();
                boolean agregado = crearUsuario.agregarUsuario(usuario, contrasena, rol);

                
                if (agregado) {
                    out.println("<p style='color:green;'>Usuario agregado correctamente.</p>");
                } else {
                    out.println("<p style='color:red;'>Error al agregar usuario.</p>");
                }
            }
        %>
    </div>
</body>
</html>
