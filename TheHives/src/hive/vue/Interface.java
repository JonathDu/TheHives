/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import java.awt.Dimension;
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
    Stage primaryStage;
    Pane panePrincipale;
    Background background;
    int height;
    int width;
    String police;
    CacheImage c;

    double max_screen_height;
    double max_screen_width;
    int tailleDeCase;
    int maxJoueur;
    int minJoueur;

    HiveBouton boutonPreference;
    HiveBouton boutonPleinEcran;
    HiveBouton boutonRetourMenu;

    public Interface(Stage primaryStage, Controller controller, CacheImage cacheImage) {

        /* INITIALISATION DES OBJETS */
        this.controller = controller;
        this.primaryStage = primaryStage;
        panePrincipale = new Pane();
        c = cacheImage;

        height = (int) primaryStage.getHeight();
        width = (int) primaryStage.getWidth();
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        max_screen_height = dimension.getHeight();
        max_screen_width = dimension.getWidth();
        tailleDeCase = (width / 8 > height / 6) ? height / 6 : width / 8;
        maxJoueur = (int) ((int) width / 2.5);
        minJoueur = maxJoueur / 2;

        Image fond = controller.typeTheme.equals("Jour") ? c.getImage("MaquetteFond.png") : c.getImage("Design/Fond/fondNuit.png");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, false, true);
        BackgroundImage backgroundFond = new BackgroundImage(fond, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        background = new Background(backgroundFond);

        police = controller.getPolice();

        boutonPreference = new HiveBouton(c.getImage("Design/MenuPrincipaux/BouttonParametre.png"), width);
        boutonPleinEcran = new HiveBouton(c.getImage("Design/MenuPrincipaux/pleinEcran.png"), width);
        boutonRetourMenu = new HiveBouton(c.getImage("Design/FenetrePlateau/bouttonRetourMenu.png"), width);

        panePrincipale.setBackground(background);
        Preferences pref = controller.getPreferences();
        pref.setVisible(false);
        panePrincipale.getChildren().add(pref);


        /* HANDLERS */
        boutonPreference.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            pref.toFront();
            pref.setVisible(true);
        });

        boutonPleinEcran.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            primaryStage.setMaximized(!primaryStage.isMaximized());
        });

        boutonRetourMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            controller.goToMenu();
        });
        
        this.getChildren().add(panePrincipale);
    }
}
