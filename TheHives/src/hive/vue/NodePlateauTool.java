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
import javafx.event.EventType;
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
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 *
 * @author jonathan
 */
public class NodePlateauTool extends Parent {

    HiveBouton boutonHome;
    HiveBouton boutonSave;
    HiveBouton boutonReplay;
    HiveBouton boutonAnnuler;
    HiveBouton boutonRecommencer;
    HiveBouton boutonConseil;
    HiveBouton boutonParam;
    HiveBouton boutonPleinEcran;
    HiveBouton boutonRegle;
    BorderPane pane;

    HBox gauche;
    HBox centre;
    HBox droite;
    String j1;
    String j2;

    int width;

    CacheImage c;

    public NodePlateauTool(CacheImage c, Stage stage, Controller controller, String j1, String j2, Game game, HiveBouton pleinEcran, HiveBouton param) {
        width = (int) stage.getWidth();
        this.c = c;
        this.j1 = j1;
        this.j2 = j2;

        String repertoire = "Design/FenetrePlateau/";

        pane = new BorderPane();
        pane.prefWidthProperty().bind(stage.widthProperty());

        gauche = new HBox();
        droite = new HBox();
        centre = new HBox();

        boutonSave = new HiveBouton(c.getImage(repertoire + "BoutonDisquette.png"), width);
        boutonHome = new HiveBouton(c.getImage(repertoire + "bouttonRetourMenu.png"), width);
        boutonAnnuler = new HiveBouton(c.getImage(repertoire + "FlecheUndo.png"), width);
        boutonConseil = new HiveBouton(c.getImage(repertoire + "Ampoule.png"), width);
        boutonReplay = new HiveBouton(c.getImage(repertoire + "FlecheRedo.png"), width);
        boutonPleinEcran = pleinEcran;
        boutonRegle = new HiveBouton(c.getImage(repertoire + "Boutonlivre.png"), width);
        boutonParam = param;

        boutonHome.setOnMouseClicked(value -> {
            Stage primaryStage = new Stage();
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            NodePopup root = new  NodePopup("Etes vous sur de vouloir quitter la partie ?", "Quitter", "Sauvegarder et quitter", "Annuler");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            
            root.valider.addEventHandler(EventType.ROOT, (event)->{
                
                
                
                
            });


            //controller.goToMenu();
        });

        boutonSave.setOnMouseClicked(value -> {
            controller.enregistrerGame(game, "test.xml");
        });

        boutonRegle.setOnMouseClicked(value -> {

            Stage primaryStage = new Stage();
            Parent root;
            root = new InterfaceRegles(stage, controller, c, true);
            primaryStage.setTitle("Regles");
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.show();
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
        droite.getChildren().add(boutonPleinEcran);
        centre.getChildren().add(boutonAnnuler);
        centre.getChildren().add(boutonConseil);
        centre.getChildren().add(boutonReplay);
        this.getChildren().add(pane);
    }

    public void majRetourPreference() {
    }

}
