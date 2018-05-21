/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.thehives.TheHives;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author jonathan
 */
public class InterfacePlateauTool extends Parent {

    Button boutonHome;
    Button boutonSave;
    Button boutonReplay;
    Button boutonAnnuler;
    Button boutonRejouer;
    Button boutonConseil;
    Button boutonParam;
    Button pleinEcran;
    BorderPane pane;

    HBox gauche;
    HBox centre;
    HBox droite;
    String j1;
    String j2;
    StackPane centrage;
    
    int width;
    int tailleDeCase;

    CacheImage c;

    public InterfacePlateauTool(CacheImage c, Stage stage, TheHives i, String j1, String j2) {
        width = (int) stage.getWidth();
        tailleDeCase = width / 8;
        this.c = c;
        this.j1 = j1;
        this.j2 = j2;

        pane = new BorderPane();
        pane.prefWidthProperty().bind(stage.widthProperty());

        gauche = new HBox();
        droite = new HBox();

        boutonSave = creerBouton("BoutonDisquette.png");
        boutonHome = creerBouton("bouttonRetourMenu.png");
        boutonAnnuler = creerBouton("FlecheUndo.png");
        boutonParam = creerBouton("BouttonParametre.png");
        boutonConseil = creerBouton("Ampoule.png");
        boutonReplay = creerBouton("FlecheRedo.png");
        pleinEcran = creerBouton("pleinEcran.png");

        boutonHome.setOnMouseClicked(value -> {
            i.goToMenu();
        });

        boutonParam.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            Preferences p = new Preferences(stage, i);
            pane.getChildren().add(p);
            StackPane pref = new StackPane();
            Image imageQ = c.getImage("exit3.png");
            ImageView ImQ = new ImageView(imageQ);
            ImQ.setFitHeight(40);
            ImQ.setPreserveRatio(true);
            pref.getChildren().add(ImQ);
            pref.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event1) -> {
                pane.getChildren().remove(pane.getChildren().size() - 2, pane.getChildren().size());
                i.goToPlateau(j1, j2);
            });
            AnchorPane.setRightAnchor(pref, (double) 5);
            AnchorPane.setTopAnchor(pref, (double) 5);
            pane.getChildren().add(pref);
        });

        pleinEcran.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            i.pleinEcran = 1;
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("Sortie de plein écran - esc");
        });

        centrage = new StackPane();

        centrage.getChildren().addAll(boutonConseil);

        pane.setLeft(gauche);
        pane.setRight(droite);
        pane.setCenter(centrage);

        gauche.getChildren().add(boutonHome);
        gauche.getChildren().add(boutonSave);
        droite.getChildren().add(boutonParam);
        droite.getChildren().add(pleinEcran);

        this.getChildren().add(pane);
    }

    private Button creerBouton(String path) {
        Button bouton = new Button();
        ImageView image;
        image = new ImageView(c.getImage(path));
        image.setFitHeight(tailleDeCase/2);
        image.setFitWidth(tailleDeCase/2*1.07);
        image.setSmooth(true);
        image.setCache(true);
        bouton.setGraphic(image);
        bouton.setBackground(Background.EMPTY);
        return bouton;
    }

}
