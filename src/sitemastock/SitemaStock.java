/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitemastock;

import Utilidades.*;
import java.util.Locale;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Nati
 * 
 */
public class SitemaStock extends Application {
    
    static private Stage ventana;// Variable para futuras referencias a este Stage, del tipo static
      public static String parameters;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        ventana = stage; // referencia del Stage para cargar los diferentes escenarios posteriores
        
        parameters = getParameters().getNamed().toString();
        
        Scene scene = new Scene(root, 500,500);
        stage.getIcons().add(new Image("archivo.png"));
        stage.setScene(scene);
        stage.setTitle("Sistema de Stock");
        stage.setResizable(false);
        Locale.getDefault();
  
        stage.setOnCloseRequest( e -> {
            e.consume(); // Consume el evento para que JavaFx no cierre automaticamente el Stage permitiendo q se ejecute el metodo CerrarCruz
            CerrarCruz ();
            }   
        );
        stage.show();  
         
    }
    
    /*
    Metodo para cerrar la ventana por medio de la X, sin perder informacion. 
    */
    private void CerrarCruz()   {
        boolean salir;
        
        salir = MensajeDeConfirmacion.show("¿Esta seguro que desea salir?","Confirmación","Si", "No");
        if (salir){  
            Conexion con = new Conexion ();
            con.terminarConexion();
            
            
            /*Realizar tareas de limpieza aquí, 
             como guardar archivos o liberar recursos*/
            
            ventana.close();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
       
    }
    
}
