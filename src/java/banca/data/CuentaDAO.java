/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca.data;

import banca.Logic.Cuenta;
import banca.Logic.Persona;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andre
 */
public class CuentaDAO {
    public List<Cuenta> CuentaSearch(String cedula,RelDatabase db){
        List<Cuenta> cuentas = new ArrayList<>();
        try {
            String sql="select c.Numero_cuenta, c.Saldo, c.Moneda,c.Limite_diario, p.Cedula, p.Nombre, p.Telefono "+
                    "from Cuenta c inner join Persona p on c.Cliente=p.Cedula " +
                    "where c.Cliente='%s' ";
           sql=String.format(sql,cedula);
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                cuentas .add(cuenta(rs));
            }
        } catch (SQLException ex) { }
        return cuentas ;
    }
    public List<Cuenta> CuentasGet(RelDatabase db){
        List<Cuenta> cuentas = new ArrayList<>();
        try {
            String sql="select c.Numero_cuenta, c.Saldo, c.Moneda,c.Limite_diario, p.Cedula, p.Nombre, p.Telefono "+
                    "from Cuenta c inner join Persona p on c.Cliente=p.Cedula ";
                   
        
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                cuentas .add(cuenta(rs));
            }
        } catch (SQLException ex) { }
        return cuentas ;
    }
      public void CuentaUpdate(RelDatabase db,String Numero, double saldo) throws Exception
      {
      String sql="update Cuenta set Saldo='%s' "+
              "where Numero_cuenta='%s'";
      sql=String.format(sql,saldo,Numero);
      int count=db.executeUpdate(sql);
      if(count==0)
      {
          throw new Exception("falloo");
      }
      }
        public Cuenta CuentaGet(String Numero,RelDatabase db){
    
        try {
            String sql="select c.Numero_cuenta, c.Saldo, c.Moneda, c.Limite_diario, p.Cedula, p.Nombre, p.Telefono "+
                    "from Cuenta c inner join Persona p on c.Cliente=p.Cedula " +
                    "where c.Numero_cuenta='%s' ";
           sql=String.format(sql,Numero);
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                return cuenta(rs);
            }
        } catch (SQLException ex) {  }
        return null;
    } 
    public boolean IsCuenta(String Cuenta,RelDatabase db){
        List<Cuenta> cuentas = new ArrayList<>();
        try {
            String sql="select * "+
                    "from Cuenta c " +
                    "where c.Numero_cuenta='%s' ";
           sql=String.format(sql,Cuenta);
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
               return true;
            }
        } catch (SQLException ex) { }
        return false ;
    } 
  public void CuentaAdd(Cuenta cuenta,RelDatabase db) throws Exception{
        String sql="insert into Cuenta(Numero_cuenta, Saldo, Moneda, Cliente, Limite_diario) "+
                "values('%s','%s','%s','%s','%s')";
        sql=String.format(sql,cuenta.getNumero(),cuenta.getSaldo(),cuenta.getMoneda(),cuenta.getPersona().getCedula(),cuenta.getLimite_remoto());
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("Ya existe");
        }
     }
    private Cuenta cuenta(ResultSet rs) throws SQLException {
        return new Cuenta(rs.getNString("Numero_cuenta"),rs.getDouble("Saldo"),rs.getNString("Moneda"),new Persona(rs.getNString("Cedula"),rs.getNString("Nombre"),rs.getNString("Telefono")),rs.getDouble("Limite_diario")); //To change body of generated methods, choose Tools | Templates.
    }

}
