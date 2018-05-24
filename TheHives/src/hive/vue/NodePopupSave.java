/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import hive.model.game.Game;
import hive.model.players.TeamColor;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author jonathan
 */
public class NodePopupSave extends Parent {

    private String message;
    private String messageValider;
    private String messageQuitter;
    private String messageValiderQuitter;

    private Controller controller;
    private Stage stage;
    private Game game;

    private HBox horizontal;
    private VBox vertical;

    public Button valider;
    public Button quitter;
    public Button validerSave;

    public TextField field;

    public NodePopupSave(Controller controller, Stage primaryStage, Game game) {
        this.controller = controller;
        this.stage = primaryStage;
        this.game = game;

        horizontal = new HBox();
        vertical = new VBox();

        quitter = new Button("Annuler");
        valider = new Button("Sauvegarder");
        validerSave = new Button("Sauvegarder et quitter");

        quitter.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            stage.close();
        });

        valider.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            controller.enregistrerGame(game, field.getText() + ".xml");
            stage.close();
        });

        validerSave.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            controller.enregistrerGame(game, field.getText());
            stage.close();
            controller.goToMenu();
        });

        field = new TextField("Nom de la sauvegarde");

        horizontal.getChildren().add(quitter);
        horizontal.getChildren().add(valider);
        horizontal.getChildren().add(validerSave);

        horizontal.setAlignment(Pos.CENTER);
        vertical.getChildren().add(new Label(message));
        vertical.getChildren().add(field);
        vertical.getChildren().add(horizontal);

        this.getChildren().add(vertical);

    }
}
