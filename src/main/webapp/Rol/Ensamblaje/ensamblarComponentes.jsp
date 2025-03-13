<%-- 
    Document   : ensamblarComponentes
    Created on : 1 mar 2025, 21:24:39
    Author     : alejandro
--%>

<%@ page import="Admin.ConectarUsuarios" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="AreaEnsamblaje.EnsamblajeConMolde" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ensamblaje de Componentes</title>
        <link href="EnsamblajeCSS/styleEnsamblarMolde.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h1>Área de Ensamblaje</h1>
        
        <!-- Formulario para seleccionar un molde -->
        <form action="ensamblarComponentes.jsp" method="get">
            <label for="molde">Seleccionar Molde:</label>
            <select name="idMolde" id="molde">
                <option value="" selected disabled>Seleccione un molde</option>
                <%
                    Connection conn = null;
                    PreparedStatement stmt = null;
                    ResultSet rs = null;
                    try {
                        conn = new ConectarUsuarios().conectar();
                        String query = "SELECT idMolde, nombreMolde FROM molde";
                        stmt = conn.prepareStatement(query);
                        rs = stmt.executeQuery();
                        while (rs.next()) {
                %>
                <option value="<%= rs.getInt("idMolde") %>"><%= rs.getString("nombreMolde") %></option>
                <%
                        }
                    } catch (SQLException e) {
                        out.println("<p>Error al cargar los moldes: " + e.getMessage() + "</p>");
                    } finally {
                        if (rs != null) rs.close();
                        if (stmt != null) stmt.close();
                        if (conn != null) conn.close();
                    }
                %>
            </select>
            <button type="submit">Ver Componentes</button>
        </form>

        <!-- Botón para crear un nuevo molde -->
        <form action="crearMolde.jsp" method="get">
            <button type="submit">Crear Nuevo Molde</button>
        </form>

        <%
            String idMolde = request.getParameter("idMolde");
            if (idMolde != null) {
                try {
                    conn = new ConectarUsuarios().conectar();
                    
                    String query = "SELECT c.idComponente, c.nombreComponente, mc.cantidad " +
                                   "FROM componente c " +
                                   "JOIN molde_componente mc ON c.idComponente = mc.idComponente " +
                                   "WHERE mc.idMolde = ?";
                    stmt = conn.prepareStatement(query);
                    stmt.setInt(1, Integer.parseInt(idMolde));
                    rs = stmt.executeQuery();
        %>
        
        <h2>Componentes del Molde Seleccionado</h2>
        <table border="1">
            <tr>
                <th>Componente</th>
                <th>Cantidad</th>
            </tr>
            <%
                while (rs.next()) {
            %>
                <tr>
                    <td><%= rs.getString("nombreComponente") %></td>
                    <td><%= rs.getInt("cantidad") %></td>
                </tr>
            <%
                }
            %>
        </table>

        <form action="ensamblarComponentes.jsp" method="post">
            <input type="hidden" name="idMolde" value="<%= idMolde %>">
            <button type="submit">Ensamblar Computadora</button>
        </form>

        <%
                } catch (SQLException e) {
                    out.println("<p>Error al cargar los componentes: " + e.getMessage() + "</p>");
                } finally {
                    if (rs != null) rs.close();
                    if (stmt != null) stmt.close();
                    if (conn != null) conn.close();
                }
            }
        %>

        <form action="ensamblaje.jsp" method="get">
            <button type="submit">Regresar</button>
        </form>
        
        <%
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                String idMoldeSeleccionado = request.getParameter("idMolde");

                if (idMoldeSeleccionado != null) {
                    try {
                        conn = new ConectarUsuarios().conectar();
                        EnsamblajeConMolde ensamblaje = new EnsamblajeConMolde();

                        String query = "SELECT idComponente, cantidad FROM molde_componente WHERE idMolde = ?";
                        stmt = conn.prepareStatement(query);
                        stmt.setInt(1, Integer.parseInt(idMoldeSeleccionado));
                        rs = stmt.executeQuery();

                        java.util.List<String> componentes = new java.util.ArrayList<>();
                        java.util.List<Integer> cantidades = new java.util.ArrayList<>();

                        while (rs.next()) {
                            componentes.add(rs.getString("idComponente"));
                            cantidades.add(rs.getInt("cantidad"));
                        }

                        String[] componentesArray = componentes.toArray(new String[0]);
                        int[] cantidadesArray = cantidades.stream().mapToInt(i -> i).toArray();

                        boolean resultado = ensamblaje.ensamblarComputadora(
                            Integer.parseInt(idMoldeSeleccionado), componentesArray, cantidadesArray);

                        if (resultado) {
                            out.println("<p>¡Computadora ensamblada correctamente y stock actualizado!</p>");
                        } else {
                            out.println("<p>No hay suficiente stock para ensamblar la computadora.</p>");
                        }
                    } catch (NumberFormatException | SQLException e) {
                        out.println("<p>Error: " + e.getMessage() + "</p>");
                    } finally {
                        if (stmt != null) stmt.close();
                        if (conn != null) conn.close();
                    }
                }
            }
        %>
    </body>
</html>
