/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca.Presentacion.Cajero.Deposito;



import banca.Logic.Cuenta;
import banca.Logic.Deposito;
import banca.Logic.Login_usuario;
import banca.Logic.Persona;
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


@WebServlet(name = "CajeroDepositoController", urlPatterns = {"/Presentacion/Cajero/Deposito/showcuentas","/Presentacion/Cajero/Deposito/showcuenta","/Presentacion/Cajero/Deposito/add","/Presentacion/Cajero/Deposito/Add"})
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
          case "/Presentacion/Cajero/Deposito/showcuentas":
              viewUrl = this.showCuentas(request);
              break;
           case "/Presentacion/Cajero/Deposito/add":
              viewUrl = this.add(request);
              break;
         case "/Presentacion/Cajero/Deposito/Add":
              viewUrl = this.Add(request);
              break;
             case "/Presentacion/Cajero/Deposito/showcuenta":
              viewUrl = this.showCuenta(request);
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
        return this.DepositoAdd(request,"/Presentacion/Cajero/Deposito/Menu_depositocedu.jsp");
    }
      public String Add(HttpServletRequest request) {
        return this.DepositoAdd(request,"/Presentacion/Cajero/Deposito/Menu_depositoCuenta.jsp");
    }
   public String ShowCuenta(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        banca.Logic.Model domainModel = banca.Logic.Model.instance();
        HttpSession session = request.getSession(true);
 
    
      
        try {        

            model.setCurrent(domainModel.CuentaGet(request.getParameter("txtNumero")));
         if(model.getCurrent()==null)
      {
            Map<String,String> errores = new HashMap<>();
             request.setAttribute("errores", errores);
             errores.put("txtNumero","Numero inexistente");
      }
     
           return "/Presentacion/Cajero/Deposito/Menu_depositoCuenta.jsp";
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
           else
           {
            Login_usuario usuario = new Login_usuario();
            usuario.setCedula(request.getParameter("txtCedula"));
            model.setCuentas(domainModel.cuentasFind(usuario));
           }
           return "/Presentacion/Cajero/Deposito/Menu_depositocedu.jsp";
        } catch (Exception ex) {
            return "";
        }
    }
    public String DepositoAdd(HttpServletRequest request,String url) {
        Model model = (Model) request.getAttribute("model");
        banca.Logic.Model domainModel = banca.Logic.Model.instance();
        HttpSession session = request.getSession(true);
 
    
      
        try { 
            switch(url)
            {
                case "/Presentacion/Cajero/Deposito/Menu_depositocedu.jsp":
            Login_usuario user = new Login_usuario();
        user.setCedula(domainModel.CuentaGet(request.getParameter("Buttoncuenta")).getPersona().getCedula());
         model.setCuentas(domainModel.cuentasFind(user));
                    break;
                 case "/Presentacion/Cajero/Deposito/Menu_depositoCuenta.jsp":
         model.setCurrent(domainModel.CuentaGet(request.getParameter("Buttoncuenta")));
                    break;
            }
       
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        

        
        domainModel.CuentaUpdate(Double.parseDouble(request.getParameter("txtMonto")),request.getParameter("Buttoncuenta") );
        domainModel.DepositoAdd(new Deposito(0,formatter.format(new Date()),domainModel.CuentaGet(request.getParameter("Buttoncuenta")),Double.parseDouble(request.getParameter("txtMonto")),request.getParameter("txtDescripcion")));
        request.setAttribute("model",new banca.Presentacion.login.Model());
           return "/Presentacion/Inicio.jsp";
        } catch (Exception ex) {
            Map<String,String> errores = new HashMap<>();
             request.setAttribute("errores", errores);
             errores.put("txtMonto","No se digitó un numero");
            
            return url;
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