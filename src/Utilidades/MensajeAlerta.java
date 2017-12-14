/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;


import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

/**
 *
 * @author Nati
 */
public class MensajeAlerta {

    

    
/*
    Metodo para Visulalizar una ventana de alerta o MessageBox
    */
static public  void show (String mensaje, String titulo) {
    Stage stage = new Stage(); 
    
    stage.initModality(Modality.APPLICATION_MODAL); // Visualiza el stage y bloque cualquier otro.
    stage.setTitle(titulo); 
    stage.setMinWidth(350); 
    stage.setResizable(false);
    
    
    
    Label lbl = new Label(); 
    lbl.setText(mensaje);
    lbl.setStyle(
                 "-fx-font-weight: bold;\n" +
                 "-fx-text-fill: #333333;\n" +
                  "-fx-font-size: 12px;"
                );
    
    Button btnOK = new Button(); 
    btnOK.setText("OK");
    btnOK.setPrefWidth(70);
    btnOK.setStyle("-fx-text-fill: White;\n" +
                   "-fx-font-family: \"Arial Narrow\";\n" +
                   "-fx-font-weight: bold;\n" +
                   "-fx-background-color: linear-gradient(#61a2b1, #2A5058);\n" +
                   "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );"  
                  );
    btnOK.setOnAction(e -> stage.close()); 
    
    
    VBox pane = new VBox(20); // Crea el LayoutPAne
    pane.getChildren().addAll(lbl, btnOK);
    pane.setAlignment(Pos.CENTER); 
    pane.setPadding(new Insets (15, 0, 15, 0));
    
    
    Scene scene = new Scene(pane); // Agrega el LayoutPane a la escena
    
    stage.setScene(scene);
    stage.showAndWait(); // Asegura que el cuadro de mensaje sea modal - ninguna otra parte del programa recibirá eventos hasta que el usuario haya cerrado el cuadro de mensaje.

}

    
}

