/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca.Presentacion.Cajero;

import banca.Logic.Cuenta;
import banca.Logic.Persona;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Escinf
 */
public class Model{
    Persona current;

    public Model() {
        this.reset();
    }
    
    public void reset(){
        setCurrent(new Persona());        
    }
    
    public Persona getCurrent() {
        return current;
    }

    public void setCurrent(Persona current) {
        this.current = current;
        
    }
}
