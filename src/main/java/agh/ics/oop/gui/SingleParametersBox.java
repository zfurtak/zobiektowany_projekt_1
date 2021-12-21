package agh.ics.oop.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class SingleParametersBox {
    private final VBox vbox;
    TextField textField;

    public SingleParametersBox(String labelTitle, String value){
        Label label = new Label(labelTitle);
        label.setFont(new Font("Arial", 16));
        textField = new TextField(value);
        textField.setMaxWidth(300);
        vbox = new VBox(5);
        vbox.getChildren().addAll(label, textField);
        vbox.setAlignment(Pos.CENTER);
    }

    public VBox getSingleBox(){
        return vbox;
    }

    public String getValue(){
        return textField.getText();
    }
}
