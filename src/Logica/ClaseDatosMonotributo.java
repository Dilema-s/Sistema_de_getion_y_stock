/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Mati_ferrero
 */
public class ClaseDatosMonotributo {
    
    private String razonSocial;
    private long cuit;
    private long cai;
    private Date inicioActividades;
    private String calle;
    private String numero;
    private String dpto;
    private int piso;
    private String localidad;
    private String provincia;
    private int cp;
    
    
    public ClaseDatosMonotributo (){
    
    
    }
    
    
    
    public MensajeError Validar (){
        
        MensajeError v = new MensajeError();
        
        Pattern patron = Pattern.compile("^[A-Z a-z\\ñ\\.\\&\\@\\-\\_]{2,150}");
        Matcher coincidencia = patron.matcher(getRazonSocial());
        if (!coincidencia.find()){
            v.setResultado(false);
            v.mensaje_error("Nombre de Razon social Incorrecto\n");
        }else {
            v.setResultado(true);
        }
        
        
        patron = Pattern.compile("^[A-Z a-z\\ñ\\.]{2,50}");
        coincidencia = patron.matcher(getCalle());
        if (!coincidencia.find()){
            v.setResultado(false);
            v.mensaje_error("Nombre de calle Incorrecto\n");   
        }
        
         patron = Pattern.compile("^[1-9]{1,6}");
        coincidencia = patron.matcher(getNumero());
        if (!coincidencia.find()){
            v.setResultado(false);
            v.mensaje_error("Numero de calle Incorrecta\n");
        }
        
        
        if (!"".equals(getDpto())){
            patron = Pattern.compile("^[A-Za-z]{0,2}");
            coincidencia = patron.matcher(getDpto());
            if (!coincidencia.find()){
                v.setResultado(false);
                v.mensaje_error("Departamento incorreto\n");
            }
        }
        
        
        patron = Pattern.compile("^[A-Z a-z\\ñ\\.]{2,50}");
        coincidencia = patron.matcher(getLocalidad());
        if (!coincidencia.find()){
            v.setResultado(false);
            v.mensaje_error("Nombre localidad no valida\n");
        }
       
        return v;
    }
    
    
    
    
    
    
    
    
    

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public long getCuit() {
        return cuit;
    }

    public void setCuit(long cuit) {
        this.cuit = cuit;
    }

    public long getCai() {
        return cai;
    }

    public void setCai(long cai) {
        this.cai = cai;
    }

    public Date getInicioActividades() {
        return inicioActividades;
    }

    public void setInicioActividades(Date inicioActividades) {
        this.inicioActividades = inicioActividades;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDpto() {
        return dpto;
    }

    public void setDpto(String dpto) {
        this.dpto = dpto;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }
    
    
    
    
    
}
