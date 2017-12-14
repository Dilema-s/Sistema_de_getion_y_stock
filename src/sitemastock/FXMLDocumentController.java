/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitemastock;

import Logica.Cliente;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import Utilidades.*;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Nati
 */
public class FXMLDocumentController implements Initializable  {
    
    // Variables
    
    @FXML private TableView<Cliente> tableView;
    
    @FXML private Label label;
   
    @FXML private TextField nombre;
    
    @FXML private TextField apellido;
    
    @FXML private TextField telefono;
    
    @FXML private TextField buscar;
    
    @FXML private TextField direccion;
    
    @FXML private TextField DNI;
    
    @FXML private TextField email;
    
    @FXML private RadioButton buscarDNI;
    
    @FXML private RadioButton buscarNombre;
    
    @FXML private RadioButton buscarDireccion;
    
    @FXML private ToggleGroup group1;
    
    @FXML private Pane pane;
    
    @FXML private boolean v = false;
    
    static private Cliente cli;
    
    @FXML private Button aceptar;
    
    //Metodos
    
    /*
    Carga un cliente nuevo a la base de datos y lo visualiza en una tableView
    */
    @FXML private void agregarCliente(ActionEvent event) {
        Cliente c = new Cliente();
        
        c.setNombre(nombre.getText());
        c.setApellido(apellido.getText());
        c.setDNI(DNI.getText());
        c.setDireccion(direccion.getText());
        c.setTelefono(telefono.getText());
        c.setMail(email.getText());
        
        
        
        if (c.Validar().getResultado()){

            
            pane.setStyle(" -fx-background-color: #D1F4B6;");
            label.setText("");

            nombre.setText("");
            apellido.setText("");
            direccion.setText("");
            DNI.setText("");
            telefono.setText("");
            email.setText("");
            
            
            Conexion g = new Conexion();
            Conexion.conexionMySql();
            g.insertarClientes(c.getNombre(), c.getApellido(), c.getDNI(), c.getMail(), c.getTelefono(), c.getDireccion());
            
            
        } else {
            
            label.setText(c.Validar().toString());
            pane.setStyle(" -fx-background-color: #FCA185;");
            
        }      
        conectar();
    }
    
   /*
    Elimina un Cliente selecionado desde un TableView, tambien lo elimina de la base de datos.
    */
    @FXML private void eliminarCliente(ActionEvent event) {
            ObservableList<Cliente> sel, items;
            items = tableView.getItems(); // getItems devuelve una ObservableList con todos los clientes
            sel = tableView.getSelectionModel().getSelectedItems(); // getSelectedItems devuelve una ObservableList con el item seleccionado


            if(sel.get(0) == null){
                MensajeAlerta.show("Por Favor seleccione un cliente de la lista", "No se ha selecionado usuario");
            } else {    
                for (Cliente m : sel){

                    if (MensajeDeConfirmacion.show("Esta seguro que desea eliminar al cliente: " + m.getApellido(), "Eliminar Cliente", "Aceptar", "Cancelar")){
                        items.remove(m); // Elimina el item seleccionado del TableView
                        Conexion c = new Conexion(); 
                        Conexion.conexionMySql();      
                        c.borrarCliente(m.getDNI());// Elimina el Item de la base de datos
                    }

                }
            }
      }
      
