/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author Mati F
 */
public class Factura {
    
    private Venta venta;
    private long numeroFactura;
    private ClaseDatosMonotributo monotributo;
    private String condicionDeVenta;
    
    
    
    public Factura (Venta v, ClaseDatosMonotributo datosMonotributo, String condicionDeVenta){
        setVenta(v);
        setMonotributo(datosMonotributo);
        setCondicionDeVenta(condicionDeVenta);
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public long getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(long numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public ClaseDatosMonotributo getMonotributo() {
        return monotributo;
    }

    public void setMonotributo(ClaseDatosMonotributo monotributo) {
        this.monotributo = monotributo;
    }

    public String getCondicionDeVenta() {
        return condicionDeVenta;
    }

    public void setCondicionDeVenta(String condicionDeVenta) {
        this.condicionDeVenta = condicionDeVenta;
    }

   
    
    
    
    
}
