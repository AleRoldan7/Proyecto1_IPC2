<%-- 
    Document   : agregar-institucion
    Created on : 7 sept 2025, 18:30:51
    Author     : alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Agregar Nueva Institución</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">

        <div class="container mt-5">
            <div class="card shadow-lg">
                <div class="card-header bg-primary text-white">
                    <h3 class="mb-0">Registrar Nueva Institución</h3>
                </div>
                <div class="card-body">

                    <% if (request.getAttribute("institucionCreada") != null) { %>
                    <div class="alert alert-success">
                        Institución "<%= ((EntidadModelo.EntidadInstitucion)request.getAttribute("institucionCreada")).getNombreInstitucion() %>" creada correctamente.
                    </div>
                    <% } %>

                    <% if (request.getAttribute("error") != null) { %>
                    <div class="alert alert-danger">
                        <%= request.getAttribute("error") %>
                    </div>
                    <% } %>

                    <form action="${pageContext.request.contextPath}/formularios/InstitucionServlet" method="POST">
                        <div class="mb-3">
                            <label for="nuevaInstitucion" class="form-label">Nombre de la Institución *</label>
                            <input type="text" class="form-control" id="nuevaInstitucion" name="nuevaInstitucion" required>
                        </div>

                        <div class="mb-3">
                            <label for="descripcion" class="form-label">Descripción</label>
                            <textarea class="form-control" id="descripcion" name="descripcion" rows="3"></textarea>
                        </div>

                        <div class="d-flex justify-content-between">
                            <button type="submit" class="btn btn-primary">Agregar Institución</button>
                            <a href="${pageContext.request.contextPath}/Inicio/index.jsp" class="btn btn-secondary">Cancelar</a>
                        </div>
                    </form>

                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
