/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
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

    StackPane centrage;

    CacheImage c;

    public InterfacePlateauTool(CacheImage c, Stage stage) {
        this.c = c;

        pane = new BorderPane();
        pane.prefWidthProperty().bind(stage.widthProperty());

        gauche = new HBox();
        droite = new HBox();
        
        boutonSave = creerBouton("BoutonDisquette.png");
        boutonHome = creerBouton("bouttonRetourMenu.png");
        boutonAnnuler =creerBouton("FlecheUndo.png");
        boutonParam = creerBouton("BouttonParametre.png");
        boutonConseil = creerBouton("Ampoule.png");
        boutonReplay = creerBouton("FlecheRedo.png");
        pleinEcran = creerBouton("pleinEcran.png");

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
        image.setFitWidth(40);
        image.setPreserveRatio(true);
        image.setSmooth(true);
        image.setCache(true);
        bouton.setGraphic(image);
        bouton.setBackground(Background.EMPTY);
        return bouton;
    }

}
