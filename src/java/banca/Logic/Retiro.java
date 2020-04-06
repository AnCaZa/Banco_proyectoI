/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca.Logic;

/**
 *
 * @author andre
 */
public class Retiro {
    int Identificador;
    String fecha;
    Cuenta cuenta;
    Double Monto;

    public Retiro(int Identificador, String fecha, Cuenta cuenta, Double monto) {
        this.Identificador = Identificador;
        this.fecha = fecha;
        this.cuenta = cuenta;
        this.Monto = monto;
    }

    public Retiro() {
    }

    public int getIdentificador() {
        return Identificador;
    }

    public void setIdentificador(int Identificador) {
        this.Identificador = Identificador;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Double getMonto() {
        return Monto;
    }

    public void setMonto(Double monto) {
        this.Monto = monto;
    }
    
    
}
