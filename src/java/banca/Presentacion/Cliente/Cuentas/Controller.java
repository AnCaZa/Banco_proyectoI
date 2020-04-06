/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca.Presentacion.Cliente.Cuentas;


import banca.Logic.Cliente;
import banca.Logic.Login_usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "ClienteCuentasController", urlPatterns = {"/Presentacion/Cliente/Cuentas/show", "/Presentacion/Cliente/Cuentas/Showcuenta","/Presentacion/Cliente/Cuentas/showCuentas","/Presentacion/Cliente/Cuentas/Transaccion"})
public class Controller extends HttpServlet {
    
  protected void processRequest(HttpServletRequest request, 
                                HttpServletResponse response)
         throws ServletException, IOException {

        request.setAttribute("model", new Model());
        
     HttpSession session = request.getSession(true);
       Login_usuario usuario=  (Login_usuario) session.getAttribute("usuario");
       String viewUrl="/Presentacion/Error.jsp";
       if(usuario!=null && usuario.getTipo()==1) 
       {    
        switch (request.getServletPath()) {
          case "/Presentacion/Cliente/Cuentas/show":
              viewUrl = this.show(request);
              break;
          case "/Presentacion/Cliente/Cuentas/showCuentas":
              viewUrl = this.show_cuentas_cuentasamigas(request);
              break;
          case "/Presentacion/Cliente/Cuentas/Showcuenta":
              viewUrl = this.show_cuenta(request);
              break;
           case "/Presentacion/Cliente/Cuentas/Transaccion":
              viewUrl = this.show_cuentas(request);
              break;
        
        }}          
        request.getRequestDispatcher(viewUrl).forward( request, response); 
  }

    public String show(HttpServletRequest request) {
        return this.showAction(request);
    }
     
    public String show_cuentas_cuentasamigas(HttpServletRequest request) {
       return this.Cuentas_cuentaamiga(request);
    }
    public String show_cuentas(HttpServletRequest request) {
        return this.Cuentas_transaccion(request);
    }
    
    
    
    public String showAction(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        banca.Logic.Model domainModel = banca.Logic.Model.instance();
        HttpSession session = request.getSession(true);
 
      Login_usuario usuario = (Login_usuario) session.getAttribute("usuario");
     
      
        try {        
            model.setCuentas(domainModel.cuentasFind(usuario));
            return "/Presentacion/Cliente/Menu_usuario/Cuentas.jsp";
        } catch (Exception ex) {
            return "";
        }
    }
    public String show_cuenta(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        banca.Logic.Model domainModel = banca.Logic.Model.instance();
        HttpSession session = request.getSession(true);
 
      Login_usuario usuario = (Login_usuario) session.getAttribute("usuario");
     
      
        try {        
            model.setSeleccionado(domainModel.CuentaGet(request.getParameter("numCuenta")));
            return "/Presentacion/Cliente/Menu_usuario/Cuenta.jsp";
        } catch (Exception ex) {
            return "";
        }
    }
    public String Cuentas_cuentaamiga(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        banca.Logic.Model domainModel = banca.Logic.Model.instance();
        HttpSession session = request.getSession(true);
 
      Login_usuario usuario = (Login_usuario) session.getAttribute("usuario");
     
      
        try {        
            model.setCuentas(domainModel.cuentasFind(usuario));
            return "/Presentacion/Cliente/Cuenta_amiga/Menu_cuenta_amiga.jsp";
        } catch (Exception ex) {
            return "";
        }
    }
   
      public String Cuentas_transaccion(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        banca.Logic.Model domainModel = banca.Logic.Model.instance();
        HttpSession session = request.getSession(true);
 
      Login_usuario usuario = (Login_usuario) session.getAttribute("usuario");
     
      
        try {        
            model.setCuentas(domainModel.cuentasFind(usuario));
            return "/Presentacion/Cliente/Transaccion/Menu_transaccion.jsp";
        } catch (Exception ex) {
            return "";
        }
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