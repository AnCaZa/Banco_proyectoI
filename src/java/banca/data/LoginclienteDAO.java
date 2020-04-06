/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca.data;

import java.sql.ResultSet;
import banca.Logic.Login_usuario;
import banca.Logic.Persona;
import java.sql.SQLException;
/**
 *
 * @author andre
 */
public class LoginclienteDAO {
      public Login_usuario LoginclienteGet(String cedula,String contraseña,RelDatabase db) throws  Exception{
        
       
            String sql="select a.Contraseña, p.Cedula, p.Nombre, p.Telefono "+
                    "from Login_cliente a inner join Persona p on a.Cedula=p.Cedula " +
                    "where a.Cedula='%s' AND a.Contraseña='%s' ";
           sql=String.format(sql,cedula,contraseña);
            ResultSet rs =  db.executeQuery(sql);
             if (rs.next()) {
            return Cliente(rs);
        }
        else{
            throw new Exception ("No Existe");
        }
    }
       public boolean Iscliente(String cedula,RelDatabase db) throws SQLException {
        
       
            String sql="select a.Contraseña, p.Cedula, p.Nombre, p.Telefono "+
                    "from Login_cliente a inner join Persona p on a.Cedula=p.Cedula " +
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
   public void PersonaAdd(String cedula,String nombre,String Telefono,RelDatabase db) throws Exception{
        String sql="insert into Persona(Cedula, Nombre, Telefono) "+
                "values('%s','%s','%s')";
        sql=String.format(sql,cedula,nombre,Telefono);
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("Ya existe");
        }
     }
     public void Loginclienteadd(String cedula,String contraseña,RelDatabase db) throws Exception
     {
     String sql="insert into Login_cliente(Cedula, Contraseña) "+
                "values('%s','%s')";
        sql=String.format(sql,cedula,contraseña);
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("Ya existe");
        }
     }
     public Login_usuario Cliente(ResultSet rs) throws SQLException
      {
      return new Login_usuario(rs.getNString("Cedula"),rs.getNString("Contraseña"),new Persona(rs.getNString("Cedula"),rs.getNString("Nombre"),rs.getNString("Telefono")),1);
      }
}
