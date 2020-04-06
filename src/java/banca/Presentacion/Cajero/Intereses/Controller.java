/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca.Presentacion.Cajero.Intereses;



import banca.Presentacion.Cajero.Deposito.*;
import banca.Logic.Cuenta;
import banca.Logic.Deposito;
import banca.Logic.Login_usuario;
import banca.Logic.Persona;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
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


@WebServlet(name = "CajeroInteresesController", urlPatterns = {"/Presentacion/Cajero/Intereses/add"})
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
        
         case "/Presentacion/Cajero/Intereses/add":
              viewUrl = this.add(request);
            
        
        }}          
        request.getRequestDispatcher(viewUrl).forward( request, response); 
  }

    
     public String add(HttpServletRequest request) {
        return this.InteresesAdd(request);
    }
    public void Acreditar_intereses(Cuenta cuenta,banca.Logic.Model domainModel) throws Exception
    {
     if(cuenta.getSaldo()!=0)
      {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        switch(cuenta.getMoneda())
        {
        case "Colones":
            domainModel.CuentaUpdate(cuenta.getSaldo()+(cuenta.getSaldo()*0.02), cuenta.getNumero());
            domainModel.DepositoAdd(new Deposito(0,formatter.format(new Date()),cuenta,cuenta.getSaldo()*0.02,"Intereses"));
            break;
        case "Dolares":
             domainModel.CuentaUpdate(cuenta.getSaldo()+(cuenta.getSaldo()*0.03), cuenta.getNumero());
            domainModel.DepositoAdd(new Deposito(0,formatter.format(new Date()),cuenta,cuenta.getSaldo()*0.03,"Intereses"));
            break;
        case "Euros":
             domainModel.CuentaUpdate(cuenta.getSaldo()+(cuenta.getSaldo()*0.01), cuenta.getNumero());
            domainModel.DepositoAdd(new Deposito(0,formatter.format(new Date()),cuenta,cuenta.getSaldo()*0.01,"Intereses"));
            break;
        }
     }
    }
    public String InteresesAdd(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        banca.Logic.Model domainModel = banca.Logic.Model.instance();
        HttpSession session = request.getSession(true);
            
        List<Cuenta> cuentas = domainModel.Cuentasget();
        
        try { 
          
            
         for(Cuenta cuenta:cuentas)
             this.Acreditar_intereses(cuenta, domainModel);
        

        
         request.setAttribute("model",new banca.Presentacion.login.Model());
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