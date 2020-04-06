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
public class Login_usuario implements java.io.Serializable{
    String cedula;
    String contraseña;
   Persona persona;
int tipo;
    public Login_usuario() {
    }

    public Login_usuario(String cedula, String contraseña, Persona persona,int tipo) {
        this.cedula = cedula;
        this.contraseña = contraseña;
        this.persona = persona;
        this.tipo=tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    
}
