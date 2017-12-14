/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitemastock;

import Utilidades.Conexion;
import Utilidades.MensajeAlerta;
import Utilidades.MensajeDeConfirmacion;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mati F
 */
public class VentanaPrincipalController implements Initializable {

@FXML private Button clientes; 

@FXML private Button administrador;

@FXML private  DatePicker fechaActual;

private static LocalDate hoy;

private static int c;


    @FXML protected void pasarCliente (ActionEvent event) throws IOException{
        cargarVentana("VentanaClientes.fxml");

    } 

    @FXML protected void salir (ActionEvent event) throws IOException{ 
        boolean exit;  
        exit = MensajeDeConfirmacion.show("¿Esta seguro que desea salir?","Confirmación","Si", "No");
        if (exit){  
            Conexion con = new Conexion ();
            con.terminarConexion();
            Stage stage ; // Declaramos una variable Stage que nos servira para colocar una escena nueva  
            stage = (Stage) clientes.getScene().getWindow(); // Referenciamos nuestra variable con el Stage, el cual sacamos la referencia desde Button
            stage.close();
        }
    }

    
    @FXML protected void ventas (ActionEvent event) throws IOException{
        cargarVentana("VentanaVentas.fxml"); 
    } 
    
     @FXML protected void AgregarProovedor (ActionEvent event) throws IOException{
        cargarVentana("AgregarProovedor.fxml"); 
    } 

    @FXML protected void ventanaAdmin (ActionEvent event) throws IOException{
        cargarVentana("VentanaAdmin.fxml");
    } 

    @FXML protected void ventanaProductos (ActionEvent event){
        cargarVentana("VentanaProductos.fxml");
    }


    @FXML protected void ventanaEstadistica (ActionEvent event){
        cargarVentana("Estadistica.fxml");
    }
    
    @FXML protected void ventanaCajaDiaria (ActionEvent event){
        cargarVentana("CajaDiariafxml.fxml");
    }


                
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        botonAdmin();  
        
        if (c==0){
            this.hoy = LocalDate.now();
            c++;
        }
        
        this.fechaActual.setValue(hoy);
        
        
        

    }
    
    
    public void botonAdmin (){  // si es admin se habilita el boton de administrador, si no queda deshabilitado.
       administrador.setVisible(LoginController.esAdmin());
    }
    
    private void cargarVentana(String archivo){
        Stage stage ; // Declaramos una variable Stage que nos servira para colocar una escena nueva       
        Parent root;  // Declaramos una variable Parent para referenciar nuestro nuevo LayoutPane 
        try{
            stage = (Stage) clientes.getScene().getWindow(); // Referenciamos nuestra variable con el Stage, el cual sacamos la referencia desde el Button1
            root = FXMLLoader.load(getClass().getResource(archivo)); // Referenciamos nuestra variable con el LayoutPane que queramos colocar en nuestro Escena
            Scene scene = new Scene(root,1200,700); // Declaramos e instanciamos nuestra nueva Escena y le pasamos como parametro nuestro LayoutPane y el tamaño. 
            stage.setScene(scene); // Seteamos nuestra Escena dentro de nuestra ventana
            stage.setResizable(false);
            stage.show(); // Mostramos la Ventana
        }catch(Exception e){
            MensajeAlerta.show("Error al cargar la ventana: " + archivo , "Error de carga");
        }
        
    }
    
    @FXML private void cambiarFecha(){
        VentanaPrincipalController.hoy = fechaActual.getValue();
    }

    public static LocalDate getHoy() {
        return hoy;
    }

    public static void setHoy(LocalDate hoy) {
        VentanaPrincipalController.hoy = hoy;
    }
    
    
    
    
    
    
    
}