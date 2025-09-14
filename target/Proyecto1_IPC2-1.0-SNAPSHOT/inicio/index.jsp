<%-- 
    Document   : index
    Created on : 24 ago 2025, 2:44:14
    Author     : alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ConexionDBA.Conexion"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Inicio de Sesión</title>
        <jsp:include page="/include/resources.jsp" />
    </head>
    <body class="bg-light">

        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-5">
                    <div class="card shadow-lg">
                        <div class="card-header bg-primary text-white">
                            <h3 class="mb-0 text-center">Inicio de Sesión</h3>
                        </div>
                        <div class="card-body">
                            <% if (request.getAttribute("error") != null) { %>
                            <div class="alert alert-danger text-center">
                                <%= request.getAttribute("error") %>
                            </div>
                            <% } %>

                            <form action="<%=request.getContextPath()%>/LoginServlet" method="POST">
                                <div class="mb-3">
                                    <label for="usuario" class="form-label">Usuario</label>
                                    <input type="text" class="form-control" id="usuario" name="user_name" required>
                                </div>

                                <div class="mb-3">
                                    <label for="password" class="form-label">Contraseña</label>
                                    <input type="password" class="form-control" id="password" name="password" required>
                                </div>

                                <div class="mb-3">
                                    <label for="rol" class="form-label">Rol</label>
                                    <select class="form-select" id="rol" name="rol_usuario" required>
                                        <option value="" disabled selected>Seleccione un rol</option>
                                        <option value="Admin_General">Administrador General</option>
                                        <option value="Admin_Institucion">Administrador Institución</option>
                                        <option value="Participante">Participante</option>
                                        <option value="Comite_Cientifico">Comite Cientifico</option>
                                    </select>
                                </div>

                                <div class="d-grid gap-2 mt-3">
                                    <button type="submit" class="btn btn-primary">Ingresar</button>
                                    <a href="<%=request.getContextPath()%>/formularios/agregar-usuario.jsp" class="btn btn-success">Registrarse</a>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>