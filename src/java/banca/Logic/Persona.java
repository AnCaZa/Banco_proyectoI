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
public class Persona{
    String cedula;
    String nombre;
    String Telefono;

    public Persona(String cedula, String nombre, String Telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.Telefono = Telefono;
    }

    public Persona() {
         this.cedula = "";
        this.nombre = "";
        this.Telefono = "";
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }
    
    
    
    
}
