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
public class Producto {
    
    private String nombre;
    private int id_producto;
    private String descripcion;
    private double precio;
    private Proovedor proovedor;
    private Stock stock;
    
    
    
   
    
    
    public Producto(String nombre,String descripcion,double precio, Proovedor proovedor, int stock){
        Stock s = new Stock(stock);
        s.setId_stock(this.getId_producto());
        this.stock = s;
        
        this.proovedor = proovedor;
        
        
        setNombre(nombre);
        
        setDescripcion(descripcion);
        setPrecio(precio);
        
        
    }
    
    public MensajeError Validar (){
        
        MensajeError v = new MensajeError();
        
        Pattern patron = Pattern.compile("[a-z A-Z\\ñ]{3,150}");
        Matcher coincidencia = patron.matcher(getNombre());
        if (!coincidencia.find()){
            v.setResultado(false);
            v.mensaje_error("Nombre Incorrecto\n");
        }else {
            v.setResultado(true);
        }
        
        coincidencia = patron.matcher(getDescripcion());
        if (!coincidencia.find()){
            v.setResultado(false);
            v.mensaje_error("Descripcion con caracteres incorrectos\n");
            
        }
        
        
        
       
        
     
        
        return v;
    }
    
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setCodigo(int id) {
        this.id_producto = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    

    public int getStock() {
        return stock.getStock();
    }

    public void setStock(int stock) {
        this.stock.setStock(stock);
    }
    
    public Stock get_Stock (){
        return this.stock;
    }

    /**
     * 
     * @return devuelve la razon social del proovedor de este producto
     */
    public String getProovedor() {
        return proovedor.getRazon_social();
    }
    /**
     * 
     * @return devuelve el objeto proovedor relacionado al producto
     */
    public Proovedor get_Proovedor() {
        return proovedor;
    }

    public void setProovedor(Proovedor proovedor) {
        this.proovedor = proovedor;
    }

   
    
    
    
    
    
}
