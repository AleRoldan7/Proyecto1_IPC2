<%-- 
    Document   : cargarArchivos
    Created on : 11 mar 2025, 2:54:27
    Author     : alejandro
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Admin.CargarArchivo" %>
<%@ page import="java.io.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Cargar y Procesar Archivo</title>
        <link href="AdministradorCSS/styleCarga.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="form-container">
            <h2>Cargar Archivo de Configuración</h2>
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="file">Seleccionar archivo:</label>
                    <input type="file" id="file" name="file" accept=".txt" required>
                </div>
                <input type="submit" value="Cargar y Procesar">
            </form>

            <form action="admin.jsp" method="get">
                <input type="submit" value="Regresar">

            </form>

            <%
                String resultado = "";
                if ("POST".equalsIgnoreCase(request.getMethod())) {
                    try {
                        InputStream input = request.getInputStream();
                        if (input != null) {
                            String uploadPath = application.getRealPath("") + File.separator + "uploads";
                            File uploadDir = new File(uploadPath);
                            if (!uploadDir.exists()) {
                                uploadDir.mkdir();
                            }

                            File tempFile = new File(uploadPath + File.separator + "uploaded.txt");
                            try (FileOutputStream output = new FileOutputStream(tempFile)) {
                                byte[] buffer = new byte[1024];
                                int bytesRead;
                                while ((bytesRead = input.read(buffer)) != -1) {
                                    output.write(buffer, 0, bytesRead);
                                }
                            }

                            CargarArchivo cargador = new CargarArchivo();
                            resultado = cargador.procesarArchivo(tempFile.getAbsolutePath());
                            tempFile.delete();
                        } else {
                            resultado = "Error: No se recibió ningún archivo";
                        }
                    } catch (Exception e) {
                        resultado = "Error: " + e.getMessage();
                    }
                }
            %>

            <% if (!resultado.isEmpty()) {%>
            <div class="result">
                <h3>Resultado del procesamiento:</h3>
                <pre><%= resultado%></pre>
            </div>
            <% }%>
        </div>
    </body>
</html>
