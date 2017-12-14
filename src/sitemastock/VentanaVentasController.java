/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitemastock;

import Logica.Administrador;
import Logica.ClaseDatosMonotributo;
import Logica.Cliente;
import Logica.Movimiento;
import Logica.Persona;
import Logica.Producto;
import Logica.Usuario;
import Utilidades.Conexion;
import Utilidades.MensajeAlerta;
import Utilidades.MensajeDeConfirmacion;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import org.controlsfx.control.textfield.TextFields;
/**
 * FXML Controller class
 *
 * @author Mati F
 */
public class VentanaVentasController implements Initializable {
    
    @FXML private Label total;
    @FXML private Label cajero;
    @FXML private  Label cliente;
    
    
    @FXML private TableView<Producto> tableView;
    @FXML private DatePicker fecha;
    
    @FXML private Persona user;
    @FXML private static Cliente client;
   
    @FXML private TableColumn<Producto, Integer> col3;
   
    @FXML private TextField consutaCodigo;
    @FXML private TextField consultaProducto;
    DecimalFormat df = new DecimalFormat("#.00");
    @FXML private Button buscarCliente;
    
    @FXML private int c = 0;
    @FXML private RadioButton cc;    
    @FXML private RadioButton efectivo;   
    @FXML private ToggleGroup grupo2;
    @FXML private int id_venta;
    @FXML private long cuitMonotributo;
    
    
    
    double precioFinal = 0;
    private Administrador e;
    private Usuario u;
    
