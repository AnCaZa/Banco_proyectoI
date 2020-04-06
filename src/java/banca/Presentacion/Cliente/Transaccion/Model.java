/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca.Presentacion.Cliente.Transaccion;


import banca.Logic.Cuenta;
import banca.Logic.CuentaAmiga;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Escinf
 */
public class Model{
    List<Cuenta> cuentas;
    Cuenta seleccionado;
    List<CuentaAmiga> cuentasamigas;
    Cuenta seleccionaamiga;

    public Cuenta getSeleccionaamiga() {
        return seleccionaamiga;
    }

    public void setSeleccionaamiga(Cuenta seleccionaamiga) {
        this.seleccionaamiga = seleccionaamiga;
    }

    public List<CuentaAmiga> getCuentasamigas() {
        return cuentasamigas;
    }

    public void setCuentasamigas(List<CuentaAmiga> cuentasamigas) {
        this.cuentasamigas = cuentasamigas;
    }

    public Model() {
        this.reset();
    }

    public void reset(){ 
        List<Cuenta> rows = new ArrayList<>();   
        List<CuentaAmiga> rowsamigas = new ArrayList<>();  
        seleccionado=null;  
        this.setCuentas(rows);
        this.setCuentasamigas(rowsamigas);
    }
    
    public void setCuentas(List<Cuenta> cuentas){
        this.cuentas =cuentas;    
    }

     public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public Cuenta getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Cuenta seleccionado) {
        this.seleccionado = seleccionado;
    }
}
