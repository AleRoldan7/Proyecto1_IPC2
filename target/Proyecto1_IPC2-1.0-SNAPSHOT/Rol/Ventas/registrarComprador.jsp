<%-- 
    Document   : registrarComprador
    Created on : 10 mar 2025, 3:11:47
    Author     : alejandro
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="AreaVentas.AgregarComprador" %>
<%@ page import="java.io.*" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Comprador</title>
        <link href="VentaCSS/styleComoprador.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="form-container">
            <h1>Registrar Comprador Nuevo</h1>

            <%
                if ("POST".equalsIgnoreCase(request.getMethod())) {
                    String nombreComprador = request.getParameter("nombreComprador");
                    String nit = request.getParameter("nit");
                    String direccion = request.getParameter("direccion");
                    String celularComprador = request.getParameter("celularComprador");
                    String emailComprador = request.getParameter("emailComprador");

                    AgregarComprador nuevoComprador = new AgregarComprador(nombreComprador, nit, direccion, celularComprador, emailComprador);

                    boolean registrado = nuevoComprador.agregarComprador();

                    if (registrado) {
                        out.println("<div class='alert alert-success'>Comprador registrado con éxito.</div>");
                    } else {
                        out.println("<div class='alert alert-danger'>Hubo un error al registrar al comprador. Intente nuevamente.</div>");
                    }
                }
            %>

            <form action="registrarComprador.jsp" method="post">
                <div>
                    <label for="nombreComprador">Nombre del Comprador</label>
                    <input type="text" id="nombreComprador" name="nombreComprador" required pattern="[A-Za-z\s]+" title="Solo se permiten letras y espacios">
                </div>

                <div>
                    <label for="nit">NIT</label>
                    <input type="text" id="nit" name="nit" required pattern="^\d{8,}$" title="El NIT debe ser un número de 8 o más dígitos">
                </div>

                <div>
                    <label for="direccion">Dirección</label>
                    <input type="text" id="direccion" name="direccion" required>
                </div>

                <div>
                    <label for="celularComprador">Celular</label>
                    <input type="text" id="celularComprador" name="celularComprador" required pattern="^\d{8}$" title="El teléfono debe tener 8 dígitos numéricos">
                </div>

                <div>
                    <label for="emailComprador">Correo Electrónico</label>
                    <input type="email" id="emailComprador" name="emailComprador" required pattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" title="El correo debe tener formato válido y terminar en @.com">
                </div>

                <button type="submit">Registrar Comprador</button>
            </form>

            <form action="venta.jsp" method="get">
                <button type="submit">Regresar a Ventas</button>
            </form>
        </div>
    </body>
</html>
