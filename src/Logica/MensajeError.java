/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.LinkedList;

/**
 *
 * @author Nati
 */
 public class MensajeError {
    
    LinkedList<String> listaResultados = new LinkedList();
    private boolean resultado ;
    private boolean defoult;
    
    public MensajeError () {
        setResultado(false);
        defoult = true;
    
    }

    
    
    public LinkedList<String> getListaResultados() {
        return listaResultados;
    }

    public void mensaje_error (String mensaje) {
          if (defoult == true){
            listaResultados.clear();
            defoult = false;
        }
        this.listaResultados.add(mensaje);
    }

    public boolean getResultado() {
        return resultado;
    }

    public void setResultado(boolean resultado) {
        if (defoult == true){
            listaResultados.clear();
            if(resultado == true) {
                listaResultados.add("Operación correcta");
                     }else{
                listaResultados.add("Operación con errores");
            }
        }
       this.resultado = resultado;
        
    }

    @Override
    public String toString() {
        String mensaje = "";
        for (int x=0;x<listaResultados.size();x++){
            mensaje = mensaje + listaResultados.get(x);
        }
        return  mensaje;
    }

   
    
    
}
