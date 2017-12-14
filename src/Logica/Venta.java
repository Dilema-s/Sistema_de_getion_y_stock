/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;
import java.util.Date;




/**
 *
 * @author Nati
 */
public class Venta {
    private int id_venta;
    private double total;
    private float subtotal;
    private float descuento;
    private ArrayList<Producto> producto;
    private Date fecha;
    private Cliente cliente;
    private Usuario usuario;
    
   
    
    
    public Venta (double total,Cliente cliente,ArrayList<Producto> producto, Date fecha, Usuario usuario){
       setTotal(total);
       setFecha(fecha);
       setCliente(cliente);
       setProducto(producto);
       setUsuario(usuario);   
       
    }
    
    public Venta (){
        
    }

    public MensajeError validar (){
        MensajeError v = new MensajeError();
        
       
        if (this.total <= 0){
            v.setResultado(false);
            v.mensaje_error("Total negativo no válido\n");
        }else {
            v.setResultado(true);
        }
        
        if (this.subtotal <= 0){
            v.setResultado(false);
            v.mensaje_error("Subtotal negativo no válido\n");
        }else {
            v.setResultado(true);
        }
        
        if (this.descuento > 0){
            v.setResultado(false);
            v.mensaje_error("Descuento positivo no válido\n");
        }else {
            v.setResultado(true);
        }
        
        
        return v;
    }
    
    
    
    
    
    
    
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public ArrayList<Producto> getProducto() {
        return producto;
    }

    public void setProducto(ArrayList<Producto> producto) {
        this.producto = producto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public Usuario getObjetoUsuario() {
        return usuario;
    }
    
    public String getUsuario() {
        
        return usuario.getNombre() + ", " +usuario.getApellido();
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }
    
    
            
}
