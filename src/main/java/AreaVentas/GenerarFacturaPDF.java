/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AreaVentas;

import Admin.ConectarUsuarios;
import java.io.File;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author alejandro
 */
public class GenerarFacturaPDF {
    
    public void generarFactura(int idVenta) {
        ConectarUsuarios conexionBD = new ConectarUsuarios();
        Connection conn = null;

        try {
            conn = conexionBD.conectar();
            if (conn == null) {
                System.out.println("Error al conectar con la base de datos.");
                return;
            }

        
            String proyectoPath = System.getProperty("user.dir");  
            String reportPath = proyectoPath + "/ruta/a/tu/archivo/factura.jrxml";  
            
            JasperDesign jd = JRXmlLoader.load(reportPath);
            JasperReport jasperReport = JasperCompileManager.compileReport(jd);

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("idVenta", idVenta); 

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conn);

            JasperViewer.viewReport(jasperPrint, false);

            String facturasPath = proyectoPath + "/Facturas";  

            File facturasDir = new File(facturasPath);
            if (!facturasDir.exists()) {
                facturasDir.mkdir(); 
            }

            String outputPath = facturasPath + "/factura_" + idVenta + ".pdf";  
            JasperExportManager.exportReportToPdfFile(jasperPrint, outputPath);

            System.out.println("Factura generada exitosamente: " + outputPath);

            guardarFacturaEnBaseDeDatos(idVenta, "factura_" + idVenta + ".pdf", outputPath, conn);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conexionBD.cerrarConexion(conn, null, null);
        }
    }

    private void guardarFacturaEnBaseDeDatos(int idVenta, String nombreFactura, String rutaFactura, Connection conn) {
        PreparedStatement pst = null;
        
        try {
            String sql = "INSERT INTO factura (idVendido, nombreFactura, rutaFactura) VALUES (?, ?, ?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, idVenta);           
            pst.setString(2, nombreFactura);  
            pst.setString(3, rutaFactura);    

            pst.executeUpdate();
            System.out.println("Factura guardada correctamente en la base de datos.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