    /*
    Nos dirige a una ventana nueva para mosificar los datos de algun cliente en particular
    */
    @FXML private void editar (ActionEvent event) {
          
        ObservableList<Cliente> sel;
        
        sel = tableView.getSelectionModel().getSelectedItems(); // getSelectedItems devuelve una ObservableList con el item seleccionado
  
        if(sel.isEmpty()){
            MensajeAlerta.show("Por Favor seleccione un usuario de la lista", "No se ha selecionado usuario");
        } else {
            
            
            for (Cliente m : sel){ //recorre la lista hasta q encuentra el q seleccionamos
                
                // Creamos una nueva ventana para modificar los datos
                
                Stage stage = new Stage(); // Declaramos una variable Stage que nos servira para colocar una escena nueva 

                stage.initModality(Modality.APPLICATION_MODAL); // Visualiza el stage y bloque cualquier otro.
                stage.setTitle("Modificar datos de cliente"); // titulo de la ventana
                stage.setMinWidth(350); //ancho minimo
                stage.setResizable(false); // no ajustable la ventana

                FXMLLoader loader = new FXMLLoader(getClass().getResource("EditarCliente.fxml")); // carga el fxml
                Parent root; //declara un objeto Parent
                
                try {
                    root = loader.load(); // Carga el panel 
                    EditarCliente controller = loader.getController(); // referencia la controlador de la clase
                    controller.initData(m); // ejecuta el metodo initData que envia com parametro el usuario a modificar   
                    Scene scene = new Scene(root, 800, 600); // Crea un Scene con el panel y le da valores al whidth y height
                    stage.setScene(scene); //setea la escena a la ventana
                } catch (IOException ex) {
                    MensajeAlerta.show("Se produjo un error inesperado", "No cargo el Stage");
                }
                stage.showAndWait(); // muestra la ventana
                
                
                conectar();
   
            }
        }     
      }
    
    /*
    Vuelve al menu Principal
    */    
    @FXML protected void volver (ActionEvent event) {
        Stage stage ; // Declaramos una variable Stage que nos servira para colocar una escena nueva       
        Parent root;  // Declaramos una variable Parent para referenciar nuestro nuevo LayoutPane 
        try{
            stage = (Stage) label.getScene().getWindow(); // Referenciamos nuestra variable con el Stage, el cual sacamos la referencia desde el label
            
            if (v){
                stage.close();
            } else {
                root = FXMLLoader.load(getClass().getResource("VentanaPrincipal.fxml")); // Referenciamos nuestra variable con el LayoutPane que queramos colocar en nuestro Escena
                Scene scene = new Scene(root,1200,600); // Declaramos e instanciamos nuestra nueva Escena y le pasamos como parametro nuestro LayoutPane y el tamaño. 
                stage.setScene(scene); // Seteamos nuestra Escena dentro de nuestra ventana
                stage.show(); // Mostramos la Ventana
            }
             
            
        }catch(Exception e){
            MensajeAlerta.show("Error al cargar la ventana", "Volver al menu principal");
        }
           
         } 
         
    /*
    Comienza la conexion con la base de datos y carga los datos de la tabla de clientes en una tableView
    */
    @FXML protected void conectar (){ 
        Conexion c = new Conexion();
        Conexion.conexionMySql(); // comienza la conexion con la base de datos
        
        
      // el metodo getItems() devuelve la observableList del la tabla la cual la pasamos como parametro al metodo cargardatos() 
      // luego el metodo setItems vuelve a cargar en la tabla la observableList pero ya con los datos de la base de datos !! Genial!
        try {
             tableView.getItems().clear();
             tableView.setItems(c.cargarCliente(tableView.getItems())); 
        } catch (Exception e) {
            MensajeAlerta.show("No se pudo Cargar!!", "Cargar datos");
        }
         
      
    }
    
