<%-- 
    Document   : listar-congreso
    Created on : 17 sept 2025, 2:15:29
    Author     : alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Congresos Creados</title>
        <style>
            table {
                border-collapse: collapse;
                width: 100%;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
            }
        </style>
    </head>
    <body>
        <h1>Congresos Creados</h1>

        <table>
            <thead>
                <tr>

                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Fecha Inicio</th>
                    <th>Precio</th>
                    <th>Ubicación</th>

                    <th>Convocatoria Inicio</th>
                    <th>Convocatoria Cierre</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="congreso" items="${congresos}">
                    <tr>

                        <td>${congreso.nombre}</td>
                        <td>${congreso.descripcion}</td>
                        <td>${congreso.fechaInicio}</td>
                        <td>${congreso.precioCongreso}</td>
                        <td>${congreso.ubicacion}</td>

                        <td>${congreso.fechaInicioConvocatoria}</td>
                        <td>${congreso.fechaCierreConvocatoria}</td>
                    </tr>
                </c:forEach>
            </tbody>
            <a href="<%=request.getContextPath()%>/vista/vista-admin/admin-general-vista.jsp" class="btn btn-secondary">Cancelar</a>

    </body>
</html>
