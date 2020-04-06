<%-- 
    Document   : Fecha_movimientos
    Created on : 21/03/2020, 01:54:22 PM
    Author     : andre
--%>

<%@page import="banca.Logic.Cuenta"%>
<%@page import="banca.Presentacion.Cliente.Movimientos.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Model model = (Model) request.getAttribute("model");
 
 Cuenta cuenta = model.getSeleccionado();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <%@ include file="/Presentacion/link.jsp" %>
        <title>Fecha-Movimientos</title>
    </head>
    <body>
     <%@ include file="/Presentacion/Encabezado.jsp" %>
     <form class="form_inicio" action="/Banco_proyectoI/Presentacion/Cliente/Movimientos/selectFecha" method="POST">
            <p>Mi cuenta:</p><input  type="text" name="txtCuenta" value="<%=cuenta.getNumero() %>" readonly>
            <p>Fecha-inicio:</p><input class="fecha"  type="text" placeholder="yyyy-mm-dd" name="txtFechaA"  pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}">
            <p>Fecha-Final:</p><input class="fecha"  type="text" placeholder="yyyy-mm-dd" name="txtFechaB"  pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"> <br> <br>
           <center><input  type="submit" name="txtEnviar" value="Enviar"></center>
     </form>
            <%@ include file="/Presentacion/Footer.jsp" %>
    </body>
</html>
