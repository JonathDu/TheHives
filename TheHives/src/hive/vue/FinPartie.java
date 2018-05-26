/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import hive.controller.plateauscene.game.GameController;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author jonathan
 */
public class FinPartie extends Parent {

    private final Stage primaryStage;
    private final Controller controller;
    private final CacheImage cacheImage;
    private final GameController gameController;
    private final Interface i;
    private final HBox bouton;
    private final VBox principal;

    private final Label message;
    private final String gagnant;

    private final HiveBouton recommencer;
    private final HiveBouton retourMenu;

    public FinPartie(Stage _primaryStage, Controller _controller, CacheImage _cacheImage, GameController _gameController, Interface _i, String joueurGagnant) {
        primaryStage = _primaryStage;
        controller = _controller;
        gameController = _gameController;
        i = _i;
        cacheImage = _cacheImage;
        principal = new VBox(50);
        bouton = new HBox(50);

        recommencer = new HiveBouton(cacheImage.getImage("Design/FenetrePlateau/replay.png"), primaryStage);
        retourMenu = new HiveBouton(cacheImage.getImage("Design/FenetrePlateau/bouttonRetourMenu.png"), primaryStage);

        gagnant = joueurGagnant;
        message = new Label();

        setTextWithCurrentLanguage();
        setHandlers();
        placerObjetsGraphiques();

        this.getChildren().add(principal);
    }


    private void setTextWithCurrentLanguage() {
        message.setText(gagnant + " " + controller.gestionnaireLangage.getText("text_gagne"));

    }

    private void setHandlers() {
        recommencer.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            i.panePrincipale.getChildren().remove(this);
            gameController.restart();
        });

        retourMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            controller.goToMenu();
        });
    }

    private void placerObjetsGraphiques() {

        principal.prefWidthProperty().bind(primaryStage.widthProperty());
        principal.prefHeightProperty().bind(primaryStage.heightProperty());

        principal.getChildren().add(message);

        bouton.getChildren().add(recommencer);
        bouton.getChildren().add(retourMenu);

        recommencer.setAlignment(Pos.CENTER);
        retourMenu.setAlignment(Pos.CENTER);

        principal.getChildren().add(bouton);

        principal.setAlignment(Pos.CENTER);
        bouton.setAlignment(Pos.CENTER);
    }
}
