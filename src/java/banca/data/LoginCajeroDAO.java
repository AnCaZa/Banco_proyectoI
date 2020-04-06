/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca.data;

import banca.Logic.Login_usuario;
import banca.Logic.Persona;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author andre
 */
public class LoginCajeroDAO {
     public Login_usuario LogincajeroGet(String cedula,String contraseña,RelDatabase db) throws  Exception{
        
       
            String sql="select a.Contraseña, p.Cedula, p.Nombre, p.Telefono "+
                    "from Cajero a inner join Persona p on a.Cedula=p.Cedula " +
                    "where a.Cedula='%s' AND a.Contraseña='%s' ";
           sql=String.format(sql,cedula,contraseña);
            ResultSet rs =  db.executeQuery(sql);
             if (rs.next()) {
            return Cajero(rs);
        }
        else{
            throw new Exception ("No Existe");
        }
    }
      public boolean Iscajero(String cedula,RelDatabase db) throws  Exception{
        
       
            String sql="select a.Contraseña, p.Cedula, p.Nombre, p.Telefono "+
                    "from Cajero a inner join Persona p on a.Cedula=p.Cedula " +
                    "where a.Cedula='%s' ";
           sql=String.format(sql,cedula);
            ResultSet rs =  db.executeQuery(sql);
             if (rs.next()) {
            return true;
        }
        else{
            return false;
        }
    }
      public Login_usuario Cajero(ResultSet rs) throws SQLException
      {
      return new Login_usuario(rs.getNString("Cedula"),rs.getNString("Contraseña"),new Persona(rs.getNString("Cedula"),rs.getNString("Nombre"),rs.getNString("Telefono")),2);
      }
      
}
