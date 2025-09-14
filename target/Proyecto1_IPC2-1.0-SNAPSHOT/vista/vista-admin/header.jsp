<%-- 
    Document   : header
    Created on : 14 sept 2025, 3:56:47
    Author     : alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand">CodeBugs</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarMenu"
                aria-controls="navbarMenu" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarMenu">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/formularios/agregar-congreso.jsp">Agregar Congreso</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Gestionar Congresos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Usuarios</a>
                </li>
            </ul>
           
            <a class="btn btn-outline-light" href="<%=request.getContextPath()%>/LoginServlet?logout=true">Cerrar Sesión</a>
        </div>
    </div>
</nav>
