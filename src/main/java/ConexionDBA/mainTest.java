/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexionDBA;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author alejandro
 */
public class mainTest {

    public static void main(String[] args) {
        try {
            // Obtenemos la conexión desde la clase Conexion
            Conexion conexion = Conexion.getInstance();
            Connection conn = conexion.getConnect();

            if (conn != null && !conn.isClosed()) {
                System.out.println("Conexión exitosa a la base de datos!");
            } else {
                System.out.println("La conexión es nula o está cerrada.");
            }

        } catch (SQLException e) {
            System.out.println("Error al probar la conexión:");
            e.printStackTrace();
        }
    }
}
