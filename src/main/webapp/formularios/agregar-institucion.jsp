<%-- 
    Document   : agregar-institucion
    Created on : 15 sept 2025, 22:41:13
    Author     : alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="EntidadModelo.EntidadInstitucion"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Institucion</title>
        <jsp:include page="/include/resources.jsp" />
    </head>
    <script src="${pageContext.servletContext.contextPath}/resources/js/mostrar-instituciones.js"></script>
    <body class="bg-light">

        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card shadow-lg">
                        <div class="card-header bg-primary text-white text-center">
                            <h3>Registrar Nueva Institucion</h3>
                        </div>
                        <div class="card-body">

                            <c:if test="${not empty mensajeExito}">
                                <div class="alert alert-success text-center">
                                    ${mensajeExito}
                                </div>
                            </c:if>

                            <c:if test="${not empty error}">
                                <div class="alert alert-danger text-center">
                                    ${error}
                                </div>
                            </c:if>

                            <form action="<%=request.getContextPath()%>/institucion/InstitucionServlet" method="POST">
                                <div class="mb-3">
                                    <label for="nombre" class="form-label">Nombre Intitucion</label>
                                    <input type="text" class="form-control" id="nombre_insti" name="nombre_institucion" required>
                                </div>

                                <div class="mb-3">
                                    <label for="descripcion" class="form-label">Descripción</label>
                                    <textarea class="form-control" id="descripcion" name="descripcion" rows="3" required></textarea>
                                </div>


                                <div class="d-grid gap-2 mt-3">
                                    <button type="submit" class="btn btn-success">Registrar Institucion</button>
                                    <a href="<%=request.getContextPath()%>/vista/vista-admin/admin-general-vista.jsp" class="btn btn-secondary">Cancelar</a>
                                </div>
                            </form>

                            <div class="mt-4 text-center">
                                <button class="btn btn-outline-info" type="button" onclick="toggleTabla()">
                                    Ver Instituciones Registradas
                                </button>
                            </div>

                            <div id="tablaInstituciones" class="mt-3" style="display:none;">
                                <c:if test="${not empty instituciones}">
                                    <h4 class="mb-3">Instituciones Registradas</h4>
                                    <table class="table table-bordered table-striped">
                                        <thead class="table-dark">
                                            <tr>
                                                <th>Nombre</th>
                                                <th>Descripción</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="inst" items="${instituciones}">
                                                <tr>
                                                    <td>${inst.nombre}</td>
                                                    <td>${inst.descripcion}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </c:if>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
       

    </body>
</html>
