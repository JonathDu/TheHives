/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import static javafx.geometry.Pos.CENTER;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Adeline
 */
public class InterfaceMenu extends Interface {

    private final Label newGame;
    private final Label chargerPartie;
    private final Label statistiques;
    private final Label credits;
    private final Label regles;

    public InterfaceMenu(Scene scene, Stage primaryStage, Controller controller, CacheImage c) {
        super(scene, primaryStage, controller, c);

        AnchorPane pane = new AnchorPane();
        pane.prefWidthProperty().bind(scene.widthProperty());
        pane.prefHeightProperty().bind(scene.heightProperty());

        Image hive = c.getImage("Design/MenuPrincipaux/TheHives.png");
        ImageView hiveIm = new ImageView(hive);
        hiveIm.fitWidthProperty().bind(scene.widthProperty().divide(5));
        hiveIm.setPreserveRatio(true);

        AnchorPane.setLeftAnchor(hiveIm, (double) 5);
        AnchorPane.setTopAnchor(hiveIm, (double) 5);
        pane.getChildren().add(hiveIm);

        StackPane hex = new StackPane();
        hex.prefHeightProperty().bind(scene.heightProperty());
        hex.prefWidthProperty().bind(scene.widthProperty());
        Image hexagone = c.getImage("Design/MenuPrincipaux/Hexagone.png");
        ImageView hexagoneIm = new ImageView(hexagone);
        hexagoneIm.setPreserveRatio(true);
        hexagoneIm.fitWidthProperty().bind(scene.widthProperty().divide(3));
        hex.getChildren().add(hexagoneIm);

        newGame = new Label();
        chargerPartie = new Label();
        statistiques = new Label();
        credits = new Label();
        regles = new Label();

        setTextWithCurrentLanguage();


        VBox menu_hex = new VBox(15);
        menu_hex.prefWidthProperty().bind(scene.widthProperty());
        menu_hex.prefHeightProperty().bind(scene.heightProperty());
        menu_hex.setAlignment(Pos.CENTER);
        StackPane NewGame = new StackPane();

        Image fleche = c.getImage("Design/MenuPrincipaux/FlecheDuMenuDansHexagone.png");
        ImageView flecheImNG = new ImageView(fleche);
        flecheImNG.fitWidthProperty().bind(scene.widthProperty().divide(3).subtract(20));
        flecheImNG.setPreserveRatio(true);



        newGame.setTextFill(Color.web("#fbe5b5"));
        newGame.setAlignment(CENTER);
        NewGame.getChildren().add(flecheImNG);
        NewGame.getChildren().add(newGame);
        NewGame.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            controller.goToChoixJoueur();
        });
        menu_hex.getChildren().add(NewGame);
        StackPane ChargerPartie = new StackPane();
        ImageView flecheImCP = new ImageView(fleche);
        flecheImCP.fitWidthProperty().bind(scene.widthProperty().divide(3).subtract(20));
        flecheImCP.setPreserveRatio(true);

        chargerPartie.setTextFill(Color.web("#fbe5b5"));
        chargerPartie.setAlignment(CENTER);
        ChargerPartie.getChildren().add(flecheImCP);
        ChargerPartie.getChildren().add(chargerPartie);
        ChargerPartie.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            try {
                controller.goToChargerPartie();
            } catch (IOException ex) {
                Logger.getLogger(InterfaceMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        menu_hex.getChildren().add(ChargerPartie);
        StackPane Regles = new StackPane();
        ImageView flecheImR = new ImageView(fleche);

        flecheImR.fitWidthProperty().bind(scene.widthProperty().divide(3).subtract(20));
        flecheImR.setPreserveRatio(true);

        regles.setTextFill(Color.web("#fbe5b5"));
        regles.setAlignment(CENTER);
        Regles.getChildren().add(flecheImR);
        Regles.getChildren().add(regles);
        Regles.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            controller.goToRegles();
        });
        menu_hex.getChildren().add(Regles);
        hex.setAlignment(CENTER);
        StackPane.setAlignment(menu_hex, CENTER);
        hex.getChildren().add(menu_hex);
        pane.getChildren().add(hex);

        StackPane Statistiques = new StackPane();
        Image gauche = c.getImage("Design/MenuPrincipaux/FlecheEnBasGauche.png");
        ImageView gaucheIm = new ImageView(gauche);

        gaucheIm.fitWidthProperty().bind(scene.widthProperty().divide(4));
        gaucheIm.setPreserveRatio(true);

        statistiques.setTextFill(Color.web("#fbe5b5"));
        statistiques.setAlignment(CENTER);
        Statistiques.getChildren().add(gaucheIm);
        Statistiques.getChildren().add(statistiques);
        Statistiques.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            controller.goToStat();
        });
        AnchorPane.setLeftAnchor(Statistiques, (double) 5);
        AnchorPane.setBottomAnchor(Statistiques, (double) 30);
        pane.getChildren().add(Statistiques);

        StackPane Credits = new StackPane();
        Image droite = c.getImage("Design/MenuPrincipaux/FlecheEnBasDroite.png");
        ImageView droiteIm = new ImageView(droite);
        droiteIm.fitWidthProperty().bind(scene.widthProperty().divide(4));
        droiteIm.setPreserveRatio(true);

        credits.setTextFill(Color.web("#fbe5b5"));
        credits.setAlignment(CENTER);

        Credits.getChildren().add(droiteIm);
        Credits.getChildren().add(credits);
        Credits.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            controller.goToCredits();
        });
        AnchorPane.setRightAnchor(Credits, (double) 5);
        AnchorPane.setBottomAnchor(Credits, (double) 30);
        pane.getChildren().add(Credits);

        AnchorPane.setRightAnchor(this.droite, (double) 5);
        AnchorPane.setTopAnchor(this.droite, (double) 5);
        pane.getChildren().add(this.droite);

        this.panePrincipale.getChildren().add(pane);

    }

    @Override
    public void setTextWithCurrentLanguage() {
        newGame.setText(controller.gestionnaireLangage.getText("text_nouvelle_partie"));
        chargerPartie.setText(controller.gestionnaireLangage.getText("text_reprendre_partie"));
        statistiques.setText(controller.gestionnaireLangage.getText("text_statistiques"));
        credits.setText(controller.gestionnaireLangage.getText("text_credit"));
        regles.setText(controller.gestionnaireLangage.getText("text_regles"));
    }
}
