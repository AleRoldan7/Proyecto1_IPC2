<%-- 
    Document   : crearComponente
    Created on : 21 feb 2025, 2:13:21
    Author     : alejandro
--%>

<%@ page import="AreaEnsamblaje.GestionComponentes" %>
<%@ page import="java.io.IOException" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Creación y Actualización de Componentes</title>
        <link href="EnsamblajeCSS/styleCrear.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        
        <h1>Creación y Actualización de Componentes</h1>
        
        
        <div class="container">
            <h2>Crear Nuevo Componente</h2>
            <form action="crearComponente.jsp" method="post">
                <label for="nombreComponente">Nombre de Componente:</label>
                <input type="text" id="nombreComponente" name="nombreComponente" required>
                <br>

                <label for="tipoComponente">Tipo de Componente:</label>
                <select id="tipoComponente" name="tipoComponente" required>
                    <option value="" disabled selected>Tipo de Componente</option>
                    <option value="Procesador">Procesador</option>
                    <option value="Almacenamiento">Almacenamiento</option>
                    <option value="Ram">Ram</option>
                    <option value="Tarjeta Grafica">Tarjeta Gráfica</option>
                </select>
                <br>

                <label for="precioComponente">Precio:</label>
                <input type="number" step="1" id="precioComponente" name="precioComponente" required />
                <br>

                <label for="cantidadComponente">Cantidad en Stock:</label>
                <input type="number" id="cantidadComponente" name="cantidadComponente" required /><br><br>

                <button type="submit">Agregar Nuevo Componente</button>
            </form>
        </div>

        
        <div class="container">
            <h2>Agregar Más Cantidad a un Componente Existente</h2>
            <form action="crearComponente.jsp" method="post">
                <label for="nombreComponenteExistente">Nombre de Componente Existente:</label>
                <input type="text" id="nombreComponenteExistente" name="nombreComponenteExistente" required>
                <br>

                <label for="cantidadAgregar">Cantidad a Agregar:</label>
                <input type="number" id="cantidadAgregar" name="cantidadAgregar" required /><br><br>

                <button type="submit">Agregar Cantidad</button>
            </form>
        </div>

        <form action="ensamblaje.jsp" method="get">
            <button type="submit">Regresar</button>
        </form>
        
        <%
            String nombreComponente = request.getParameter("nombreComponente");
            String tipoComponente = request.getParameter("tipoComponente");
            String precioComponenteStr = request.getParameter("precioComponente");
            String cantidadComponenteStr = request.getParameter("cantidadComponente");

            String nombreComponenteExistente = request.getParameter("nombreComponenteExistente");
            String cantidadAgregarStr = request.getParameter("cantidadAgregar");

            
            if (nombreComponente != null && tipoComponente != null && precioComponenteStr != null && cantidadComponenteStr != null) {
                try {
                    precioComponenteStr = precioComponenteStr.replace(",", ".");
                    double precioComponente = Double.parseDouble(precioComponenteStr);
                    int cantidadComponente = Integer.parseInt(cantidadComponenteStr);

                    GestionComponentes nuevoComponente = new GestionComponentes(nombreComponente, tipoComponente, cantidadComponente, precioComponente);

                    if (nuevoComponente.verificarComponenteExistente(nombreComponente)) {
                        out.println("<p style='color:red;'>El componente ya existe en la base de datos.</p>");
                    } else {
                        boolean componenteAgregado = nuevoComponente.agregarComponente();
                        if (componenteAgregado) {
                            out.println("<p style='color:green;'>Componente agregado correctamente.</p>");
                        } else {
                            out.println("<p style='color:red;'>Error al agregar el componente.</p>");
                        }
                    }
                } catch (NumberFormatException e) {
                    out.println("<p style='color:red;'>Por favor ingrese valores válidos para precio y cantidad.</p>");
                }
            }

            
            if (nombreComponenteExistente != null && cantidadAgregarStr != null) {
                try {
                    int cantidadAgregar = Integer.parseInt(cantidadAgregarStr);
                    GestionComponentes gestionExistente = new GestionComponentes(nombreComponenteExistente, "", 0, 0);

                    if (gestionExistente.verificarComponenteExistente(nombreComponenteExistente)) {
                        boolean cantidadAgregada = gestionExistente.agregarCantidad(cantidadAgregar);
                        if (cantidadAgregada) {
                            out.println("<p style='color:green;'>Cantidad agregada correctamente al componente.</p>");
                        } else {
                            out.println("<p style='color:red;'>Error al agregar cantidad al componente.</p>");
                        }
                    } else {
                        out.println("<p style='color:red;'>El componente no existe en la base de datos.</p>");
                    }
                } catch (NumberFormatException e) {
                    out.println("<p style='color:red;'>Por favor ingrese un valor válido para la cantidad a agregar.</p>");
                }
            }
        %>

    </body>
</html>
