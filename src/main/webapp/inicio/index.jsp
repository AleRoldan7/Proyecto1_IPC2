<%-- 
    Document   : index
    Created on : 24 ago 2025, 2:44:14
    Author     : alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ConexionDBA.Conexion, java.sql.Connection"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Inicio de Sesión</title>
        <script src="../resources/Js/bootstrap.bundle.js"></script>
        <link rel="stylesheet" href="../resources/Css/bootstrap.css">
    </head>
    <body>

        <h2>Inicio de Sesión</h2>
        <div class="login-container">
            <form action="<%=request.getContextPath()%>/LoginServlet" method="POST">
                <label>Usuario:</label><br>
                <input type="text" name="usuario" required><br>

                <label>Contraseña:</label><br>
                <input type="password" name="password" required><br>

                <label>Rol:</label><br>
                <select name="rol" required>
                    <option value="Seleccionar">Seleccionar</option>
                    <option value="Admin_General">Administrador General</option>
                    <option value="Admin_Institucion">Administrador Institución</option>
                    <option value="Participante">Participante</option>
                </select><br><br>

                <button type="submit">Ingresar</button>
            </form>

            <a href="<%=request.getContextPath()%>/formularios/agregar-usuario.jsp" class="btn btn-success mt-3">Registrarse</a>
        </div>
    </body>
</html>
