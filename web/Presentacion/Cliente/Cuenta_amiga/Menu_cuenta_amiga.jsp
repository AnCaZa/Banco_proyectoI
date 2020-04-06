<%-- 
    Document   : Menu_cuenta_amiga
    Created on : 13/03/2020, 02:33:26 PM
    Author     : andre
--%>

<%@page import="banca.Presentacion.Cliente.Cuenta_amiga.Model"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="banca.Logic.Cuenta"%>

<%
   Model model = (Model) request.getAttribute("model");
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
        <title>Cuenta favorita</title>
    </head>
    <body>
         <%@ include file="/Presentacion/Encabezado.jsp" %> 
          
         <form class="form_inicio" action="/Banco_proyectoI/Presentacion/Cliente/Cuenta_amiga/add" method="POST">
             <h2>Cuenta Favorita</h2>
        <p>Numero de cuenta favorita:</p>
        <input type="text" name="txtCuentaamiga" placeholder="<%=this.placeholder("txtCuentaamiga", errores, "# Cuenta favorita") %>">
         <p>Nombre propietario:</p>
        <input type="text" name="txtNombre" placeholder="Nombre">
           <p>Cedula propietario:</p>
        <input type="text" name="txtCedula" placeholder="Cedula"> <br> <br>
         <center><input type="submit" name="butttonenviar" value="Añadir" ></center>
         </form>
         <%@ include file="/Presentacion/Footer.jsp" %>
    </body>
</html>
<%! private String placeholder(String campo, Map<String,String> errores,String mensaje){
      if ( (errores!=null) && (errores.get(campo)!=null) )
        return errores.get(campo);
      else
        return mensaje;
    }
%>