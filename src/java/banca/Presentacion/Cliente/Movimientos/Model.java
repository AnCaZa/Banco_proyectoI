/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca.Presentacion.Cliente.Movimientos;

import banca.Presentacion.Cliente.Cuentas.*;
import banca.Logic.Cuenta;
import banca.Logic.Deposito;
import banca.Logic.Retiro;
import banca.Logic.Transferencia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Escinf
 */
public class Model{
  
    Cuenta seleccionado;
List<Deposito> depositos;
List<Retiro> retiros;
List<Transferencia> transferencias;
    public Model() {
        this.reset();
    }

    public void reset(){ 
        

        seleccionado=null;  
        this.setDepositos(new ArrayList<>());
      this.setRetiros(new ArrayList<>());
        this.setTransferencias(new ArrayList<>());
    }

    public List<Deposito> getDepositos() {
        return depositos;
    }

    public void setDepositos(List<Deposito> depositos) {
        this.depositos = depositos;
    }

    public List<Retiro> getRetiros() {
        return retiros;
    }

    public void setRetiros(List<Retiro> retiros) {
        this.retiros = retiros;
    }

    public List<Transferencia> getTransferencias() {
        return transferencias;
    }

    public void setTransferencias(List<Transferencia> transferencias) {
        this.transferencias = transferencias;
    }

   
  
    public Cuenta getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Cuenta seleccionado) {
        this.seleccionado = seleccionado;
    }
}
