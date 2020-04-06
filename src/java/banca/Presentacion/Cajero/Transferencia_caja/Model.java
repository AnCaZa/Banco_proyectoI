/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca.Presentacion.Cajero.Transferencia_caja;

import banca.Presentacion.Cajero.Retiro.*;
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
    Cuenta currentdestino;
    Double Monto;
    String Descripcion;

    public Model() {
        this.reset();
        
    }

    public Double getMonto() {
        return Monto;
    }

    public void setMonto(Double Monto) {
        this.Monto = Monto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public Cuenta getCurrentdestino() {
        return currentdestino;
    }

    public void setCurrentdestino(Cuenta currentdestino) {
        this.currentdestino = currentdestino;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
    
    public void reset(){
        setCurrent(null); 
         cuentas = null;
    }

    public Cuenta getCurrent() {
        return current;
    }

    public void setCurrent(Cuenta current) {
        this.current = current;
    }
    

}
