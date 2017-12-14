/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Utilidades.MensajeAlerta;
import java.util.LinkedList;

/**
 *
 * @author Mati_ferrero
 */
public class CuentaCorriente {
    
    private Cliente cliente;
    LinkedList<Venta> lista_ventas;
    
    
    public CuentaCorriente (Cliente cliente){
     setCliente(cliente);
     lista_ventas = new LinkedList<Venta>();
     
    
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        if (cliente != null){this.cliente = cliente;}
        else { MensajeAlerta.show("El cliente no puede ser nulo.\nSeleccione un cliente ya registrado o agregue uno nuevo", "No se ha asociado un cliente a la cuenta corriente");}
    }
    
    public boolean agregarVenta (Venta venta){
        if (venta != null){
         getLista_ventas().add(venta);
         return true;
        } else {
            MensajeAlerta.show("No se ha registradola venta.\nPor favor intente nuevamente", "Carga de venta a cuenta corriente");
         return false;}
    }

    public LinkedList<Venta> getLista_ventas() {
        return lista_ventas;
    }

    public void setLista_ventas(LinkedList<Venta> lista_ventas) {
        if (lista_ventas != null){
           
            for(Venta v: lista_ventas){
                agregarVenta(v);
            }
        }
        
    }
    
    
    
}
