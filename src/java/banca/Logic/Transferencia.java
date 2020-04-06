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
public class Transferencia {
    int Identificador;
    String fecha;
    Double monto;
    String moneda;
    String Descripcion;
    Cuenta cuentaorigen;
    Cuenta cuentadestino;
    String tipo;

    public Transferencia(int Identificador, String fecha, Double monto, String moneda, String Descripcion, Cuenta cuentaorigen, Cuenta cuentadestino, String tipo) {
        this.Identificador = Identificador;
        this.fecha = fecha;
        this.monto = monto;
        this.moneda = moneda;
        this.Descripcion = Descripcion;
        this.cuentaorigen = cuentaorigen;
        this.cuentadestino = cuentadestino;
        this.tipo = tipo;
    }

    public Transferencia() {
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

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public Cuenta getCuentaorigen() {
        return cuentaorigen;
    }

    public void setCuentaorigen(Cuenta cuentaorigen) {
        this.cuentaorigen = cuentaorigen;
    }

    public Cuenta getCuentadestino() {
        return cuentadestino;
    }

    public void setCuentadestino(Cuenta cuentadestino) {
        this.cuentadestino = cuentadestino;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
