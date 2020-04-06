/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banca.Presentacion.login;

import banca.Logic.Login_usuario;

/**
 *
 * 
 */
public class Model {
    Login_usuario current;

    public Model() {
        this.reset();
    }
    
    public void reset(){
        setCurrent(new Login_usuario());        
    }
    
    public Login_usuario getCurrent() {
        return current;
    }

    public void setCurrent(Login_usuario current) {
        this.current = current;
    }
   
}
