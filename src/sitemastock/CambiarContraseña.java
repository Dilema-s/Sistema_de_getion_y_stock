/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitemastock;

import Logica.Usuario;
import Utilidades.Conexion;
import Utilidades.MensajeAlerta;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mati F
 */
public class CambiarContraseña implements Initializable {
     
     @FXML private TextField text1;
     
     @FXML private PasswordField text2;
     
     @FXML private PasswordField text3;
     
     @FXML private String usuarioViejo;
     
     @FXML private Label label;
     
     private Usuario usuario;
    
    
    @FXML protected void modificarDatos (){ 
        usuario.setUsuario(text1.getText());
        usuario.setContraseña(text3.getText());
        if (usuario.Validar().getResultado()){
            if (text2.getText().equals(text3.getText())){
                Conexion c = new Conexion();
                c.conexionMySql(); // comienza la conexion con la base de datos
                c.modificarUsuario(text1.getText(), usuarioViejo, text2.getText());
                cancelar();       
            }else {
                MensajeAlerta.show("Vuelva a ingresar la contraseña", "Ingreso de contraseña incorrecto");
                text2.setText("");
                text3.setText("");
            }
        }else {
            label.setText(usuario.Validar().toString());
        }
    }
    
    @FXML protected void cancelar (){
        Stage stage ; // Declaramos una variable Stage que nos servira para colocar una escena nueva       
        stage = (Stage) text1.getScene().getWindow(); // Referenciamos nuestra variable con el Stage, el cual sacamos la referencia desde el text
        stage.close(); // Cerramos la Ventana
        
    
    } 
    
   
    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    
    public void initData1(Usuario u) {
        text1.setText(u.getUsuario());
        this.usuarioViejo = u.getUsuario();
        usuario = u;
        
    }
    
    
    
}
