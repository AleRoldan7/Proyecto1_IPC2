<%-- 
    Document   : venta
    Created on : 17 feb 2025, 2:37:16
    Author     : alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ventas</title>
        <link href="VentaCSS/styleVenta.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            
            <h1>Bienvenido Vendedor</h1>
                
            <form action="registrarComprador.jsp" method="get" >
                <button type="submit">Registrar Comprador</button>
            </form>
                
                <form action="registrarVenta.jsp" method="get">
                    <button type="submit">Registrar Venta</button>
                </form>


                <form action="eliminarComponente.jsp" method="get">
                    <button type="submit">Registrar Devoluciones</button>
                </form>
                
                <form action="consultarBodega.jsp" method="get">
                    <button>Consultar Computadoras En Bodega</button>
                </form>
                
                <form action="registrarNombreComputadora.jsp" method="get">
                    <button>Consultar Detalles Factura</button>
                </form>
                
                
                <form action="facturaGenerada.jsp" method="get">
                    <button>Consultar Ventas Del Día</button>
                </form>
                
                <form action="../../Frontend/index.jsp" method="get">
                    <button>Inicio Sesión</button>
                </form>
        </div>
        
    </body>
</html>
