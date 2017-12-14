/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Utilidades.Conexion;
import java.util.Calendar;

/**
 *
 * @author Nati
 */
public class Stock  {
    private int stock;
    private int stockMinimo;
    private int id_stock;
    
    public Stock(int s) {
        setStock(s);      
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock > 0){
            this.stock = stock;
        } else {
            this.stock = 0;
        }
        
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public int getId_stock() {
        return id_stock;
    }

    public void setId_stock(int id_stock) {
        this.id_stock = id_stock;
    }

    
    
    
    
    
    public int calculoStockMinimo (Producto p,int plazoEntregaAproxProovedor){
        //(consumo maximo mensual / 30 dias)  x dias de plazo de entrega
        // para sacar el consumo mensual habria q tener un metodo en Caja diaria
        if (plazoEntregaAproxProovedor <= 0){
            plazoEntregaAproxProovedor=1;
        }
        
        
        int[] meses = new int[12];
        int año = Calendar.YEAR-1;
        int [] parametros = Conexion.dibujarGrafico(p, meses, año);
        int mes = Calendar.MONTH;
        
        if (parametros[mes] <= 0){
            parametros[mes] = 1;
        }
        
        int stockMin =  (parametros[mes]/26) * plazoEntregaAproxProovedor;
       
        if (stockMin < 1){
            stockMin = 1;
        }
        
        this.stockMinimo = stockMin;
        return stockMin;
    }
    
    


}
