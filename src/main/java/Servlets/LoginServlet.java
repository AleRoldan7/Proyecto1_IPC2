/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import ConexionDBA.Conexion;
import ConexionDBA.UsuarioDB;
import EntidadModelo.EntidadAdmin;
import OpcionesENUM.RolAdmin;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

/**
 *
 * @author alejandro
 */
public class LoginServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/inicio/index.jsp");

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("user_name");
        String password = request.getParameter("password");
        String rol = request.getParameter("rol_usuario");

        String passwordEncriptado = Base64.getEncoder().encodeToString(password.getBytes());

        System.out.println("Datos recibidos del formulario:");
        System.out.println("Usuario: " + usuario);
        System.out.println("Password: " + password);
        System.out.println("Rol: " + rol);
        System.out.println("Password en Base64: " + passwordEncriptado);

        UsuarioDB usuarioDB = new UsuarioDB();

        try {
            boolean valido = usuarioDB.verificarUsuario(usuario, passwordEncriptado, rol);

            if (valido) {
                HttpSession session = request.getSession();
                session.setAttribute("user_name", usuario);
                session.setAttribute("rol_usuario", rol);

                switch (rol) {
                    case "Admin_General":
                        response.sendRedirect(request.getContextPath() + "/vista/vista-admin/admin-general-vista.jsp");
                        break;
                    case "Admin_Institucion":
                        response.sendRedirect(request.getContextPath() + "/vista/vista-institucion-admin/institucion-admin-vista.jsp");
                        break;
                    case "Participante":
                        response.sendRedirect(request.getContextPath() + "/vista/participante/participante-vista.jsp");
                        break;
                    case "Comite_Cientifico":
                        response.sendRedirect(request.getContextPath() + "/vista/comite/comite-vista.jsp");

                        break;
                    default:
                        request.setAttribute("error", "Rol no existe");
                        request.getRequestDispatcher("/inicio/index.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "Usuario, contraseña o rol incorrectos");
                request.getRequestDispatcher("/inicio/index.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al procesar el login: " + e.getMessage());
            request.getRequestDispatcher("/inicio/index.jsp").forward(request, response);
        }
    }

}

/*
private void iniciarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        String rol = request.getParameter("rol");

        RolAdmin roladmin = RolAdmin.valueOf(rol);

        EntidadAdmin entidadAdmin = new EntidadAdmin();
        entidadAdmin.setUsuario(usuario);
        entidadAdmin.setPassword(password);
        entidadAdmin.setRolAdmin(roladmin);

        try {
            if (controladorAdmin.verificarAdmin(entidadAdmin)) {
                
                response.sendRedirect(request.getContextPath() + "/VistaGeneral/vista.jsp");

            } else {
                HttpSession sesion = request.getSession();
                sesion.setAttribute("usuario", usuario);
                response.sendRedirect(request.getContextPath() + "/inicio/index.jsp");
                

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
 */
