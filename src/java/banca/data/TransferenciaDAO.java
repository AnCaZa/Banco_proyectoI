/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca.data;

import banca.Logic.Model;
import banca.Logic.Transferencia;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author andre
 */
public class TransferenciaDAO {
    
    
      public void TransaccionAdd(Transferencia transferencia,RelDatabase db) throws Exception{
        String sql="insert into Transferencia (Fecha, Monto, Moneda, Descripcion, Cuenta_origen, Cuenta_destino, Tipo) "+
                "values('%s','%s','%s','%s','%s','%s','%s')";
        sql=String.format(sql,transferencia.getFecha(),transferencia.getMonto(),transferencia.getMoneda(),transferencia.getDescripcion(),transferencia.getCuentaorigen().getNumero(),transferencia.getCuentadestino().getNumero(),transferencia.getTipo());
        int count=db.executeUpdate(sql);
        if (count==0){
            throw new Exception("Ya existe");
        }
     }
         public List<Transferencia> TransferenciaSearchremota(String Numeroorigen,RelDatabase db){
       Format formatter = new SimpleDateFormat("yyyy-MM-dd");	
    banca.Logic.Model model = banca.Logic.Model.instance();
             List<Transferencia> transferencias = new ArrayList<>();
        try {
            String sql="select * "+
                    "from Transferencia " +
                    "where Cuenta_origen='%s' AND Tipo='%s' AND Fecha='%s' ";
           sql=String.format(sql,Numeroorigen,"Remoto", formatter.format(new Date()));
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                transferencias.add(transferencia(rs,model));
            }
        } catch (SQLException ex) { }
        return transferencias ;
    }
         
        public List<Transferencia> Transferenciasearchfecha(String Numero,String FechaA,String FechaB,RelDatabase db){
      
    banca.Logic.Model model = banca.Logic.Model.instance();
             List<Transferencia> transferencias = new ArrayList<>();
        try {
            String sql="select * from Transferencia  WHERE ( Cuenta_origen='%s' OR Cuenta_destino='%s' )  AND  (CAST(Fecha AS DATE) "+
                    "BETWEEN CAST('%s' AS DATE) AND CAST('%s' AS DATE)) ";
           sql=String.format(sql,Numero,Numero, FechaA,FechaB);
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                transferencias.add(transferencia(rs,model));
            }
        } catch (SQLException ex) { }
        return transferencias ;
    }
        
     public List<Transferencia> Transferenciasearch(String Numero,RelDatabase db){

    banca.Logic.Model model = banca.Logic.Model.instance();
             List<Transferencia> transferencias = new ArrayList<>();
        try {
            String sql="select * from Transferencia  WHERE Cuenta_origen='%s' or Cuenta_destino='%s' "; 
           sql=String.format(sql,Numero,Numero);
            ResultSet rs =  db.executeQuery(sql);
            while (rs.next()) {
                transferencias.add(transferencia(rs,model));
            }
        } catch (SQLException ex) { }
        return transferencias ;
    }
         public Transferencia transferencia(ResultSet rs,Model model) throws SQLException
         {
         return new Transferencia(rs.getInt("Identificador"),rs.getNString("Fecha"),rs.getDouble("Monto"),rs.getNString("Moneda"),rs.getNString("Descripcion"),model.CuentaGet(rs.getNString("Cuenta_origen")),model.CuentaGet(rs.getNString("Cuenta_destino")),rs.getNString("Tipo"));
         }
}
