/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import ConexionDBA.UsuarioDB;
import EntidadModelo.EntidadUsuario;
import Excepciones.ConversionNotFound;
import Excepciones.DatosInvalidos;
import Excepciones.EntityExists;
import OpcionesENUM.RolAdmin;
import com.mycompany.proyecto1_ipc2.resources.imagenes.ConvertirFoto;
import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Base64;

/**
 *
 * @author alejandro
 */
public class CrearUsuarios {

    public EntidadUsuario crearUsuario(HttpServletRequest request) throws EntityExists, DatosInvalidos, ConversionNotFound, FileNotFoundException, SQLException {
        UsuarioDB usuarioDB = new UsuarioDB();

        EntidadUsuario entidadUsuario = extraer(request);

        if (usuarioDB.existeUsuario(entidadUsuario.getIdentificacion())) {
            throw new EntityExists(
                    String.format("El usuario con identificacion %s ya existe", entidadUsuario.getIdentificacion())
            );
        }

        usuarioDB.agregarUsuario(entidadUsuario);

        return entidadUsuario;
    }

    private EntidadUsuario extraer(HttpServletRequest request) throws DatosInvalidos, ConversionNotFound, FileNotFoundException {

        RolAdmin rol = RolAdmin.valueOf(request.getParameter("rol_usuario"));

        byte[] foto = null;
        String fotoDireccion =  request.getParameter("foto");
        
        if (fotoDireccion  != null && !fotoDireccion .isEmpty()) {
            File fotoFile = new File(fotoDireccion );
            ConvertirFoto convertir = new ConvertirFoto();
            foto = convertir.convertirFoto(fotoFile);
        }
        
        String password =  request.getParameter("password");
        String passEncriptado = Base64.getEncoder().encodeToString(password.getBytes());
        
        try {
            EntidadUsuario entidadUsuario = new EntidadUsuario(
                    request.getParameter("nombre_completo"),
                    request.getParameter("correo"),
                    request.getParameter("identificacion"),
                    request.getParameter("no_celular"),
                    foto,
                    true,
                    0.0,
                    request.getParameter("user_name"),
                    passEncriptado,
                    rol
            );

            if (!entidadUsuario.esValido()) {
                throw new DatosInvalidos("Error en los datos enviados");
            }

            return entidadUsuario;
        } catch (Exception e) {
            throw new DatosInvalidos("Error en los datos enviados" + e.getMessage());
        }
    }
}
