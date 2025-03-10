<%-- 
    Document   : procesar
    Created on : 14 feb 2025, 1:41:29
    Author     : alejandro
--%>

<%@ page import="java.sql.*,java.io.*" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Verificación de Usuario</title>
</head>
<body>
    <%
        String usuario = request.getParameter("userName").trim().toLowerCase();
        String contrasena = request.getParameter("pass").trim();
        String rol = request.getParameter("rolTrabajador").trim().toLowerCase();

       
        String dbURL = "jdbc:mysql://localhost:3306/TechHub"; 
        String dbUser = "root"; 
        String dbPass = "010418"; 

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

            
            System.out.println("Usuario: " + usuario);
            System.out.println("Contraseña: " + contrasena);
            System.out.println("Rol: " + rol);

            
            String sql = "SELECT * FROM usuario WHERE userName = ? AND pass = ? AND rolTrabajador = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);
            stmt.setString(3, rol);

            rs = stmt.executeQuery();

            if (rs.next()) {
                
                if ("administrador".equals(rol)) {
                    response.sendRedirect("../Rol/Administrador/admin.jsp");
                } else if ("ensamblaje".equals(rol)) {
                    response.sendRedirect("../Rol/Ensamblaje/ensamblaje.jsp");
                } else if ("ventas".equals(rol)) {
                    response.sendRedirect("../Rol/Ventas/venta.jsp");
                }
            } else {
                
                out.println("<h3>Credenciales incorrectas. Por favor, intente nuevamente.</h3>");
                out.println("<p>Usuario: " + usuario + "</p>");
                out.println("<p>Contraseña: " + contrasena + "</p>");
                out.println("<p>Rol: " + rol + "</p>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Error en la conexión a la base de datos.</h3>");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    %>
</body>
</html>
