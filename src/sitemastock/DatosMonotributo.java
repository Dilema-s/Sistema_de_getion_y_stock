/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitemastock;

import Logica.ClaseDatosMonotributo;
import Utilidades.Conexion;
import Utilidades.MensajeAlerta;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.converter.LongStringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author Mati_ferrero
 */
public class DatosMonotributo implements Initializable {

    @FXML TextField razonSocial;
    @FXML TextField cuit;
    @FXML TextField cai;
    @FXML TextField calle;
    @FXML TextField numero;
    @FXML TextField dpto;
    @FXML TextField piso;
    @FXML TextField localidad;
    @FXML TextField cp;
    @FXML ComboBox<String> provincia;
    @FXML DatePicker inicioActividades;
    @FXML Pane pane;
    @FXML Label validCuit;
    @FXML Label validCai;
    @FXML Label validPiso;
    @FXML Label validCp;
    @FXML Label validaciones;
    @FXML Label validFecha;
    @FXML Button volver;
    @FXML Button aceptar;
    @FXML Button modificar;
    private LongStringConverter d = new LongStringConverter();
    
    
    
    
    @FXML public void modificarDatos (){
    
           boolean aux = true;
            
            ClaseDatosMonotributo datos = new ClaseDatosMonotributo();

            datos.setRazonSocial(razonSocial.getText());
            datos.setCalle(calle.getText());
            datos.setNumero(numero.getText());
            datos.setDpto(dpto.getText());
            datos.setLocalidad(localidad.getText());
            datos.setProvincia(provincia.getValue());

            
            if (inicioActividades.getValue() == null ){
                validFecha.setStyle(" -fx-background-color: #F0AFA1;"
                        + "-fx-border-style: solid;"
                        + "-fx-border-width: 1px;");
                validFecha.setText("Ingrese una fecha valida");
                aux = false;
            }

            

            try {
                datos.setCuit(d.fromString(cuit.getText()));

            } catch (Exception e) {
                validCuit.setStyle(" -fx-background-color: #F0AFA1;"
                         + "-fx-border-style: solid;"
                        + "-fx-border-width: 1px;");
                validCuit.setText("Ingrese solo números");
                cuit.setText("");
                aux = false;

            }
             try {
                 datos.setCai(d.fromString(cai.getText()));

            } catch (Exception e) {
                validCai.setStyle(" -fx-background-color: #F0AFA1;"
                        + "-fx-border-style: solid;"
                        + "-fx-border-width: 1px;");
                validCai.setText("Ingrese solo números enteros");
                cai.setText("");
                aux = false;
            }
            try {
                 datos.setPiso(new IntegerStringConverter().fromString(piso.getText()));
            } catch (Exception e) {
                validPiso.setStyle(" -fx-background-color: #F0AFA1;"
                         + "-fx-border-style: solid;"
                        + "-fx-border-width: 1px;");
                validPiso.setText("Ingrese solo números");
                piso.setText("");
                aux = false;
            }
            try {
                 datos.setCp(new IntegerStringConverter().fromString(cp.getText()));
            } catch (Exception e) {
                validCp.setStyle(" -fx-background-color: #F0AFA1;"
                        + "-fx-border-style: solid;"
                        + "-fx-border-width: 1px;");
                validCp.setText("Ingrese solo números");
                cp.setText("");
                aux = false;
            }




            if (datos.Validar().getResultado() || aux){
               if( Conexion.actualizarDatosMonotributo(datos, inicioActividades.getValue())){
                   MensajeAlerta.show("Los datos se actualizaron en la base de datos", "Carga de datos Monotributo");
               } else {
                   MensajeAlerta.show("No se puedieron actualizar los datos en la base de datos", "Datos de monotributo");
               }
            } else {
                validaciones.setText(datos.Validar().getListaResultados().toString());
            }
        
        
        
    
    
    
    }
    
    
    @FXML public void aceptar (){
  
        validCuit.setText("");
        validCai.setText("");
        validPiso.setText("");
        validCp.setText("");
        validaciones.setText("");

        
            boolean aux = true;
            
            ClaseDatosMonotributo datos = new ClaseDatosMonotributo();

            datos.setRazonSocial(razonSocial.getText());
            datos.setCalle(calle.getText());
            datos.setNumero(numero.getText());
            datos.setDpto(dpto.getText());
            datos.setLocalidad(localidad.getText());
            datos.setProvincia(provincia.getValue());

            


            try {
                datos.setCuit(d.fromString(cuit.getText()));

            } catch (Exception e) {
                validCuit.setStyle(" -fx-background-color: #F0AFA1;"
                         + "-fx-border-style: solid;"
                        + "-fx-border-width: 1px;");
                validCuit.setText("Ingrese solo números");
                cuit.setText("");
                aux = false;

            }
             try {
                 datos.setCai(d.fromString(cai.getText()));

            } catch (Exception e) {
                validCai.setStyle(" -fx-background-color: #F0AFA1;"
                        + "-fx-border-style: solid;"
                        + "-fx-border-width: 1px;");
                validCai.setText("Ingrese solo números enteros");
                cai.setText("");
                aux = false;
            }
            
            if (!"".equals(piso.getText())){
                try {
                     datos.setPiso(new IntegerStringConverter().fromString(piso.getText()));
                } catch (Exception e) {
                    validPiso.setStyle(" -fx-background-color: #F0AFA1;"
                             + "-fx-border-style: solid;"
                            + "-fx-border-width: 1px;");
                    validPiso.setText("Ingrese solo números");
                    piso.setText("");
                    aux = false;
                }           
            } else {
                datos.setPiso(0);
            
            }
            
            try {
                 datos.setCp(new IntegerStringConverter().fromString(cp.getText()));
            } catch (Exception e) {
                validCp.setStyle(" -fx-background-color: #F0AFA1;"
                        + "-fx-border-style: solid;"
                        + "-fx-border-width: 1px;");
                validCp.setText("Ingrese solo números");
                cp.setText("");
                aux = false;
            }
            
            if (inicioActividades.getValue() == null ){
                validFecha.setStyle(" -fx-background-color: #F0AFA1;"
                        + "-fx-border-style: solid;"
                        + "-fx-border-width: 1px;");
                validFecha.setText("Ingrese una fecha valida");
                aux = false;
            }



            if (datos.Validar().getResultado() || aux){
               if( Conexion.cargarDatosMonotributo(datos, inicioActividades.getValue())){
                   MensajeAlerta.show("Los datos se guardaron correctamente", "Dtos de monotributo");
               } else {                  
                   MensajeAlerta.show("No se puedieron cargar los datos en la base de datos", "Carga de datos Monotributo");
               }
            } else {
                validaciones.setText(datos.Validar().getListaResultados().toString());
            }
            
           cargarDatos();
        
    
    }
    
    
    