    @FXML private void aceptar(){

        if (tableView.getSelectionModel().getSelectedItem() == null){
            MensajeAlerta.show("Por Favor seleccione un usuario de la lista", "No se ha selecionado usuario");       
        }else {
            this.cli = tableView.getSelectionModel().getSelectedItem();
            Stage stage;
            stage = (Stage) label.getScene().getWindow(); // Referenciamos nuestra variable con el Stage, el cual sacamos la referencia desde el label
            stage.close();
        }
    }

    
    @FXML private void cuentaCorriente (){
        if (tableView.getSelectionModel().getSelectedItem() != null){
            FXMLDocumentController.cli = tableView.getSelectionModel().getSelectedItem();
            if (Conexion.cuentaCorriente(tableView.getSelectionModel().getSelectedItem().getId_cliente())){ // revisa si elusuario tiene cuenta corriente
                
                Stage stage = new Stage(); // Declaramos una variable Stage que nos servira para colocar una escena nueva 
                stage.initModality(Modality.APPLICATION_MODAL); // Visualiza el stage y bloque cualquier otro.
                stage.setTitle("Cuenta corriente de: " + cli.getNombre()+", " + cli.getApellido());

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("VentanaCuentaCorriente.fxml")); // carga el archivo fxml
                    Parent root; //declara un objeto Parent
                    root = loader.load(); // Carga el panel 

                   
                    Scene scene = new Scene(root,1200, 700); // Crea un Scene con el panel y le da valores al whidth y height
                    stage.setScene(scene); //setea la escena a la ventana
                    stage.showAndWait(); // muestra la ventana

                } catch (IOException ex) {
                    MensajeAlerta.show("Se produjo un error inesperado", "No cargo el Stage");
                }    
            }else {
               if( MensajeDeConfirmacion.show("El Cliente seleccionado no posee cuenta corriente.\nDesea crearla ??", "Cuenta Corriente","Aceptar","Salir")){
                  if(false){  //cambiar el estado de la columna cuentaCorriente en la tabla Cliente devuelve true
                      MensajeAlerta.show("La cuenta fue creada con éxito!!", "Cuenta Corriente");
                  } else {
                       MensajeAlerta.show("Se produjo un error al crear la cuenta.\nIntentalo nuevamente por favor!!!", "Cuenta Corriente");
                  }
                  
               }
            }
            
            
        }else {
            MensajeAlerta.show("Debe Selecionar un cliente para ver o crear una cuenta corriente", "Cuenta Corriente");
        }
    
    
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
       
          aceptar.setVisible(v);
        
        
        
        // Se conecta a la base de datos y carga los Cliente en una tableView
        conectar();

        // seteamos un dni para cuando se seleccione
        buscarDNI.setUserData("DNI"); 
        
        // seteamos un nombre para cuadno se seleccione
        buscarNombre.setUserData("Nombre");
        
        // seteamos una direccion para cuadno se seleccione
        buscarDireccion.setUserData("Direccion");
         
        // por default seteamos con true el radio button buscar por nombre
        buscarNombre.setSelected(true);
        
        // por default lo remarcamos
        buscarNombre.requestFocus();
        
        //Crea una label nuevo y se le agrega stylo para mostrar en caso la busqueda no tenga resultados
        Label nuevo = new Label();
        nuevo.setStyle("-fx-font-size:13px;"
                + "-fx-text-fill: firebrick;");
        nuevo.setText("Su búsqueda no arrojo resultados");
        
        //evento Keyboard, se redefine el metodo Handle
        buscar.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {

                // aca se tendrian q validar la entrada de caracteres prohibidos
                if(!"%".equals(buscar.getText())){

                    //Se inicializa una ObservableList
                    ObservableList<Cliente> resultadoBusqueda;

                    //Se limpia el contenido del listView
                    tableView.getItems().clear();

                    //Se le pasa al metodo buscar cliente el texto a buscar y la observableList de la tableView
                    resultadoBusqueda =  Conexion.buscarCliente(buscar.getText(),tableView.getItems(),group1.getSelectedToggle().getUserData().toString());

                    // Se setea la ovservableList y se muestra el resultado en caso que haya, si no queda vacia
                    tableView.setItems(resultadoBusqueda);
                    
                    
                    tableView.setPlaceholder(nuevo);

                }else{
                    MensajeAlerta.show("caracter invalido", "ddddd");
                }
        });
        
    }    
    
    static Cliente getCliente (){
     return cli;
    }
    
    /*
      Metodo q setea un true a la variable v para saber q proviene de la ventanaVentas por lo tanto configura el volver y boton aceptar
    */   
    public void botonVolver (boolean v){
        if (v){
            aceptar.setVisible(true);
        }  
        
        this.v = v;
          
          
      
    }
    
}
