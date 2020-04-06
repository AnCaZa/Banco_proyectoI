<%-- 
    Document   : Cuenta
    Created on : 11/03/2020, 08:11:08 PM
    Author     : andre
--%>


<%@page import="java.util.Map"%>
<%@page import="banca.Logic.Persona"%>
<%@page import="banca.Presentacion.Cajero.Model"%>
<%@page import="java.util.Random"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Model model = (Model) request.getAttribute("model");
    Persona persona=null;
    if(model!=null)
     persona = model.getCurrent();
    
%>
<%! 
public String CuentaRandom(){

char [] chars = "0123456789".toCharArray();

// Longitud del array de char.
int charsLength = chars.length;

// Instanciamos la clase Random
Random random = new Random();
StringBuffer buffer = new StringBuffer();

// Bucle para elegir una cadena de 10 caracteres al azar
for (int i=0;i<16;i++){

   // Añadimos al buffer un caracter al azar del array
   buffer.append(chars[random.nextInt(charsLength)]);
}
return buffer.toString();
}%>
<%
    Map<String,String> errores=null;
if(model!=null)
 errores = (Map<String,String>) request.getAttribute("errores"); 
%>
<!DOCTYPE html>
<html>
    <head>
       <%@ include file="/Presentacion/link.jsp" %>
    </head>
    <body>
             <% 
                   banca.Logic.Model domainModel = banca.Logic.Model.instance();
                   String Numero = CuentaRandom();
                   while(domainModel.Iscuenta(Numero))
                    Numero = CuentaRandom();
               
               %>
               
 <%@ include file="/Presentacion/Encabezado.jsp" %>  
 

      <%if(persona != null){%>
      <form class="form_inicio" action="/Banco_proyectoI/Presentacion/Cajero/addCuenta" method="POST">
               <h2>Creación de cuenta</h2>
          
               <p>Numero cuenta:</p> <input type="text" name="txtNumeroCuenta"  value="<%=Numero%>" readonly>
               <%if(!persona.getCedula().equals("") ){%>
               <p>Cedula de cliente:</p> <input type="text" name="txtCedula"  value="<%= persona.getCedula() %>" readonly>
                <%} else {%>
                 <p>Cedula de cliente:</p> <input type="text" name="txtCedula"  placeholder="<%=this.placeholder("txtCedula", errores, "Cedula") %>" >
                  <%} %>
               <p>Moneda:</p> <select name="txtMoneda" >
                   <option   value="Colones" >Colones</option>
                    <option   value="Dolares" >Dolares</option>
                     <option   value="Euros" >Euros</option>
               </select>
               <p>Limite diario:</p> <input type="text" name="txtLimite_diario" placeholder="<%=this.placeholder("txtLimite_diario", errores, "Limite diario") %>"> <br> <br>
               <center><input type="submit" name="butttonenviar" value="Añadir" ></center>
              
          </form>
               <%}%>
                    <%if(persona==null){%>
      <form class="form_inicio" action="/Banco_proyectoI/Presentacion/Cajero/addCuenta" method="POST">
               <h2>Creación de cuenta</h2>
          
               <p>Numero cuenta:</p> <input type="text" name="txtNumeroCuenta"  value="<%=Numero%>" readonly>
               <p>Cedula de cliente:</p> <input type="text" name="txtCedula"  placeholder="<%=this.placeholder("txtCedula", errores, "Cedula") %>">
               <p>Moneda:</p> <select name="txtMoneda" >
                   <option   value="Colones" >Colones</option>
                    <option   value="Dolares" >Dolares</option>
                     <option   value="Euros" >Euros</option>
               </select>
               <p>Limite diario:</p> <input type="text" name="txtLimite_diario" placeholder="<%=this.placeholder("txtLimite_diario", errores, "Limite diario") %>"> <br> <br>
                  <center><input type="submit" name="butttonenviar" value="Añadir" ></center>
              
          </form>
               <%}%>
               
           
    </body>
</html>
<%! private String placeholder(String campo, Map<String,String> errores,String mensaje){
      if ( (errores!=null) && (errores.get(campo)!=null) )
        return errores.get(campo);
      else
        return mensaje;
    }
%>