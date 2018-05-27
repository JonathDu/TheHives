/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import hive.controller.plateau.PlateauController;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
    private final PlateauController gameController;
    private final Interface i;
    private final HBox bouton;
    private final VBox principal;

    private final Label message;
    private final String gagnant;

    private final HiveBouton recommencer;
    private final HiveBouton retourMenu;

    public FinPartie(Stage _primaryStage, Controller _controller, CacheImage _cacheImage, PlateauController _gameController, Interface _i, String joueurGagnant) {
        primaryStage = _primaryStage;
        controller = _controller;
        gameController = _gameController;
        i = _i;
        cacheImage = _cacheImage;
        principal = new VBox(50);
        bouton = new HBox(50);

        recommencer = new HiveBouton(cacheImage.getImage("Design/FenetrePlateau/BoutonRestart.png"), primaryStage);
        retourMenu = new HiveBouton(cacheImage.getImage("Design/FenetrePlateau/bouttonRetourMenu.png"), primaryStage);

        gagnant = joueurGagnant;
        message = new Label();

        setTextWithCurrentLanguage();
        setHandlers();
        placerObjetsGraphiques();

        this.getChildren().add(principal);
    }

    private void setTextWithCurrentLanguage() {
        if (gagnant != null) {
            message.setText(gagnant + " " + controller.gestionnaireLangage.getText("text_gagne"));
        } else {
            message.setText(controller.gestionnaireLangage.getText("text_egalite"));
        }

    }

    private void setHandlers() {
        recommencer.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            i.panePrincipale.getChildren().remove(this);
            gameController.restart();
        });

        retourMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            i.panePrincipale.getChildren().remove(this);
            controller.goToMenu();
        });
    }

    private void placerObjetsGraphiques() {
        Image fond = cacheImage.getImage("Design/MenuPrincipaux/panneauTheHive.png");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundFond = new BackgroundImage(fond, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        principal.setBackground(new Background(backgroundFond));

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
