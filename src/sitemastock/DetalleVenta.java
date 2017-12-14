/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitemastock;


import Logica.Producto;
import Logica.Venta;

import Utilidades.MensajeAlerta;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.converter.DateTimeStringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;



/**
 * FXML Controller class
 *
 * @author Mati_ferrero
 */
public class DetalleVenta implements Initializable {
    @FXML private Button volver;
    
    @FXML private TableView<Producto> tableView;
    
    @FXML private Venta venta;
    
    @FXML private Label id_venta;
    
    @FXML private Label fecha;
    
    @FXML private Label cliente;
    
    @FXML private Label total;
    
    @FXML protected void volver (ActionEvent event) {
        Stage stage ; // Declaramos una variable Stage que nos servira para colocar una escena nueva       
        try{
            stage = (Stage) volver.getScene().getWindow(); // Referenciamos nuestra variable con el Stage, el cual sacamos la referencia desde el label
            stage.close();  
        }catch(Exception e){
            MensajeAlerta.show("Error al cargar la ventana cuenta corriente", "Detalle de venta");
             stage = (Stage) volver.getScene().getWindow(); 
//           CambiarStage c = new CambiarStage(stage,"ddd");
        }          
    } 
    
    @FXML private void cargarTabla (){    
        
        ObservableList<Producto> lista = tableView.getItems();
        venta = VentanaCuentaCorriente.getVenta();
        if (venta != null){
            
            id_venta.setText(new IntegerStringConverter().toString(venta.getId_venta()));
            fecha.setText(new DateTimeStringConverter().toString(venta.getFecha()));
            cliente.setText(venta.getCliente().getNombre() + ", " + venta.getCliente().getApellido());
            total.setText(new DoubleStringConverter().toString(venta.getTotal()));
            
            for (Producto p: venta.getProducto()){
                lista.add(p);
            }
            
            
            tableView.setItems(lista);
        } else {
            MensajeAlerta.show("No se cargaron los detalles de la venta seleccionada.\nError con la venta.", "Cuenta corriente");
        } 
        
        
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarTabla();
    }    
    
}
