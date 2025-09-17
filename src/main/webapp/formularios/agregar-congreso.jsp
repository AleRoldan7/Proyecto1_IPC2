<%-- 
    Document   : agregar-congreso
    Created on : 14 sept 2025, 3:40:33
    Author     : alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="EntidadModelo.EntidadInstitucion" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Nuevo Congreso</title>
        <jsp:include page="/include/resources.jsp" />
    </head>
    <body class="bg-light">

        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card shadow-lg">
                        <div class="card-header bg-primary text-white text-center">
                            <h3>Registrar Nuevo Congreso</h3>
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

                            <form action="<%=request.getContextPath()%>/congreso/CongresoServlet" method="POST">
                                <div class="mb-3">
                                    <label for="nombre_congreso" class="form-label">Nombre del Congreso</label>
                                    <input type="text" class="form-control" id="nombre_congreso" name="nombre_congreso" required>
                                </div>

                                <div class="mb-3">
                                    <label for="descripcion" class="form-label">Descripción</label>
                                    <textarea class="form-control" id="descripcion" name="descripcion" rows="3" required></textarea>
                                </div>

                                <div class="mb-3">
                                    <label for="fecha_inicio" class="form-label">Fecha de Inicio</label>
                                    <input type="date" class="form-control" id="fecha_inicio" name="fecha_inicio" required>
                                </div>

                                <div class="mb-3">
                                    <label for="precio" class="form-label">Precio</label>
                                    <input type="number" class="form-control" id="precio" name="precio" step="0.01" required>
                                </div>

                                <div class="mb-3">
                                    <label for="ubicacion" class="form-label">Ubicación</label>
                                    <input type="text" class="form-control" id="ubicacion" name="ubicacion" required>
                                </div>

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
                                    
                                <div class="mb-3">
                                    <label for="fecha_apertura_conv" class="form-label">Fecha Apertura Convocatoria</label>
                                    <input type="date" class="form-control" id="fecha_apertura_conv" name="fecha_apertura_conv" required>
                                </div>

                                <div class="mb-3">
                                    <label for="fecha_cierre_conv" class="form-label">Fecha Cierre Convocatoria</label>
                                    <input type="date" class="form-control" id="fecha_cierre_conv" name="fecha_cierre_conv" required>
                                </div>



                                <div class="d-grid gap-2 mt-3">
                                    <button type="submit" class="btn btn-success">Registrar Congreso</button>
                                    <a href="<%=request.getContextPath()%>/vista/vista-admin/admin-general-vista.jsp" class="btn btn-secondary">Cancelar</a>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>