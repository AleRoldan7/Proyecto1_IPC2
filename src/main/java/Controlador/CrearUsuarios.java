/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import ConexionDBA.UsuarioDB;
import EntidadModelo.EntidadUsuario;
import Excepciones.DatosInvalidos;
import Excepciones.EntityExists;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author alejandro
 */
public class CrearUsuarios {

    public EntidadUsuario crearUsuario(HttpServletRequest request) throws EntityExists, DatosInvalidos {
        UsuarioDB usuarioDB = new UsuarioDB();

        EntidadUsuario entidadUsuario = extraer(request);

        if (usuarioDB.existeUsuario(entidadUsuario.getIdentificador())) {
            throw new EntityExists(
                    String.format("El usuario con identificado %s ya existe", entidadUsuario.getIdentificador())
            );
        }

        usuarioDB.agregarUsuario(entidadUsuario);

        return entidadUsuario;
    }

    private EntidadUsuario extraer(HttpServletRequest request) throws DatosInvalidos {
        try {
            String institucion = (String) request.getAttribute("institucionUsuario");
            EntidadUsuario entidadUsuario = new EntidadUsuario(
                    request.getParameter("nombre_usuario"),
                    request.getParameter("correo_usuario"),
                    request.getParameter("identificacion"),
                    institucion != null ? institucion : request.getParameter("institucion"),
                    request.getParameter("no_celular")
            );

            String fotoUrl = request.getParameter("fotoUrl");
            entidadUsuario.setFoto(fotoUrl != null && !fotoUrl.isEmpty() ? fotoUrl.getBytes() : null);

            entidadUsuario.setEstado(true);
            entidadUsuario.setSaldo(0.0);

            if (!entidadUsuario.esValido()) {
                throw new DatosInvalidos("Nombre e identificador son obligatorios");
            }

            return entidadUsuario;
        } catch (Exception e) {
            throw new DatosInvalidos("Error al procesar los datos: " + e.getMessage());
        }
    }
}
