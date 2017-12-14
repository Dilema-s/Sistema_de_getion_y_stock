/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitemastock;

import Logica.Administrador;
import Logica.Persona;
import Utilidades.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Text;
import javafx.stage.Stage;




/**
 * FXML Controller class
 *
 * @author Nati
 */
public class LoginController implements Initializable {
    
    @FXML private Button bt1; 
    
    @FXML private PasswordField pass;
   
    @FXML private TextField usuario;
    
    @FXML private Text mensaje;
    
    static private int cont;
    
    Conexion con = new Conexion();
 
    private static Persona user;

    static private boolean esAdmin;
 
 
 
 
    /*
    Metodo para corroborar el login y acceder a nuestra proxima ventana.
    */
    @FXML protected void Login (ActionEvent event) {
        Stage stage ; // Declaramos una variable Stage que nos servira para colocar una escena nueva       
        Parent root;  // Declaramos una variable Parent para referenciar nuestro nuevo LayoutPane 

        if (cont < 3) { // cuenta los intentos de login si son mas que tres cierra el programa
            
            if (" ".equals(usuario.getText()) || " ".equals(pass.getText())){ // controla que el usuario, ni la contraseña sean nulos 
                
                mensaje.setText("Usuario y/o contraseña incorrecta.");
                cont = cont + 1;  
                usuario.setText("");
                pass.setText("");             

                 } else {
                    boolean aux=false;
                    ValidarUsuario v = Conexion.validarUsuario(usuario.getText(), pass.getText()); // busca el usuario en la BD y devuelve un true y el usuario, si lo encuentra
                    if (v!=null){aux =true;}
                    if (aux){  
                        
                        esAdmin = v.getUser().getClass().equals(Administrador.class);   //si es administrador seteamos la variable esAdmin en true;  
                        LoginController.user = v.getUser(); //seteamos el usuario q ingresa al sistema
                        
                        try {                          
                            
                            stage = (Stage) bt1.getScene().getWindow();
                            root = FXMLLoader.load(getClass().getResource("VentanaPrincipal.fxml")); // Referenciamos nuestra variable con el LayoutPane que queramos colocar en nuestro Escena                                             
                            Scene scene = new Scene(root, 1200, 600); // Crea un Scene con el panel y le da valores al whidth y height
                            stage.setScene(scene); //setea la escena a la ventana
                            stage.show(); // muestra la ventana

                        } catch (IOException ex) {
                           MensajeAlerta.show("Se produjo un error inesperado, cierre y abra el programa nuevamente", "No cargo el Stage");
                        }
                    }else {
                        mensaje.setText("Usuario y/o contraseña incorrecta.");
                        cont = cont + 1;  
                        usuario.setText("");
                        pass.setText("");  

                    
                    }
                }
            

            } else {
             
                MensajeAlerta.show("Demasiados intentos, comuniquese con el Administrador.", "Registro de Usuario");           
                mensaje.setText("Demasiados intentos, comuniquese con el Administrador");
                stage = (Stage) bt1.getScene().getWindow(); // Referenciamos nuestra variable con el Stage, el cual sacamos la referencia desde el Button1
                stage.close();         
             }  
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cont = 0;
        bt1.setTooltip(new Tooltip ("Bienvenido!!"));
        Conexion.conexionMySql();
       
       
       
       
   
		
		  
        
        
    }   
    
    static public  Persona getUsuario (){
        
        return user;
    }
    
    static public boolean esAdmin (){
        
        return esAdmin;
    }
    
}
