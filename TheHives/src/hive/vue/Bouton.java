/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

/**
 *
 * @author Adeline
 */
class Bouton extends Parent {

    public Bouton(Scene scene, Stage primaryStage, Controller controller, String type, Pane pane, String origin) {

        int height = (int) primaryStage.getHeight();
        int width = (int) primaryStage.getWidth();

        Image imageCase = new Image(getClass().getResourceAsStream("rsc/images/case.png"));
        DropShadow shadow = new DropShadow();
        int tailleDeCase;
        if (width / 8 > height / 6) {
            tailleDeCase = height / 6;
        } else {
            tailleDeCase = width / 8;
        }

        StackPane bouton = new StackPane();
        ImageView caseIm = new ImageView(imageCase);
        caseIm.setFitHeight(tailleDeCase);
        caseIm.setFitWidth(tailleDeCase);
        bouton.getChildren().add(caseIm);

        if (type == "menu") {

            Image image = new Image(getClass().getResourceAsStream("rsc/images/menu2.png"));
            ImageView Im = new ImageView(image);
            Im.setFitHeight(tailleDeCase / 2.5);
            Im.setFitWidth(tailleDeCase / 2.5);
            bouton.getChildren().add(Im);
            bouton.setLayoutX(0);
            bouton.setLayoutY(0);
            bouton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                System.out.println("Menu ! ");
                controller.goToMenu();
            });

        } else if (type == "preferences") {
            Image image = new Image(getClass().getResourceAsStream("rsc/images/settings1.png"));
            ImageView Im = new ImageView(image);
            Im.setFitHeight(tailleDeCase / 2.5);
            Im.setFitWidth(tailleDeCase / 2.5);
            bouton.getChildren().add(Im);
            bouton.setLayoutX(width - tailleDeCase * 2);
            bouton.setLayoutY(0);
            bouton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("Préférences ! ");

                    Preferences p = new Preferences(scene, controller, new CacheImage());
                    pane.getChildren().add(p);
                }
            });
        } else if (type == "sortie") {
            Image image = new Image(getClass().getResourceAsStream("rsc/images/exit3.png"));
            ImageView Im = new ImageView(image);
            Im.setFitHeight(tailleDeCase / 2.5);
            Im.setFitWidth(tailleDeCase / 2.5);
            bouton.getChildren().add(Im);
            bouton.setLayoutX(width - tailleDeCase);
            bouton.setLayoutY(0);
            bouton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                System.out.println("Sortie ! ");
                Platform.exit();
            });
        } else if (type == "ecran") {
            Image image = new Image(getClass().getResourceAsStream("rsc/images/full.png"));
            ImageView Im = new ImageView(image);
            Im.setFitHeight(tailleDeCase / 2.5);
            Im.setFitWidth(tailleDeCase / 2.5);
            bouton.getChildren().add(Im);
            bouton.setLayoutX(width - tailleDeCase * 1.5);
            bouton.setLayoutY(tailleDeCase / 1.33);
            bouton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                System.out.println("Plein Écran ! ");
                primaryStage.setFullScreen(true);
                primaryStage.setFullScreenExitHint("Sortie de plein écran - esc");
            });
        }

        this.getChildren().add(bouton);

    }
}
