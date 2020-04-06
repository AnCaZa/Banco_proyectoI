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
public class CuentaAmiga {
    int Id;
    Cuenta vinculada;
    Persona persona;

    public CuentaAmiga(int Id, Cuenta vinculada, Persona persona) {
        this.Id = Id;
        this.vinculada = vinculada;
        this.persona = persona;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Cuenta getVinculada() {
        return vinculada;
    }

    public void setVinculada(Cuenta vinculada) {
        this.vinculada = vinculada;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

   
}
