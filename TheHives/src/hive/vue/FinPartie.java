/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import hive.controller.plateau.PlateauController;
import hive.model.players.TeamColor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author jonathan
 */
public class FinPartie extends Parent {

    private final Stage primaryStage;
    private final Controller controller;
    private final CacheImage cacheImage;
    private final Scene scene;
    private final PlateauController gameController;
    private final Interface i;
    private final HBox bouton;
    private final VBox principal;
    public final HBox image;
    private final GridPane ecran;
    public ImageView view;

    private final Label message;
    private final String gagnant;

    private final HiveBouton recommencer;
    private final HiveBouton retourMenu;
    private final HiveBouton refaireDernier;

    public FinPartie(Scene _scene, Stage _primaryStage, Controller _controller, CacheImage _cacheImage, PlateauController _gameController, Interface _i, String joueurGagnant, TeamColor couleur) {
        primaryStage = _primaryStage;
        scene = _scene;
        controller = _controller;
        gameController = _gameController;
        i = _i;
        cacheImage = _cacheImage;
        principal = new VBox(30);
        bouton = new HBox(30);
        image = new HBox(30);
        if (couleur != null) {
            if (couleur == TeamColor.BLACK) {
                view = new ImageView(cacheImage.getImage("FenetrePlateau/VictoireDuNoir.png"));

            } else {
                view = new ImageView(cacheImage.getImage("FenetrePlateau/VictoireDuBlanc.png"));

            }
            view.setPreserveRatio(true);
            view.fitHeightProperty().bind(scene.heightProperty().divide(2));
            image.getChildren().add(view);
        }

        recommencer = new HiveBouton(cacheImage.getImage("FenetrePlateau/BoutonRestart.png"), scene);
        retourMenu = new HiveBouton(cacheImage.getImage("FenetrePlateau/bouttonRetourMenu.png"), scene);
        refaireDernier = new HiveBouton(cacheImage.getImage("FenetrePlateau/bouttonRetourMenu.png"), scene);

        gagnant = joueurGagnant;
        message = new Label();

        Rectangle rec = new Rectangle();
        rec.widthProperty().bind(scene.widthProperty());
        rec.heightProperty().bind(scene.heightProperty());
        rec.setFill(Color.BLACK);
        rec.setOpacity(0.6);
        rec.setSmooth(true);
        this.getChildren().add(rec);

        ecran = new GridPane();
        ecran.prefWidthProperty().bind(primaryStage.widthProperty());
        ecran.prefHeightProperty().bind(primaryStage.heightProperty());
        ecran.minHeightProperty().bind(primaryStage.heightProperty());
        ecran.setAlignment(Pos.CENTER);
        setTextWithCurrentLanguage();
        setHandlers();
        placerObjetsGraphiques();

        ecran.getChildren().add(principal);
        principal.setAlignment(Pos.TOP_CENTER);
        this.getChildren().add(ecran);
    }

    private void setTextWithCurrentLanguage() {
        if (gagnant != null) {
            message.setText(gagnant + " " + controller.gestionnaireLangage.getText("text_gagne"));
        } else {
            message.setText(controller.gestionnaireLangage.getText("text_egalite"));
        }

        message.setWrapText(true);
        message.setTextAlignment(TextAlignment.JUSTIFY);
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
        refaireDernier.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            for (int i = 0; i < 4; i++) {
                gameController.undo();

            }
            gameController.start();
            this.setVisible(false);
        });
    }

    private void placerObjetsGraphiques() {
        principal.prefWidthProperty().bind(ecran.widthProperty());
        principal.prefHeightProperty().bind(ecran.heightProperty());

        message.maxWidthProperty().bind(principal.widthProperty());
        message.setTextFill(Color.WHITE);
        message.setAlignment(Pos.CENTER);
        message.setPadding(new Insets(0));
        message.prefHeightProperty().bind(ecran.heightProperty().divide(4));

        recommencer.setSize(ecran.widthProperty().divide(16));
        retourMenu.setSize(ecran.widthProperty().divide(16));
        refaireDernier.setSize(ecran.widthProperty().divide(16));

        bouton.getChildren().add(recommencer);
        bouton.getChildren().add(refaireDernier);
        bouton.getChildren().add(retourMenu);

        bouton.prefWidthProperty().bind(principal.widthProperty());
        bouton.setAlignment(Pos.BOTTOM_CENTER);
        bouton.prefHeightProperty().bind(principal.heightProperty().divide(4));
        bouton.setPadding(new Insets(30));

        image.setAlignment(Pos.CENTER);
        image.prefHeightProperty().bind(principal.heightProperty().divide(2));

        recommencer.setAlignment(Pos.CENTER);
        retourMenu.setAlignment(Pos.CENTER);

        principal.getChildren().add(image);
        principal.getChildren().add(message);
        principal.getChildren().add(bouton);

    }
}
