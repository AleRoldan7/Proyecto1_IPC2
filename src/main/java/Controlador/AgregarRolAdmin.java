/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import ConexionDBA.AdminDB;
import EntidadModelo.EntidadAdmin;
import Excepciones.DatosInvalidos;
import Excepciones.EntityExists;
import OpcionesENUM.RolAdmin;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author alejandro
 */
public class AgregarRolAdmin {

    public EntidadAdmin crearAdmin(HttpServletRequest request) throws EntityExists, DatosInvalidos {
        String userName = request.getParameter("usuario");
        String password = request.getParameter("password");
        String rol = request.getParameter("rol");
     

        if (userName == null || userName.isBlank()
                || password == null || password.isBlank()
                || rol == null || rol.isBlank()) {
            throw new DatosInvalidos("Todos los campos son obligatorios");
        }

        EntidadAdmin admin = new EntidadAdmin();
        admin.setUsuario(userName);
        admin.setPassword(password);
        admin.setRolAdmin(RolAdmin.valueOf(rol));
       

        AdminDB adminDB = new AdminDB();
        adminDB.agregarAdmin(admin);

       

        return admin;
    }
}

