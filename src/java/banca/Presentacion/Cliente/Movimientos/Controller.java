/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca.Presentacion.Cliente.Movimientos;


import banca.Logic.Login_usuario;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "ClienteMovimientosController", urlPatterns = {"/Presentacion/Cliente/Movimientos/Movimientos", "/Presentacion/Cliente/Movimientos/Movimientosfecha","/Presentacion/Cliente/Movimientos/selectFecha"})
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
          case "/Presentacion/Cliente/Movimientos/Movimientos":
              viewUrl = this.Movimiento(request);
              break;
          case "/Presentacion/Cliente/Movimientos/Movimientosfecha":
              viewUrl = this.Movimientofecha(request);
              break;
          case "/Presentacion/Cliente/Movimientos/selectFecha":
              viewUrl = this.ShowMovimientosfecha(request);
              break;
         
        
        }}          
        request.getRequestDispatcher(viewUrl).forward( request, response); 
  }

    public String Movimiento(HttpServletRequest request) {
        return this.showMovimiento(request);
    }
     
    public String Movimientofecha(HttpServletRequest request) {
       return this.ShowCuentaFechas(request);
    }
    public String ShowMovimientosfecha(HttpServletRequest request)
    {
    return this.ShowMovimientosselectfecha(request);
    }
    
    
    
    
    public String showMovimiento(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        banca.Logic.Model domainModel = banca.Logic.Model.instance();
        HttpSession session = request.getSession(true);
 
      Login_usuario usuario = (Login_usuario) session.getAttribute("usuario");
     
      
        try {
            model.setDepositos(domainModel.DepositoSearch(request.getParameter("numCuenta")));
           model.setRetiros(domainModel.RetiroSearch(request.getParameter("numCuenta")));
           model.setTransferencias(domainModel.TransferenciaSearch(request.getParameter("numCuenta")));
         
            return "/Presentacion/Cliente/Movimiento/Menu_movimientos.jsp";
        } catch (Exception ex) {
            return "";
        }
    }
        public String ShowCuentaFechas(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        banca.Logic.Model domainModel = banca.Logic.Model.instance();
        HttpSession session = request.getSession(true);
 
      Login_usuario usuario = (Login_usuario) session.getAttribute("usuario");
     
      
        try {
            model.setSeleccionado(domainModel.CuentaGet(request.getParameter("numCuenta")));
         
            return "/Presentacion/Cliente/Movimiento/Fecha_movimientos.jsp";
        } catch (Exception ex) {
            return "";
        }
    }
    public String ShowMovimientosselectfecha(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        banca.Logic.Model domainModel = banca.Logic.Model.instance();
        HttpSession session = request.getSession(true);
 
      Login_usuario usuario = (Login_usuario) session.getAttribute("usuario");
     Format formatter = new SimpleDateFormat("yyyy-MM-dd");
      
        try {
            if(request.getParameter("txtFechaA").equals("") && request.getParameter("txtFechaB").equals("") )
            {
            model.setTransferencias(domainModel.TransferenciaSearchFecha(request.getParameter("txtCuenta"), "2020-01-01", formatter.format(new Date())));
                model.setDepositos(domainModel.DepositoSearchFecha(request.getParameter("txtCuenta"), "2020-01-01", formatter.format(new Date())));
                model.setRetiros(domainModel.RetiroSearchFecha(request.getParameter("txtCuenta"), "2020-01-01", formatter.format(new Date())));
            }
            else
            {
             if(request.getParameter("txtFechaA").equals(""))
             {
             model.setTransferencias(domainModel.TransferenciaSearchFecha(request.getParameter("txtCuenta"), "2020-01-01", request.getParameter("txtFechaB")));
                model.setDepositos(domainModel.DepositoSearchFecha(request.getParameter("txtCuenta"), "2020-01-01", request.getParameter("txtFechaB")));
                model.setRetiros(domainModel.RetiroSearchFecha(request.getParameter("txtCuenta"), "2020-01-01", request.getParameter("txtFechaB")));
             }
             else
             {
                 if(request.getParameter("txtFechaB").equals(""))
                {
              model.setTransferencias(domainModel.TransferenciaSearchFecha(request.getParameter("txtCuenta"), request.getParameter("txtFechaA"), formatter.format(new Date())));
                model.setDepositos(domainModel.DepositoSearchFecha(request.getParameter("txtCuenta"), request.getParameter("txtFechaA"), formatter.format(new Date())));
                model.setRetiros(domainModel.RetiroSearchFecha(request.getParameter("txtCuenta"), request.getParameter("txtFechaA"), formatter.format(new Date())));
                }
                else
                 {
                 
                model.setTransferencias(domainModel.TransferenciaSearchFecha(request.getParameter("txtCuenta"), request.getParameter("txtFechaA"), request.getParameter("txtFechaB")));
                model.setDepositos(domainModel.DepositoSearchFecha(request.getParameter("txtCuenta"), request.getParameter("txtFechaA"), request.getParameter("txtFechaB")));
                model.setRetiros(domainModel.RetiroSearchFecha(request.getParameter("txtCuenta"), request.getParameter("txtFechaA"), request.getParameter("txtFechaB")));
                } 
             
             }    
                 
            }
           
            return "/Presentacion/Cliente/Movimiento/Menu_movimientos.jsp";
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