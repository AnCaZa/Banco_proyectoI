/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca.Presentacion.Cliente.Cuenta_amiga;


import banca.Logic.Cuenta;
import banca.Logic.CuentaAmiga;

import banca.Logic.Login_usuario;
import banca.Logic.Persona;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
@WebServlet(name = "ClienteCuenta_amigaController", urlPatterns = {"/Presentacion/Cliente/Cuenta_amiga/add"})
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
        switch(request.getServletPath()){
            case "/Presentacion/Cliente/Cuenta_amiga/add":
                viewUrl=this.add(request);
                break;
        
           
        }}
        request.getRequestDispatcher(viewUrl).forward( request, response); 
  }

    private String add(HttpServletRequest request) { 
        return this.Cuentaamigaadd(request);         
    }

    private String Cuentaamigaadd(HttpServletRequest request) { 
       
        return this.Registroadd(request);         
    }
         public String Registroadd(HttpServletRequest request) {
        banca.Logic.Model  domainModel = banca.Logic.Model.instance();
        HttpSession session = request.getSession(true);
        Map<String,String> errores = new HashMap<>();
             request.setAttribute("errores", errores);
           
        try {
    
         Cuenta cuentavinculada=new Cuenta();
        cuentavinculada=domainModel.CuentaGet(request.getParameter("txtCuentaamiga"));
        if(cuentavinculada==null)
             errores.put("txtCuentaamiga","Cuenta inexistente");
        if(cuentavinculada!=null && cuentavinculada.getPersona().getCedula().equals(request.getParameter("txtCedula")) && cuentavinculada.getPersona().getNombre().equals(request.getParameter("txtNombre")))
        {  
         Login_usuario persona = (Login_usuario) session.getAttribute("usuario");
         domainModel.AddCuentaAmiga(new CuentaAmiga(0,cuentavinculada, persona.getPersona()));
        request.setAttribute("model", new banca.Presentacion.login.Model());
        }
        else
        {
        errores.put("txtCuentaamiga","Cuenta inexistente");
        }
        
        if(!errores.isEmpty())
            return "/Presentacion/Cliente/Cuenta_amiga/Menu_cuenta_amiga.jsp";
        
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
