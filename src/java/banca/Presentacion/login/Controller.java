/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca.Presentacion.login;

import banca.Logic.Login_usuario;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jsanchez
 */
@WebServlet(name = "LoginController", urlPatterns = {"/Presentacion/login/login","/Presentacion/login/logout","/Presentacion/login/registro"})
public class Controller extends HttpServlet {

  protected void processRequest(HttpServletRequest request, 
                                HttpServletResponse response)
         throws ServletException, IOException {
      
        request.setAttribute("model", new Model()); 
        
        String viewUrl="";
        switch(request.getServletPath()){
            case "/Presentacion/login/login":
                viewUrl=this.login(request);
                break;
            case "/Presentacion/login/registro":
                viewUrl=this.registro(request);
                break;                
            case "/Presentacion/login/logout":
                viewUrl=this.logout(request);
                break;
        }
        request.getRequestDispatcher(viewUrl).forward( request, response); 
  }

    private String login(HttpServletRequest request) { 
        Model model = (Model) request.getAttribute("model");
       model.setCurrent(new Login_usuario(request.getParameter("txtID"),request.getParameter("txtContraseña"),null,1));
        return this.loginAction(request);         
    }

    private String registro(HttpServletRequest request) { 
       
        return this.Registroadd(request);         
    }
         public String Registroadd(HttpServletRequest request) {
        banca.Logic.Model  domainModel = banca.Logic.Model.instance();
        HttpSession session = request.getSession(true);
        try {
          Map<String,String> errores = new HashMap<>();
           if(domainModel.Iscliente(request.getParameter("txtCedula")) || domainModel.Iscajero(request.getParameter("txtCedula")))
           {errores.put("txtCedula","Usuario ya registrado");
           request.setAttribute("errores", errores);
           return "/Presentacion/Registro/Registro.jsp";
           }
           else
           {
            domainModel.AddLoginCliente(request.getParameter("txtCedula"), request.getParameter("txtContrasena"));
           }
        
            String viewUrl="";
            switch(1){
                case 1:
                    viewUrl="/Presentacion/Inicio.jsp";
                    break;
                case 2:
                     viewUrl="";
                    break;             
            }
            return viewUrl;
        } catch (Exception ex) {
            Map<String,String> errores = new HashMap<>();
            request.setAttribute("errores", errores);
             
           
                    errores.put("txtCedula","Cedula inexistente");
           
            return "/Presentacion/Registro/Registro.jsp"; 
        }        
    }   
    public String loginAction(HttpServletRequest request) {
        Model model= (Model) request.getAttribute("model");
        banca.Logic.Model  domainModel = banca.Logic.Model.instance();
        HttpSession session = request.getSession(true);
        try {
  
            Login_usuario real = null;
           if(request.getParameter("txtID").length()>3 && !request.getParameter("txtID").substring(0, 3).equals("CAJ") && domainModel.Iscliente(request.getParameter("txtID")))
           real = domainModel.ClienteFind(request.getParameter("txtID"),request.getParameter("txtContrasena"));
            else{ if(request.getParameter("txtID").length()>3 && request.getParameter("txtID").substring(0, 3).equals("CAJ") && domainModel.Iscajero(request.getParameter("txtID").replaceFirst("CAJ", "")))
                  real = domainModel.CajeroFind(request.getParameter("txtID").replaceFirst("CAJ", ""),request.getParameter("txtContrasena"));
                  else
                  { Map<String,String> errores = new HashMap<>();
                    request.setAttribute("errores", errores);
                    errores.put("txtID","Usuario o clave incorrectos");
                    errores.put("txtContrasena","Usuario o clave incorrectos");
                  }
           }
             session.setAttribute("usuario", real);
            String viewUrl="";
            switch(1){
                case 1:
                    viewUrl="/Presentacion/Inicio.jsp";
                    break;
                case 2:
                     viewUrl="";
                    break;             
            }
            return viewUrl;
        } catch (Exception ex) {
            Map<String,String> errores = new HashMap<>();
            request.setAttribute("errores", errores);
            errores.put("txtID","Usuario o clave incorrectos");
            errores.put("txtContrasena","Usuario o clave incorrectos");
            return "/Presentacion/Inicio.jsp"; 
        }        
    }   

    public String logout(HttpServletRequest request){
        return this.logoutAction(request);
    }
    
    public String logoutAction(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        session.removeAttribute("usuario");
        session.invalidate();
        return "/Presentacion/Inicio.jsp";   
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
}
