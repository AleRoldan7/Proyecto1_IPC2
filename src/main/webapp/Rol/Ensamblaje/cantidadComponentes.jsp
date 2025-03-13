<%-- 
    Document   : cantidadComponentes
    Created on : 8 mar 2025, 2:38:59
    Author     : alejandro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="Admin.ConectarUsuarios"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Stock de Componentes</title>
        <link href="EnsamblajeCSS/styleStock.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <h1>Stock de Componentes</h1>

        <table border="1">
            <tr>
                <th>ID Componente</th>
                <th>Nombre</th>
                <th>Cantidad en Stock</th>
            </tr>

            <%
                Connection conn = null;
                PreparedStatement stmt = null;
                ResultSet rs = null;

                try {

                    conn = new ConectarUsuarios().conectar();
                   

                    String query = "SELECT idComponente, nombreComponente, cantidadStock FROM componente";
                    stmt = conn.prepareStatement(query);
                    rs = stmt.executeQuery();

                    if (!rs.next()) {
                        out.println("No hay datos en la tabla de componentes."); 
                    } else {

                        do {
                            int idComponente = rs.getInt("idComponente");
                            String nombre = rs.getString("nombreComponente");
                            int cantidadStock = rs.getInt("cantidadStock");
            %>
            <tr>
                <td><%= idComponente%></td>
                <td><%= nombre%></td>
                <td><%= cantidadStock%></td>
            </tr>
            <%
                        } while (rs.next());
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    out.println("Error en la consulta SQL: " + e.getMessage()); // Mensaje de error
                } finally {
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                        if (stmt != null) {
                            stmt.close();
                        }
                        if (conn != null) {
                            conn.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            %>
        </table>

        <form action="ensamblaje.jsp" method="get">
            <button type="submit">Regresar</button>
        </form>

    </body>
</html>
