/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitemastock;

import Logica.Administrador;
import Logica.Persona;
import Logica.Usuario;
import Utilidades.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mati F
 */
public class AgregarUsuario implements Initializable {
   
    @FXML Button bt1; 
    
    @FXML TextField apellido; 
    
    @FXML TextField nombre; 
    
    @FXML TextField usuario; 
    
    @FXML PasswordField contraseña; 
    
    @FXML Label mensaje; 
    
    @FXML private ComboBox<String> combo;
    
    

    @FXML protected void volver () {
        Stage stage ; // Declaramos una variable Stage que nos servira para colocar una escena nueva       
        stage = (Stage) bt1.getScene().getWindow(); // Referenciamos nuestra variable con el Stage, el cual sacamos la referencia desde el Button
        stage.close(); // Cerramos la Ventana
} 
    
    @FXML protected void agregar (ActionEvent event){
         Conexion con = new Conexion();
         con.conexionMySql();
         
         boolean aux = true;
         
         
         Usuario usuarioNuevo = new Usuario(usuario.getText(), contraseña.getText(), apellido.getText(), nombre.getText()); //Creamos un suario para usar el metodo validar
     
         if (usuarioNuevo.Validar().getResultado() ){
            
             for (String i:Conexion.nombre_Usuarios()){
                 if (usuario.getText().equals(i)){
                     MensajeAlerta.show("Nombre de usuario repetido, elija otro", "Validar nombre de Usuario");  
                     aux = false;
                 }                 
             }
             if (aux){
                    con.insertarUsuario(nombre.getText(), apellido.getText(), usuario.getText(), contraseña.getText(),combo.getSelectionModel().getSelectedItem());  
                    mensaje.setText("");
                    volver();
              }
         } else {
             mensaje.setText("");
             mensaje.setText(usuarioNuevo.Validar().toString());                 
         }   
     }
     
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listaCombo = combo.getItems();
        listaCombo.add("Usuario del sistema");
        
        listaCombo.add("Administrador del sistema");
        combo.setItems(listaCombo);
        combo.getSelectionModel().selectFirst();
    }    
    
}
