<%-- 
    Document   : agregar-usuario
    Created on : 27 ago 2025, 10:12:08
    Author     : alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="EntidadModelo.EntidadUsuario"%>
<%@ page import="java.util.List" %>
<%@ page import="EntidadModelo.EntidadInstitucion" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Registrar Nuevo Usuario</title>
        <jsp:include page="/include/resources.jsp" />
    </head>
    <script src="${pageContext.servletContext.contextPath}/resources/js/ocultar-forms.js"></script>
    <body class="bg-light">

        <div class="container mt-5">
            <div class="card shadow-lg">
                <div class="card-header bg-primary text-white">
                    <h3 class="mb-0">Registrar Nuevo Usuario</h3>
                </div>
                <div class="card-body">

                    <c:if test="${not empty usuarioCreado}">
                        <div class="alert alert-success text-center">
                            ${usuarioCreado}
                        </div>
                    </c:if>

                    <c:if test="${not empty error}">
                        <div class="alert alert-danger text-center">
                            ${error}
                        </div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/formularios/UsuarioServlet" method="POST" enctype="multipart/form-data">

                        <input type="hidden" name="action" value="registrar">
                        <fieldset class="border p-3 mb-3">


                            <div class="mb-3">
                                <label for="nombre" class="form-label">Nombre Completo *</label>
                                <input type="text" class="form-control" id="nombre" name="nombre_completo" required
                                       pattern="[A-Za-záéíóúÁÉÍÓÚñÑ\s]{3,50}"
                                       title="Solo letras y espacios (3-50 caracteres)">
                            </div>

                            <div class="mb-3">
                                <label for="correo" class="form-label">Correo Electrónico *</label>
                                <input type="email" class="form-control" id="correo" name="correo" required
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
                                <label class="form-label">Foto de Usuario</label>
                                <input type="file" class="form-control mb-2" name="foto" accept="image/*">
                            </div>
                        </fieldset>

                        <div class="mb-3">
                            <label for="id_institucion" class="form-label">Institución</label>
                            <select name="institucion" required>
                                <option value="">Seleccione una institución</option>
                                <%
                                    List<EntidadInstitucion> instituciones = 
                                        (List<EntidadInstitucion>) request.getAttribute("instituciones");
                                    if (instituciones != null) {
                                        for (EntidadInstitucion inst : instituciones) {
                                %>
                                <option value="<%= inst.getId_institucion() %>">
                                    <%= inst.getNombre() %>
                                </option>
                                <%
                                        }
                                    }
                                %>
                            </select>

                        </div>


                        <fieldset class="border p-3 mb-3" id="cuentaFieldset" style="display:none;">

                            <div class="mb-3">
                                <label for="userName" class="form-label">Nombre de Usuario *</label>
                                <input type="text" class="form-control" id="userName" name="user_name" required
                                       pattern="[A-Za-z0-9]{3,20}" title="3-20 caracteres alfanuméricos">
                            </div>

                            <div class="mb-3">
                                <label for="password" class="form-label">Contraseña *</label>
                                <input type="text" class="form-control" id="password" name="password" required
                                       pattern=".{6,20}" title="6-20 caracteres">
                            </div>

                            <div class="mb-3">
                                <label for="rol" class="form-label">Rol de Usuario *</label>
                                <select class="form-select" id="rol" name="rol_usuario" required>
                                    <option value="" disabled selected>Seleccione un rol</option>
                                    <option value="Admin_General">Administrador</option>
                                    <option value="Admin_Institucion">Administrador Institucion</option>
                                    <option value="Participante">Participante</option>
                                    <option value="Comite_Cientifico">Comite Cientifico</option>
                                </select>
                            </div>
                        </fieldset>
                        <div class="d-flex justify-content-between">
                            <button type="submit" class="btn btn-primary">Registrar Usuario</button>
                            <a href="${pageContext.request.contextPath}/inicio/index.jsp" class="btn btn-secondary">Cancelar</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>


    </body>
</html>