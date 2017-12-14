/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitemastock;

import Logica.Cliente;
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
 * @author Mati F
 */
public class EditarCliente implements Initializable {
 @FXML protected Label label1;
 
 @FXML protected TextField nombre;
 
 @FXML protected TextField apellido;
 
 @FXML protected TextField DNI;
 
 @FXML protected TextField direccion;
 
 @FXML protected TextField telefono;
 
 @FXML protected TextField email;
 
 @FXML private Cliente cliente ;
 
 @FXML private String dniViejo ;
 
 
 
    
 @FXML protected void volver (){
    Stage stage ; // Declaramos una variable Stage que nos servira para colocar una escena nueva       
    stage = (Stage) email.getScene().getWindow(); // Referenciamos nuestra variable con el Stage, el cual sacamos la referencia desde el text
    stage.close(); // Cerramos la Ventana

 } 
    
 
 @FXML protected void modificarDatos (){ 
        
        cliente.setDNI(DNI.getText());
        cliente.setApellido(apellido.getText());
        cliente.setNombre(nombre.getText());
        cliente.setDireccion(direccion.getText());
        cliente.setTelefono(telefono.getText());
        cliente.setMail(email.getText());
     
        if (cliente.Validar().getResultado()){
           
                Conexion c = new Conexion();
                c.conexionMySql(); // comienza la conexion con la base de datos
                c.modificarCliente(dniViejo,cliente);
                volver();       
            
        }else {
            label1.setText(cliente.Validar().toString());
        }
    }
    
 
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    
    
    public void initData (Cliente c){ 
        this.dniViejo = c.getDNI();
        this.cliente = c;
        nombre.setText(c.getNombre());
        DNI.setText(c.getDNI());
        apellido.setText(c.getApellido());
        telefono.setText(c.getTelefono());
        direccion.setText(c.getDireccion());
        email.setText(c.getMail());
        
    }
}
