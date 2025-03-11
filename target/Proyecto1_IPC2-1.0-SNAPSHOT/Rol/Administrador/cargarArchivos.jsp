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
    <style>
        /* Definición de colores */
        :root {
            --color-principal: #3498db; /* Color principal (azul) */
            --color-secundario: #ffffff; /* Blanco */
            --color-terciario: #f0f0f0; /* Gris claro */
            --color-boton: #3498db; /* Botón azul */
            --color-boton-hover: #2980b9; /* Hover del botón */
            --color-borde: #d4d8d9; /* Borde de los campos */
            --color-fondo: #f9e8d8; /* Fondo de la página */
            --color-hover: #e0f7fa; /* Hover de las filas de la tabla */
        }

        /* Formulario de carga de archivo */
        .form-container {
            background-color: var(--color-secundario);
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 800px;
            margin: 20px;
            overflow-x: auto;
        }

        /* Título del formulario */
        .form-container h2 {
            color: var(--color-principal);
            font-size: 28px;
            text-align: center;
            margin-bottom: 25px;
        }

        /* Estilos para las etiquetas de los campos */
        .form-container label {
            display: block;
            color: #333;
            font-weight: bold;
            margin-bottom: 8px;
            font-size: 14px;
        }

        /* Estilos para los campos de entrada */
        .form-container input[type="file"] {
            width: 100%;
            padding: 12px;
            border: 1px solid var(--color-borde);
            border-radius: 6px;
            background-color: var(--color-terciario);
            font-size: 14px;
            color: #333;
            margin-bottom: 20px;
        }

        /* Estilos para el botón */
        .form-container input[type="submit"] {
            background-color: var(--color-boton);
            color: var(--color-secundario);
            padding: 10px 20px;
            border: none;
            border-radius: 6px;
            font-size: 14px;
            cursor: pointer;
            width: 100%;
            transition: background-color 0.3s ease;
        }

        /* Cambio de color cuando el botón es hover */
        .form-container input[type="submit"]:hover {
            background-color: var(--color-boton-hover);
        }

        /* Mensajes de error o éxito */
        .mensaje-error, .mensaje-exito {
            padding: 10px;
            margin-top: 20px;
            border-radius: 4px;
            text-align: center;
            font-weight: bold;
        }

        .mensaje-error {
            background-color: #f8d7da;
            color: #721c24;
        }

        .mensaje-exito {
            background-color: #d4edda;
            color: #155724;
        }
    </style>
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

        <% if (!resultado.isEmpty()) { %>
            <div class="result">
                <h3>Resultado del procesamiento:</h3>
                <pre><%= resultado %></pre>
            </div>
        <% } %>
    </div>
</body>
</html>
