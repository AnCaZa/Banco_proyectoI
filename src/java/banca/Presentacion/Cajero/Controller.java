/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca.Presentacion.Cajero;


import banca.Logic.Cuenta;
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


@WebServlet(name = "CajeroController", urlPatterns = {"/Presentacion/Cajero/show","/Presentacion/Cajero/addCuenta"})
public class Controller extends HttpServlet {
    
  protected void processRequest(HttpServletRequest request, 
                                HttpServletResponse response)
         throws ServletException, IOException {

        request.setAttribute("model", new Model());
       HttpSession session = request.getSession(true);
       Login_usuario usuario=  (Login_usuario) session.getAttribute("usuario");
       String viewUrl="/Presentacion/Error.jsp";
       if(usuario!=null && usuario.getTipo()==2) 
       { switch (request.getServletPath()) {
          case "/Presentacion/Cajero/show":
              viewUrl = this.show(request);
              break;
          case "/Presentacion/Cajero/addCuenta":
              viewUrl = this.add(request);
              break;  
        
        }}     
          
        request.getRequestDispatcher(viewUrl).forward( request, response); 
  }

    public String show(HttpServletRequest request) {
    
        return this.showAction(request);
    }
   public String add(HttpServletRequest request) {
        return this.addAction(request);
    }
       public String addAction(HttpServletRequest request) {
         Model model = (Model) request.getAttribute("model");
        banca.Logic.Model domainModel = banca.Logic.Model.instance();
        HttpSession session = request.getSession(true);
 
    
      
        
       Map<String,String> errores = new HashMap<>();
            request.setAttribute("errores", errores);
            
        try {  
            
            if(!domainModel.Ispersona(request.getParameter("txtCedula")) ||!this.verificacion_cedulaotelefono(request.getParameter("txtCedula")) || request.getParameter("txtCedula").length()>9 ||  request.getParameter("txtCedula").length()<9)
            {
            errores.put("txtCedula","Cedula invalida");
            }
            else
            {
            model.setCurrent(domainModel.PersonaGet(request.getParameter("txtCedula")));
            }
   
           if(!this.verificacion_Limitediario(request.getParameter("txtLimite_diario")))
            {
            errores.put("txtLimite_diario","Limite invalido");
            }
            if(!errores.isEmpty())
            {
            return "/Presentacion/Cajero/Cuenta.jsp";
            }    
            Persona persona = new Persona();
            persona.setCedula(request.getParameter("txtCedula"));
           domainModel.Addcuenta(new Cuenta(request.getParameter("txtNumeroCuenta"),0,request.getParameter("txtMoneda"),persona,Double.parseDouble(request.getParameter("txtLimite_diario"))));
           request.setAttribute("model", new banca.Presentacion.login.Model());
           return "/Presentacion/Inicio.jsp";
        } catch (Exception ex) {
            return "";
        }
    }
    public boolean verificacion_Limitediario(String limitediario)
    {
    
     try {        
         Double.parseDouble(limitediario);
        } catch (Exception ex) {
            return false;
        }
     return true;
    }   
    public boolean verificacion_cedulaotelefono(String cedula)
    {
    
     try {        
         Integer.parseInt(cedula);
        } catch (Exception ex) {
            return false;
        }
     return true;
    }
    public String showAction(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        banca.Logic.Model domainModel = banca.Logic.Model.instance();
        HttpSession session = request.getSession(true);
 
    
       Map<String,String> errores = new HashMap<>();
            request.setAttribute("errores", errores);
            
        try {        
            if(!this.verificacion_cedulaotelefono(request.getParameter("txtCedula")) || request.getParameter("txtCedula").length()>9 ||  request.getParameter("txtCedula").length()<9)
            {
            errores.put("txtCedula","Cedula invalida");
            }
              if(!this.verificacion_cedulaotelefono(request.getParameter("txtTelefono")) || request.getParameter("txtTelefono").length()>8 || request.getParameter("txtTelefono").length()<8)
            {
            errores.put("txtTelefono","Telefono invalido");
            }
            if(!errores.isEmpty())
                return "/Presentacion/Registro.jsp";
           domainModel.Addpersona(request.getParameter("txtCedula"), request.getParameter("txtNombre"), request.getParameter("txtTelefono"));
            model.setCurrent(new Persona(request.getParameter("txtCedula"), request.getParameter("txtNombre"), request.getParameter("txtTelefono")));
           return "/Presentacion/Cajero/Cuenta.jsp";
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