/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validaciones;

import ConexionDBA.UsuarioDB;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author alejandro
 */
public class ValidacionUsuario {

    private UsuarioDB usuarioDB = new UsuarioDB();

    public ValidacionUsuario() {
        this.usuarioDB = usuarioDB;
    }

    public String validarFormularioUsuario(HttpServletRequest request) {

        String nombre = request.getParameter("nombre_completo");
        String correo = request.getParameter("correo");
        String identi = request.getParameter("identificacion");
        String celular = request.getParameter("no_celular");
        String user = request.getParameter("user_name");
        String password = request.getParameter("password");
        String rol = request.getParameter("rol_usuario");

        if (nombre == null || nombre.trim().isEmpty()) {
            return "EL nombre es obligatorio";
        }
        
        if (correo == null || correo.trim().isEmpty()) {
            return "Correo obligatorio";
        }
        String regexCorreo = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
        if (!correo.matches(regexCorreo)) {
            return "Correo no válido";
        }

        if (identi == null || identi.trim().isEmpty()) {
            return "Identificación obligatoria";
        }

        if (celular == null || celular.trim().isEmpty()) {
            return "Número de celular obligatorio";
        }
        if (!celular.matches("\\d{8,15}")) { 
            return "Número de celular no válido";
        }

        if (user == null || user.trim().isEmpty()) {
            return "Nombre de usuario obligatorio";
        }

        if (password == null || password.trim().isEmpty()) {
            return "Contraseña obligatoria";
        }
        if (password.length() < 6) {
            return "La contraseña debe tener al menos 6 caracteres";
        }

        if (rol == null || rol.trim().isEmpty()) {
            return "Rol de usuario obligatorio";
        }
        try {
            OpcionesENUM.RolAdmin.valueOf(rol); 
        } catch (IllegalArgumentException e) {
            return "Rol de usuario inválido";
        }

        return null;

    }

}
