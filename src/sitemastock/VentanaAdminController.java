/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitemastock;

import Logica.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import Utilidades.*;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Mati F
 */
public class VentanaAdminController implements Initializable {
   
    @FXML
    private TableView<Persona> tableView;
    @FXML
    private Button conectar;
    
    /**
     * Crea un objeto conexion y ejecuta la conexion a traves del metodo conexionMySql()
     */
    @FXML protected void conectar (){ 
        Conexion c = new Conexion();
        c.conexionMySql(); // comienza la conexion con la base de datos
        
        
      // el metodo getItems() devuelve la observableList del la tabla la cual la pasamos como parametro al metodo cargardatos() 
      // luego el metodo setItems vuelve a cargar en la tabla la observableList pero ya con los datos de la base de datos !! Genial!
        try {
            tableView.getItems().clear();
            tableView.setItems(c.cargarDatos(tableView.getItems())); 
        } catch (Exception e) {
            MensajeAlerta.show("No se pudo Cargar!!", "Cargar datos");
        }
    }
            
    @FXML protected void ventanaAgregar (ActionEvent event) throws IOException{
        
        Stage stage = new Stage(); // Declaramos una variable Stage que nos servira para colocar una escena nueva 
        Parent root;  // Declaramos una variable Parent para referenciar nuestro nuevo LayoutPane 
    
        stage.initModality(Modality.APPLICATION_MODAL); // Visualiza el stage y bloque cualquier otro.
        stage.setTitle("Agregar usuario nuevo"); 
        stage.setMinWidth(450); 
        stage.setResizable(false);
       
        root = FXMLLoader.load(getClass().getResource("AgregarUsuario.fxml")); // Referenciamos nuestra variable con el LayoutPane que queramos colocar en nuestro Escena

        Scene scene = new Scene(root,600,400); // Declaramos e instanciamos nuestra nueva Escena y le pasamos como parametro nuestro LayoutPane y el tamaño. 

        stage.setScene(scene); // Seteamos nuestra Escena dentro de nuestra ventana
        stage.showAndWait(); // Mostramos la Ventana
        conectar();
        
} 
    
     @FXML protected void volver (ActionEvent event) throws IOException{
        Stage stage ; // Declaramos una variable Stage que nos servira para colocar una escena nueva       
        Parent root;  // Declaramos una variable Parent para referenciar nuestro nuevo LayoutPane 
     
        stage = (Stage) conectar.getScene().getWindow(); // Referenciamos nuestra variable con el Stage, el cual sacamos la referencia desde el Button1
        root = FXMLLoader.load(getClass().getResource("VentanaPrincipal.fxml")); // Referenciamos nuestra variable con el LayoutPane que queramos colocar en nuestro Escena

        Scene scene = new Scene(root,1200,600); // Declaramos e instanciamos nuestra nueva Escena y le pasamos como parametro nuestro LayoutPane y el tamaño. 

        stage.setScene(scene); // Seteamos nuestra Escena dentro de nuestra ventana
        stage.show(); // Mostramos la Ventana
} 
        
   @FXML protected void eliminar (ActionEvent event) {
   
         
        
        
       Usuario m ;
        
        
        if(tableView.getSelectionModel().getSelectedItem()  == null){
            MensajeAlerta.show("Por Favor seleccione un usuario de la lista", "No se ha selecionado usuario");
        } else {    
           
//                if (true){
//                    MensajeAlerta.show("No esta permitido elminar el usuario, ya que es el administrador", "Eliminar Administrador");
//                }else {
            m = (Usuario)tableView.getSelectionModel().getSelectedItem();
            if (MensajeDeConfirmacion.show("Esta seguro que desea eliminar al usuario: " + m.getUsuario(), "Eliminar Usuario", "Aceptar", "Cancelar")){

                Conexion c = new Conexion(); 
                c.conexionMySql();      
                c.borrarUsuario(m.getId_usuario());// Elimina el Item de la base de datos
                conectar();
            }
                
            
        }
   }
   
   @FXML protected void modificar (ActionEvent event)  {
   
        Usuario e;

        if(tableView.getSelectionModel().getSelectedItem()  == null){
            MensajeAlerta.show("Por Favor seleccione un usuario de la lista", "No se ha selecionado usuario");
        } else {

                // Creamos una nueva ventana para modificar los datos
                
                Stage stage = new Stage(); // Declaramos una variable Stage que nos servira para colocar una escena nueva 

                stage.initModality(Modality.APPLICATION_MODAL); // Visualiza el stage y bloque cualquier otro.
                stage.setTitle("Agregar usuario nuevo"); // titulo de la ventana
                stage.setMinWidth(350); //ancho minimo
                stage.setResizable(false); // no ajustable la ventana

                FXMLLoader loader = new FXMLLoader(getClass().getResource("CambiarContraseña.fxml")); // carga el fxml
                Parent root; //declara un objeto Parent
                
                try {
                    root = loader.load(); // Carga el panel 
                    CambiarContraseña controller = loader.getController(); // referencia la controlador de la clase
                    e = (Usuario)tableView.getSelectionModel().getSelectedItem();
                    controller.initData1(e); // ejecuta el metodo initData que envia com parametro el usuario a modificar   
                    Scene scene = new Scene(root, 600, 400); // Crea un Scene con el panel y le da valores al whidth y height
                    stage.setScene(scene); //setea la escena a la ventana
                } catch (IOException ex) {
                    MensajeAlerta.show("Se produjo un error inesperado", "No cargo el Stage");
                }
                stage.showAndWait(); // muestra la ventana
               
                
                conectar();
                
            
        }
   }
        
   
   
   
   @FXML protected void datosMonotributo (ActionEvent event)  {

        // Creamos una nueva ventana para modificar los datos

        Stage stage = new Stage(); // Declaramos una variable Stage que nos servira para colocar una escena nueva 

        stage.initModality(Modality.APPLICATION_MODAL); // Visualiza el stage y bloque cualquier otro.
        stage.setTitle("Agregue los datos de su Monotributo"); // titulo de la ventana
       
        stage.setResizable(false); // no ajustable la ventana

        

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DatosMonotributo.fxml")); // carga el fxml
        Parent root; //declara un objeto Parent
            root = loader.load(); // Carga el panel 
            Scene scene = new Scene(root,832 , 611); // Crea un Scene con el panel y le da valores al whidth y height
            stage.setScene(scene); //setea la escena a la ventana
            stage.showAndWait(); // muestra la ventana
             
        } catch (IOException ex) {
            MensajeAlerta.show("Se produjo un error inesperado", "No cargo el Stage");
        }  
   }
        
    

   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
     conectar();
     
    }

   
    
    }
    
    
    
    

