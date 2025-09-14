<%-- 
    Document   : agregar-congreso
    Created on : 14 sept 2025, 3:40:33
    Author     : alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ConexionDBA.CongresoDB, java.sql.Connection, java.sql.PreparedStatement, java.sql.ResultSet"%>
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

                            <% if (request.getAttribute("error") != null) { %>
                            <div class="alert alert-danger text-center">
                                <%= request.getAttribute("error") %>
                            </div>
                            <% } %>

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
