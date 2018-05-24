/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author jonathan
 */
public abstract class Interface extends Parent {

    Controller controller;
    Pane panePrincipale;
    Background background;
    int height;
    int width;
    String police;
    CacheImage c;

    HiveBouton boutonPreference;
    HiveBouton boutonPleinEcran;
    HiveBouton boutonRetourMenu;

    public Interface(Stage primaryStage, Controller controller, CacheImage cacheImage) {
        
        /* INITIALISATION DES OBJETS */
        this.controller = controller;
        panePrincipale = new Pane();
        c = cacheImage;

        Image fond = controller.typeTheme.equals("Jour") ? c.getImage("Design/Fond/fondMontagne.png") : c.getImage("Design/Fond/fondNuit.png");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, false, true);
        BackgroundImage backgroundFond = new BackgroundImage(fond, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        background = new Background(backgroundFond);

        height = (int) primaryStage.getHeight();
        width = (int) primaryStage.getWidth();
        police = controller.getPolice();

        boutonPreference = new HiveBouton(c.getImage("Design/MenuPrincipaux/BouttonParametre.png"), width );
        boutonPleinEcran = new HiveBouton(c.getImage("Design/MenuPrincipaux/pleinEcran.png"), width);
        boutonRetourMenu = new HiveBouton(c.getImage("Design/FenetrePlateau/bouttonRetourMenu.png"), width);
        
        if (controller.pleinEcran == 1) {
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("Sortie de plein écran - esc");
        }

        panePrincipale.setBackground(background);
        
        
        /* HANDLERS */
        boutonPreference.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            panePrincipale.getChildren().add(controller.getPreferences());
        });

        boutonPleinEcran.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            controller.pleinEcran = 1;
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("Sortie de plein écran - esc");
        });

        boutonRetourMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            controller.goToMenu();
        });
    }

}


