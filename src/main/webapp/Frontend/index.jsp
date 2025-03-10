<%-- 
    Document   : index
    Created on : 14 feb 2025, 0:52:10
    Author     : alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Proyecto 1</title>
        <link href="style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        
        <div id="contenedor" class="form-container">
            <h1>Inicio de sesión</h1>

            <form action="../Backend/procesar.jsp" method="post">

                <label for="userName">Usuario:</label>
                <input type="text" id="userName" name="userName" required>
                <br>

                <label for="pass">Contraseña:</label>
                <input type="password" id="pass" name="pass" required>
                <br>

                <label for="rolTrabajador">Rol de Trabajador:</label>
                <select id="rolTrabajador" name="rolTrabajador" required>
                    <option value="" disabled selected>Selecciona un rol</option>
                    <option value="Administrador">Administrador</option>
                    <option value="Ensamblaje">Encargado Ensamblaje</option>
                    <option value="Ventas">Encargado Ventas</option>
                </select>
                <br>
                <input type="submit" value="Ingresar">
            </form>
            
           
        </div>
    </body>
</html>
