/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitemastock;

import Logica.Producto;
import Logica.Proovedor;
import Utilidades.Conexion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author Mati_ferrero
 */
public class EditarProducto implements Initializable {

   @FXML private TextField nombre;
   
   @FXML private TextField precio;
   
   @FXML private TextField stock;
   
   @FXML private TextField stock1;
   
   @FXML private TextArea descripcion;
  
   @FXML private ComboBox<Proovedor> proovedor;
  
   @FXML private Label razon_social;
   
   @FXML private Label codigo;
   
   @FXML private Pane pane;
   
   @FXML private Label validaciones;
   
   @FXML private Producto producto;

   
   
   
   @FXML protected void volver (){
        Stage stage ; // Declaramos una variable Stage que nos servira para colocar una escena nueva       
        stage = (Stage) codigo.getScene().getWindow(); // Referenciamos nuestra variable con el Stage, el cual sacamos la referencia desde el text
        stage.close(); // Cerramos la Ventana

    } 
   
   
   
    @FXML protected void modificarDatos (){ 
        
         producto.setNombre(nombre.getText());
         producto.setPrecio(Double.parseDouble(precio.getText()));
         producto.setDescripcion(descripcion.getText());
         producto.setCodigo(Integer.parseInt(codigo.getText()));
         producto.setStock(Integer.parseInt(stock.getText()));
         producto.get_Stock().setStockMinimo(new IntegerStringConverter().fromString(stock1.getText()));
        
        if (!proovedor.getSelectionModel().isEmpty()){ // si eligio cambiar de proovedor se guarda el nuevo     
             producto.setProovedor(proovedor.getSelectionModel().getSelectedItem());           
        } else {
            producto.setProovedor(producto.get_Proovedor());
        }
        
        if (producto.Validar().getResultado()){ // validación de la clase producto 

            Conexion.editarProducto(producto);
            volver();       

        }else {
            validaciones.setText(producto.Validar().toString());
            pane.setStyle(" -fx-background-color: #FCA185;");      
        }
             
             
         
        
     
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        proovedor.setStyle("-fx-font: 19px \" arial \";");
        proovedor.valueProperty().addListener(new ChangeListener<Proovedor>() { // metodo que agrega un listener para cambiar el valor del label de proovedor dependiendo el item seleccionado en el comboBox
       

            @Override
            public void changed(ObservableValue<? extends Proovedor> observable, Proovedor oldValue, Proovedor newValue) {
                razon_social.setText(proovedor.getSelectionModel().getSelectedItem().getRazon_social());
            }
        });
        
        
    }    
    
     public void initData (Producto p){ 
        
        this.producto = p;
        
        
        nombre.setText(p.getNombre());
        precio.setText(Double.toString(p.getPrecio()));
        descripcion.setText(p.getDescripcion());
        stock.setText(Integer.toString(p.get_Stock().getStock()));
        razon_social.setText(p.get_Proovedor().getRazon_social());
        proovedor.setItems(Conexion.cargarProovedor(proovedor.getItems()));
        stock1.setText(Integer.toString(p.get_Stock().getStockMinimo()));
        
        
        
        codigo.setText(Integer.toString(p.getId_producto()));
        
    }
    
}
