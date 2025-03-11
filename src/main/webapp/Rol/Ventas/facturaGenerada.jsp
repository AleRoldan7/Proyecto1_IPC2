<%-- 
    Document   : facturaGenerada
    Created on : 10 mar 2025, 1:29:14
    Author     : alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, Admin.ConectarUsuarios"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Vista Previa de la Venta</title>
</head>
<body>

<%-- Obtener el idVenta desde los parámetros de la solicitud (por ejemplo, después de confirmar la compra) --%>
<%
    int idVenta = Integer.parseInt(request.getParameter("idVenta"));
    ConectarUsuarios conexionBD = new ConectarUsuarios();
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        conn = conexionBD.conectar();

        if (conn != null) {
            // Obtener los detalles del comprador
            String queryComprador = "SELECT c.nombre AS nombreComprador, c.direccion, c.telefono, p.nombre AS nombreProducto, p.precio FROM venta_comprador vc"
                                    + " JOIN comprador c ON vc.idComprador = c.idComprador"
                                    + " JOIN venta v ON vc.idVenta = v.idVenta"
                                    + " JOIN producto p ON v.idProducto = p.idProducto"
                                    + " WHERE vc.idVendido = ?";
            pst = conn.prepareStatement(queryComprador);
            pst.setInt(1, idVenta);
            rs = pst.executeQuery();

            if (rs.next()) {
                String nombreComprador = rs.getString("nombreComprador");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String nombreProducto = rs.getString("nombreProducto");
                double precio = rs.getDouble("precio");

                // Mostrar los datos del comprador y producto
                out.println("<h2>Vista Previa de la Compra</h2>");
                out.println("<h3>Datos del Comprador</h3>");
                out.println("<p><strong>Nombre: </strong>" + nombreComprador + "</p>");
                out.println("<p><strong>Dirección: </strong>" + direccion + "</p>");
                out.println("<p><strong>Teléfono: </strong>" + telefono + "</p>");
                
                out.println("<h3>Producto Comprado</h3>");
                out.println("<p><strong>Producto: </strong>" + nombreProducto + "</p>");
                out.println("<p><strong>Precio: </strong>$" + precio + "</p>");

                // Botón para generar la factura
                out.println("<form action='GenerarFactura.jsp' method='post'>");
                out.println("<input type='hidden' name='idVenta' value='" + idVenta + "' />");
                out.println("<button type='submit'>Generar Factura</button>");
                out.println("</form>");
            } else {
                out.println("<h3>No se encontraron detalles para esta venta.</h3>");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        out.println("<p>Error al procesar la solicitud.</p>");
    } finally {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (conn != null) conexionBD.cerrarConexion(conn, null, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
%>

</body>
</html>
