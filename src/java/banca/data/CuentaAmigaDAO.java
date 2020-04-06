/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca.data;

import banca.Logic.CuentaAmiga;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andre
 */
public class CuentaAmigaDAO {
      public void CuentaAdd(CuentaAmiga cuentaamiga,RelDatabase db) throws Exception{
        String sql="insert into Cuenta_Vinculada(Vinculada, Persona) "+
                "values('%s','%s')";
        sql=String.format(sql,cuentaamiga.getVinculada().getNumero(),cuentaamiga.getPersona().getCedula());
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("Ya existe");
        }
     }
         public List<CuentaAmiga> CuentaSearch(String cedula,RelDatabase db) throws Exception{
        List<CuentaAmiga> cuentas = new ArrayList<>();
        try {
            String sql="select * "+
                    "from Cuenta_Vinculada cv " +
                    "where cv.Persona='%s' ";
           sql=String.format(sql,cedula);
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                cuentas .add(cuentaamiga(rs));
            }
        } catch (SQLException ex) { }
        return cuentas ;
    } 
    public boolean IsCuentaAmiga(String cedula,String amiga,RelDatabase db){
   
        try {
            String sql="select * "+
                    "from Cuenta_Vinculada  " +
                    "where cv.Persona='%s' AND cv.Vinculada='%s' ";
           sql=String.format(sql,cedula,amiga);
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
               return true;
            }
        } catch (SQLException ex) { }
        return false ;
    } 
         
    public CuentaAmiga cuentaamiga(ResultSet rs) throws SQLException, Exception    
    {  banca.Logic.Model domainModel = banca.Logic.Model.instance();
    return new CuentaAmiga(rs.getInt(1),domainModel.CuentaGet(rs.getNString(2)),domainModel.PersonaGet(rs.getNString(3)));
    }
}
