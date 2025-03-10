<%-- 
    Document   : ensamblarMoldeComputadora
    Created on : 9 mar 2025, 22:19:32
    Author     : alejandro
--%>

<%@page import="java.util.ArrayList"%>
<%-- 
    Document   : unionComputadoraMolde
    Created on : 9 mar 2025
    Author     : alejandro
--%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.*" %>
<%@ page import="AreaEnsamblaje.ComputadoraEnsamblada" %>
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
                %>
                            <option value="<%=idComputadora%>"><%=nombreComputadora%></option>
                <%
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (rs != null) rs.close();
                            if (ps != null) ps.close();
                            if (conexion != null) conexion.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                %>
            </select>
            <br><br>

            <label>Molde Disponible:</label>
            <select name="idMolde" id="idMolde" onchange="this.form.submit()" required>
                <%
                    try {
                        conexion = new ConectarUsuarios().conectar();

                        String sql = "SELECT idMolde, nombreMolde FROM molde";
                        ps = conexion.prepareStatement(sql);
                        rs = ps.executeQuery();

                        while (rs.next()) {
                            int idMolde = rs.getInt("idMolde");
                            String nombreMolde = rs.getString("nombreMolde");
                %>
                            <option value="<%=idMolde%>"><%=nombreMolde%></option>
                <%
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (rs != null) rs.close();
                            if (ps != null) ps.close();
                            if (conexion != null) conexion.close();
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
                            String sql = "SELECT c.idComponente, c.nombreComponente, c.precioComponente " +
                                         "FROM componente c " +
                                         "JOIN molde_componente mc ON c.idComponente = mc.idComponente " +
                                         "WHERE mc.idMolde = ?";
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
                                if (rs != null) rs.close();
                                if (ps != null) ps.close();
                                if (conexion != null) conexion.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                %>
            </div>
            <br>

            <label>Nombre de quien ensambla:</label>
            <input type="text" name="nombreUsuario" required>
            <br><br>

            <input type="submit" value="Registrar Ensamblaje">
        </form>

        <%
           
            String idComputadora = request.getParameter("idComputadora");
            String idMolde = request.getParameter("idMolde");
            String nombreUsuario = request.getParameter("nombreUsuario");
            String[] componentesSeleccionados = request.getParameterValues("componentes");

            if (idComputadora != null && idMolde != null && nombreUsuario != null && componentesSeleccionados != null) { 
                
                ComputadoraEnsamblada computadoraEnsamblada = new ComputadoraEnsamblada("", "", 0, 0.0);
                List<Integer> idsComponentes = new ArrayList<>();
                
                
                for (String id : componentesSeleccionados) {
                    idsComponentes.add(Integer.parseInt(id));
                }

               
                double precioTotal = computadoraEnsamblada.calcularPrecioTotal(idsComponentes);

             
                boolean exito = false;
              
                try {
                    ConectarUsuarios conn = new ConectarUsuarios();
                    conexion = conn.conectar();

                    
                    String sql = "INSERT INTO computadora_molde_componente (idComputadora, idMolde, nombreUsuario, precioTotal) VALUES (?, ?, ?, ?)";
                    ps = conexion.prepareStatement(sql);
                    ps.setInt(1, Integer.parseInt(idComputadora));
                    ps.setInt(2, Integer.parseInt(idMolde));
                    ps.setString(3, nombreUsuario);
                    ps.setDouble(4, precioTotal); 

                    int resultado = ps.executeUpdate();
                    if (resultado > 0) {
                        exito = true;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (ps != null) ps.close();
                        if (conexion != null) conexion.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                
                if (exito) {
                    out.println("<p style='color:green;'>Ensamblaje registrado exitosamente.</p>");
                } else {
                    out.println("<p style='color:red;'>Error al registrar el ensamblaje. Intenta nuevamente.</p>");
                }
            }
        %>

    </body>
</html>
