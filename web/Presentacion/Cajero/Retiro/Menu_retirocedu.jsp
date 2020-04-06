<%-- 
    Document   : Cedula_deposito
    Created on : 17/03/2020, 08:06:09 PM
    Author     : andre
--%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="banca.Logic.Cuenta"%>
<%@page import="banca.Presentacion.Cajero.Retiro.Model"%>
<%
    Model model = (Model) request.getAttribute("model");
    List<Cuenta> cuentas=null;
    if(model!=null)
    {  cuentas = model.getCuentas();}
    
%>
<%
    Map<String,String> errores=null;
if(model!=null)
 errores = (Map<String,String>) request.getAttribute("errores"); 
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       
        <%@ include file="/Presentacion/link.jsp" %>
        <title>Retiro-Cliente</title>
    </head>
    <body>
         <%@ include file="/Presentacion/Encabezado.jsp" %>
         <%if(cuentas==null) {%>
         <form action="/Banco_proyectoI/Presentacion/Cajero/Retiro/showcuentas" method="POST">
             <p>Cedula:</p><input  type="text" name="txtCedula" placeholder="<%=this.placeholder("txtCedula", errores, "Cedula cliente") %>">
               <input  type="Submit" name="Enviar" value="Enviar">
               
         </form>
              <%}%>
               <%if(cuentas!=null) {%>
               <form action="/Banco_proyectoI/Presentacion/Cajero/Retiro/add" method="POST">
               <p>Monto:</p><input  type="text" name="txtMonto" placeholder="<%=this.placeholder("txtMonto", errores, "Monto") %>">
                  <h2>Cuenta cliente</h2>
                <table>
            <thead>
                <tr> <td>Numero</td> <td>Cedula</td>  <td>Nombre</td> <td>Moneda</td> </tr>
            </thead>
            <tbody>
                        <% for(Cuenta c:cuentas){%>
                        <tr> <td class="td_button"><input  type="Submit" name="Buttoncuenta" value="<%=c.getNumero()%>"> </td>  <td><%=c.getPersona().getCedula() %></td> <td><%=c.getPersona().getNombre() %> </td><td><%=c.getMoneda() %></td></tr>
                        <%}%>
            </tbody>
        </table>
            </form>
              <%}%>
              <%@ include file="/Presentacion/Footer.jsp" %>
    </body>
</html>
<%!
   private String placeholder(String campo, Map<String,String> errores,String mensaje){
      if ( (errores!=null) && (errores.get(campo)!=null) )
        return errores.get(campo);
      else
        return mensaje;
    }
%>