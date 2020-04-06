/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca.Presentacion.Cliente.Transaccion;




import banca.Logic.Cuenta;
import banca.Logic.Login_usuario;
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


@WebServlet(name = "ClienteTransaccionController", urlPatterns = {"/Presentacion/Cliente/Transaccion/Transaccion","/Presentacion/Cliente/Transaccion/cuentas","/Presentacion/Cliente/Transaccion/Menu_transaccion"})
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
          case "/Presentacion/Cliente/Transaccion/Transaccion":
              viewUrl = this.transaccion(request);
              break;
          case "/Presentacion/Cliente/Transaccion/cuentas":
              viewUrl = this.cuentasget(request);
              break;
           case "/Presentacion/Cliente/Transaccion/Menu_transaccion":
              viewUrl = this.showMenu_transaccion(request);
              break;
          
        
        }}          
        request.getRequestDispatcher(viewUrl).forward( request, response); 
  }
    public String showMenu_transaccion(HttpServletRequest request) {
        return this.Menu_transaccion(request);
    }
    public String transaccion(HttpServletRequest request) {
        return this.Transaccion(request);
    }
     public String cuentasget(HttpServletRequest request) {
        return this.CuentasGet(request);
    }
    
    public boolean Conversion_transaccion(Cuenta cuenta1,Cuenta cuenta2,Double Monto) throws Exception
    {
     banca.Logic.Model domainModel = banca.Logic.Model.instance();
     List<Transferencia>  transferencias = new ArrayList<>(); 
     transferencias=domainModel.TransferenciaSearchRemota(cuenta1.getNumero());
     Double Montotransferidodiaro=0.0;
     for (Transferencia t: transferencias)
         Montotransferidodiaro += t.getMonto();
    if(cuenta1.getMoneda().equals(cuenta2.getMoneda()))
    {
    
        if(cuenta1.getSaldo()>=Monto && cuenta1.getLimite_remoto()>=Montotransferidodiaro+Monto)
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
    if(cuenta1.getSaldo()>=Monto && cuenta1.getLimite_remoto()>=Montotransferidodiaro+Monto)
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
    
    public String Transaccion(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        banca.Logic.Model domainModel = banca.Logic.Model.instance();
        HttpSession session = request.getSession(true);
 
      Login_usuario usuario = (Login_usuario) session.getAttribute("usuario");
     
             Map<String,String> errores = new HashMap<>();
            request.setAttribute("errores", errores);
        try {
     model.setSeleccionado(domainModel.CuentaGet(request.getParameter("txtMicuenta")));
            model.setSeleccionaamiga(domainModel.CuentaGet(request.getParameter("txtCuentafavorita")));
           if(this.Conversion_transaccion(domainModel.CuentaGet(request.getParameter("txtMicuenta")),domainModel.CuentaGet(request.getParameter("txtCuentafavorita")),Double.parseDouble(request.getParameter("txtMonto"))))
           {
                Format formatter = new SimpleDateFormat("yyyy-MM-dd");	
               domainModel.TransferenciaAdd(new Transferencia(0,formatter.format(new Date()),Double.parseDouble(request.getParameter("txtMonto")),domainModel.CuentaGet(request.getParameter("txtMicuenta")).getMoneda(),request.getParameter("txtDescripcion"),domainModel.CuentaGet(request.getParameter("txtMicuenta")),domainModel.CuentaGet(request.getParameter("txtCuentafavorita")),"Remoto"));
               request.setAttribute("model", new banca.Presentacion.login.Model());
            return "/Presentacion/Inicio.jsp";
           }
           else
           {
           errores.put("txtMonto","Insuficientes fondos o LD");
           }
          
        } catch (Exception ex) {
          
             errores.put("txtMonto","No digitó un numero");
            return "/Presentacion/Cliente/Transaccion/TransaccionAdd.jsp";
        }
         return "/Presentacion/Cliente/Transaccion/TransaccionAdd.jsp";
    }
       public String Menu_transaccion(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        banca.Logic.Model domainModel = banca.Logic.Model.instance();
        HttpSession session = request.getSession(true);
 
      Login_usuario usuario = (Login_usuario) session.getAttribute("usuario");
     
      
        try {        
           
            model.setCuentas(domainModel.cuentasFind(usuario));
            model.setCuentasamigas(domainModel.CuentasAmigaSearch(usuario.getCedula()));
            return "/Presentacion/Cliente/Transaccion/Menu_transaccion.jsp";
        } catch (Exception ex) {
            return "";
        }
    }
   
      public String CuentasGet(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        banca.Logic.Model domainModel = banca.Logic.Model.instance();
        HttpSession session = request.getSession(true);
 
      Login_usuario usuario = (Login_usuario) session.getAttribute("usuario");
     
      
        try {        
           
            model.setSeleccionado(domainModel.CuentaGet(request.getParameter("selectCuenta")));
            model.setSeleccionaamiga(domainModel.CuentaGet(request.getParameter("numeroFld")));
            return "/Presentacion/Cliente/Transaccion/TransaccionAdd.jsp";
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