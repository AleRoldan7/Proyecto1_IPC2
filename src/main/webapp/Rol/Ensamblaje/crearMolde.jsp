<%-- 
    Document   : crearMolde
    Created on : 1 mar 2025, 23:29:46
    Author     : alejandro
--%>

<%@ page import="java.sql.Connection" %>
<%@ page import="Admin.ConectarUsuarios" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Crear Molde</title>
    <link href="EnsamblajeCSS/styleMolde.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <div>
        <h1>Crear Nuevo Molde</h1>

        <%
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                String nombreMolde = request.getParameter("nombreMolde");
                String[] componentesSeleccionados = request.getParameterValues("componentes");

                if (nombreMolde != null && !nombreMolde.trim().isEmpty() && componentesSeleccionados != null) {
                    Connection conn = null;
                    PreparedStatement stmt = null;
                    ResultSet rs = null;

                    try {
                        conn = new ConectarUsuarios().conectar();
                        conn.setAutoCommit(false); 

                        String verificarMoldeSQL = "SELECT idMolde FROM molde WHERE nombreMolde = ?";
                        stmt = conn.prepareStatement(verificarMoldeSQL);
                        stmt.setString(1, nombreMolde);
                        rs = stmt.executeQuery();

                        if (rs.next()) {
                            out.println("<p>Error: Ya existe un molde con el nombre '" + nombreMolde + "'.</p>");
                        } else {
                            String insertMoldeSQL = "INSERT INTO molde (nombreMolde) VALUES (?)";
                            stmt = conn.prepareStatement(insertMoldeSQL, PreparedStatement.RETURN_GENERATED_KEYS);
                            stmt.setString(1, nombreMolde);
                            int filasAfectadas = stmt.executeUpdate();

                            if (filasAfectadas > 0) {
                                rs = stmt.getGeneratedKeys();
                                int idMolde = -1;
                                if (rs.next()) {
                                    idMolde = rs.getInt(1);
                                }

                                if (idMolde > 0) {
                                    String insertComponentSQL = "INSERT INTO molde_componente (idMolde, idComponente, cantidad) VALUES (?, ?, ?)";
                                    stmt = conn.prepareStatement(insertComponentSQL);

                                    for (String idComponente : componentesSeleccionados) {
                                        int cantidad = Integer.parseInt(request.getParameter("cantidad_" + idComponente)); // Obtener la cantidad
                                        stmt.setInt(1, idMolde);
                                        stmt.setInt(2, Integer.parseInt(idComponente));
                                        stmt.setInt(3, cantidad);
                                        stmt.executeUpdate();
                                    }

                                    conn.commit(); 
                                    out.println("<p>Molde creado correctamente.</p>");
                                }
                            } else {
                                conn.rollback(); 
                                out.println("<p>Error al crear el molde.</p>");
                            }
                        }
                    } catch (SQLException e) {
                        if (conn != null) conn.rollback(); 
                        out.println("<p>Error en la base de datos: " + e.getMessage() + "</p>");
                    } finally {
                        if (rs != null) rs.close();
                        if (stmt != null) stmt.close();
                        if (conn != null) conn.close();
                    }
                } else {
                    out.println("<p>Error: Debes ingresar un nombre de molde y seleccionar al menos un componente.</p>");
                }
            }
        %>

        <form action="crearMolde.jsp" method="post">
            <label for="nombreMolde">Nombre del Molde:</label>
            <input type="text" name="nombreMolde" required><br>

            <h2>Seleccionar Componentes</h2>
            <%
                Connection conn = new ConectarUsuarios().conectar();
                String query = "SELECT idComponente, nombreComponente FROM componente";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
            %>
                <input type="checkbox" name="componentes" value="<%= rs.getInt("idComponente") %>">
                <%= rs.getString("nombreComponente") %> 
                <input type="number" name="cantidad_<%= rs.getInt("idComponente") %>" value="1" min="1" style="width: 50px;"> <br>
            <%
                }
                conn.close();
            %>

            <button type="submit">Guardar Molde</button>
        </form>

        <form action="ensamblarComponentes.jsp" method="post">
            <button type="submit">Regresar</button>
        </form>
    </div>
</body>
</html>