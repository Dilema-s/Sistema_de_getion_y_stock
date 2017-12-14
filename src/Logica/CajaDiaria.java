/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author Nati
 */
public class CajaDiaria {
    private LocalDate fecha;
    private LinkedList<Movimiento> listaMovimiento;
    private double total = 0;
    
    
    public CajaDiaria (LocalDate fecha){
       setFecha(fecha);
       setListaMovimiento(new LinkedList<>());
       calculoTotal();
    
    }
    
    
    public LocalDate getFecha() {
        return fecha;
    }

    public final void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public final LinkedList<Movimiento> getListaMovimiento() {
        return listaMovimiento;
    }

    public final void setListaMovimiento(LinkedList<Movimiento> listaMovimiento) {
        this.listaMovimiento = listaMovimiento;
    }
    
    public final void setMovimiento(Movimiento movimiento) {
        this.listaMovimiento.add(movimiento);
    }

    public double getTotal() {
        return total;
    }

    public final void calculoTotal() {
        double suma = 0;
        if (!this.listaMovimiento.isEmpty()){
            for (Movimiento m: this.listaMovimiento){
                if (m.getTipoM() == Movimiento.tipoMovimiento.EGRESO){
                    suma = suma - m.getValor();
                } else {
                    suma = suma + m.getValor();
                }
            }
        }
        this.total = suma;
        
    }

    
    
    
    
}
