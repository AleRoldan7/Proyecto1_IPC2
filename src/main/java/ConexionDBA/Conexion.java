/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexionDBA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author alejandro
 */
public class Conexion {

     private static final String IP = "localhost";
    private static final int PUERTO = 3306;
    private static final String SCHEMA = "CodeBugs";
    private static final String USER = "root";
    private static final String PASSWORD = "010418";
    private static final String URL = "jdbc:mysql://" + IP + ":" + PUERTO + "/" + SCHEMA;

    private static Conexion instance;
    private Connection connection;

    private Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅Conexión establecida con la base: " + SCHEMA);
            
        } catch (ClassNotFoundException e) {
            System.out.println("❌No se encontró el driver de MySQL");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌Error al conectarse a la base");
            e.printStackTrace();
        }
    }

    public Connection getConnect() {
        return connection;
    }
    
    public static Conexion getInstance(){
         if (instance == null) {
            instance = new Conexion();
        }
        return instance;
    }
}
