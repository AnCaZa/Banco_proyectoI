/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca.Presentacion.Cajero.Transferencia_caja;



import banca.Presentacion.Cajero.Retiro.*;
import banca.Presentacion.Cajero.Deposito.*;
import banca.Logic.Cuenta;
import banca.Logic.Deposito;
import banca.Logic.Login_usuario;
import banca.Logic.Persona;
import banca.Logic.Retiro;
import banca.Logic.Transferencia;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "CajeroTransferenciaController", urlPatterns = {"/Presentacion/Cajero/Transferencia/showcuentas","/Presentacion/Cajero/Transferencia/showcuenta","/Presentacion/Cajero/Transferencia/Verificacion","/Presentacion/Cajero/Transferencia/add","/Presentacion/Cajero/Transferencia/verificacion"})
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
          case "/Presentacion/Cajero/Transferencia/showcuentas":
              viewUrl = this.showCuentas(request);
              break;
           case "/Presentacion/Cajero/Transferencia/showcuenta":
              viewUrl = this.showCuenta(request);
              break;
           case "/Presentacion/Cajero/Transferencia/add":
              viewUrl = this.add(request);
              break;
              case "/Presentacion/Cajero/Transferencia/Verificacion":
              viewUrl = this.Verificacion(request);
              break;
            case "/Presentacion/Cajero/Transferencia/verificacion":
              viewUrl = this.verificacion(request);
              break;
         
        
        } }         
        request.getRequestDispatcher(viewUrl).forward( request, response); 
  }

    public String showCuentas(HttpServletRequest request) {
        return this.ShowCuentas(request);
    }
     public String showCuenta(HttpServletRequest request) {
        return this.ShowCuenta(request);
    }
     public String add(HttpServletRequest request) {
        return this.TransferenciaAdd(request);
    }
         public String verificacion(HttpServletRequest request) {
        return this.ShowVerificacion(request,"/Presentacion/Cajero/Transferencia_caja/Menu_transferenciaCuenta.jsp");
    }
    public String Verificacion(HttpServletRequest request) {
        return this.ShowVerificacion(request,"/Presentacion/Cajero/Transferencia_caja/Menu_transferenciacedu.jsp");
    }
 public String ShowVerificacion(HttpServletRequest request, String url) {
        Model model = (Model) request.getAttribute("model");
        banca.Logic.Model domainModel = banca.Logic.Model.instance();
        HttpSession session = request.getSession(true);
 
    
       Map<String,String> errores = new HashMap<>();
        request.setAttribute("errores", errores);
        try {
            
          switch(url)
          {
              case "/Presentacion/Cajero/Transferencia_caja/Menu_transferenciacedu.jsp":
                  Login_usuario persona = new Login_usuario();
                  persona.setCedula( domainModel.CuentaGet(request.getParameter("Buttoncuenta")).getPersona().getCedula());
                  model.setCuentas(domainModel.cuentasFind(persona));
                  break;
              case "/Presentacion/Cajero/Transferencia_caja/Menu_transferenciaCuenta.jsp": 
                  model.setCurrent(domainModel.CuentaGet(request.getParameter("Buttoncuenta")));
                  break;
          
          }
         model.setCurrent(domainModel.CuentaGet(request.getParameter("Buttoncuenta")));
         model.setCurrentdestino(domainModel.CuentaGet(request.getParameter("txtCuentafavorita")));
         if(model.getCurrentdestino()==null)
         {

             errores.put("txtCuentafavorita","Cuenta inexistente");
         }
         if(domainModel.CuentaGet(request.getParameter("Buttoncuenta")).getSaldo()-Double.parseDouble(request.getParameter("txtMonto"))<0.0)
         {
         errores.put("txtMonto","Fondos insuficientes");
         }
         model.setMonto(Double.parseDouble(request.getParameter("txtMonto")));
         model.setDescripcion(request.getParameter("txtDescripcion"));
         
         if(!errores.isEmpty())
           return url;
         
           return "/Presentacion/Cajero/Transferencia_caja/Verificacion_transferencia.jsp";
        } catch (Exception ex) {
             errores.put("txtMonto","No se digitó un numero");
            return url;
        }
    }
     public String ShowCuenta(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        banca.Logic.Model domainModel = banca.Logic.Model.instance();
        HttpSession session = request.getSession(true);
 
    
      
        try {        

            model.setCurrent(domainModel.CuentaGet(request.getParameter("txtNumero")));
            
            if(model.getCurrent()==null)
            {Map<String,String> errores = new HashMap<>();
             request.setAttribute("errores", errores);
             errores.put("txtNumero","Cuenta inexistente");
            
            }
     
           return "/Presentacion/Cajero/Transferencia_caja/Menu_transferenciaCuenta.jsp";
        } catch (Exception ex) {
            return "";
        }
    }
       public String ShowCuentas(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        banca.Logic.Model domainModel = banca.Logic.Model.instance();
        HttpSession session = request.getSession(true);
 
    
      
        try {        
            if(!domainModel.Ispersona(request.getParameter("txtCedula")))
            {
             Map<String,String> errores = new HashMap<>();
             request.setAttribute("errores", errores);
             errores.put("txtCedula","Cedula inexistente");
            }
            else{
            Login_usuario usuario = new Login_usuario();
            usuario.setCedula(request.getParameter("txtCedula"));
            model.setCuentas(domainModel.cuentasFind(usuario));}
     
           return "/Presentacion/Cajero/Transferencia_caja/Menu_transferenciacedu.jsp";
        } catch (Exception ex) {
            return "";
        }
    }
    public boolean Conversion_transaccion(Cuenta cuenta1,Cuenta cuenta2,Double Monto) throws Exception
    {
     banca.Logic.Model domainModel = banca.Logic.Model.instance();
   
    if(cuenta1.getMoneda().equals(cuenta2.getMoneda()))
    {
    
        if(cuenta1.getSaldo()>=Monto )
        {
        domainModel.CuentaUpdate(cuenta1.getSaldo()-Monto,cuenta1.getNumero());
       domainModel.CuentaUpdate(cuenta2.getSaldo()+Monto, cuenta2.getNumero());
        return true;
        } 
        else
        {
        //error
        }
        
    }
    else
    {
    if(cuenta1.getSaldo()>=Monto )
        {
            switch(cuenta1.getMoneda()+"-"+cuenta2.getMoneda())
            {
                case "Dolares-Colones":
           domainModel.CuentaUpdate(cuenta1.getSaldo()-Monto,cuenta1.getNumero());
          domainModel.CuentaUpdate(cuenta2.getSaldo()+(Monto*564.41), cuenta2.getNumero());
           
                    break;
                case "Colones-Dolares":
          domainModel.CuentaUpdate(cuenta1.getSaldo()-Monto,cuenta1.getNumero());
          domainModel.CuentaUpdate(cuenta2.getSaldo()+(Monto*0.0018), cuenta2.getNumero());
           
                    break;
                case "Euros-Dolares":
          domainModel.CuentaUpdate(cuenta1.getSaldo()-Monto,cuenta1.getNumero());
          domainModel.CuentaUpdate(cuenta2.getSaldo()+(Monto*1.08), cuenta2.getNumero());
           
                    break;
                case "Dolares-Euros":
                    domainModel.CuentaUpdate(cuenta1.getSaldo()-Monto,cuenta1.getNumero());
          domainModel.CuentaUpdate(cuenta2.getSaldo()+(Monto*0.93), cuenta2.getNumero());
           
                    break;
                case "Colones-Euros":
                    domainModel.CuentaUpdate(cuenta1.getSaldo()-Monto,cuenta1.getNumero());
          domainModel.CuentaUpdate(cuenta2.getSaldo()+(Monto*0.0016), cuenta2.getNumero());
           
                    break;
                case "Euros-Colones":
                    domainModel.CuentaUpdate(cuenta1.getSaldo()-Monto,cuenta1.getNumero());
          domainModel.CuentaUpdate(cuenta2.getSaldo()+(Monto*607.50), cuenta2.getNumero());
           
                    break;
            }  
             return true;
        }
    else
        {
          //error
        }
    }
        
        
    return false;
    }
    
    public String TransferenciaAdd(HttpServletRequest request) {
     
        banca.Logic.Model domainModel = banca.Logic.Model.instance();
        HttpSession session = request.getSession(true);
 
      Login_usuario usuario = (Login_usuario) session.getAttribute("usuario");
     
      
        try {

           if(this.Conversion_transaccion(domainModel.CuentaGet(request.getParameter("txtMicuenta")),domainModel.CuentaGet(request.getParameter("txtCuentafavorita")),Double.parseDouble(request.getParameter("txtMonto"))))
           {
                Format formatter = new SimpleDateFormat("yyyy-MM-dd");	
               domainModel.TransferenciaAdd(new Transferencia(0,formatter.format(new Date()),Double.parseDouble(request.getParameter("txtMonto")),domainModel.CuentaGet(request.getParameter("txtMicuenta")).getMoneda(),request.getParameter("txtDescripcion"),domainModel.CuentaGet(request.getParameter("txtMicuenta")),domainModel.CuentaGet(request.getParameter("txtCuentafavorita")),"Caja"));
               request.setAttribute("model",new banca.Presentacion.login.Model());
            return "/Presentacion/Inicio.jsp";
           }
        } catch (Exception ex) {
            return "";
        }
         return "";
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