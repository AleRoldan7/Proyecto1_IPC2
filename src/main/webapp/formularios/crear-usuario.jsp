<%-- 
    Document   : crear-usuario
    Created on : 28 ago 2025, 17:24:54
    Author     : alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Crear Usuario de Acceso</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">

        <div class="container mt-5">
            <div class="card shadow-lg">
                <div class="card-header bg-primary text-white">
                    <h3 class="mb-0">Crear Usuario de Acceso</h3>
                </div>
                <div class="card-body">

                    <% if (request.getAttribute("mensaje") != null) { %>
                    <div class="alert alert-success">
                        <%= request.getAttribute("mensaje") %>
                    </div>
                    <% } %>

                    <% if (request.getAttribute("error") != null) { %>
                    <div class="alert alert-danger">
                        <%= request.getAttribute("error") %>
                    </div>
                    <% } %>

                    <form action="<%=request.getContextPath()%>/formularios/RolUsuario" method="POST">

                        <div class="mb-3">
                            <label for="usuario" class="form-label">Nombre de Usuario</label>
                            <input type="text" class="form-control" id="usuario" name="usuario" required>
                        </div>

                        <div class="mb-3">
                            <label for="password" class="form-label">Contraseña</label>
                            <input type="text" class="form-control" id="password" name="password" required>
                        </div>

                        <div class="mb-3">
                            <label for="rol" class="form-label">Rol</label>
                            <select class="form-select" id="rol" name="rol" required>
                                <option value="" disabled selected>Seleccionar rol</option>
                                <option value="Admin_General">Administrador General</option>
                                <option value="Admin_Institucion">Administrador Institución</option>
                                <option value="Participante">Participante</option>
                            </select>
                        </div>

                        <div class="d-flex justify-content-between">
                            <button type="submit" class="btn btn-primary">Agregar Usuario</button>
                             <a href="${pageContext.request.contextPath}/Inicio/index.jsp" class="btn btn-secondary">Cancelar</a>
                        </div>

                    </form>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>