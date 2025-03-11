<%-- 
    Document   : registrarVenta
    Created on : 10 mar 2025, 0:50:46
    Author     : alejandro
--%>
<%@page import="java.util.List"%>
<%@page import="AreaVentas.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registrar Venta</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>Formulario de Venta</h2>

        <form action="${pageContext.request.contextPath}/registrarVenta" method="get">
            <div class="mb-3">
                <label for="nit" class="form-label">NIT del Cliente</label>
                <input type="text" class="form-control" id="nit" name="nit" value="${param.nit}" required>
            </div>
            <button type="submit" class="btn btn-secondary">Buscar</button>
        </form>

        <% 
            String nombreCliente = (String) request.getAttribute("nombreCliente");
            String direccionCliente = (String) request.getAttribute("direccionCliente");
            String celularCliente = (String) request.getAttribute("celularCliente");
            String emailCliente = (String) request.getAttribute("emailCliente");
            String mensajeError = (String) request.getAttribute("mensajeError");

            if (mensajeError != null && !mensajeError.isEmpty()) { 
                out.print("<div class='alert alert-danger'>" + mensajeError + "</div>");
            } 
            if (nombreCliente != null && !nombreCliente.isEmpty()) { 
        %>
        <h4>Datos del Cliente</h4>
        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre</label>
            <input type="text" class="form-control" id="nombre" name="nombre" value="<%= nombreCliente %>" readonly>
        </div>
        <div class="mb-3">
            <label for="direccion" class="form-label">Dirección</label>
            <input type="text" class="form-control" id="direccion" name="direccion" value="<%= direccionCliente %>" readonly>
        </div>
        <div class="mb-3">
            <label for="celular" class="form-label">Celular</label>
            <input type="text" class="form-control" id="celular" name="celular" value="<%= celularCliente %>" readonly>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" value="<%= emailCliente %>" readonly>
        </div>
        <% } %>

        <!-- Formulario para confirmar la compra -->
        <form action="${pageContext.request.contextPath}/registrarVenta" method="post">
            <input type="hidden" name="nit" value="${param.nit}">
            <h4>Productos a Comprar</h4>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Seleccionar</th>
                        <th>Nombre de la Computadora</th>
                        <th>Nombre del Molde</th>
                        <th>Componente</th>
                        <th>Precio Total</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        List<Producto> productos = (List<Producto>) request.getAttribute("productos");
                        if (productos != null) {
                            for (Producto producto : productos) { 
                    %>
                    <tr>
                        <td>
                            <input type="checkbox" name="productos" value="<%= producto.getIdUnion() %>">
                        </td>
                        <td><%= producto.getNombreComputadora() %></td>
                        <td><%= producto.getNombreMolde() %></td>
                        <td><%= producto.getNombreComponente() != null ? producto.getNombreComponente() : "Sin componente" %></td>
                        <td><%= producto.getPrecioTotal() %></td>
                    </tr>
                    <% 
                            }
                        } 
                    %>
                </tbody>
            </table>
            <button type="submit" class="btn btn-primary">Confirmar Compra</button>
        </form>

        <!-- Botones adicionales -->
        <form action="${pageContext.request.contextPath}/Frontend/Ventas/facturaGenerada.jsp" method="get">
            <button type="submit" class="btn btn-primary">Ver Factura</button>
        </form>
        <form action="${pageContext.request.contextPath}/Rol/Ventas/venta.jsp" method="get">
            <button type="submit" class="btn btn-primary">Regresar</button>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
