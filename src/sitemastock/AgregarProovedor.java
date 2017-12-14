/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitemastock;

import Logica.Proovedor;
import Utilidades.Conexion;
import Utilidades.MensajeAlerta;
import Utilidades.MensajeDeConfirmacion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author Mati_ferrero
 */
public class AgregarProovedor implements Initializable {

    @FXML private TableView<Proovedor> tableView;
    
    @FXML private TextField razon_social;
          
    @FXML private TextField telefono;
         
    @FXML private TextField direccion;
    
    @FXML private TextField plazoEntrega;
    
    @FXML private TextField mail;
    
    @FXML private TextField buscar;
    
    @FXML private Pane pane;
    
    @FXML private Label label;
    
    @FXML private RadioButton buscarRazon;
    
    @FXML private RadioButton buscarId;
    
    @FXML private RadioButton buscarMail;
    
    @FXML private ToggleGroup group1;
    
    
    @FXML protected void conectar (){ 
        
      // el metodo getItems() devuelve la observableList del la tabla la cual la pasamos como parametro al metodo cargardatos() 
      // luego el metodo setItems vuelve a cargar en la tabla la observableList pero ya con los datos de la base de datos !! Genial!
        try {
             tableView.getItems().clear();
             tableView.setItems(Conexion.cargarProovedor(tableView.getItems())); 
        } catch (Exception e) {
            MensajeAlerta.show("No se pudo Cargar!!", "Cargar datos");
        }
    }
    
    
    @FXML public void volver (ActionEvent event){
        Stage stage ; // Declaramos una variable Stage que nos servira para colocar una escena nueva       
        Parent root;  // Declaramos una variable Parent para referenciar nuestro nuevo LayoutPane 
        try{
            stage = (Stage) mail.getScene().getWindow(); // Referenciamos nuestra variable con el Stage, el cual sacamos la referencia desde el radioButton
            root = FXMLLoader.load(getClass().getResource("VentanaPrincipal.fxml")); // Referenciamos nuestra variable con el LayoutPane que queramos colocar en nuestro Escena

            Scene scene = new Scene(root,1200,600); // Declaramos e instanciamos nuestra nueva Escena y le pasamos como parametro nuestro LayoutPane y el tamaño. 

            stage.setScene(scene); // Seteamos nuestra Escena dentro de nuestra ventana
            stage.show(); // Mostramos la Ventana
        }catch(Exception e){
            MensajeAlerta.show("Error al cargar la ventana", "Volver al menu principal");
            e.printStackTrace();
        }
    }
    
    
    
    @FXML public void eliminar(ActionEvent event){
         ObservableList<Proovedor> sel, items;
            items = tableView.getItems(); // getItems devuelve una ObservableList con todos los proovedores
            sel = tableView.getSelectionModel().getSelectedItems(); // getSelectedItems devuelve una ObservableList con el item seleccionado

            if(sel.get(0) == null){
                MensajeAlerta.show("Por Favor seleccione un proovedor de la lista", "No se ha selecionado proovedor");
            } else {    
                for (Proovedor p : sel){
                    if (MensajeDeConfirmacion.show("Esta seguro que desea eliminar el proovedor: " + p.getRazon_social(), "Eliminar proovedor", "Aceptar", "Cancelar")){                             
                       Conexion.eliminarProovedor(p.getId_proovedor());  
                    }

                }
            }
            conectar();
    }
    
    
    
    @FXML private void agregarProovedor(ActionEvent event) {
        
        
       
        Proovedor c = new Proovedor(razon_social.getText(),direccion.getText(),mail.getText(),telefono.getText());
        
        c.setTiempoAproxEntrega(new IntegerStringConverter().fromString(plazoEntrega.getText()));
        if (c.Validar().getResultado()){           
            pane.setStyle(" -fx-background-color: #D1F4B6;");
            label.setText("");
            razon_social.setText("");         
            direccion.setText("");         
            telefono.setText("");
            mail.setText("");
            plazoEntrega.setText("");

            Conexion.insertarProovedor(c.getRazon_social(), c.getDireccion(), c.getMail(), c.getTelefono(),c.getTiempoAproxEntrega());
                       
        } else {
            
            label.setText(c.Validar().toString());
            pane.setStyle(" -fx-background-color: #FCA185;");          
        }      
        conectar();
    }
    
    
    @FXML private void editar (ActionEvent event) {
          
        ObservableList<Proovedor> sel;
        
        sel = tableView.getSelectionModel().getSelectedItems(); // getSelectedItems devuelve una ObservableList con el item seleccionado
  
        if(sel.get(0) == null){
            MensajeAlerta.show("Por Favor seleccione un usuario de la lista", "No se ha selecionado usuario");
        } else {
            
            
            for (Proovedor m : sel){ //recorre la lista hasta q encuentra el q seleccionamos
                
                // Creamos una nueva ventana para modificar los datos
                
                Stage stage = new Stage(); // Declaramos una variable Stage que nos servira para colocar una escena nueva 

                stage.initModality(Modality.APPLICATION_MODAL); // Visualiza el stage y bloque cualquier otro.
                stage.setTitle("Modificar datos de un proovedor"); // titulo de la ventana
                stage.setMinWidth(350); //ancho minimo
                stage.setResizable(false); // no ajustable la ventana

                FXMLLoader loader = new FXMLLoader(getClass().getResource("EditarProovedor.fxml")); // carga el fxml
                Parent root; //declara un objeto Parent
                
                try {
                    root = loader.load(); // Carga el panel 
                    EditarProovedor controller = loader.getController(); // referencia la controlador de la clase
                    controller.initData(m); // ejecuta el metodo initData que envia com parametro el prooedor a modificar   
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
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
          
        // Se conecta a la base de datos y carga los Cliente en una tableView
        conectar();
        
        
        // seteamos un dni para cuando se seleccione
        buscarRazon.setUserData("razon_social"); 
        
        // seteamos un nombre para cuadno se seleccione
        buscarId.setUserData("id_proovedor");
        
        // seteamos una direccion para cuadno se seleccione
        buscarMail.setUserData("mail");
         
        // por default seteamos con true el radio button buscar por nombre
        buscarRazon.setSelected(true);
        
        // por default lo remarcamos
        buscarRazon.requestFocus();
        
        //Crea una label nuevo y se le agrega stylo para mostrar en caso la busqueda no tenga resultados
        Label nuevo = new Label();
        nuevo.setStyle("-fx-font-size:13px;"
                + "-fx-text-fill: firebrick;");
        nuevo.setText("Su búsqueda no arrojo resultados");
        
        //evento Keyboard, se redefine el metodo Handle
        buscar.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {            

                    //Se inicializa una ObservableList
                    ObservableList<Proovedor> resultadoBusqueda;

                    //Se limpia el contenido del listView
                    tableView.getItems().clear();

                    //Se le pasa al metodo buscar cliente el texto a buscar y la observableList de la tableView
                    resultadoBusqueda =  Conexion.buscarProovedor(buscar.getText(),tableView.getItems(),group1.getSelectedToggle().getUserData().toString());

                    // Se setea la ovservableList y se muestra el resultado en caso que haya, si no queda vacia
                    tableView.setItems(resultadoBusqueda);
                    
                    
                    tableView.setPlaceholder(nuevo);

               
        });
        

        
        
        
        
        
    }    
    
}
