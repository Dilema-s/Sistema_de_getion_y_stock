/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitemastock;

import Logica.CajaDiaria;
import Logica.Movimiento;
import Utilidades.Conexion;
import Utilidades.MensajeAlerta;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mati_ferrero
 */
public class CajaDiarialController implements Initializable {
    
    @FXML private DatePicker fecha1;
    
    @FXML private TableView<Movimiento> tabla;
    
    @FXML private Label total;
   
    DecimalFormat df = new DecimalFormat("#.00");
    
    
    
    @FXML private void ventanaMovimientos (){
        
        Stage stage = new Stage(); // Declaramos una variable Stage que nos servira para colocar una escena nueva 

        stage.initModality(Modality.APPLICATION_MODAL); // Visualiza el stage y bloque cualquier otro.

        stage.setResizable(false); // no ajustable la ventana

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Movimiento.fxml")); // carga el archivo fxml
        Parent root; //declara un objeto Parent

        try {
            root = loader.load(); // Carga el panel 
            MovimientoController control = loader.getController();
            control.fechaHoy(fecha1.getValue());

            Scene scene = new Scene(root, 1000, 600); // Crea un Scene con el panel y le da valores al whidth y height
            stage.setScene(scene); //setea la escena a la ventana
        } catch (IOException ex) {
            MensajeAlerta.show("Se produjo un error inesperado", "No cargo el Stage");
        }
        stage.showAndWait(); // muestra la ventana
        actualizar();
    }
    
    
    
    
    @FXML private void actualizar (){
        
        tabla.getItems().clear();
        
        CajaDiaria caja = Conexion.verCajaDiaria(fecha1.getValue());
        
        for (Movimiento m : caja.getListaMovimiento()){
            tabla.getItems().add(m);
        
        }
        
        caja.calculoTotal();
       
        total.setText("$ " + df.format(caja.getTotal()));
    }
    
    
    @FXML private void Volver (){
        
         Stage stage ; // Declaramos una variable Stage que nos servira para colocar una escena nueva       
        Parent root;  // Declaramos una variable Parent para referenciar nuestro nuevo LayoutPane 
       
        try{
            stage = (Stage) tabla.getScene().getWindow(); // Referenciamos nuestra variable con el Stage, el cual sacamos la referencia desde el radioButton
             if (false){
                 stage.close();
             }else {

                root = FXMLLoader.load(getClass().getResource("VentanaPrincipal.fxml")); // Referenciamos nuestra variable con el LayoutPane que queramos colocar en nuestro Escena

                Scene scene = new Scene(root,1200,600); // Declaramos e instanciamos nuestra nueva Escena y le pasamos como parametro nuestro LayoutPane y el tamaño. 

                stage.setScene(scene); // Seteamos nuestra Escena dentro de nuestra ventana
                stage.show(); // Mostramos la Ventana
             }
        }catch(Exception e){
            MensajeAlerta.show("Error al cargar la ventana", "Volver al menu principal");
        }
        
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
        fecha1.setValue(LocalDate.now());
        
        actualizar();
        
        
  
        
        
        
        
        
        
        
        
        
    }    
    
    
    
    
}
