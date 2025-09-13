<%-- 
    Document   : agregar-usuario
    Created on : 27 ago 2025, 10:12:08
    Author     : alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="EntidadModelo.EntidadUsuario"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Registrar Nuevo Usuario</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">

        <div class="container mt-5">
            <div class="card shadow-lg">
                <div class="card-header bg-primary text-white">
                    <h3 class="mb-0">Registrar Nuevo Usuario</h3>
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

                    <form action="${pageContext.request.contextPath}/formularios/UsuarioServlet" method="POST" id="usuarioForm">

                        <input type="hidden" name="action" value="registrar">

                        <div class="mb-3">
                            <label for="nombre" class="form-label">Nombre Completo *</label>
                            <input type="text" class="form-control" id="nombre" name="nombre_usuario" required
                                   pattern="[A-Za-záéíóúÁÉÍÓÚñÑ\s]{3,50}"
                                   title="Solo letras y espacios (3-50 caracteres)">
                        </div>

                        <div class="mb-3">
                            <label for="correo" class="form-label">Correo Electrónico *</label>
                            <input type="email" class="form-control" id="correo" name="correo_usuario" required
                                   pattern="^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"
                                   title="Correo válido en formato: usuario@dominio.com">
                        </div>

                        <div class="mb-3">
                            <label for="identificacion" class="form-label">Número de Identificación *</label>
                            <input type="text" class="form-control" id="identificacion" name="identificacion" required
                                   pattern="[A-Za-z0-9]{5,20}"
                                   title="Identificación alfanumérica (5-20 caracteres)">
                        </div>

                        <div class="mb-3">
                            <label for="celular" class="form-label">Número de Celular *</label>
                            <input type="tel" class="form-control" id="celular" name="no_celular" required
                                   pattern="[0-9]{8,12}"
                                   title="Solo números (8-12 dígitos)">
                        </div>

                        <div class="mb-3">
                            <label for="institucionSelect" class="form-label">Institución *</label>
                            <select class="form-select" id="institucionSelect" name="institucionSeleccionada" required>
                                <option value="" disabled selected>Seleccione una institución</option>
                                <% 
                                    List<String> instituciones = (List<String>) request.getAttribute("instituciones");
                                    if (instituciones != null) {
                                        for (String inst : instituciones) {
                                %>
                                <option value="<%=inst%>"><%=inst%></option>
                                <%      }
                                    }
                                %>
                                <option value="nueva">Agregar nueva institución...</option>
                            </select>
                        </div>

                        <div id="nuevaInstitucionDiv" style="display:none;">
                            <div class="mb-3">
                                <label for="nuevaInstitucion" class="form-label">Nombre de la nueva institución</label>
                                <input type="text" class="form-control" id="nuevaInstitucion" name="nuevaInstitucion"
                                       pattern=".{1,100}" title="Máximo 100 caracteres">
                            </div>
                            <div class="mb-3">
                                <label for="descripcion" class="form-label">Descripción</label>
                                <textarea class="form-control" id="descripcion" name="descripcion" rows="2"
                                          maxlength="255"></textarea>
                            </div>
                        </div>



                        <div class="mb-3">
                            <label class="form-label">Foto de Usuario (Opcional)</label>
                            <input type="file" class="form-control mb-2" name="foto_usuario" accept="image/*">
                            <small class="form-text text-muted">O bien, ingresa una URL de la foto:</small>
                            <input type="url" class="form-control" name="fotoUrl" placeholder="https://ejemplo.com/foto.jpg">
                        </div>

                        <div class="d-flex justify-content-between">
                            <a href="${pageContext.request.contextPath}/formularios/crear-usuario.jsp" class="btn btn-secondary">Registrar Usuario</button>
                            <a href="${pageContext.request.contextPath}/Inicio/index.jsp" class="btn btn-secondary">Cancelar</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <script>
            const selectInstitucion = document.getElementById("institucionSelect");
            const nuevaInstitucionDiv = document.getElementById("nuevaInstitucionDiv");

            selectInstitucion.addEventListener("change", function () {
                if (this.value === "nueva") {
                    nuevaInstitucionDiv.style.display = "block";
                } else {
                    nuevaInstitucionDiv.style.display = "none";
                }
            });
        </script>
    </body>
</html>
