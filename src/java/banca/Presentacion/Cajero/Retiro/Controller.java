/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca.Presentacion.Cajero.Retiro;



import banca.Presentacion.Cajero.Deposito.*;
import banca.Logic.Cuenta;
import banca.Logic.Deposito;
import banca.Logic.Login_usuario;
import banca.Logic.Persona;
import banca.Logic.Retiro;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "CajeroRetiroController", urlPatterns = {"/Presentacion/Cajero/Retiro/showcuentas","/Presentacion/Cajero/Retiro/showcuenta","/Presentacion/Cajero/Retiro/add","/Presentacion/Cajero/Retiro/Add"})
public class Controller extends HttpServlet {
    
  protected void processRequest(HttpServletRequest request, 
                                HttpServletResponse response)
         throws ServletException, IOException {

        request.setAttribute("model", new Model());
        
        HttpSession session = request.getSession(true);
       Login_usuario usuario=  (Login_usuario) session.getAttribute("usuario");
       String viewUrl="/Presentacion/Error.jsp";
       if(usuario!=null && usuario.getTipo()==2) 
       {   
        switch (request.getServletPath()) {
            case "/Presentacion/Cajero/Retiro/showcuenta":
              viewUrl = this.showCuenta(request);
              break;
          case "/Presentacion/Cajero/Retiro/showcuentas":
              viewUrl = this.showCuentas(request);
              break;
           case "/Presentacion/Cajero/Retiro/add":
              viewUrl = this.add(request);
              break;
           case "/Presentacion/Cajero/Retiro/Add":
              viewUrl = this.Add(request);
              break;
         
        
        }}          
        request.getRequestDispatcher(viewUrl).forward( request, response); 
  }

    public String showCuentas(HttpServletRequest request) {
        return this.ShowCuentas(request);
    }
    public String showCuenta(HttpServletRequest request) {
        return this.ShowCuenta(request);
    }
     public String add(HttpServletRequest request) {
        return this.RetiroAdd(request,"/Presentacion/Cajero/Retiro/Menu_retirocedu.jsp");
    }
      public String Add(HttpServletRequest request) {
        return this.RetiroAdd(request,"/Presentacion/Cajero/Retiro/Menu_retiroCuenta.jsp");
    }
  public String ShowCuenta(HttpServletRequest request) {
        
        banca.Logic.Model domainModel = banca.Logic.Model.instance();
        HttpSession session = request.getSession(true);
  Model model = (Model) request.getAttribute("model");
    
      
        try {        

           model.setCurrent(domainModel.CuentaGet(request.getParameter("txtNumero")));
          if(model.getCurrent()==null)
          {
              Map<String,String> errores = new HashMap<>();
              request.setAttribute("errores", errores);
              errores.put("txtNumero","Cuenta inexistente");
              return "/Presentacion/Cajero/Retiro/Menu_retiroCuenta.jsp";
          }
           return "/Presentacion/Cajero/Retiro/Menu_retiroCuenta.jsp";
        } catch (Exception ex) {
            return "";
        }
    }
   
       public String ShowCuentas(HttpServletRequest request) {
        
        banca.Logic.Model domainModel = banca.Logic.Model.instance();
        HttpSession session = request.getSession(true);
 
    
      
        try {        

            Login_usuario usuario = new Login_usuario();
            Map<String,String> errores = new HashMap<>();
           request.setAttribute("errores", errores);
           if(!domainModel.Ispersona(request.getParameter("txtCedula")))
           {
                 errores.put("txtCedula","Cedula inexistente");
           }
           else{
             Model model = (Model) request.getAttribute("model");
            usuario.setCedula(request.getParameter("txtCedula"));
            model.setCuentas(domainModel.cuentasFind(usuario));}
     
           return "/Presentacion/Cajero/Retiro/Menu_retirocedu.jsp";
        } catch (Exception ex) {
            return "";
        }
    }
    public boolean Verificacion_monto(HttpServletRequest request) throws Exception
    {
         banca.Logic.Model domainModel = banca.Logic.Model.instance();
     if (domainModel.CuentaGet(request.getParameter("Buttoncuenta")).getSaldo()-Double.parseDouble(request.getParameter("txtMonto"))>=0.0)
     {
         domainModel.CuentaUpdate(domainModel.CuentaGet(request.getParameter("Buttoncuenta")).getSaldo()-Double.parseDouble(request.getParameter("txtMonto")),request.getParameter("Buttoncuenta")  );
         return true;
     }
    return false;
    }
    public String RetiroAdd(HttpServletRequest request, String url) {
        
        banca.Logic.Model domainModel = banca.Logic.Model.instance();
        HttpSession session = request.getSession(true);
 
    
      
        try { 
            if(this.Verificacion_monto(request))
            { 
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        domainModel.RetiroAdd(new Retiro(0,formatter.format(new Date()),domainModel.CuentaGet(request.getParameter("Buttoncuenta")),Double.parseDouble(request.getParameter("txtMonto"))));
        request.setAttribute("model", new banca.Presentacion.login.Model()) ;
            }
            else
            {
            Model model = (Model) request.getAttribute("model");
            Map<String,String> errores = new HashMap<>();
            request.setAttribute("errores", errores);
             errores.put("txtMonto","Insuficientes fondos");
             model.setCurrent(domainModel.CuentaGet(request.getParameter("Buttoncuenta")));
             Login_usuario persona=new Login_usuario();
             persona.setCedula(domainModel.CuentaGet(request.getParameter("Buttoncuenta")).getPersona().getCedula());
             model.setCuentas(domainModel.cuentasFind(persona) );
             return url;
            }
           return "/Presentacion/Inicio.jsp";
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