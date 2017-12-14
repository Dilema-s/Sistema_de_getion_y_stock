/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitemastock;

import Logica.Producto;
import Logica.Proovedor;
import Utilidades.Conexion;
import Utilidades.MensajeAlerta;
import Utilidades.MensajeDeConfirmacion;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nati
 */
public class VentanaProductosController implements Initializable {
    
    @FXML private Button volver;
    
    @FXML private TableView<Producto> tableView;
    
    @FXML private TextField busqueda;
    
    @FXML private TextField nombre;
     
    @FXML private TextField precio;
      
    @FXML private TextField descripcion;
    
    @FXML private Label id_producto;
    
    @FXML private TextField stock;
    
    @FXML private RadioButton buscarCodigo;
    
    @FXML private RadioButton buscarNombre;
    
    @FXML private RadioButton buscarProovedor;
    
    @FXML private RadioButton buscarDescripcion;
    
    @FXML private ToggleGroup RadioButton;
    
    @FXML private Pane pane;
    
    @FXML private Label label; 
    
    @FXML private ComboBox<Proovedor> combo;
    
    @FXML private boolean v = false;
    

    @FXML public void volver (ActionEvent event){
        Stage stage ; // Declaramos una variable Stage que nos servira para colocar una escena nueva       
        Parent root;  // Declaramos una variable Parent para referenciar nuestro nuevo LayoutPane 
       
        try{
            stage = (Stage) volver.getScene().getWindow(); // Referenciamos nuestra variable con el Stage, el cual sacamos la referencia desde el radioButton
             if (v){
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
     
    @FXML public void actualizar(){   
        //metodo para setear los productos a la tabale view
        // una observableList q cargamos a traves de un metodo productos de la clase conexion
        //el cual recibe como parametro la observableList de la tableView y dos string q le indican que accion hacer, en este caso cargar la lista con todos los productos
       tableView.getItems().clear();
       ArrayList<Producto> lista = new ArrayList<>();
       for (Producto p: tableView.getItems()){
           lista.add(p);
        }
       
       for (Producto p : Conexion.cargarProducto(lista,-1)){
           tableView.getItems().add(p) ;
       }
       
        
    }
    
    @FXML public void eliminar(ActionEvent event){
         ObservableList<Producto> sel;
           
            sel = tableView.getSelectionModel().getSelectedItems(); // getSelectedItems devuelve una ObservableList con el item seleccionado


            if(sel.get(0) == null){
                MensajeAlerta.show("Por Favor seleccione un producto de la lista", "No se ha selecionado producto");
            } else {    
                for (Producto m : sel){

                    if (MensajeDeConfirmacion.show("Esta seguro que desea eliminar el producto: " + m.getNombre(), "Eliminar producto", "Aceptar", "Cancelar")){   
                        Conexion.eliminarProducto(m.getId_producto());// Elimina el Item de la base de datos                      
                    }

                }
            }
            actualizar();
    }
    
    
    @FXML public void insertarProducto(){
                      
            try {
                if (combo.getSelectionModel().isEmpty()){
                
                    label.setText("Por favor seleccione un proovedor de la lista");
                } else {
                    Producto p = new Producto(nombre.getText(),descripcion.getText(),Double.parseDouble(precio.getText()),combo.getSelectionModel().getSelectedItem(),Integer.parseInt(stock.getText()));
                    if (p.Validar().getResultado()){

                        pane.setStyle(" -fx-background-color: #D1F4B6;");
                        label.setText("");
                        nombre.setText("");         
                        descripcion.setText("");         
                        precio.setText("");
                        stock.setText("");
                        
                        
                        p.get_Stock().calculoStockMinimo(p, combo.getSelectionModel().getSelectedItem().getTiempoAproxEntrega());
                        Conexion.insertarProducto(p); 
                        id_producto.setText(Integer.toString(Conexion.ultimo_id_producto() + 1));
                     } else {
                        label.setText(p.Validar().toString());
                        pane.setStyle(" -fx-background-color: #FCA185;");          
                    }    
                    
                    
                }
                
                
                
                
                    

                 
            } catch (Exception e) {
                label.setText("Precio incorreto (no utlilize ',' si no '.')\nO Stock incorrecto (no acepta números decimales)");
                pane.setStyle(" -fx-background-color: #FCA185;");
            }
            actualizar();    

    }
    
    @FXML public void cargarCombo (){
        combo.getItems().clear();
        combo.setItems(Conexion.cargarProovedor(combo.getItems()));
        combo.getSelectionModel().selectFirst(); // selecciona por default el primer valor
        actualizar();
        
    }
    
     /*
    Nos dirige a una ventana nueva para modificar los datos de algun cliente en particular
    */
    @FXML public void editarProducto(){
       
    
          
        ObservableList<Producto> sel;
        
        sel = tableView.getSelectionModel().getSelectedItems(); // getSelectedItems devuelve una ObservableList con el item seleccionado
  
        if(sel.isEmpty()){
            MensajeAlerta.show("Por Favor seleccione un usuario de la lista", "No se ha selecionado usuario");
        } else {
            
            
            for (Producto m : sel){ //recorre la lista hasta q encuentra el q seleccionamos
                
                // Creamos una nueva ventana para modificar los datos
                
                Stage stage = new Stage(); // Declaramos una variable Stage que nos servira para colocar una escena nueva 

                stage.initModality(Modality.APPLICATION_MODAL); // Visualiza el stage y bloque cualquier otro.
                stage.setTitle("Modificar datos del producto"); // titulo de la ventana
                stage.setMinWidth(350); //ancho minimo
                stage.setResizable(false); // no ajustable la ventana

                FXMLLoader loader = new FXMLLoader(getClass().getResource("EditarProducto.fxml")); // carga el archivo fxml
                Parent root; //declara un objeto Parent
                
                try {
                    root = loader.load(); // Carga el panel 
                    EditarProducto controller = loader.getController(); // referencia la controlador de la clase
                    controller.initData(m); // ejecuta el metodo initData que envia como parametro el producto a modificar   
                   
                    Scene scene = new Scene(root, 800, 600); // Crea un Scene con el panel y le da valores al whidth y height
                    stage.setScene(scene); //setea la escena a la ventana
                } catch (IOException ex) {
                    MensajeAlerta.show("Se produjo un error inesperado", "No cargo el Stage");
                }
                stage.showAndWait(); // muestra la ventana
                
                
                actualizar();
   
            }
        }     
      }
        
        
        
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualizar();
        cargarCombo();
        id_producto.setText(Integer.toString(Conexion.ultimo_id_producto() + 1));
        
        // seteamos "id_producto" para cuando se seleccione
        buscarCodigo.setUserData("id_producto"); 
        
        // seteamos "nombre" para cuadno se seleccione
        buscarNombre.setUserData("nombre");
        
        // seteamos "descripcion" para cuadno se seleccione
        buscarDescripcion.setUserData("descripcion");
        
        // seteamos "proovedor" para cuadno se seleccione
        buscarProovedor.setUserData("razon_social");
         
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
        busqueda.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {

               

                    //Se inicializa una ObservableList
                    ObservableList<Producto> resultadoBusqueda;

                    //Se limpia el contenido del listView
                    tableView.getItems().clear();

                    //Se le pasa al metodo buscar cliente el texto a buscar y la observableList de la tableView
                    resultadoBusqueda =  Conexion.consulta_producto(busqueda.getText(),tableView.getItems(),RadioButton.getSelectedToggle().getUserData().toString());

                    // Se setea la ovservableList y se muestra el resultado en caso que haya, si no queda vacia
                    tableView.setItems(resultadoBusqueda);
                    
                    
                    tableView.setPlaceholder(nuevo); // se inserta el label creado con aterioridad 

                
        });
    }


/*
      Metodo q setea un true a la variable v para saber q proviene de la ventanaVentas por lo tanto configura el volver y boton aceptar
    */   
    public void botonVolver (boolean v){
      
        this.v = v;     
      
    }    
    
}
