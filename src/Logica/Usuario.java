/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Nati
 */
public class Usuario extends Persona {
    private String Usuario;
    private String Contraseña;
    private int id_usuario;
            
    public Usuario (String u, String c,String a, String n){
        super.setApellido(a);
        super.setNombre(n);
        setUsuario(u);
        setContraseña(c);
        
    }

    
    @Override
    public MensajeError Validar (){
        
        MensajeError v = new MensajeError();
        
        Pattern patron = Pattern.compile("[a-z A-Z\\ñ]{3,15}");
        Matcher coincidencia = patron.matcher(getNombre());
        if (!coincidencia.find()){
            v.setResultado(false);
            v.mensaje_error("Nombre Incorrecto\n");
        }else {
            v.setResultado(true);
        }
        
        coincidencia = patron.matcher(getApellido());
        if (!coincidencia.find()){
            v.setResultado(false);
            v.mensaje_error("Apellido Incorrecto\n");
            
        }
        
        patron = Pattern.compile("^\\w{4,15}");
        coincidencia = patron.matcher(getUsuario());
        if (!coincidencia.find()){
            v.setResultado(false);
            if (getContraseña().length() <= 3){
                v.mensaje_error("El usuario no puede ser menor a 4 caracteres\n");
            } else{
                v.mensaje_error("Usuario no valido. Intente nuevamente\n");
            }
        }
        
        
        coincidencia = patron.matcher(getContraseña());
        if (!coincidencia.find()){          
            v.setResultado(false);
            if (getContraseña().length() <= 3){
                v.mensaje_error("La contraseña no puede ser menor a 4 caracteres\n");
            } else{
                v.mensaje_error("Contraseña no valida. Intente nuevamente\n");
            }
            
        }
           
        return v;
    }
    
    
    
    
    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    
    
    
}
