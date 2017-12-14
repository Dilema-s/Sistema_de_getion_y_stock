/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
import java.util.regex.*;
import javafx.beans.property.SimpleStringProperty;



/**
 *
 * @author Nati
 */
public class Persona {
    
    private String  apellido, direccion, mail,DNI, telefono;
    
    private final SimpleStringProperty nombre = new SimpleStringProperty("");
  
    
    
    
    
    public Persona(){
        setNombre("");
        setApellido("");
        setDireccion("");
        setMail("@");
        setTelefono("");
        setDNI("");
    }
    
    

    
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
        
        patron = Pattern.compile("(\\d{7,8})|(\\d{1,2}.\\d{3}.\\d{3})");
        coincidencia = patron.matcher(getDNI());
        if (!coincidencia.find()){
            v.setResultado(false);
            v.mensaje_error("Dni Incorrecto\n");
        }
        
        patron = Pattern.compile("[A-Za-z ./\\ñ]{5,16}(\\d{2,5})?([ a-z\\d]{9})?");
        coincidencia = patron.matcher(getDireccion());
        if (!coincidencia.find()){
            v.setResultado(false);
            v.mensaje_error("Direccion Incorrecta\n");
        }
        
        patron = Pattern.compile("\\+?\\d{1,4}?[- .]?\\(?(?:\\d{2,3})?\\)?[- .]?\\d\\d\\d[\\d]?[\\d]?[- .]?\\d\\d[\\d]?[\\d]?[\\d]?[\\d]?[\\d]?[\\d]?[\\d]?[\\d]?");
        coincidencia = patron.matcher(getTelefono());
        if (!coincidencia.find()){
            v.setResultado(false);
            v.mensaje_error("Telefono Incorrecto\n");
        }
        
        patron = Pattern.compile("[_a-z0-9-\\ñ]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,3})");
        coincidencia = patron.matcher(getMail());
        if (!coincidencia.find()){
            v.setResultado(false);
            v.mensaje_error("Mail Incorrecto\n");
        }
        
        return v;
    }
    
    
    
    
    public String getNombre() {
        return this.nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String email) {
        this.mail = email;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
   
    
    
    
    
}
