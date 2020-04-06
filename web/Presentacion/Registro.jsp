<%-- 
    Document   : Registro
    Created on : 03/03/2020, 08:01:45 PM
    Author     : andre
--%>

<%@page import="banca.Presentacion.Cajero.Model"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  Model model = (Model) request.getAttribute("model");
    Map<String,String> errores=null;
if(model!=null)
 errores = (Map<String,String>) request.getAttribute("errores"); 
%>
<!DOCTYPE html>
<html>
    <head>
         <%@ include file="link.jsp" %>
        <title>Registro</title>
    </head>
    <body>
          <%@ include file="Encabezado.jsp" %>
          <form class="form_inicio"    action="/Banco_proyectoI/Presentacion/Cajero/show" method="POST">
               <h2>Registro</h2>
               <p>Cedula:</p> <input type="text" name="txtCedula" placeholder="<%=this.placeholder("txtCedula", errores, "Cedula") %>">
               <p>Nombre:</p> <input type="text" name="txtNombre" placeholder="Nombre">
               <p>Telefono:</p> <input type="text" name="txtTelefono" placeholder="<%=this.placeholder("txtTelefono", errores, "numero telefonico") %>"> <br> <br>
                  <center><input type="submit" name="butttonenviar" value="Añadir" ></center>
              
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