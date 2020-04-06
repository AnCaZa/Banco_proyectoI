/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca.data;

import banca.Logic.Deposito;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andre
 */
public class DepositoDAO {
    public void CuentaAdd(Deposito deposito,RelDatabase db) throws Exception{
        String sql="insert into Deposito (Fecha, Cuenta, Monto, Descripcion) "+
                "values('%s','%s','%s','%s')";
        sql=String.format(sql,deposito.getFecha(),deposito.getCuenta().getNumero(),deposito.getMonto(),deposito.getDescripcion());
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("Ya existe");
        }
     }
     public List<Deposito> Depositosearchfecha(String Numero,String FechaA,String FechaB,RelDatabase db){
      
    banca.Logic.Model model = banca.Logic.Model.instance();
             List<Deposito> depositos = new ArrayList<>();
        try {
            String sql="select * from Deposito WHERE Cuenta='%s'  AND  (CAST(Fecha AS DATE) "+
                    "BETWEEN CAST('%s' AS DATE) AND CAST('%s' AS DATE)) ";
           sql=String.format(sql,Numero, FechaA,FechaB);
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                depositos.add(deposito(rs,model));
            }
        } catch (SQLException ex) { }
        return depositos ;
    }
          public List<Deposito> Depositosearch(String Numero,RelDatabase db){

    banca.Logic.Model model = banca.Logic.Model.instance();
             List<Deposito> depositos = new ArrayList<>();
        try {
            String sql="select * from Deposito  WHERE Cuenta='%s' "; 
           sql=String.format(sql,Numero,Numero);
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                depositos.add(deposito(rs,model));
            }
        } catch (SQLException ex) { }
        return depositos ;
    }
     public Deposito deposito(ResultSet rs,banca.Logic.Model model) throws SQLException
     {
     return new Deposito(rs.getInt("Identificador"),rs.getNString("Fecha"),model.CuentaGet(rs.getNString("Cuenta")),rs.getDouble("Monto"),rs.getNString("Descripcion"));
     }
}
