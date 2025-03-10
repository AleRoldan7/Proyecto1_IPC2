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
    <!-- Incluir Bootstrap desde CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Personalización del formulario */
        .form-container {
            max-width: 600px;
            margin: 0 auto;
            padding: 30px;
            border: 1px solid #ddd;
            border-radius: 8px;
            background-color: #f9f9f9;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <div class="form-container">
            <h2>Registrar Comprador Nuevo</h2>
            
            <% 
                // Si el formulario ha sido enviado
                if ("POST".equalsIgnoreCase(request.getMethod())) {
                    // Obtener los datos del formulario
                    String nombreComprador = request.getParameter("nombreComprador");
                    String nit = request.getParameter("nit");
                    String direccion = request.getParameter("direccion");
                    String celularComprador = request.getParameter("celularComprador");
                    String emailComprador = request.getParameter("emailComprador");
                    
                    // Crear una instancia de AgregarComprador
                    AgregarComprador nuevoComprador = new AgregarComprador(nombreComprador, nit, direccion, celularComprador, emailComprador);
                    
                    // Intentar agregar al comprador a la base de datos
                    boolean registrado = nuevoComprador.agregarComprador();
                    
                    // Mostrar mensaje de éxito o error
                    if (registrado) {
                        out.println("<div class='alert alert-success'>Comprador registrado con éxito.</div>");
                    } else {
                        out.println("<div class='alert alert-danger'>Hubo un error al registrar al comprador. Intente nuevamente.</div>");
                    }
                }
            %>
            
            <form action="registrarComprador.jsp" method="post">
                <!-- Nombre del comprador -->
                <div class="mb-3">
                    <label for="nombreComprador" class="form-label">Nombre del Comprador</label>
                    <input type="text" class="form-control" id="nombreComprador" name="nombreComprador" required pattern="[A-Za-z\s]+" title="Solo se permiten letras y espacios">
                </div>

                <!-- NIT -->
                <div class="mb-3">
                    <label for="nit" class="form-label">NIT</label>
                    <input type="text" class="form-control" id="nit" name="nit" required pattern="^\d{8,}$" title="El NIT debe ser un número de 8 o más dígitos">
                </div>

                <!-- Dirección -->
                <div class="mb-3">
                    <label for="direccion" class="form-label">Dirección</label>
                    <input type="text" class="form-control" id="direccion" name="direccion" required>
                </div>

                <!-- Teléfono del comprador -->
                <div class="mb-3">
                    <label for="celularComprador" class="form-label">Celular</label>
                    <input type="text" class="form-control" id="celularComprador" name="celularComprador" required pattern="^\d{8}$" title="El teléfono debe tener 8 dígitos numéricos">
                </div>

                <!-- Correo electrónico -->
                <div class="mb-3">
                    <label for="emailComprador" class="form-label">Correo Electrónico</label>
                    <input type="email" class="form-control" id="emailComprador" name="emailComprador" required pattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" title="El correo debe tener formato válido y terminar en @.com">
                </div>

                <!-- Botón de enviar -->
                <button type="submit" class="btn btn-primary">Registrar Comprador</button>
                
                <button type="submit" class="btn btn-primary">Regresar Ventas</button>
            </form>
        </div>
    </div>

    <!-- Incluir Bootstrap JS desde CDN -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
