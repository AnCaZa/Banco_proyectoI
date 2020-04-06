<%-- 
    Document   : Menu_movimientos
    Created on : 21/03/2020, 01:16:57 PM
    Author     : andre
--%>

<%@page import="banca.Logic.Retiro"%>
<%@page import="banca.Logic.Deposito"%>
<%@page import="banca.Logic.Transferencia"%>
<%@page import="java.util.List"%>
<%@page import="banca.Presentacion.Cliente.Movimientos.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Model model = (Model) request.getAttribute("model");
 
   List<Transferencia> transferencias = model.getTransferencias();
   List<Deposito> depositos = model.getDepositos();
   List<Retiro> retiros = model.getRetiros();

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/Presentacion/link.jsp" %>
        <title>Movimientos</title>
    </head>
    <body>
            
         <%@ include file="/Presentacion/Encabezado.jsp" %>
     <h2>Transferencias:</h2>
      <table>
            <thead>
                <tr> <td>Fecha</td> <td>Cuenta-proveniente</td><td>Nombre</td><td>Descripcion</td> <td>Monto</td></tr>
            </thead>
            <tbody>
                        <% for( Transferencia t :transferencias ){%>
                        <tr>     <td><%=t.getFecha() %> </td><td><%=t.getCuentadestino().getNumero() %></td><td><%=t.getCuentadestino().getPersona().getNombre() %></td><td><%=t.getDescripcion() %></td><td><%=t.getMonto() %></td></tr>
                        <%}%>
            </tbody>
        </table>
            <h2>Depositos:</h2>
      <table>
            <thead>
                <tr> <td>Fecha</td> <td>Descripcion</td> <td>Monto</td></tr>
            </thead>
            <tbody>
                        <% for( Deposito d :depositos ){%>
                        <tr>     <td><%=d.getFecha() %> </td><td><%=d.getDescripcion() %></td><td><%=d.getMonto() %></td></tr>
                        <%}%>
            </tbody>
        </table>
            <h2>Retiros:</h2>
      <table>
            <thead>
                <tr> <td>Fecha</td> <td>Monto</td></tr>
            </thead>
            <tbody>
                        <% for( Retiro r :retiros ){%>
                        <tr>     <td><%=r.getFecha() %> </td><td><%=r.getMonto() %></td></tr>
                        <%}%>
            </tbody>
        </table>
            <%@ include file="/Presentacion/Footer.jsp" %>
    </body>
</html>
