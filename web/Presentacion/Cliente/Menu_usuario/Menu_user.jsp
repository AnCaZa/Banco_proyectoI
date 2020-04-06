
<%@page import="banca.Presentacion.Cliente.Cuentas.Model"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="banca.Logic.Cuenta"%>
<%
    Model model = (Model) request.getAttribute("model");
    List<Cuenta> cuentas = model.getCuentas();
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
      <%@ include file="/Presentacion/link.jsp" %>
    </head>
    <body>
     
      <%@ include file="/Presentacion/Encabezado.jsp" %>        
    <div style="width:50%;margin:auto;">
        <h1>Listado de Cuentas del Cliente</h1>     
    
        <table>
            <thead>
                <tr> <td>Numero</td> <td>Saldo</td>  </tr>
            </thead>
            <tbody>
                        <% for(Cuenta c:cuentas){%>
                <tr> <td><%=c.getNumero()%> </td>  <td><%=c.getSaldo()%></td></tr>
                        <%}%>
            </tbody>
        </table>
            
                   
    </div>
 <%@ include file="/Presentacion/Footer.jsp" %>
    </body>
</html>
