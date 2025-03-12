/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

/**
 *
 * @author alejandro
 */
public class CargarArchivo {

    private ConectarUsuarios conexion = new ConectarUsuarios();

    public String procesarArchivo(String rutaArchivo) {
        String resultado = "";
        try {

            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
            String linea;

            while ((linea = br.readLine()) != null) {

                linea = linea.trim();

                if (linea.startsWith("USUARIO")) {
                    if (!procesarUsuario(linea)) {
                        resultado += "Error al procesar usuario: " + linea + "\n";
                    } else {
                        resultado += "Usuario procesado exitosamente: " + linea + "\n";
                    }
                } else if (linea.startsWith("PIEZA")) {
                    if (!procesarPieza(linea)) {
                        resultado += "Error al procesar pieza: " + linea + "\n";
                    } else {
                        resultado += "Pieza procesada exitosamente: " + linea + "\n";
                    }
                } else if (linea.startsWith("COMPUTADORA")) {
                    if (!procesarComputadora(linea)) {
                        resultado += "Error al procesar computadora: " + linea + "\n";
                    } else {
                        resultado += "Computadora procesada exitosamente: " + linea + "\n";
                    }
                } else if (linea.startsWith("ENSAMBLE_PIEZAS")) {
                    if (!procesarEnsamble(linea)) {
                        resultado += "Error al procesar ensamblaje: " + linea + "\n";
                    } else {
                        resultado += "Ensamble procesado exitosamente: " + linea + "\n";
                    }
                } else if (linea.startsWith("ENSAMBLAR_COMPUTADORA")) {
                    if (!procesarEnsamblajeComputadora(linea)) {
                        resultado += "Error al procesar ensamblaje de computadora: " + linea + "\n";
                    } else {
                        resultado += "Computadora ensamblada exitosamente: " + linea + "\n";
                    }
                } else {
                    resultado += "Instrucción no reconocida: " + linea + "\n";
                }
            }

            br.close();
        } catch (IOException e) {
            resultado = "Error al leer el archivo: " + e.getMessage();
        }
        return resultado;
    }

    private boolean procesarUsuario(String linea) {
       
        linea = linea.substring(7, linea.length() - 1).trim(); 

        
        String[] partes = linea.split(",");
        if (partes.length == 3) {
            
            String nombreUsuario = limpiarCampo(partes[0].trim());
            String password = limpiarCampo(partes[1].trim());
            int tipo = Integer.parseInt(partes[2].trim());

            
            if (existeUsuario(nombreUsuario)) {
                return false; 
            }

            
            return guardarUsuario(nombreUsuario, password, tipo);
        } else {
            return false;
        }
    }

    private String limpiarCampo(String campo) {
        campo = campo.replace("\"", "").replace("(", "").replace(")", "").trim();
        return campo;
    }

    private boolean procesarPieza(String linea) {
        linea = linea.substring(6, linea.length() - 1).trim(); 

        String[] partes = linea.split(",");
        if (partes.length == 2) {
            String nombrePieza = partes[0].trim().replace("\"", "");  
            double costo = Double.parseDouble(partes[1].trim());

            guardarPieza(nombrePieza, costo);
            return true;
        } else {
            return false;
        }
    }

    private boolean procesarComputadora(String linea) {
        linea = linea.substring(13, linea.length() - 1).trim(); 

        String[] partes = linea.split(",");
        if (partes.length == 2) {
            String nombreComputadora = partes[0].trim().replace("\"", "");  
            double precio = Double.parseDouble(partes[1].trim());

            guardarComputadora(nombreComputadora, precio);
            return true;
        } else {
            return false;
        }
    }

    private boolean procesarEnsamble(String linea) {
        linea = linea.substring(14, linea.length() - 1).trim(); 

        String[] partes = linea.split(",");
        if (partes.length == 3) {
            String nombreComputadora = partes[0].trim().replace("\"", "");
            String nombrePieza = partes[1].trim().replace("\"", "");
            int cantidad = Integer.parseInt(partes[2].trim());

            ensamblarPiezaComputadora(nombreComputadora, nombrePieza, cantidad);
            return true;
        } else {
            return false;
        }
    }

    private boolean procesarEnsamblajeComputadora(String linea) {
        linea = linea.substring(22, linea.length() - 1).trim(); 

        String[] partes = linea.split(",");
        if (partes.length == 3) {
            String nombreComputadora = partes[0].trim().replace("\"", "");
            String nombreUsuario = partes[1].trim().replace("\"", "");
            String fecha = partes[2].trim().replace("\"", "");

            ensamblarComputadora(nombreComputadora, nombreUsuario, fecha);
            return true;
        } else {
            return false;
        }
    }

    private boolean guardarUsuario(String nombreUsuario, String password, int tipo) {
        try (Connection conn = conexion.conectar()) {
            if (conn == null) {
                System.out.println("Error al conectar a la base de datos.");
                return false;
            }

            String rol = "";
            switch (tipo) {
                case 1:
                    rol = "Ensamblaje";
                    break;
                case 2:
                    rol = "Ventas";
                    break;
                case 3:
                    rol = "Administrador";
                    break;
                default:
                    return false;
            }

            String sql = "INSERT INTO usuario (userName, pass, rolTrabajador) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nombreUsuario);
                stmt.setString(2, password);
                stmt.setString(3, rol);
                int filasAfectadas = stmt.executeUpdate();
                return filasAfectadas > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al guardar el usuario: " + e.getMessage());
            return false;
        }
    }

    private void guardarPieza(String nombrePieza, double costo) {
        System.out.println("Pieza guardada: " + nombrePieza + ", Costo: " + costo);
    }

    private void guardarComputadora(String nombreComputadora, double precio) {
        System.out.println("Computadora guardada: " + nombreComputadora + ", Precio: " + precio);
    }

    private void ensamblarPiezaComputadora(String nombreComputadora, String nombrePieza, int cantidad) {
        System.out.println("Ensamblando " + cantidad + " piezas de " + nombrePieza + " en la computadora " + nombreComputadora);
    }

    private void ensamblarComputadora(String nombreComputadora, String nombreUsuario, String fecha) {
        System.out.println("Computadora ensamblada: " + nombreComputadora + " por " + nombreUsuario + " el " + fecha);
    }

    private boolean existeUsuario(String nombreUsuario) {
        return false; 
    }
}
