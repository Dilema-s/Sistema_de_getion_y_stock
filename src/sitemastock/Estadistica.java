/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitemastock;

import Logica.Producto;
import Utilidades.Conexion;
import Utilidades.MensajeAlerta;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;


/**
 * FXML Controller class
 *
 * @author Mati_ferrero
 */
public class Estadistica implements Initializable {

   
    

    @FXML private ComboBox<Integer> año;
     @FXML private ComboBox<Integer> año1;
    @FXML TextField buscar;
    String hasta;
    @FXML DatePicker fecha;
     @FXML NumberAxis yAxis;
    @FXML CategoryAxis xAxis;
    @FXML  AreaChart<String, Number> grafico; 
  
    @FXML private Label now;
    
    @FXML private RadioButton codigo;
    
    @FXML private CheckBox comparar;
    
    @FXML private RadioButton nombre;
    
    @FXML private ToggleGroup grupo1;
    
    private Producto prod;
    
    
    
    @FXML public void volver (ActionEvent event){
        Stage stage ; // Declaramos una variable Stage que nos servira para colocar una escena nueva       
        Parent root;  // Declaramos una variable Parent para referenciar nuestro nuevo LayoutPane 
        try{
            stage = (Stage) grafico.getScene().getWindow(); // Referenciamos nuestra variable con el Stage, el cual sacamos la referencia desde el radioButton
            root = FXMLLoader.load(getClass().getResource("VentanaPrincipal.fxml")); // Referenciamos nuestra variable con el LayoutPane que queramos colocar en nuestro Escena

            Scene scene = new Scene(root,1200,600); // Declaramos e instanciamos nuestra nueva Escena y le pasamos como parametro nuestro LayoutPane y el tamaño. 

            stage.setScene(scene); // Seteamos nuestra Escena dentro de nuestra ventana
            stage.show(); // Mostramos la Ventana
        }catch(Exception e){
            MensajeAlerta.show("Error al cargar la ventana", "Volver al menu principal");
        }
    }
    

    
    @FXML private void dibujarGrafico (){
        int[] meses = new int[12];
        
        int [] parametros = Conexion.dibujarGrafico(prod, meses, año.getValue());
        
        
        
        
        grafico.getData().clear();
        grafico.setTitle("Consumo mes x mes del producto: " + prod.getNombre());
        yAxis.setTickUnit(5);
        
        XYChart.Series<String , Number> series1 = new XYChart.Series();
        series1.setName(" " + año.getValue()); 
        series1.getData().add(new XYChart.Data("Enero",parametros[0]));
        series1.getData().add(new XYChart.Data("Febrero",parametros[1]));
        series1.getData().add(new XYChart.Data("Marzo",parametros[2]));
        series1.getData().add(new XYChart.Data("Abril",parametros[3]));
        series1.getData().add(new XYChart.Data("Mayo",parametros[4]));
        series1.getData().add(new XYChart.Data("Junio",parametros[5]));
        series1.getData().add(new XYChart.Data("julio",parametros[6]));
        series1.getData().add(new XYChart.Data("Agosto",parametros[7]));
        series1.getData().add(new XYChart.Data("Septiembre",parametros[8]));
        series1.getData().add(new XYChart.Data("Octubre",parametros[9]));
        series1.getData().add(new XYChart.Data("Noviembre",parametros[10]));
        series1.getData().add(new XYChart.Data("Diciembre",parametros[11]));
        grafico.getData().add(series1);
        
        
        if (comparar.isSelected()){
            
            parametros = Conexion.dibujarGrafico(prod, meses, año1.getValue());
            XYChart.Series<String , Number> series2 = new XYChart.Series();
            series2.setName(" " + año1.getValue()); 
            series2.getData().add(new XYChart.Data("Enero",parametros[0]));
            series2.getData().add(new XYChart.Data("Febrero",parametros[1]));
            series2.getData().add(new XYChart.Data("Marzo",parametros[2]));
            series2.getData().add(new XYChart.Data("Abril",parametros[3]));
            series2.getData().add(new XYChart.Data("Mayo",parametros[4]));
            series2.getData().add(new XYChart.Data("Junio",parametros[5]));
            series2.getData().add(new XYChart.Data("julio",parametros[6]));
            series2.getData().add(new XYChart.Data("Agosto",parametros[7]));
            series2.getData().add(new XYChart.Data("Septiembre",parametros[8]));
            series2.getData().add(new XYChart.Data("Octubre",parametros[9]));
            series2.getData().add(new XYChart.Data("Noviembre",parametros[10]));
            series2.getData().add(new XYChart.Data("Diciembre",parametros[11]));
            grafico.getData().add(series2);
        
        
        
        
        
        
        }
        
    
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        año1.setVisible(false);
        
         // seteamos "codigo" para cuando se seleccione
        codigo.setUserData("codigo"); 
        
        // seteamos "nombre" para cuadno se seleccione
        nombre.setUserData("nombre");
        
        
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        
        Calendar ahora = Calendar.getInstance();
        Date date = ahora.getTime();
        now.setText(dateFormat.format(date));
        
        
        
        cargarTextField();
        
        cargarCombo();
        
        
        
        grupo1.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
        
        @Override
        public void changed(ObservableValue<? extends Toggle> ov,
        Toggle old_toggle, Toggle new_toggle){
            if (grupo1.getSelectedToggle() != null) {
              
                cargarTextField();
        
        
            }
        
        }
        
        
        });
        
        grafico.setTitle("Consumo mes x mes del producto:   ");
        yAxis.setLabel("Cantidad vendidos");
        xAxis.setLabel("Meses");
        yAxis.setTickUnit(5);
        
        
        XYChart.Series<String , Number> series2 = new XYChart.Series();
        
        
        series2.getData().add(new XYChart.Data("Enero",0));
        series2.getData().add(new XYChart.Data("Febrero",0));
        series2.getData().add(new XYChart.Data("Marzo",0));
        series2.getData().add(new XYChart.Data("Abril",0));
        series2.getData().add(new XYChart.Data("Mayo",0));
        series2.getData().add(new XYChart.Data("Junio",0));
        series2.getData().add(new XYChart.Data("julio",0));
        series2.getData().add(new XYChart.Data("Agosto",0));
        series2.getData().add(new XYChart.Data("Septiembre",0));
        series2.getData().add(new XYChart.Data("Octubre",0));
        series2.getData().add(new XYChart.Data("Noviembre",0));
        series2.getData().add(new XYChart.Data("Diciembre",0));
        grafico.getData().add(series2);
        
       
        
        
         buscar.addEventHandler(KeyEvent.KEY_RELEASED , new EventHandler<KeyEvent>() {
            
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER)
                {
                    int code;
                    try {
                        
                        if ("codigo".equals(grupo1.getSelectedToggle().getUserData().toString())){
                            code = Integer.parseInt(buscar.getText());
                            prod = Conexion.lector(code);
                            dibujarGrafico();
                        } else {
                            prod = Conexion.lector_Nombre(buscar.getText());
                            dibujarGrafico();
                        }
                        
                        
                         
                    } catch (Exception e) {
                        MensajeAlerta.show("Código incorrecto, intente nuevamente", "Lectura código de barras");
                    }

                
                }
            }
        });  
        
        
       
        
        
    
       
        

    }    
    
    
    @FXML public void cargarTextField (){
          
        ArrayList<String> nombre1 = new ArrayList();
        ArrayList<String> codigo1 = new ArrayList();
        ArrayList<Producto> des = new ArrayList();
        
        try {
          
          for (Producto p: Conexion.cargarProducto(des,-1)){            
                nombre1.add(p.getDescripcion());
                codigo1.add("" + p.getId_producto());              
            }              
          
            if ("codigo".equals(grupo1.getSelectedToggle().getUserData().toString())){
              TextFields.bindAutoCompletion(buscar, codigo1);
            } else {
               TextFields.bindAutoCompletion(buscar, nombre1);
            }

        } catch (Exception e) {
            MensajeAlerta.show("Falla cargando los productos", "Error");
            
        }
    }
    
    
    
    @FXML public void cargarCombo (){
        
        Calendar ahora = Calendar.getInstance();
        int year = ahora.get(Calendar.YEAR);
        
        for (int x =0; x< 99;x++){
            año1.getItems().add(year-x);
            año.getItems().add(year-x);
        }
        año.getSelectionModel().selectFirst(); // selecciona por default el primer valor
        año1.getSelectionModel().selectFirst();
    }
    
    
    @FXML public void habilitaComparacion (){
        if (comparar.isSelected()){
            año1.setVisible(true);
            
        }else {
            año1.setVisible(false);
        }
        
    
    }
    
}
