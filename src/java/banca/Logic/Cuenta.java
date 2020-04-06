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
public class Cuenta {
    String Numero;
    double Saldo;
    String Moneda;
    Persona persona;
    double Limite_remoto;

    public Cuenta(String Numero, double Saldo, String Moneda, Persona persona, double Limite_remoto) {
        this.Numero = Numero;
        this.Saldo = Saldo;
        this.Moneda = Moneda;
        this.persona = persona;
        this.Limite_remoto = Limite_remoto;
    }

    public double getSaldo() {
        return Saldo;
    }

    public void setSaldo(double Saldo) {
        this.Saldo = Saldo;
    }

    public double getLimite_remoto() {
        return Limite_remoto;
    }

    public void setLimite_remoto(double Limite_remoto) {
        this.Limite_remoto = Limite_remoto;
    }

  
    public Cuenta() {

    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

   
    

    public String getMoneda() {
        return Moneda;
    }

    public void setMoneda(String Moneda) {
        this.Moneda = Moneda;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

   
}
