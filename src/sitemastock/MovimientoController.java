/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sitemastock;

import Logica.Administrador;
import Logica.Movimiento;
import Logica.Persona;
import Logica.Usuario;
import Utilidades.Conexion;
import Utilidades.MensajeAlerta;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

/**
 * FXML Controller class
 *
 * @author Mati_ferrero
 */
public class MovimientoController implements Initializable {

   @FXML private Label fecha;
   
   @FXML private ToggleGroup grupo;
    
   @FXML private RadioButton ingreso;
    
    @FXML private RadioButton egreso;
    
    @FXML private TextField monto;
    
    @FXML private TextArea motivo;
    
    private LocalDate now;
    
    @FXML private void cancelar(){
    
        Stage stage ; // Declaramos una variable Stage que nos servira para colocar una escena nueva       
        stage = (Stage) fecha.getScene().getWindow(); // Referenciamos nuestra variable con el Stage, el cual sacamos la referencia desde el label
        stage.close(); // Cerramos la Ventana

    
    }
    
    
    
    @FXML private void confirmar(){
        
       Persona user;
       int id; //del usuario
       user = LoginController.getUsuario();

                if (user.getClass().equals(Administrador.class)){
                     Administrador e;
                     e = (Administrador)user;
                     id = e.getId_usuario();
                }else {
                     Usuario u;
                     u = (Usuario)user;
                     id = u.getId_usuario();
                }
        
        
        
        
        Movimiento movimiento;
        
        try {
            
       
            if ("INGRESO".equals(grupo.getSelectedToggle().getUserData().toString())){

                movimiento = new Movimiento(Movimiento.tipoMovimiento.INGRESO, new DoubleStringConverter().fromString(monto.getText()), motivo.getText());

            } else {

                 movimiento = new Movimiento(Movimiento.tipoMovimiento.EGRESO, new DoubleStringConverter().fromString(monto.getText()), motivo.getText());
            }

            if (Conexion.confirmarMovimiento(movimiento,now,id)){
                MensajeAlerta.show("Registro Exitoso!", "Registro de movimiento en caja diaria");
                motivo.setText("");
                monto.setText("");

            }else {
                MensajeAlerta.show("Error al registrar el movimiento\nIntente nuevamente", "Registro de movimiento en caja diaria");
            }
        
        } catch (Exception e) {
            MensajeAlerta.show("Error en el ingreso de datos. Por favor revise los valores\nPara el monto en decimales se debe usar el punto, no la coma\nNo agrege el signo $ ni otro simbolo que no sea un número","Ingreso de datos" );
            
        }
        
      
        
    }
    
    
    
   
   @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        ingreso.setSelected(true);
        
        
        ingreso.setUserData("INGRESO");
        
        
        egreso.setUserData("EGRESO");
        
    }    
    
   public  void fechaHoy(LocalDate date) {
       fecha.setText(date.getDayOfMonth() + "/"  + date.getMonthValue() + "/"  + date.getYear()) ;
       this.now = date;
   
   } 
    
    
}
