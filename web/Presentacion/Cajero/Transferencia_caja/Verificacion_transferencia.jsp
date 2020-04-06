<%-- 
    Document   : Verificacion_transferencia
    Created on : 22/03/2020, 10:10:00 AM
    Author     : andre
--%>

<%@page import="banca.Logic.Cuenta"%>
<%@page import="banca.Presentacion.Cajero.Transferencia_caja.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Model model = (Model) request.getAttribute("model");
   Cuenta current=model.getCurrent();
   Cuenta currentdestino=model.getCurrentdestino();
   Double monto=model.getMonto();
   String Descripcion = model.getDescripcion();
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <%@ include file="/Presentacion/link.jsp" %>
        <title>Verificacion_transferencia</title>
    </head>
    <body>
          <%@ include file="/Presentacion/Encabezado.jsp" %>
      <form action="/Banco_proyectoI/Presentacion/Cajero/Transferencia/add" method="POST">
          <p>Cuenta-origen:</p><input  type="text" name="txtMicuenta" value="<%=current.getNumero()%>" readonly>
                <p>Cedula:</p><input  type="text" name="txtCedulaorigen" value="<%=current.getPersona().getCedula() %>" readonly>
                <p>Nombre:</p><input  type="text" name="txtNombreorigen" value="<%=current.getPersona().getNombre() %>" readonly>
                <p>Cuenta-destino:</p><input  type="text" name="txtCuentafavorita" value="<%=currentdestino.getNumero()%>" readonly>
                <p>Cedula:</p><input  type="text" name="txtCeduladestino" value="<%=currentdestino.getPersona().getCedula() %>" readonly>
                <p>Nombre:</p><input  type="text" name="txtNombredestino" value="<%=currentdestino.getPersona().getNombre() %>" readonly>
                <p>Monto:</p><input  type="text" name="txtMonto" value="<%=monto%>" readonly>
                <p>Descripcion:</p><input  type="text" name="txtDescripcion" value="<%=Descripcion%>" readonly> <br> <br>
                <center> <input  type="submit" name="añadir" value="Transferir"></center>
       </form>
<%@ include file="/Presentacion/Footer.jsp" %>
    </body>
</html>
