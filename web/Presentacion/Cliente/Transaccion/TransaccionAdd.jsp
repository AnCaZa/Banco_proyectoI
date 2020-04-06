<%-- 
    Document   : TransaccionAdd
    Created on : 15/03/2020, 04:14:20 PM
    Author     : andre
--%>

<%@page import="java.util.Map"%>
<%@page import="banca.Logic.Cuenta"%>
<%@page import="banca.Presentacion.Cliente.Transaccion.Model"%>
<%
    Model model = (Model) request.getAttribute("model");
 
  Cuenta cuenta = model.getSeleccionado();
 Cuenta cuentafavorita = model.getSeleccionaamiga();
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
        <title>Transaccion</title>
    </head>
    <body>
   <%@ include file="/Presentacion/Encabezado.jsp" %>
   <form class="form_inicio" action="/Banco_proyectoI/Presentacion/Cliente/Transaccion/Transaccion" method="POST">
           <p>Mi cuenta:</p><input  type="text" name="txtMicuenta" value="<%=cuenta.getNumero() %>" readonly>
           <p>cuenta a depositar:</p><input  type="text" name="txtCuentafavorita" value="<%=cuentafavorita.getNumero() %>" readonly>
           <p>Monto:</p><input  type="text" name="txtMonto" placeholder="<%=this.placeholder("txtMonto", errores, "Monto a transferir") %>">
           <p>Descripcion:</p><input  type="text" name="txtDescripcion" placeholder="Descripcion"> <br> <br>
           
          <center>  <input  type="submit" name="buttonenviar" value="Enviar"> </center>
           
       </form>
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