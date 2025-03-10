<%-- 
    Document   : ensamblaje
    Created on : 17 feb 2025, 2:36:51
    Author     : alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ensamblador</title>
        <link href="EnsamblajeCSS/styleEnsam.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <h1>Bienvenido Ensamblador</h1>

                <form action="crearComponente.jsp" method="get">
                    <button type="submit">Crear Componente</button>
                </form>


                <form action="eliminarComponente.jsp" method="get">
                    <button type="submit">Eliminar Componente</button>
                </form>
                
                <form action="ensamblarComponentes.jsp" method="get">
                    <button>Ensamblar Componentes</button>
                </form>
                
                <form action="registrarNombreComputadora.jsp" method="get">
                    <button>Registrar Computadoras</button>
                </form>
                
                
                <form action="cantidadComponentes.jsp" method="get">
                    <button>Stock de componentes</button>
                </form>
                
                <button>Info. Ensamblador</button>
                
                <form action="../../Frontend/index.jsp" method="get">
                    <button>Inicio Sesión</button>
                </form>
        </div>
    </body>
</html>
