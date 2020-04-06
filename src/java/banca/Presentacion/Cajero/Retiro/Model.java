/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca.Presentacion.Cajero.Retiro;

import banca.Presentacion.Cajero.Deposito.*;
import banca.Presentacion.Cajero.*;
import banca.Logic.Cuenta;
import banca.Logic.Persona;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Escinf
 */
public class Model{
    List<Cuenta> cuentas;
    Cuenta current;

    public Model() {
        this.reset();
        
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
    
    public void reset(){
        setCurrent(new Cuenta()); 
         cuentas = null;
    }

    public Cuenta getCurrent() {
        return current;
    }

    public void setCurrent(Cuenta current) {
        this.current = current;
    }
    

}
