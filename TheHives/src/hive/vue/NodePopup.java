/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author jonathan
 */
public class NodePopup extends Parent {

    private String message;
    private String messageValider;
    private String messageQuitter;
    private String messageValiderQuitter;

    private HBox horizontal;
    private VBox vertical;

    public Button valider;
    public Button quitter;
    public Button validerSave;

    public NodePopup(String _message, String _messageValider, String _messageQuitter, String _messageValideQuitter) {
        message = _message;
        messageValider = _messageValider;
        messageQuitter = _messageQuitter;
        messageValiderQuitter = _messageValideQuitter;

        horizontal = new HBox();
        vertical = new VBox();
        
        

        quitter = new Button(messageQuitter);
        
        valider = new Button(messageValider);
        validerSave = new Button(messageValiderQuitter);

        horizontal.getChildren().add(quitter);
        horizontal.getChildren().add(valider);
        horizontal.getChildren().add(validerSave);

        horizontal.setAlignment(Pos.CENTER);
        vertical.getChildren().add(new Label(message));
        vertical.getChildren().add(horizontal);

        this.getChildren().add(vertical);

    }

}
