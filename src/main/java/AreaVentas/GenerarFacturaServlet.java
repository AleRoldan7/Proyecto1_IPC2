/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AreaVentas;

import Admin.ConectarUsuarios;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author alejandro
 */
@WebServlet(name = "GenerarFacturaServlet", urlPatterns = {"/GenerarFacturaServlet"})
public class GenerarFacturaServlet extends HttpServlet {

    private ConectarUsuarios conectarUsuarios = new ConectarUsuarios();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("GenerarFacturaServlet: doGet ejecutado");

        try {
            // Obtener el compradorId desde la solicitud
            int compradorId = Integer.parseInt(request.getParameter("compradorId"));
            System.out.println("compradorId recibido: " + compradorId);

            // Obtener los productos comprados por el comprador
            List<Producto> productos = Producto.obtenerProductosPorCompradorId(compradorId);
            System.out.println("Productos obtenidos: " + productos.size());

            // Pasar los productos al JSP para mostrar
            request.setAttribute("productos", productos);

            // Generar la factura en PDF
            generarFacturaPDF(productos, request, response);

        } catch (NumberFormatException e) {
            System.out.println("Error: compradorId no válido");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de comprador no válido");
        } catch (SQLException e) {
            System.out.println("Error de base de datos: " + e.getMessage());
            throw new ServletException("Error al acceder a la base de datos", e);
        } catch (JRException e) {
            System.out.println("Error al generar el PDF: " + e.getMessage());
            throw new ServletException("Error al generar la factura PDF", e);
        }
    }

    public void generarFacturaPDF(List<Producto> productos, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JRException, SQLException {
        // Verificar el stock de los componentes antes de generar la factura
        for (Producto producto : productos) {
            verificarStock(producto.getIdComponente());
        }

        // Cargar el reporte Jasper
        String reportPath = getServletContext().getRealPath("/WEB-INF/reports/factura.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportPath);

        // Crear el datasource con la lista de productos
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(productos);

        // Llenar el reporte con los datos
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new java.util.HashMap<>(), dataSource);

        // Exportar el reporte a PDF
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        byte[] pdfData = outputStream.toByteArray();

        // Guardar la factura en la base de datos
        Connection conn = conectarUsuarios.conectar();
        String sql = "INSERT INTO factura (idComprador, total, archivoFactura) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, Integer.parseInt(request.getParameter("compradorId")));

            double total = productos.stream().mapToDouble(Producto::getPrecioTotal).sum();
            stmt.setDouble(2, total);
            stmt.setBytes(3, pdfData);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int idFactura = rs.getInt(1);
                System.out.println("Factura guardada con ID: " + idFactura);
            }
        }

        // Enviar el PDF al navegador
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=factura.pdf");
        response.getOutputStream().write(pdfData);
    }

    private void verificarStock(int idComponente) throws SQLException {
        // Aquí agregamos la lógica para verificar el stock del componente
        Connection conn = conectarUsuarios.conectar();
        String sql = "SELECT stock FROM componente WHERE idComponente = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idComponente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int stock = rs.getInt("stock");
                    if (stock <= 0) {
                        System.out.println("Componente agotado: " + idComponente);
                        // Realizar alguna acción, por ejemplo, mostrar una advertencia o no generar la factura
                    } else if (stock <= 5) {
                        System.out.println("Componente a punto de agotarse: " + idComponente);
                        // Aquí podrías agregar alguna lógica para advertir al usuario sobre el stock bajo
                    }
                }
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("Rol/Ventas/facturaGenerada.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Genera una factura en PDF para un comprador específico.";
    }
}
