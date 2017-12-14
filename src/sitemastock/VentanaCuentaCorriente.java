/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitemastock;

import Logica.Administrador;
import Logica.Cliente;
import Logica.CuentaCorriente;
import Logica.Movimiento;
import Logica.Persona;
import Logica.Usuario;
import Logica.Venta;
import Utilidades.Conexion;
import Utilidades.MensajeAlerta;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

/**
 * FXML Controller class
 *
 * @author Mati_ferrero
 */
public class VentanaCuentaCorriente implements Initializable {
    
    @FXML private TableView<Venta> tableView;
    
    @FXML private Cliente cliente = null;
    
    @FXML private Label nombre_cliente;
    
    @FXML private Label deuda_total;
    
    @FXML private Button volver;
    
    @FXML static private Venta venta;
    
    private Administrador e;
    
    private Usuario u;
    
    private Persona user;
    
    @FXML private void cargarTabla (){    
        double monto = 0;
        ObservableList<Venta> lista = tableView.getItems();
        if (cliente != null){
            
            CuentaCorriente cuenta = new CuentaCorriente (cliente);
            cuenta.setLista_ventas(Conexion.cargarCuentaCorriente(cliente));  //devuelve un linkedList con todas las ventas en cuenta corriente de un cliente
            for (Venta v: cuenta.getLista_ventas()){
                lista.add(v);
                monto = monto + v.getTotal();
            }        
            tableView.setItems(lista);
        } else {
            MensajeAlerta.show("No se cargo la cuenta corriente.\nError con el cliente.", "Cuenta corriente");
        } 
        deuda_total.setText(new DoubleStringConverter().toString(monto) );
    }
    
     /*
    Vuelve a la ventana cuenta corriente
    */    
    @FXML protected void volver (ActionEvent event) {
        Stage stage ; // Declaramos una variable Stage que nos servira para colocar una escena nueva       
        try{
            stage = (Stage) volver.getScene().getWindow(); // Referenciamos nuestra variable con el Stage, el cual sacamos la referencia desde el label
            stage.close();  
        }catch(Exception e){
            MensajeAlerta.show("Error al cargar la ventana Cliente", "Clientes");
        }
           
    } 
    
    
    @FXML protected void detalleVenta (){
        if (tableView.getSelectionModel().getSelectedItem() != null){
            VentanaCuentaCorriente.venta = tableView.getSelectionModel().getSelectedItem(); 
            
            Stage stage = new Stage(); // Declaramos una variable Stage que nos servira para colocar una escena nueva 
            stage.initModality(Modality.APPLICATION_MODAL); // Visualiza el stage y bloque cualquier otro.
            stage.setTitle("Detalle de venta");

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DetalleVenta.fxml")); // carga el archivo fxml
                Parent root; //declara un objeto Parent
                root = loader.load(); // Carga el panel 


                Scene scene = new Scene(root,800, 500); // Crea un Scene con el panel y le da valores al whidth y height
                stage.setScene(scene); //setea la escena a la ventana
                stage.showAndWait(); // muestra la ventana

            } catch (IOException ex) {
                MensajeAlerta.show("Se produjo un error inesperado al cargar los detalles de la venta", "No cargo el Stage");
            }         
        } else{
            MensajeAlerta.show("Por favor sellecione una venta de la lista", "Ver detalles de venta");
        
        }
    }
    
    
    @FXML protected void pagarDeuda (){
        
        if (tableView.getSelectionModel().getSelectedItem() != null){
            for (Venta v: tableView.getSelectionModel().getSelectedItems()){
                VentanaCuentaCorriente.venta = v; 
                int id; //del usuario

                if (user.getClass().equals(Administrador.class)){

                     e = (Administrador)user;
                     id = e.getId_usuario();
                }else {

                     u = (Usuario)user;
                     id = u.getId_usuario();
                }

               if ( Conexion.pagarCuentaCorriente(v.getId_venta())){
                   tableView.getItems().clear();
                    cargarTabla();
                    Movimiento movimiento;
                    movimiento = new Movimiento(Movimiento.tipoMovimiento.INGRESO, v.getTotal(), "Pago de cuenta corriente, venta N° " + v.getId_venta() + ". Cliente "+ cliente.getApellido()+", " + cliente.getNombre());
                    if (!Conexion.confirmarMovimiento(movimiento,VentanaPrincipalController.getHoy(),id)){
                        MensajeAlerta.show("Error al registrar el movimiento en caja diaria\nIntente nuevamente", "Registro de movimiento en caja diaria");        
                    }else {
                        MensajeAlerta.show("La deuda seleccionada fue saldada", "Saldar Deuda");
                    }
               } else {
                   MensajeAlerta.show("La operación tuvo errores, vuelva a intentar", "Pagar Venta en cuenta corriente");
               }
        }
            
            
        } else{     
            MensajeAlerta.show("Por favor sellecione una venta de la lista", "Ver detalles de venta");
        }
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCliente();
        cargarTabla();   
        this.user = LoginController.getUsuario();
        
    }    
    
    public void cargarCliente (){
        if ( FXMLDocumentController.getCliente() != null){
            this.cliente =  FXMLDocumentController.getCliente();
            nombre_cliente.setText(cliente.getNombre()+", "+ cliente.getApellido());
        }       
    }
    
    public static Venta getVenta (){
         return VentanaCuentaCorriente.venta;
        
    }
    
}
