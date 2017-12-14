/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author Mati_ferrero
 */
public class Movimiento {
    public static enum tipoMovimiento {
        EGRESO,INGRESO
    };
    
    private tipoMovimiento tipoM;
    private double valor;
    private String motivo;
    private String cajero;
    
    
    public Movimiento (tipoMovimiento movimiento, double valor,String motivo){   
        setValor(valor);
        setTipoM(movimiento);
        setMotivo(motivo);        
    
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public tipoMovimiento getTipoM() {
        return tipoM;
    }

    public void setTipoM(tipoMovimiento tipoM) {
        this.tipoM = tipoM;
    }

   

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getCajero() {
        return cajero;
    }

    public void setCajero(String cajero) {
        this.cajero = cajero;
    }
    
    
    
    
    
    
}
