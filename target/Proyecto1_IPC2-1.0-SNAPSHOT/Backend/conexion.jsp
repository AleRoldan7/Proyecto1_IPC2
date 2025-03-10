<%-- 
    Document   : conexion
    Created on : 15 feb 2025, 19:06:26
    Author     : alejandro
--%>

<%@ page import="java.sql.*" %>
<%
    String url = "jdbc:mysql://localhost:3306/Trabajador"; 
    String user = "root"; 
    String password = "010418";

    Connection conn = null;
    String mensaje = "";

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url, user, password);
        application.setAttribute("dbConnection", conn);
        mensaje = "Conexión establecida correctamente.";
    } catch (Exception e) {
        mensaje = "Error de conexión: " + e.getMessage();
        e.printStackTrace();
    }

%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Estado de Conexión</title>
</head>
<body>
    <h2>Estado de la Conexión</h2>
    <p><%= mensaje %></p>
    <a href="../Frontend/index.jsp">Volver al inicio</a>
</body>
</html>
