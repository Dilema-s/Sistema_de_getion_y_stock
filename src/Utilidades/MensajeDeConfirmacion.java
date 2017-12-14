/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;
 import javafx.application.Application;
 import javafx.geometry.Rectangle2D;
 import javafx.scene.Group;
 import javafx.scene.Scene; 
 import javafx.scene.image.Image;
 import javafx.scene.image.ImageView;
 import javafx.scene.layout.HBox;
 import javafx.scene.paint.Color;
 import javafx.stage.Stage; 




import javafx.stage.*;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.image.ImageView;
/**
 *
 * @author Nati
 */
public class MensajeDeConfirmacion {
    
static Stage stage; // Se declara la variable static para que este disponible en todos los metodos ya que tambien son statics
static boolean btnYesClicked;  //Indica si el usuario hace clic en el botón Sí o No.

/*
Metodo para visualizar un mensaje de confirmación de acción
Return: Boolean, dependiendo la decision que haya tomado el usuario
*/
public static boolean show(String mensaje, String title,String textSi, String textNo){
    btnYesClicked = false;
    stage = new Stage(); // crea una nuevo Stage
    stage.initModality(Modality.APPLICATION_MODAL); // Visualiza el stage y bloque cualquier otro.
    stage.setTitle(title);
    stage.setResizable(false);
    stage.setMinWidth(350);
    
    Image i = new Image ("interrogacion1.png");
    ImageView iv1 = new ImageView();
    iv1.setImage(i);
    iv1.setFitWidth(70);
    iv1.setPreserveRatio(true);
    
//         iv1.setSmooth(true);
//         iv1.setCache(true);
//    Rectangle2D viewportRect = new Rectangle2D(30, 35,80, 50);
//    iv1.setViewport(viewportRect);
         
       
    
    Label lbl = new Label(); // Crea un label
    lbl.setText(mensaje);
    lbl.setPadding(new  Insets (8, 0, 0, 20));
    lbl.setStyle(
                 "-fx-font-weight: bold;\n" +
                 "-fx-text-fill: #333333;\n" +
                  "-fx-font-size: 14px;"
                );
    
    HBox imagen = new HBox ();
    imagen.getChildren().addAll(iv1,lbl);
    imagen.setPadding(new  Insets (0, 0, 0, 10));
   
    Button btnSi = new Button();
    btnSi.setText(textSi);
    btnSi.setOnAction(e -> btnSi_Clicked() ); //Utiliza expresiones Lambda, cuadno se presiona el boton ejecuta la funcion btnSi_Clicked
    btnSi.setPrefWidth(70);
    btnSi.setStyle("-fx-text-fill: White;\n" +
                   "-fx-font-family: \"Arial Narrow\";\n" +
                   "-fx-font-weight: bold;\n" +
                   "-fx-background-color: linear-gradient(#61a2b1, #2A5058);\n" +
                   "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );"  
                  );
    
    
    Button btnNo = new Button(); 
    btnNo.setText(textNo);
    btnNo.setOnAction(e -> btnNo_Clicked() );
    btnNo.setPrefWidth(70);
    btnNo.setStyle("-fx-text-fill: White;\n" +
                   "-fx-font-family: \"Arial Narrow\";\n" +
                   "-fx-font-weight: bold;\n" +
                   "-fx-background-color: linear-gradient(#61a2b1, #2A5058);\n" +
                   "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );"  
                  );
    
    HBox paneBtn = new HBox(20); // Crea un HBox y agrega los dos botones
    paneBtn.getChildren().addAll(btnSi, btnNo);
    paneBtn.setAlignment(Pos.CENTER);
    paneBtn.setPadding(new Insets (10,0 ,20 , 0));
    
    VBox pane = new VBox(20); // Crea un Vbox y agrega el HBox y el label
    pane.getChildren().addAll(imagen, paneBtn);
    pane.setAlignment(Pos.CENTER);
    pane.setPadding(new Insets (15, 0, 0, 0));
    
    
    Scene scene = new Scene(pane); // Instancia un escena nueva
    stage.setScene(scene);
   
    stage.showAndWait(); // Asegura que el cuadro de mensaje sea MODAL - ninguna otra parte del programa recibirá eventos hasta que el usuario haya cerrado el cuadro de mensaje.
    return btnYesClicked; // Retorna un Booleano
}

private static void btnSi_Clicked() {
    stage.close();
    btnYesClicked = true;
}

private static void btnNo_Clicked() {
    stage.close();
    btnYesClicked = false;
}
    
    
}
