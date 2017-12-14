/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;


import Logica.Cliente;
 import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
/**
 *
 * @author Mati F
 */
public class FxUtilTest {
   



    /**
     * metodo para comparar dos string devuelve true si el primer string contiene al segundo
     * @param typedText
     * @param objectToCompare
     * @return 
     */
       public static boolean matches(String typedText, String objectToCompare){
           boolean b = false;
           if (objectToCompare.toLowerCase().contains(typedText.toLowerCase())){
               b = true;
           }
           
           
           return b;
       }
        
    

    public static void autoCompleteComboBoxPlus(ComboBox comboBox ) {
        ObservableList<String> data = comboBox.getItems(); // data es la lista de todos lo clientes

        comboBox.setEditable(true); // permite hacer editable al conbo box
        comboBox.getEditor().focusedProperty().addListener(observable -> { // metodo que sirve para validar q el texto no se nulo y la opcion elegida sea mayor a 0
            if (comboBox.getSelectionModel().getSelectedIndex() < 0) {
                comboBox.getEditor() .setText(null);
            }
        });
        comboBox.addEventHandler(KeyEvent.KEY_PRESSED, t -> comboBox.hide()); //cuando presiona un tecla se esconde el comboBox 
        comboBox.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() { // sobreescribe el metodo "handle" para cuando la tecla es soltada

            private boolean moveCaretToPos = false;
            private int caretPos;

            @Override
            public void handle(KeyEvent event) {
                
                // validaciones por si se presionan otras teclas no deseadas
                if (event.getCode() == KeyCode.UP) {
                    caretPos = -1;
                    moveCaret(comboBox.getEditor().getText().length());
                    return;
                } else if (event.getCode() == KeyCode.DOWN) {
                    if (!comboBox.isShowing()) {
                        comboBox.show();
                    }
                    caretPos = -1;
                    moveCaret(comboBox.getEditor().getText().length());
                    return;
                } else if (event.getCode() == KeyCode.BACK_SPACE) {
                    moveCaretToPos = true;
                    caretPos = comboBox.getEditor().getCaretPosition();
                } else if (event.getCode() == KeyCode.DELETE) {
                    moveCaretToPos = true;
                    caretPos = comboBox.getEditor().getCaretPosition();
                } else if (event.getCode() == KeyCode.ENTER) {
                    return;
                }

                if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT || event.getCode().equals(KeyCode.SHIFT) || event.getCode().equals(KeyCode.CONTROL)
                        || event.isControlDown() || event.getCode() == KeyCode.HOME
                        || event.getCode() == KeyCode.END || event.getCode() == KeyCode.TAB) {
                    return;
                }

                
                
                
                
                /////////////
                ObservableList<String> list = FXCollections.observableArrayList(); 
                for (String i : data) { // recorre los clientes y pregunta si los datos no son nulos y si se encontro alguna coincidencia...si es asi guarda todo el una observableList
                    if (i != null && comboBox.getEditor().getText() != null && matches(comboBox.getEditor().getText(), i)) {
                        list.add(i);
                    }
                }
                String t = comboBox.getEditor().getText(); //guarda en t lo ingresado hasta el momento en el comboBox

                comboBox.setItems(list); // setea la lista con todas las coincidencias
                comboBox.getEditor().setText(t); // vuelve reescribir lo ingresado el en comboBox al comboBox
                if (!moveCaretToPos) { //setea la variable caretPos en -1, en caso que no halla pasado por alguna validacion anterior
                    caretPos = -1;
                }
                moveCaret(t.length());
                if (!list.isEmpty()) { // muestra los resultados solo si hubo una coincidencia
                    comboBox.show();
                }
            }

            private void moveCaret(int textLength) {
                if (caretPos == -1) {
                    comboBox.getEditor().positionCaret(textLength);
                } else {
                    comboBox.getEditor().positionCaret(caretPos);
                }
                moveCaretToPos = false;
            }
        });
    }

    public static<T> T getComboBoxValue(ComboBox<T> comboBox){
        if (comboBox.getSelectionModel().getSelectedIndex() < 0) {
            return null;
        } else {
            return comboBox.getItems().get(comboBox.getSelectionModel().getSelectedIndex());
        }
    }


}
