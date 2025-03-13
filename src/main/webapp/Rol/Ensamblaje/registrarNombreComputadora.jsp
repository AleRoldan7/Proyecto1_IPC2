<%-- 
    Document   : registrarComputadora
    Created on : 8 mar 2025, 2:46:16
    Author     : alejandro
--%>

<%@ page import="AreaEnsamblaje.ComputadoraEnsamblada" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Computadora</title>
        <link href="EnsamblajeCSS/styleEnsamblarMolde.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h1>Registro de Computadora Ensamblada</h1>
        
        <div class="container">
            <form action="registrarNombreComputadora.jsp" method="post">
                <label for="nombreComputadora">Nombre de la Computadora:</label>
                <input type="text" id="nombreComputadora" name="nombreComputadora" required><br><br>
                
                <button type="submit">Registrar Computadora</button>
            </form>
        </div>

        <%
            String nombreComputadora = request.getParameter("nombreComputadora");
            
            if (nombreComputadora != null) { 
                ComputadoraEnsamblada computadora = new ComputadoraEnsamblada("", "", 0, 0.0);
                boolean exito = computadora.guardarComputadora(nombreComputadora, 0.0);
               
                if (exito) {
                    out.println("<p style='color:green;'>Computadora registrada exitosamente con ID: " + computadora.getIdComputadora() + "</p>");
                } else {
                    out.println("<p style='color:red;'>Error al registrar la computadora. Intenta nuevamente.</p>");
                }
            }
        %>
        
        <br>
        
        <form action="ensamblarMoldeComputadora.jsp" method="get">
            <button type="submit">Ensamblar Computadora</button>
        </form>
        <form action="ensamblaje.jsp" method="get">
            <button type="submit">Regresar</button>
        </form>
    </body>
</html>
