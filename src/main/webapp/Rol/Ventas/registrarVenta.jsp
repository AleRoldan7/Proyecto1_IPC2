<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="AreaVentas.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Venta</title>
        <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            body {
                font-family: Arial, sans-serif;
                background-color: #f9e8d8;
                background-image: linear-gradient(135deg, #f9e8d8 0%, #e8f0f5 100%);
                color: #34495e;
                line-height: 1.6;
                padding: 20px;
            }

            h1 {
                text-align: center;
                color: #e67e22;
                margin-bottom: 20px;
            }

            form {
                display: flex;
                flex-direction: column;
                margin: 20px 0;
                background-color: white;
                padding: 25px;
                border-radius: 10px;
                box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
                width: 100%;
                max-width: 600px;
                margin: 20px auto;
            }

            .form-label {
                font-weight: bold;
                margin-bottom: 5px;
            }

            .form-control {
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 5px;
                margin-bottom: 15px;
                font-size: 16px;
            }

            button {
                background-color: #3498db;
                color: white;
                padding: 12px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                cursor: pointer;
                border-radius: 6px;
                transition: background-color 0.3s;
                margin-top: 10px;
                border: none;
            }

            button:hover {
                background-color: #2980b9;
            }

            button:last-child {
                background-color: #e67e22;
            }

            button:last-child:hover {
                background-color: #d35400;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }

            th, td {
                padding: 12px;
                text-align: left;
                border: 1px solid #ddd;
            }

            th {
                background-color: #e67e22;
                color: white;
            }

            td {
                background-color: #f4f4f4;
            }

            .alert {
                padding: 15px;
                margin-top: 20px;
                border-radius: 5px;
                font-size: 16px;
                text-align: center;
            }

            .alert-danger {
                background-color: #f8d7da;
                color: #721c24;
            }

            .alert-success {
                background-color: #d4edda;
                color: #155724;
            }

            .container {
                text-align: center;
            }

            @media (max-width: 480px) {
                form {
                    margin: 10px;
                    padding: 15px;
                }

                h1 {
                    font-size: 20px;
                }
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Formulario de Venta</h2>

            <form action="${pageContext.request.contextPath}/registrarVenta" method="get">
                <div>
                    <label for="nit" class="form-label">NIT del Cliente</label>
                    <input type="text" class="form-control" id="nit" name="nit" value="${param.nit}" required>
                </div>
                <button type="submit" class="btn">Buscar</button>
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
            <div>
                <label for="nombre" class="form-label">Nombre</label>
                <input type="text" class="form-control" id="nombre" name="nombre" value="<%= nombreCliente%>" readonly>
            </div>
            <div>
                <label for="direccion" class="form-label">Dirección</label>
                <input type="text" class="form-control" id="direccion" name="direccion" value="<%= direccionCliente%>" readonly>
            </div>
            <div>
                <label for="celular" class="form-label">Celular</label>
                <input type="text" class="form-control" id="celular" name="celular" value="<%= celularCliente%>" readonly>
            </div>
            <div>
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" value="<%= emailCliente%>" readonly>
            </div>
            <% } %>

            <%
                if ("POST".equalsIgnoreCase(request.getMethod())) {
                    String nitCliente = request.getParameter("nit");
                    String[] productosSeleccionados = request.getParameterValues("productos");

                    if (productosSeleccionados != null && productosSeleccionados.length > 0) {
                        List<Producto> productosList = new ArrayList<>();

                        // Simulación: Obtener los productos de la base de datos (debes reemplazar esto con tu lógica real)
                        for (String productoId : productosSeleccionados) {
                            Producto producto = new Producto();
                            producto.setIdComputadora(Integer.parseInt(productoId));
                            producto.setNombreComputadora("Computadora " + productoId);
                            producto.setNombreMolde("Molde " + productoId);
                            producto.setNombreComponente("Componente " + productoId);
                            producto.setPrecioTotal(1000.0); // Precio de ejemplo
                            productosList.add(producto);
                        }

                        // Guardar los productos en el request
                        request.setAttribute("productos", productosList);

                        // Redirigir a facturaGenerada.jsp
                        response.sendRedirect(request.getContextPath() + "/Rol/Ventas/facturaGenerada.jsp?compradorId=" + nitCliente);
                    } else {
                        out.print("<div class='alert alert-danger'>No has seleccionado ningún producto.</div>");
                    }
                }
            %>

            <form action="${pageContext.request.contextPath}/RegistrarVentaCompra" method="post">
                <input type="hidden" name="nit" value="${param.nit}">

                <h4>Productos a Comprar</h4>
                <table>
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
                                <input type="checkbox" name="productos" value="<%= producto.getIdUnion()%>">
                            </td>
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
                <button type="submit" class="btn">Confirmar Compra</button>
            </form>

            <form action="${pageContext.request.contextPath}/Rol/Ventas/facturaGenerada.jsp" method="get">
                <button type="submit" class="btn">Ver Factura</button>
            </form>
            <form action="${pageContext.request.contextPath}/Rol/Ventas/venta.jsp" method="get">
                <button type="submit" class="btn">Regresar</button>
            </form>
        </div>
    </body>
</html>