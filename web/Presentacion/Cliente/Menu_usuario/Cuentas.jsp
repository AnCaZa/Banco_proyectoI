
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
        <h2>Listado de cuentas del cliente</h2>     
    
        <table>
            <thead>
                <tr> <td>Numero</td> <td>Saldo</td> <td>Todos los movimientos</td><td>Todos los movimientos por rango de fechas</td> </tr>
            </thead>
            <tbody>
                        <% for(Cuenta c:cuentas){%>
                <tr> <td><a href="/Banco_proyectoI/Presentacion/Cliente/Cuentas/Showcuenta?numCuenta=<%=c.getNumero()%>"><%=c.getNumero()%> </td>  <td><%=c.getSaldo()%></td><td><a href="/Banco_proyectoI/Presentacion/Cliente/Movimientos/Movimientos?numCuenta=<%=c.getNumero()%>">Ver</td><td><a href="/Banco_proyectoI/Presentacion/Cliente/Movimientos/Movimientosfecha?numCuenta=<%=c.getNumero()%>">Ver</td></tr>
                        <%}%>
            </tbody>
        </table>
            
                   
    </div>
            <%@ include file="/Presentacion/Footer.jsp" %>
    </body>
</html>
