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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

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

    public InterfaceMenu(Stage primaryStage, Controller controller, CacheImage c) {
        super(primaryStage, controller, c);

        AnchorPane pane = new AnchorPane();
        pane.prefWidthProperty().bind(primaryStage.widthProperty());
        pane.prefHeightProperty().bind(primaryStage.heightProperty());

        Image hive = c.getImage("Design/MenuPrincipaux/TheHives.png");
        ImageView hiveIm = new ImageView(hive);
//        hiveIm.setFitHeight(tailleDeCase * 1.3);
//        hiveIm.setFitWidth(tailleDeCase * 1.3 * 1.59);
        
        hiveIm.fitWidthProperty().bind(primaryStage.widthProperty().divide(5));
        hiveIm.setPreserveRatio(true);
        
        
        
        AnchorPane.setLeftAnchor(hiveIm, (double) 5);
        AnchorPane.setTopAnchor(hiveIm, (double) 5);
        pane.getChildren().add(hiveIm);

        StackPane hex = new StackPane();
        hex.prefHeightProperty().bind(primaryStage.heightProperty());
        hex.prefWidthProperty().bind(primaryStage.widthProperty());
        Image hexagone = c.getImage("Design/MenuPrincipaux/Hexagone.png");
        ImageView hexagoneIm = new ImageView(hexagone);
        hexagoneIm.setPreserveRatio(true);
        hexagoneIm.fitWidthProperty().bind(primaryStage.widthProperty().divide(3));
        hex.getChildren().add(hexagoneIm);

        newGame = new Label();
        chargerPartie = new Label();
        statistiques = new Label();
        credits = new Label();
        regles = new Label();

        setTextWithCurrentLanguage();

        double flecheLargeur = tailleDeCase * 4 - 30;
        double flecheHauteur = flecheLargeur / 7.24;
        double flecheLargeur_en_bas = (tailleDeCase * 8) / 3;
        double flecheHauteur_en_bas = flecheLargeur_en_bas / 5.4;

        VBox menu_hex = new VBox(15);
        int ligne = 100 / 5;
        int colonne = 100 / 1;
        menu_hex.prefWidthProperty().bind(primaryStage.widthProperty());
        menu_hex.prefHeightProperty().bind(primaryStage.heightProperty());
        menu_hex.setAlignment(Pos.CENTER);
        StackPane NewGame = new StackPane();

        Image fleche = c.getImage("Design/MenuPrincipaux/FlecheDuMenuDansHexagone.png");
        ImageView flecheImNG = new ImageView(fleche);
        flecheImNG.fitWidthProperty().bind(primaryStage.widthProperty().divide(3).subtract(20));
        flecheImNG.setPreserveRatio(true);

        
        //newGame.setFont(new Font(police, 20));
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
        flecheImCP.fitWidthProperty().bind(primaryStage.widthProperty().divide(3).subtract(20));
        flecheImCP.setPreserveRatio(true);

        //chargerPartie.setFont(new Font(police, 20));
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

        flecheImR.fitWidthProperty().bind(primaryStage.widthProperty().divide(3).subtract(20));
        flecheImR.setPreserveRatio(true);

        //regles.setFont(new Font(police, 20));
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
        

        gaucheIm.fitWidthProperty().bind(primaryStage.widthProperty().divide(4));
        gaucheIm.setPreserveRatio(true);
        
        //statistiques.setFont(new Font(police,20));
        statistiques.setTextFill(Color.web("#fbe5b5"));
        statistiques.setAlignment(CENTER);
        Statistiques.getChildren().add(gaucheIm);
        Statistiques.getChildren().add(statistiques);
        Statistiques.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            controller.goToStat();
        });
        AnchorPane.setLeftAnchor(Statistiques, (double) 5);
        AnchorPane.setBottomAnchor(Statistiques, (double) height * 0.12);
        pane.getChildren().add(Statistiques);

        StackPane Credits = new StackPane();
        Image droite = c.getImage("Design/MenuPrincipaux/FlecheEnBasDroite.png");
        ImageView droiteIm = new ImageView(droite);
        droiteIm.fitWidthProperty().bind(primaryStage.widthProperty().divide(4));
        droiteIm.setPreserveRatio(true);

        //credits.setFont(new Font(police, 20));
        credits.setTextFill(Color.web("#fbe5b5"));
        credits.setAlignment(CENTER);
        
        Credits.getChildren().add(droiteIm);
        Credits.getChildren().add(credits);
        Credits.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            controller.goToCredits();
        });
        AnchorPane.setRightAnchor(Credits, (double) 5);
        AnchorPane.setBottomAnchor(Credits, (double) height * 0.12);
        pane.getChildren().add(Credits);

        AnchorPane.setRightAnchor(boutonPreference, (double) tailleDeCase / 2 * 1.07 + 10);
        AnchorPane.setTopAnchor(boutonPreference, (double) 5);
        pane.getChildren().add(boutonPreference);

        AnchorPane.setRightAnchor(boutonPleinEcran, (double) 5);
        AnchorPane.setTopAnchor(boutonPleinEcran, (double) 5);
        pane.getChildren().add(boutonPleinEcran);

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
