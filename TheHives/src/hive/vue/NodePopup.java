/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import hive.controller.plateau.PlateauController;
import hive.model.Match;
import hive.model.game.Game;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author jonathan
 */
public class NodePopup extends Parent {

    private final String message;
    private final String messageValider;
    private final String messageQuitter;
    private final String messageValiderQuitter;

    private final Controller controller;
    private final Stage stage;
    private final Match match;
    private final PlateauController plateauController;

    private final HBox horizontal;
    private final VBox vertical;

    public Button valider;
    public Button quitter;
    public Button validerSave;

    public NodePopup(Controller controller, Stage primaryStage, Match match, PlateauController plateauController) {
        this.controller = controller;
        this.stage = primaryStage;
        this.match = match;
        this.plateauController = plateauController;

        message = controller.gestionnaireLangage.getText("text_question_quitter");
        messageValider = controller.gestionnaireLangage.getText("text_quitter");
        messageQuitter = controller.gestionnaireLangage.getText("text_annuler");
        messageValiderQuitter = controller.gestionnaireLangage.getText("text_quitter_et_sauvegarder");


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

        setHandler();

        this.getChildren().add(vertical);

    }

    private void setHandler() {
        valider.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {

            stage.close();
            plateauController.stop();
            controller.goToMenu();

        });

        quitter.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            stage.close();
        });

        validerSave.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            Stage saveStage = new Stage();
            saveStage.initModality(Modality.APPLICATION_MODAL);
            NodePopupSave rootSave = new NodePopupSave(controller, saveStage, match);
            saveStage.setScene(new Scene(rootSave));
            saveStage.show();

        });
    }

}
