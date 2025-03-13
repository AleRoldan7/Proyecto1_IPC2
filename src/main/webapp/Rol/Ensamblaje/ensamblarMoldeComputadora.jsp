<%-- 
    Document   : ensamblarMoldeComputadora
    Created on : 9 mar 2025, 22:19:32
    Author     : alejandro
--%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="AreaEnsamblaje.EnsamblajeComputadora" %>
<%@ page import="java.sql.*" %>
<%@ page import="Admin.ConectarUsuarios" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Ensamblaje de Computadora</title>
        <link href="EnsamblajeCSS/styleEnsamblarMolde.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h1>Registrar Ensamblaje de Computadora</h1>

        <form action="ensamblarMoldeComputadora.jsp" method="POST">

            <label>Nombre de la Computadora:</label>
            <select name="idComputadora" required>
                <%
                    Connection conexion = null;
                    PreparedStatement ps = null;
                    ResultSet rs = null;
                    try {
                        ConectarUsuarios conn = new ConectarUsuarios();
                        conexion = conn.conectar();
                        String sql = "SELECT idComputadora, nombreComputadora FROM computadora";
                        ps = conexion.prepareStatement(sql);
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            int idComputadora = rs.getInt("idComputadora");
                            String nombreComputadora = rs.getString("nombreComputadora");
                            String selected = "";
                            if (request.getParameter("idComputadora") != null && request.getParameter("idComputadora").equals(String.valueOf(idComputadora))) {
                                selected = "selected";
                            }
                %>
                <option value="<%=idComputadora%>" <%= selected%>><%=nombreComputadora%></option>
                <%
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (rs != null) {
                                rs.close();
                            }
                            if (ps != null) {
                                ps.close();
                            }
                            if (conexion != null) {
                                conexion.close();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                %>
            </select>
            <br><br>

            <label>Molde Disponible:</label>
            <select name="idMolde" id="idMolde" required>
                <%
                    try {
                        conexion = new ConectarUsuarios().conectar();
                        String sql = "SELECT idMolde, nombreMolde FROM molde";
                        ps = conexion.prepareStatement(sql);
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            int idMolde = rs.getInt("idMolde");
                            String nombreMolde = rs.getString("nombreMolde");
                            String selected = "";
                            if (request.getParameter("idMolde") != null && request.getParameter("idMolde").equals(String.valueOf(idMolde))) {
                                selected = "selected";
                            }
                %>
                <option value="<%=idMolde%>" <%= selected%>><%=nombreMolde%></option>
                <%
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (rs != null) {
                                rs.close();
                            }
                            if (ps != null) {
                                ps.close();
                            }
                            if (conexion != null) {
                                conexion.close();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                %>
            </select>
            <br><br>

            <div id="componentes">
                <%
                    String idMoldeSeleccionado = request.getParameter("idMolde");
                    if (idMoldeSeleccionado != null) {
                        try {
                            conexion = new ConectarUsuarios().conectar();
                            String sql = "SELECT c.idComponente, c.nombreComponente, c.precioComponente "
                                    + "FROM componente c "
                                    + "JOIN molde_componente mc ON c.idComponente = mc.idComponente "
                                    + "WHERE mc.idMolde = ?";
                            ps = conexion.prepareStatement(sql);
                            ps.setInt(1, Integer.parseInt(idMoldeSeleccionado));
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                int idComponente = rs.getInt("idComponente");
                                String nombreComponente = rs.getString("nombreComponente");
                                double precioComponente = rs.getDouble("precioComponente");
                %>
                <label>
                    <input type="checkbox" name="componentes" value="<%=idComponente%>">
                    <%=nombreComponente%> - $<%=precioComponente%>
                </label>
                <br>
                <%
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                if (rs != null) {
                                    rs.close();
                                }
                                if (ps != null) {
                                    ps.close();
                                }
                                if (conexion != null) {
                                    conexion.close();
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                %>
            </div>
            <br>

            <label>Nombre de quien ensambla:</label>
            <select name="nombreUsuario" required>
                <%
                    try {
                        conexion = new ConectarUsuarios().conectar();
                        String sql = "SELECT userName FROM usuario";
                        ps = conexion.prepareStatement(sql);
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            String userName = rs.getString("userName");
                            String selected = "";
                            if (request.getParameter("nombreUsuario") != null && request.getParameter("nombreUsuario").equals(userName)) {
                                selected = "selected";
                            }
                %>
                <option value="<%=userName%>" <%= selected%>><%=userName%></option>
                <%
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (rs != null) {
                                rs.close();
                            }
                            if (ps != null) {
                                ps.close();
                            }
                            if (conexion != null) {
                                conexion.close();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                %>
            </select>
            <br><br>

            <input type="submit" value="Registrar Ensamblaje">

        </form>

        <form action="../Ensamblaje/ensamblaje.jsp" method="get">
            <button type="submit">Regresar</button>
        </form>


        <%
            String idComputadora = request.getParameter("idComputadora");
            String idMolde = request.getParameter("idMolde");
            String nombreUsuario = request.getParameter("nombreUsuario");
            String[] componentesSeleccionados = request.getParameterValues("componentes");

            if (idComputadora != null && idMolde != null && nombreUsuario != null && componentesSeleccionados != null) {
                List<Integer> idsComponentes = new ArrayList<>();
                for (String id : componentesSeleccionados) {
                    idsComponentes.add(Integer.parseInt(id));
                }

                EnsamblajeComputadora ensamblaje = new EnsamblajeComputadora();
                boolean exito = ensamblaje.registrarEnsamblaje(Integer.parseInt(idComputadora), Integer.parseInt(idMolde), nombreUsuario, idsComponentes);

                if (exito) {
                    out.println("<p style='color:green;'>Ensamblaje registrado exitosamente.</p>");
                } else {
                    out.println("<p style='color:red;'>Error al registrar el ensamblaje. Intenta nuevamente.</p>");
                }
            }
        %>

    </body>
</html>
