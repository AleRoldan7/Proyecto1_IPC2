<%-- 
    Document   : procesarCambio
    Created on : 11 mar 2025, 1:52:39
    Author     : alejandro
--%>

<%@page import="Admin.AdminUser"%>
<%@page import="java.util.Enumeration"%>  <!-- Agregar esta línea -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Crear instancia de AdminUser
    AdminUser adminUser = new AdminUser();
    
    // Recibir el formulario enviado
    Enumeration<String> parametros = request.getParameterNames();
    
    boolean exito = true;
    String mensaje = "";

    while (parametros.hasMoreElements()) {
        String parametro = parametros.nextElement();

        // Verificar si es un checkbox para cambiar el rol
        if (parametro.startsWith("cambiarRol_")) {
            // Obtener el idUsuario correspondiente
            String idUsuarioStr = parametro.split("_")[1];
            int idUsuario = Integer.parseInt(idUsuarioStr);

            // Verificar si el checkbox fue marcado
            String cambioRol = request.getParameter(parametro);
            if (cambioRol != null && cambioRol.equals("true")) {
                // Obtener el nuevo rol
                String nuevoRol = request.getParameter("nuevoRol_" + idUsuario);

                // Actualizar el rol del usuario
                boolean actualizado = adminUser.actualizarRol(idUsuario, nuevoRol);
                if (!actualizado) {
                    exito = false;
                    mensaje += "No se pudo actualizar el rol del usuario con ID: " + idUsuario + "<br>";
                }
            }
        }
    }

    if (exito) {
        mensaje = "Los roles se han actualizado correctamente.";
    } else {
        if (mensaje.isEmpty()) {
            mensaje = "Algunos roles no se pudieron actualizar.";
        }
    }

    // Establecer el mensaje en la solicitud para mostrarlo en la página anterior
    request.setAttribute("mensaje", mensaje);

    // Redirigir de nuevo a la página de cambio de rol
    request.getRequestDispatcher("cambiarRol.jsp").forward(request, response);
%>
