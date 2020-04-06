/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca.Presentacion.Cliente.Cuenta_amiga;

import banca.Logic.CuentaAmiga;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andre
 */
public class Model{
    List<CuentaAmiga> cuentas;
    CuentaAmiga seleccionado;

    public Model() {
        this.reset();
    }

    public void reset(){ 
        List<CuentaAmiga> rows = new ArrayList<>();        
        seleccionado=null;  
        this.setCuentasamigas(rows);
    }
    
    public void setCuentasamigas(List<CuentaAmiga> cuentas){
        this.cuentas =cuentas;    
    }

     public List<CuentaAmiga> getCuentasamigas() {
        return cuentas;
    }

    public CuentaAmiga getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(CuentaAmiga seleccionado) {
        this.seleccionado = seleccionado;
    }
}