    @FXML public void volver (){
        Stage stage;
        stage = (Stage) calle.getScene().getWindow(); // Referenciamos nuestra variable con el Stage, el cual sacamos la referencia desde el label
        stage.close();
    
    }
    
    @FXML public void cargarProvincia (){
        
        if (Conexion.cargarProvincia(provincia.getItems()) == null ){
        
            MensajeAlerta.show("Los datos de la provincia se crgaron incorrectamente", "Carga de provincias");
            provincia.getItems().add("Sin especificar");
        } 
    }
    
    @FXML public boolean cargarDatos (){
        ClaseDatosMonotributo datosM = new ClaseDatosMonotributo();      
        ClaseDatosMonotributo datos = Conexion.datosDeMonotributo(datosM);
        
        if (datos == null){
                      
        } else {
            razonSocial.setText(datos.getRazonSocial());
            cuit.setText(new LongStringConverter().toString(datos.getCuit()) );
            cai.setText(new LongStringConverter().toString(datos.getCai()));
            calle.setText(datos.getCalle());
            numero.setText(datos.getNumero());
            piso.setText(new IntegerStringConverter().toString(datos.getPiso()));
            dpto.setText(datos.getDpto());
            localidad.setText(datos.getLocalidad());
            cp.setText(new IntegerStringConverter().toString(datos.getCp()));
            provincia.setValue(datos.getProvincia());
            inicioActividades.setValue(Instant.ofEpochMilli(datos.getInicioActividades().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
            
           
            
        }
    return true;
    }
    
    
   
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       cargarProvincia();
       
       if (cargarDatos()){
            
            aceptar.setVisible(false);
       }else {
            modificar.setVisible(false);
           
       }
    }    
    
}
