/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import java.awt.Dimension;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author jonathan
 */
public abstract class Interface extends Parent {

    Controller controller;
    Scene scene;
    Stage primaryStage;
    Pane panePrincipale;
    Background background;
    private final DoubleProperty fontSize = new SimpleDoubleProperty(10);

    int height;
    int width;
    String police;
    CacheImage c;

    double max_screen_height;
    double max_screen_width;
    int tailleDeCase;


    HiveBouton boutonPreference;
    HiveBouton boutonPleinEcran;
    HiveBouton boutonRetourMenu;

    HBox droite;

    public Interface(Scene scene, Stage primaryStage, Controller controller, CacheImage cacheImage) {

        /* INITIALISATION DES OBJETS */
        this.controller = controller;
        this.scene = scene;
        this.primaryStage = primaryStage;
        panePrincipale = new Pane();
        c = cacheImage;
        panePrincipale.prefHeightProperty().bind(scene.heightProperty());
        panePrincipale.prefWidthProperty().bind(scene.widthProperty());

        height = (int) scene.getHeight();
        width = (int) scene.getWidth();
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        max_screen_height = dimension.getHeight();
        max_screen_width = dimension.getWidth();
        tailleDeCase = (width / 8 > height / 6) ? height / 6 : width / 8;


        police = "Papyrus";

        fontSize.bind(scene.heightProperty().divide(30));
        this.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";",
                "-fx-font-family: ", police, ";"));

        Tooltip t = new Tooltip("Retour au menu");
        Tooltip t1 = new Tooltip("Plein écran");
        Tooltip t2 = new Tooltip("Réglages");

        boutonPreference = new HiveBouton(c.getImage("Design/MenuPrincipaux/BouttonParametre.png"), scene);
        boutonPleinEcran = new HiveBouton(c.getImage("Design/MenuPrincipaux/pleinEcran.png"), scene);
        boutonRetourMenu = new HiveBouton(c.getImage("Design/FenetrePlateau/bouttonRetourMenu.png"), scene);
        Tooltip.install(boutonRetourMenu, t);
        Tooltip.install(boutonPleinEcran, t1);
        Tooltip.install(boutonPreference, t2);

        droite = new HBox(5);

        AnchorPane.setRightAnchor(droite, (double) 5);
        AnchorPane.setTopAnchor(droite, (double) 5);
        droite.getChildren().add(boutonPreference);
        droite.getChildren().add(boutonPleinEcran);

        setBackground();

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

    private void setBackground() {
        Image fond = controller.settingsGesture.get("theme").equals("Jour") ? c.getImage("MaquetteFond.png") : c.getImage("Design/Fond/fondNuit.png");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
        BackgroundImage backgroundFond = new BackgroundImage(fond, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        background = new Background(backgroundFond);
        panePrincipale.setBackground(background);
    }

    public abstract void setTextWithCurrentLanguage();

    public void majRetourPreference() {
        setBackground();
        setTextWithCurrentLanguage();
    }
}
