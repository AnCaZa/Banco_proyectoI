/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca.data;

import banca.Logic.Retiro;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andre
 */
public class RetiroDAO {
        public void RetiroAdd(Retiro retiro,RelDatabase db) throws Exception{
        String sql="insert into Retiro (Fecha, Cuenta, Monto) "+
                "values('%s','%s','%s')";
        sql=String.format(sql,retiro.getFecha(),retiro.getCuenta().getNumero(),retiro.getMonto());
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("Ya existe");
        }
     }
         public List<Retiro> Retirosearchfecha(String Numero,String FechaA,String FechaB,RelDatabase db){
      
    banca.Logic.Model model = banca.Logic.Model.instance();
             List<Retiro> retiros = new ArrayList<>();
        try {
            String sql="select * from Retiro WHERE Cuenta='%s'  AND  (CAST(Fecha AS DATE) "+
                    "BETWEEN CAST('%s' AS DATE) AND CAST('%s' AS DATE)) ";
           sql=String.format(sql,Numero, FechaA,FechaB);
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                retiros.add(retiro(rs,model));
            }
        } catch (SQLException ex) { }
        return retiros ;
    }
          public List<Retiro> Retirosearch(String Numero,RelDatabase db){

    banca.Logic.Model model = banca.Logic.Model.instance();
             List<Retiro> retiros = new ArrayList<>();
        try {
            String sql="select * from Retiro  WHERE Cuenta='%s' "; 
           sql=String.format(sql,Numero,Numero);
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                retiros.add(retiro(rs,model));
            }
        } catch (SQLException ex) { }
        return retiros ;
    }
     public Retiro retiro(ResultSet rs,banca.Logic.Model model) throws SQLException
     {
     return new Retiro(rs.getInt("Identificador"),rs.getNString("Fecha"),model.CuentaGet(rs.getNString("Cuenta")),rs.getDouble("Monto"));
     }
}
