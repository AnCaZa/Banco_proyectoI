<%@page import="java.util.HashMap"%>
<%@page import="banca.Presentacion.login.Model"%>
<%@page import="java.util.Map"%>
<%@page import="banca.Logic.Login_usuario; "%>
<%  
  
    Model model = (Model) request.getAttribute("model");%>
<% Login_usuario usuario=  (Login_usuario) session.getAttribute("usuario");  %>
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
        
        
    </head>
    <body >

        <%@ include file="/Presentacion/Encabezado.jsp" %>  
    
  

          
         <% if (usuario==null){ %>
         <form class="form_inicio" style="float: right" action="/Banco_proyectoI/Presentacion/login/login" method="POST">
                <h2>Bienvenido</h2>
                <p>Identificación:</p> <input type="text" name="txtID" placeholder="<%= this.placeholder("txtID", errores,"Cedula cliente") %>"    >
                <p>Contraseña:</p> <input type="password" name="txtContrasena"  placeholder="<%= this.placeholder("txtContrasena", errores,"Contraseña cliente") %>"><br><br>
               <center><input  type="submit" name="txtIngresar" value="Ingresar"></center><br>
               <center><a href="/Banco_proyectoI/Presentacion/Registro/Registro.jsp"><input  type="button" name="txtRegistro" value="Registrarse"></a></center>
        </form>
           <%}%>
           <% if (usuario!=null && usuario.getTipo()==1){ %>
             <nav id="Menu"> 
           <ul class="Nav">     
           <li> <a href="/Banco_proyectoI/Presentacion/Cliente/Cuentas/show">Cuentas</a>
                  <ul> <!--submenu --> </ul>
                </li> 
                <li class="Barra"> | </li>  
                <li >
                  <a  href="#">User:<%=usuario.getCedula()%></a>
                  <ul>  <!--submenu --> </ul>
                </li>
                <li class="Barra"> | </li>    
                <li >
                  <a  href="/Banco_proyectoI/Presentacion/Cliente/Cuenta_amiga/Menu_cuenta_amiga.jsp">Añadir cuenta favorita</a>
                  <ul>  <!--submenu --> </ul>
                </li>
                <li class="Barra"> | </li>  
                 <li >
                  <a  href="/Banco_proyectoI/Presentacion/Cliente/Transaccion/Menu_transaccion">Transaccion</a>
                  <ul>  <!--submenu --> </ul>
                </li>
                   <li class="Barra"> | </li>  
                <li >
                  <a  href="/Banco_proyectoI/Presentacion/login/logout">Logout</a>
                  <ul>  <!--submenu --> </ul>
                </li>
                 </ul> 
                    </nav> 
                        <% } %>
          <% if (usuario!=null && usuario.getTipo()==2){ %>
          <nav id="Menu">  
            <ul class="Nav">     
           <li>
                  <a href="/Banco_proyectoI/Presentacion/Registro.jsp">Añadir cliente</a>
                  <ul>  <!--submenu --> </ul>
                </li> 
                  <li class="Barra"> | </li>  
                 <li>
                  <a href="/Banco_proyectoI/Presentacion/Cajero/Cuenta.jsp">Crear cuenta</a>
                  <ul>  <!--submenu --> </ul>
                </li>
                  <li class="Barra"> | </li>  
                <li >
                  <a  href="#">Cajero:<%=usuario.getCedula()%></a>
                  <ul>  <!--submenu --> </ul>
                </li>
                  <li class="Barra"> | </li>  
                  <li >
                  <a  href="#">Transferencia</a>
                  <ul>
                      <li>  <a  href="/Banco_proyectoI/Presentacion/Cajero/Transferencia_caja/Menu_transferenciacedu.jsp">Transferencia por cedula</a></li>
                          <li>  <a  href="/Banco_proyectoI/Presentacion/Cajero/Transferencia_caja/Menu_transferenciaCuenta.jsp">Transferencia por numero cuenta</a></li>
                     </ul>
                </li> 
                  <li class="Barra"> | </li>  
                <li >
                  <a  href="#">Deposito</a>
                  <ul>
                      <li>  <a  href="/Banco_proyectoI/Presentacion/Cajero/Deposito/Menu_depositocedu.jsp">Deposito por cedula</a></li>
                          <li>  <a  href="/Banco_proyectoI/Presentacion/Cajero/Deposito/Menu_depositoCuenta.jsp">Deposito por numero cuenta</a></li>
                     </ul>
                </li>
                  <li class="Barra"> | </li>  
                 <li >
                  <a  href="#">Retiro</a>
                  <ul>
                      <li>  <a  href="/Banco_proyectoI/Presentacion/Cajero/Retiro/Menu_retirocedu.jsp">Retiro por cedula</a></li>
                          <li>  <a  href="/Banco_proyectoI/Presentacion/Cajero/Retiro/Menu_retiroCuenta.jsp">Retiro por numero cuenta</a></li>
                     </ul>
                </li>
                  <li class="Barra"> | </li>  
                <li>
                  <a href="/Banco_proyectoI/Presentacion/Cajero/Intereses/add">Acreditar Intereses</a>
                  <ul>  <!--submenu --> </ul>
                </li>
                  <li class="Barra"> | </li>  
                <li >
                  <a  href="/Banco_proyectoI/Presentacion/login/logout">Logout</a>
                  <ul>  <!--submenu --> </ul>
                </li>   
                 </ul> 
          
          </nav>
          <% } %>
                
         
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