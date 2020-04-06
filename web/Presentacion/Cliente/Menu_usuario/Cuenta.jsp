<%-- 
    Document   : Cuenta
    Created on : 21/03/2020, 09:40:25 AM
    Author     : andre
--%>

<%@page import="banca.Logic.Cuenta"%>
<%@page import="banca.Presentacion.Cliente.Cuentas.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Model model = (Model) request.getAttribute("model");
   Cuenta cuenta = model.getSeleccionado();
    
%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/Presentacion/link.jsp" %>
        <title>Cuenta</title>
    </head>
    <body>
            <%@ include file="/Presentacion/Encabezado.jsp" %>    
                 <table>
            <thead>
                <tr> <td>Numero</td><td>Moneda</td><td>Limite remoto diario</td> <td>Saldo</td>  </tr>
            </thead>
            <tbody>
                        
                <tr> <td><%=cuenta.getNumero()%> </td> <td><%=cuenta.getMoneda()%></td> <td><%=cuenta.getLimite_remoto() %></td>  <td><%=cuenta.getSaldo()%></td></tr>
                  
            </tbody>
        </table>
        <%@ include file="/Presentacion/Footer.jsp" %>    
    </body>
</html>
