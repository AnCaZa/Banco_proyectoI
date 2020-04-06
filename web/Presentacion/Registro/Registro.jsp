<%-- 
    Document   : Registro
    Created on : 13/03/2020, 12:42:46 PM
    Author     : andre
--%>

<%@page import="banca.Presentacion.login.Model"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%    Model model = (Model) request.getAttribute("model");%>
<%
    Map<String,String> errores=null;
if(model!=null)
 errores = (Map<String,String>) request.getAttribute("errores"); 
%>
<!DOCTYPE html>
<html>
    <head>
           <%@ include file="/Presentacion/link.jsp" %>
        <title>Registro</title>
    </head>
    <body>
        <%@ include file="/Presentacion/Encabezado.jsp" %>  
        <form class="form_inicio" action="/Banco_proyectoI/Presentacion/login/registro" method="POST">
               <h2>Registro</h2>
               <p>Cedula:</p> <input type="text" name="txtCedula" placeholder="<%= this.placeholder("txtCedula",errores,"Cedula usuario")%>">
               <p>Contraseña:</p> <input type="password" name="txtContrasena" placeholder="<%= this.placeholder("txtContrasena",errores,"Contraseña usuario")%>"> <br> <br>
           
               <center> <input type="submit" name="butttonenviar" value="Registrarse" > </center>
              
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