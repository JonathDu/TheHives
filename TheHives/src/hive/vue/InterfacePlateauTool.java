/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import hive.model.game.Game;
import hive.model.players.decisions.Level;
import hive.thehives.TheHives;
import java.io.IOException;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
    Button boutonRecommencer;
    Button boutonConseil;
    Button boutonParam;
    Button pleinEcran;
    Button boutonRegle;
    BorderPane pane;

    HBox gauche;
    HBox centre;
    HBox droite;
    String j1;
    String j2;

    int width;
    int tailleDeCase;

    CacheImage c;

    public InterfacePlateauTool(CacheImage c, Stage stage, Controller controller, String j1, String j2, Game game) {
        width = (int) stage.getWidth();
        tailleDeCase = width / 8;
        this.c = c;
        this.j1 = j1;
        this.j2 = j2;

        pane = new BorderPane();
        pane.prefWidthProperty().bind(stage.widthProperty());

        gauche = new HBox();
        droite = new HBox();
        centre = new HBox();

        boutonSave = creerBouton("BoutonDisquette.png");
        boutonHome = creerBouton("bouttonRetourMenu.png");
        boutonAnnuler = creerBouton("FlecheUndo.png");
        boutonParam = creerBouton("BouttonParametre.png");
        boutonConseil = creerBouton("Ampoule.png");
        boutonReplay = creerBouton("FlecheRedo.png");
        pleinEcran = creerBouton("pleinEcran.png");
        boutonRegle = creerBouton("Boutonlivre.png");

        boutonHome.setOnMouseClicked(value -> {
            controller.goToMenu();
        });
        
        boutonSave.setOnMouseClicked(value -> {
            controller.enregistrerGame(game, "test.xml");
        });

        boutonRegle.setOnMouseClicked(value -> {

            Stage primaryStage = new Stage();
            Parent root;
            root = new InterfaceRegles(stage, controller, true);
            primaryStage.setTitle("Regles");
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.show();
            // Hide this current window (if this is what you want)
            //((Node) (value.getSource())).getScene().getWindow().hide();

        });

        boutonParam.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            Preferences p = new Preferences(stage, controller, new CacheImage());
            pane.getChildren().add(p);

        });

        pleinEcran.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            controller.pleinEcran = 1;
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("Sortie de plein écran - esc");
        });

        Group g = new Group();
        g.getChildren().add(centre);
        pane.setLeft(gauche);
        pane.setRight(droite);
        pane.setCenter(g);

        gauche.getChildren().add(boutonHome);
        gauche.getChildren().add(boutonSave);
        droite.getChildren().add(boutonRegle);
        droite.getChildren().add(boutonParam);
        droite.getChildren().add(pleinEcran);
        centre.getChildren().add(boutonAnnuler);
        centre.getChildren().add(boutonConseil);
        centre.getChildren().add(boutonReplay);
        this.getChildren().add(pane);
    }

    private Button creerBouton(String path) {
        Button bouton = new Button();
        ImageView image;
        image = new ImageView(c.getImage("Design/FenetrePlateau/" + path));
        image.setFitHeight(tailleDeCase / 2);
        image.setFitWidth(tailleDeCase / 2 * 1.07);
        image.setSmooth(true);
        image.setCache(true);
        bouton.setGraphic(image);
        bouton.setBackground(Background.EMPTY);
        return bouton;
    }

}
