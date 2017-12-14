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
public class Proovedor {
    
   
    
    private String razon_social;
    private String direccion;
    private String mail;
    private String telefono;
    private int id_proovedor;
    private int tiempoAproxEntrega;
    
    public Proovedor (){
        
    }

    
    
    public Proovedor(String razon, String dir, String mail, String tel){
        setRazon_social(razon);
        setDireccion(dir);
        setMail(mail);
        setTelefono(tel);
        this.tiempoAproxEntrega = 1;
    }
    
    public MensajeError Validar (){
        
        MensajeError v = new MensajeError();
        
        Pattern patron = Pattern.compile("[a-z A-Z\\ñ]{3,150}");
        Matcher coincidencia = patron.matcher(getRazon_social());
        if (!coincidencia.find()){
            v.setResultado(false);
            v.mensaje_error("Razon social Incorrecta\n");
        }else {
            v.setResultado(true);
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
    
    
    
    
    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
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

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getId_proovedor() {
        return id_proovedor;
    }

    public void setId_proovedor(int id_proovedor) {
        this.id_proovedor = id_proovedor;
    }
   
    
    @Override
    public String toString (){
        
        return getRazon_social();
        
    }

    public int getTiempoAproxEntrega() {
        return tiempoAproxEntrega;
    }

    public void setTiempoAproxEntrega(int tiempoAproxEntrega) {
        this.tiempoAproxEntrega = tiempoAproxEntrega;
    }
    

    

    
    
    
    
    
}
