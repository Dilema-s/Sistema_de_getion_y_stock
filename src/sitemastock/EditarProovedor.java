/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitemastock;

import Logica.Proovedor;
import Utilidades.Conexion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mati_ferrero
 */
public class EditarProovedor implements Initializable {

    @FXML private TextField razon_social;
    
    @FXML private TextField direccion;
    
    @FXML private TextField telefono;
    
    @FXML private TextField mail;
    
    @FXML private Label label1;
    
    @FXML private Proovedor p ;
 
    @FXML private int id ;
    
    
    @FXML protected void volver (){
    Stage stage ; // Declaramos una variable Stage que nos servira para colocar una escena nueva       
    stage = (Stage) mail.getScene().getWindow(); // Referenciamos nuestra variable con el Stage, el cual sacamos la referencia desde el text
    stage.close(); // Cerramos la Ventana

 } 
    
 
 @FXML protected void modificarDatos (){ 
        
        p.setId_proovedor(id);
        p.setRazon_social(razon_social.getText());
        p.setDireccion(direccion.getText());
        p.setTelefono(telefono.getText());
        p.setMail(mail.getText());
     
        if (p.Validar().getResultado()){
           
                
                Conexion.editarProovedor(p,id);
                volver();       
            
        }else {
            label1.setText(p.Validar().toString());
        }
        
    }
    
    
    
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }   
    
    public void initData (Proovedor p){ 
                this.id = p.getId_proovedor();
                this.p = p;
                razon_social.setText(p.getRazon_social());
                telefono.setText(p.getTelefono());
                direccion.setText(p.getDireccion());
                mail.setText(p.getMail());

    }
    

}