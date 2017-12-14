/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import Logica.Persona;


/**
 *
 * @author Mati_ferrero
 */
public class ValidarUsuario {
    private Persona user;
    private boolean validacion = false;
    
    public ValidarUsuario(){
    
    }
    
    public ValidarUsuario (Persona user,boolean validacion){
        setUser(user);
        setValidacion(validacion);
    }
 
     
    public Persona getUser() {
        return user;
    }

    public void setUser(Persona user) {
        this.user = user;
    }

    public boolean isValidacion() {
        return validacion;
    }

    public void setValidacion(boolean validacion) {
        this.validacion = validacion;
    }
    
    
    
    
    
    
}