   @FXML private void venta (){
       ObservableList<Producto> lista_productos = tableView.getItems();
       
       ArrayList<Producto> listaP = new ArrayList();
       
       int id; //del usuario
       

                if (this.user.getClass().equals(Administrador.class)){
                     e = (Administrador)this.user;
                     id = e.getId_usuario();
                }else {
                    
                     u = (Usuario)this.user;
                     id = u.getId_usuario();
                }
       
       if (!lista_productos.isEmpty()){
           
           /*
            Arma un arrayList de todos los productos comprados
           */        
           for(Producto l: lista_productos){  
                   listaP.add(l);                              
           }
         
           if ("efectivo".equals(grupo2.getSelectedToggle().getUserData().toString())){
                
                int ds = Conexion.descontarStock(listaP);
                if (ds ==  1){
                        id_venta = Conexion.registroVenta(fecha.getValue(),client,precioFinal,id,listaP);
                         if ( id_venta == -1){
                             MensajeAlerta.show("Error en el registro de venta, intente nuevamente", "Confirmar Venta");
                         } else {

                             int id_factura = Conexion.factura(id_venta,0,grupo2.getSelectedToggle().getUserData().toString(),cuitMonotributo);
                             if (id_factura == -1){
                                 MensajeAlerta.show("Error en el registro de la factura", "Confirmar factura");
                             } else {
                                 imprimir1(id_venta);
                             }
                             registroCajaDiaria(id,fecha.getValue(),precioFinal,id_venta,"efectivo",client.getApellido());

                             tableView.getItems().clear();
                             precioFinal = 0;
                             total.setText("$" + df.format(precioFinal));
                             consutaCodigo.requestFocus();
                             client = Set_ConsumidorFinal(new Cliente());
                             cliente.setText(client.getNombre());           
                         }
                }else {
                    if (ds == -1){
                        Stage stage = new Stage(); // Declaramos una variable Stage que nos servira para colocar una escena nueva 

                        stage.initModality(Modality.APPLICATION_MODAL); // Visualiza el stage y bloque cualquier otro.
                       
                        stage.setResizable(false); // no ajustable la ventana

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("VentanaProductos.fxml")); // carga el archivo fxml
                        Parent root; //declara un objeto Parent

                        try {
                            root = loader.load(); // Carga el panel 
                            VentanaProductosController control = loader.getController();
                            control.botonVolver(true);

                            Scene scene = new Scene(root, 1000, 600); // Crea un Scene con el panel y le da valores al whidth y height
                            stage.setScene(scene); //setea la escena a la ventana
                        } catch (IOException ex) {
                            MensajeAlerta.show("Se produjo un error inesperado", "No cargo el Stage");
                        }
                        stage.showAndWait(); // muestra la ventana
                    }
                
                }

           } else {

               if ("Consumidor Final".equals(cliente.getText())){
                  if( MensajeDeConfirmacion.show("Seleccione un usuario registrado en \"Buscar clientes\"\nSi desea registrar un nuevo cliente presione ACEPTAR, de lo contrario SALIR", "Cuenta Corriente", "Aceptar", "Salir")){
                        Stage stage = new Stage(); // Declaramos una variable Stage que nos servira para colocar una escena nueva 
                        stage.initModality(Modality.APPLICATION_MODAL); // Visualiza el stage y bloque cualquier otro.
                        stage.setTitle("Cargue los datos del cliente y presione AGREGAR");

                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("VentanaClientes.fxml")); // carga el archivo fxml
                            Parent root; //declara un objeto Parent
                            root = loader.load(); // Carga el panel 
                            FXMLDocumentController controller = loader.getController();
                            controller.botonVolver(true);

                            Scene scene = new Scene(root,950, 600); // Crea un Scene con el panel y le da valores al whidth y height
                            stage.setScene(scene); //setea la escena a la ventana
                            stage.showAndWait(); // muestra la ventana

                        } catch (IOException ex) {
                            MensajeAlerta.show("Se produjo un error inesperado", "No cargo el Stage");
                        }
                  }  
                  
               } else{
                   
                      
                int ds = Conexion.descontarStock(listaP);
                     if (ds == 1){
                        if (Conexion.cuentaCorriente(client.getId_cliente())){
                             id_venta = Conexion.registroVenta(fecha.getValue(),client,precioFinal,id,listaP);
                             

                             if (id_venta == -1){
                                  MensajeAlerta.show("Error en el registro de venta, intente nuevamente", "Confirmar Venta");
                             } else {

                                 if (Conexion.insertarVentaEnCuentaCorriente(client,id_venta)){
                                     


                                     MensajeAlerta.show("Se registro la venta en la cuenta corriente de" + ", " + client.getApellido() , "Confirmar Venta");
                                     tableView.getItems().clear();
                                     precioFinal = 0;
                                     total.setText("$" + df.format(precioFinal));
                                     consutaCodigo.requestFocus();
                                     client = Set_ConsumidorFinal(new Cliente());
                                     cliente.setText(client.getNombre()); 
                                     int id_factura = Conexion.factura(id_venta,0,"Cuenta Corriente",cuitMonotributo);
                                     if (id_factura == -1){
                                         MensajeAlerta.show("Error en el registro de la factura", "Confirmar factura");
                                     } else {
                                         imprimir1(id_venta);
                                     }
                                     
                                 } else {
                                     MensajeAlerta.show("No se pudo registrar la venta en la cuenta orriente", "Error");
                                 }


                             }

                        }else {
                            MensajeDeConfirmacion.show("El Cliente seleccionado no posee cuenta corriente.\nDesea crearla ??", "Cuenta Corriente","Aceptar","Salir");
                            //cambiar el valor de cuenta corriente en la tabla cliente
                        }
                    } else {
                     
                            if (ds == -1){
                              Stage stage = new Stage(); // Declaramos una variable Stage que nos servira para colocar una escena nueva 

                              stage.initModality(Modality.APPLICATION_MODAL); // Visualiza el stage y bloque cualquier otro.

                              stage.setResizable(false); // no ajustable la ventana

                              FXMLLoader loader = new FXMLLoader(getClass().getResource("VentanaProductos.fxml")); // carga el archivo fxml
                              Parent root; //declara un objeto Parent

                              try {
                                  root = loader.load(); // Carga el panel 
                                  VentanaProductosController control = loader.getController();
                                  control.botonVolver(true);

                                  Scene scene = new Scene(root, 1000, 600); // Crea un Scene con el panel y le da valores al whidth y height
                                  stage.setScene(scene); //setea la escena a la ventana
                              } catch (IOException ex) {
                                  MensajeAlerta.show("Se produjo un error inesperado", "No cargo el Stage");
                              }
                              stage.showAndWait(); // muestra la ventana
                          }
//                     
                     
                     
                     
                     }
               }

           }
      
       }  else {
           MensajeAlerta.show("Venta incorrecta. \n Agregue al menos un producto", "Confirmación de venta");
       
       }
       
   }
   
   
   @FXML public void buscarCliente (){    
        Stage stage = new Stage(); // Declaramos una variable Stage que nos servira para colocar una escena nueva 

        stage.initModality(Modality.APPLICATION_MODAL); // Visualiza el stage y bloque cualquier otro.
        stage.setTitle("Seleccione un cliente y presione ACEPTAR");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VentanaClientes.fxml")); // carga el archivo fxml
            Parent root; //declara un objeto Parent
            root = loader.load(); // Carga el panel 
            FXMLDocumentController controller = loader.getController();
            controller.botonVolver(true);
            
            Scene scene = new Scene(root,950, 600); // Crea un Scene con el panel y le da valores al whidth y height
            stage.setScene(scene); //setea la escena a la ventana
            stage.showAndWait(); // muestra la ventana
            
        } catch (IOException ex) {
            MensajeAlerta.show("Se produjo un error inesperado", "No cargo el Stage");
        }    finally {
            if (FXMLDocumentController.getCliente() != null){
            client = FXMLDocumentController.getCliente();
            cliente.setText(client.getNombre()+ ", " + client.getApellido());
           } else{
             client = Set_ConsumidorFinal(new Cliente());
             cliente.setText(client.getNombre());
           }
        
        }
    
   }
   
   @FXML public void volver (){      
       cambiarStage("VentanaPrincipal.fxml");
   }
   
   
   
   @FXML private void eliminarProducto (){
       if (tableView.getSelectionModel().getSelectedItem() == null){
           MensajeAlerta.show("Seleccione el producto de la lista que desea eliminar", "Eliminar producto de venta");
       } else {
           restaTotal(tableView.getSelectionModel().getSelectedItem().getPrecio() * tableView.getSelectionModel().getSelectedItem().getStock());
           tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());
       }
       
       
   }
   
   @FXML private void eliminarVenta (){
       
            tableView.getItems().clear();
            precioFinal = 0;
            total.setText("$" + df.format(precioFinal));
            consutaCodigo.requestFocus();
            client = Set_ConsumidorFinal(new Cliente());
            cliente.setText(client.getNombre()); 

   }
    
    
    @FXML public void imprimir1 (int id_venta){
//        venta();
     try{
        Conexion.conexionMySql();
        String direccion = "C:\\Users\\Mati_ferrero\\Desktop\\Trabajo final Laboratorio II\\SitemaStock\\src\\Reportes\\report3.jrxml";
        Map parametros = new HashMap();
        
            
            parametros.put("ventaNumero", id_venta);
            JasperReport reportJasper = JasperCompileManager.compileReport(direccion);
            JasperPrint mostrarReporte = JasperFillManager.fillReport(reportJasper,parametros,Conexion.getMiConexion() );
            JasperViewer jviewer= new JasperViewer(mostrarReporte,false);
            jviewer.setTitle("FACTURA C");
            jviewer.setVisible(true);
//            JasperViewer.viewReport(mostrarReporte,false);
            
        
        
         
     }catch(Exception e){
         e.printStackTrace();
         MensajeAlerta.show(e.getMessage(), "Error con la impresión de la factura");
     }          
    }
    
    
    
    @FXML private void menu(){
        cambiarStage("CajaDiariafxml.fxml");
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             
        fecha.setValue(VentanaPrincipalController.getHoy()); // setea la fecha que toma la de la ventana principal

        cajero.setText(LoginController.getUsuario().getApellido() + " ,"+ LoginController.getUsuario().getNombre()); // setea nombre del usuario en un label con el que se inicio el sistema
        
        this.user = LoginController.getUsuario(); // setea el usuario a una variable usuario de la clase
        
        total.setText("$" + precioFinal); //Imprime el valor inicial de la variable precioFinal que se inicializa en 0
       
        efectivo.setSelected(true);
        efectivo.requestFocus();
        
        // seteamos "efectivo" para cuando se seleccione
        efectivo.setUserData("efectivo");
        
        // seteamos "cc" para cuadno se seleccione
        cc.setUserData("cc");
    
        
        
        
        
        cargarTextField ();
        
        ClaseDatosMonotributo datosM = new ClaseDatosMonotributo();
        Conexion.datosDeMonotributo(datosM);
        this.cuitMonotributo = datosM.getCuit();
       
        
        ObservableList<Producto> lista = tableView.getItems();
        
        client = Set_ConsumidorFinal(new Cliente());
        
        cliente.setText(client.getNombre());
       
        
        /*
          Metodo para hacer focus en un textField
        */    
        Platform.runLater(consutaCodigo::requestFocus);

        col3.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        col3.setOnEditCommit((CellEditEvent<Producto, Integer> event) -> {

                if (event.getNewValue() > 0){
                    int cantidad;
                    if (event.getOldValue() < event.getNewValue()){

                       cantidad = event.getNewValue()- event.getOldValue(); // unidades de productos de un mismo articulo menos uno
                       sumaTotal(event.getRowValue().getPrecio() * cantidad); // se pasa como parametro la cantidad por el vlor del producto
                       event.getRowValue().setStock(event.getNewValue()); // toma el valor de la fila y setea la cantidad comprada dentro de stock, que nos sirve como vriable auxiliar para esto

                    } else {
                        if (event.getOldValue() > event.getNewValue() ){
                            cantidad = event.getOldValue() - event.getNewValue(); // unidades de productos de un mismo articulo menos uno
                            restaTotal(event.getRowValue().getPrecio() * cantidad);
                            event.getRowValue().setStock(event.getNewValue()); 
                        }
                    }
                } else {
                    MensajeAlerta.show("No puede ingresar valores negativos, ni cero", "Cambio de cantidad");
                }
        });
        
             
       tableView.getItems().clear();
   
       consutaCodigo.addEventHandler(KeyEvent.KEY_RELEASED , new EventHandler<KeyEvent>() {
            
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER)
                {
                    int code = 0;
                    try {
                        
                        code = Integer.parseInt(consutaCodigo.getText());
                        Producto prod = Conexion.lector(code);
                        
                        prod.setStock(1);
                        lista.add(prod);
                        sumaTotal(prod.getPrecio()); 
                         
                    } catch (Exception e) {
                        MensajeAlerta.show("Código incorrecto, intente nuevamente", "Lectura código de barras");
                    }

                    consutaCodigo.setText("");
                }
            }
        });  
        
       consultaProducto.addEventHandler(KeyEvent.KEY_RELEASED , new EventHandler<KeyEvent>() {
            
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER)
                {
                    
                    try {
                        
                            Producto prod = Conexion.lector_Nombre(consultaProducto.getText());
                            
                            prod.setStock(1);
                            lista.add(prod);
                            sumaTotal(prod.getPrecio());
                        
                        
                        
                    } catch (Exception e) {
                        MensajeAlerta.show("Nombre incorrecto, intente nuevamente", "Lectura de productos");
                    }

                    consultaProducto.setText("");
                }
            }
        });  
  
    }    
    
    
    
    @FXML private void registroCajaDiaria (int id_usuario, LocalDate now,Double monto,int id_venta,String tipoVenta,String cliente){
        Movimiento movimiento;
        
        movimiento = new Movimiento(Movimiento.tipoMovimiento.INGRESO, monto, "Venta en "+ tipoVenta + " N° " + id_venta + ". Cliente "+ cliente);
        
        if (!Conexion.confirmarMovimiento(movimiento,now,id_usuario)){
            MensajeAlerta.show("Error al registrar el movimiento\nIntente nuevamente", "Registro de movimiento en caja diaria");        
        }  
    }
    
    
    @FXML private void cambiarFecha (){
    
       if( MensajeDeConfirmacion.show("Al cambiar la fecha, se cambíara en todo el sistema", "Cambio de Fecha", "Confirmar", "Cancelar")){
          
            VentanaPrincipalController.setHoy(fecha.getValue());
       } else {
           fecha.setValue(VentanaPrincipalController.getHoy());
       }
    }
    
    
    
    public void cambiarStage (String archivo){
        Stage stage ; // Declaramos una variable Stage que nos servira para colocar una escena nueva       
        Parent root;  // Declaramos una variable Parent para referenciar nuestro nuevo LayoutPane 
        try{
            stage = (Stage) buscarCliente.getScene().getWindow(); // Referenciamos nuestra variable con el Stage, el cual sacamos la referencia desde el Button1
            root = FXMLLoader.load(getClass().getResource(archivo)); // Referenciamos nuestra variable con el LayoutPane que queramos colocar en nuestro Escena
            Scene scene = new Scene(root,1200,600); // Declaramos e instanciamos nuestra nueva Escena y le pasamos como parametro nuestro LayoutPane y el tamaño. 
            stage.setScene(scene); // Seteamos nuestra Escena dentro de nuestra ventana
            stage.setResizable(false);
            stage.show(); // Mostramos la Ventana
        }catch(Exception e){
            MensajeAlerta.show("Error al cargar la ventana: " + archivo , "Error de carga");
        }
    
    
    }
   
        
    
            
    
    /**
     * Recibe un double, lo suma al precio final y lo muestra en pantalla
     * @param b 
     */          
     public void sumaTotal (double b){
        precioFinal = precioFinal + b;
        total.setText("$" + df.format(precioFinal));
     
     }
     
     
     /**
     * Recibe un double, lo resta al precio final y lo muestra en pantalla
     * @param b 
     */          
     public void restaTotal (double b){
        precioFinal = precioFinal - b;
        total.setText("$" + df.format(precioFinal));
     
     }
     
     
      @FXML public void cargarTextField (){
          
        ArrayList<String> nombre = new ArrayList();
        ArrayList<String> codigo = new ArrayList();
        ArrayList<Producto> des = new ArrayList();

        try {
            for (Producto p: Conexion.cargarProducto(des,-1)){            
                nombre.add(p.getDescripcion());
                codigo.add("" + p.getId_producto());              
            }              
            TextFields.bindAutoCompletion(consultaProducto, nombre);
            TextFields.bindAutoCompletion(consutaCodigo, codigo);
        } catch (Exception e) {
            MensajeAlerta.show("Falla cargando los productos", "Error");
        }
        
    }
      
      
      private Cliente Set_ConsumidorFinal(Cliente c){
        c.setNombre("Consumidor Final");
        c.setApellido("Consumidor Final");
        c.setDNI("");
        c.setDireccion("");
        c.setId_cliente(1);
        c.setMail("@");
        c.setTelefono("");
      return c;
      }
      
     
          
    
}
