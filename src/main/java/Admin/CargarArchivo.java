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

    // Instancia de la clase ConectarUsuarios para obtener la conexión a la base de datos
    private ConectarUsuarios conexion = new ConectarUsuarios();

    // Método principal para cargar y procesar el archivo
    public String procesarArchivo(String rutaArchivo) {
        String resultado = "";
        try {
            // Abrir el archivo
            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
            String linea;

            // Leer línea por línea
            while ((linea = br.readLine()) != null) {
                // Limpiar la línea (eliminar espacios innecesarios)
                linea = linea.trim();

                // Verificar el tipo de instrucción en la línea
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

    // Método para procesar una línea que define un usuario
private boolean procesarUsuario(String linea) {
    // Eliminar la parte 'USUARIO(' y la parte ')'
    linea = linea.substring(7, linea.length() - 1).trim(); // Quitamos "USUARIO(" y ")"

    // Separar por comas, cada parte corresponde a un dato
    String[] partes = linea.split(",");
    if (partes.length == 3) {
        // Limpiar y quitar comillas de cada campo (usuario y password)
        String nombreUsuario = limpiarCampo(partes[0].trim());
        String password = limpiarCampo(partes[1].trim());
        int tipo = Integer.parseInt(partes[2].trim());

        // Validar que el nombre de usuario no exista
        if (existeUsuario(nombreUsuario)) {
            return false; // El usuario ya existe
        }

        // Guardar usuario en la base de datos
        return guardarUsuario(nombreUsuario, password, tipo);
    } else {
        return false;
    }
}

// Método auxiliar para limpiar comillas y otros caracteres indeseados
private String limpiarCampo(String campo) {
    // Eliminar comillas dobles y otros caracteres indeseados como paréntesis
    campo = campo.replace("\"", "").replace("(", "").replace(")", "").trim();
    return campo;
}


    // Método para procesar una línea que define una pieza
    private boolean procesarPieza(String linea) {
        // Eliminar la parte 'PIEZA(' y la parte ')'
        linea = linea.substring(6, linea.length() - 1).trim(); // Quitamos "PIEZA(" y ")"

        // Separar por comas, cada parte corresponde a un dato
        String[] partes = linea.split(",");
        if (partes.length == 2) {
            String nombrePieza = partes[0].trim().replace("\"", "");  // Quitar las comillas
            double costo = Double.parseDouble(partes[1].trim());

            // Guardar pieza en la base de datos
            guardarPieza(nombrePieza, costo);
            return true;
        } else {
            return false;
        }
    }

    // Método para procesar una línea que define una computadora
    private boolean procesarComputadora(String linea) {
        // Eliminar la parte 'COMPUTADORA(' y la parte ')'
        linea = linea.substring(13, linea.length() - 1).trim(); // Quitamos "COMPUTADORA(" y ")"

        // Separar por comas, cada parte corresponde a un dato
        String[] partes = linea.split(",");
        if (partes.length == 2) {
            String nombreComputadora = partes[0].trim().replace("\"", "");  // Quitar las comillas
            double precio = Double.parseDouble(partes[1].trim());

            // Guardar computadora en la base de datos
            guardarComputadora(nombreComputadora, precio);
            return true;
        } else {
            return false;
        }
    }

    // Método para procesar una línea que define el ensamblaje de piezas
    private boolean procesarEnsamble(String linea) {
        // Eliminar la parte 'ENSAMBLE_PIEZAS(' y la parte ')'
        linea = linea.substring(14, linea.length() - 1).trim(); // Quitamos "ENSAMBLE_PIEZAS(" y ")"

        // Separar por comas, cada parte corresponde a un dato
        String[] partes = linea.split(",");
        if (partes.length == 3) {
            String nombreComputadora = partes[0].trim().replace("\"", "");
            String nombrePieza = partes[1].trim().replace("\"", "");
            int cantidad = Integer.parseInt(partes[2].trim());

            // Asignar piezas a la computadora
            ensamblarPiezaComputadora(nombreComputadora, nombrePieza, cantidad);
            return true;
        } else {
            return false;
        }
    }

    // Método para ensamblar la computadora
    private boolean procesarEnsamblajeComputadora(String linea) {
        // Eliminar la parte 'ENSAMBLAR_COMPUTADORA(' y la parte ')'
        linea = linea.substring(22, linea.length() - 1).trim(); // Quitamos "ENSAMBLAR_COMPUTADORA(" y ")"

        // Separar por comas, cada parte corresponde a un dato
        String[] partes = linea.split(",");
        if (partes.length == 3) {
            String nombreComputadora = partes[0].trim().replace("\"", "");
            String nombreUsuario = partes[1].trim().replace("\"", "");
            String fecha = partes[2].trim().replace("\"", "");

            // Registrar el ensamblaje de la computadora
            ensamblarComputadora(nombreComputadora, nombreUsuario, fecha);
            return true;
        } else {
            return false;
        }
    }

    // Método para guardar usuario en la base de datos
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

    // Métodos para guardar piezas y computadoras
    private void guardarPieza(String nombrePieza, double costo) {
        // Guardar la pieza en la base de datos
        System.out.println("Pieza guardada: " + nombrePieza + ", Costo: " + costo);
    }

    private void guardarComputadora(String nombreComputadora, double precio) {
        // Guardar la computadora en la base de datos
        System.out.println("Computadora guardada: " + nombreComputadora + ", Precio: " + precio);
    }

    private void ensamblarPiezaComputadora(String nombreComputadora, String nombrePieza, int cantidad) {
        // Lógica para ensamblar piezas en la computadora
        System.out.println("Ensamblando " + cantidad + " piezas de " + nombrePieza + " en la computadora " + nombreComputadora);
    }

    private void ensamblarComputadora(String nombreComputadora, String nombreUsuario, String fecha) {
        // Lógica para ensamblar la computadora
        System.out.println("Computadora ensamblada: " + nombreComputadora + " por " + nombreUsuario + " el " + fecha);
    }

    private boolean existeUsuario(String nombreUsuario) {
        // Verificar si el usuario ya existe
        return false; // Simulación: el usuario no existe
    }
}
