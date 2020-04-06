/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca.data;

import banca.Logic.Persona;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author andre
 */
public class PersonaDAO {
     public Persona PersonaGet(String cedula,RelDatabase db) throws  Exception{
        
       
            String sql="select * "+
                    "from Persona a " +
                    "where a.Cedula='%s' ";
           sql=String.format(sql,cedula);
            ResultSet rs =  db.executeQuery(sql);
             if (rs.next()) {
            return persona(rs);
        }
        else{
            throw new Exception ("Cliente no Existe");
        }
    }
     
          public boolean Ispersona(String cedula,RelDatabase db) throws  Exception{
        
       
            String sql="select * "+
                    "from Persona a " +
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
     public Persona persona(ResultSet rs) throws SQLException
     {
     return new Persona(rs.getNString("Cedula"),rs.getNString("Nombre"),rs.getNString("Telefono"));
     }
}
