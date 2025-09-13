/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EntidadModelo;

import OpcionesENUM.RolAdmin;

/**
 *
 * @author alejandro
 */
public class EntidadAdmin {
    
    private String usuario;
    private String password;
    private RolAdmin rolAdmin;
    private int id_usuario;
    private int id_institucion;
    
    public EntidadAdmin() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RolAdmin getRolAdmin() {
        return rolAdmin;
    }

    public void setRolAdmin(RolAdmin rolAdmin) {
        this.rolAdmin = rolAdmin;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_institucion() {
        return id_institucion;
    }

    public void setId_institucion(int id_institucion) {
        this.id_institucion = id_institucion;
    }
    
    
    
}
