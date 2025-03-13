<%-- 
    Document   : facturaGenerada
    Created on : 10 mar 2025, 1:29:14
    Author     : alejandro
--%>

<%@page import="java.util.List"%>
<%@page import="AreaVentas.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vista Previa de la Venta</title>
    </head>
    <body>
        <h2>Factura Generada</h2>

        <!-- Mostrar el compradorId para depuración -->
        <p>compradorId: ${param.compradorId}</p>

        <!-- Mostrar los productos seleccionados -->
        <table>
            <thead>
                <tr>
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
                    <td><%= producto.getNombreComputadora()%></td>
                    <td><%= producto.getNombreMolde()%></td>
                    <td><%= producto.getNombreComponente() != null ? producto.getNombreComponente() : "Sin componente"%></td>
                    <td><%= producto.getPrecioTotal()%></td>
                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>

        <!-- Formulario para generar la factura en PDF -->
        <form action="${pageContext.request.contextPath}/GenerarFacturaServlet" method="get">
            <input type="hidden" name="compradorId" value="${param.compradorId}">
            <button type="submit">Generar Factura PDF</button>
        </form>

        <!-- Enlace para ver la factura en PDF -->
        <a href="${pageContext.request.contextPath}/GenerarFacturaServlet?compradorId=${param.compradorId}" target="_blank">Ver factura en PDF</a>

        <br><br>
        <a href="../Rol/Ventas/venta.jsp">Volver a Ventas</a>
    </body>
</html>