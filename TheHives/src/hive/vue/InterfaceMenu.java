/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import java.awt.Dimension;
import static javafx.geometry.Pos.CENTER;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Adeline
 */
public class InterfaceMenu extends Parent{

    Stage stage;
    int pleinEcran = 0;
    public InterfaceMenu(Stage primaryStage, Controller controller){


        int height = (int) primaryStage.getHeight();
        int width = (int) primaryStage.getWidth();
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        double max_height = dimension.getHeight();
        double max_width = dimension.getWidth();
        int tailleDeCase;
        if(width/8>height/6){
            tailleDeCase = height/6;
        }
        else{
            tailleDeCase = width/8;
        }

        /*if(controller.pleinEcran==1){
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("Sortie de plein écran - esc");
        }*/
        String police;
        if(controller.langue == "Russe"){
            police = "Copperplate";
        }
        else{
            police = "Papyrus";
        }

        AnchorPane pane = new AnchorPane();
        pane.prefWidthProperty().bind(primaryStage.widthProperty());
        pane.prefHeightProperty().bind(primaryStage.heightProperty());

        CacheImage c = new CacheImage();
        Image fond;
        if(controller.typeTheme=="jour"){
            fond = c.getImage("Design/Fond/fondMontagne.png");
        }
        else{
            fond = c.getImage("Design/Fond/fondNuit.png");
        }
        ImageView fondIm = new ImageView(fond);
        fondIm.fitHeightProperty().bind(primaryStage.heightProperty());
        fondIm.fitWidthProperty().bind(primaryStage.widthProperty());
        AnchorPane.setRightAnchor(fondIm, (double) 0);
        AnchorPane.setLeftAnchor(fondIm, (double) 0);
        AnchorPane.setTopAnchor(fondIm, (double) 0);
        AnchorPane.setBottomAnchor(fondIm, (double) 0);
        pane.getChildren().add(fondIm);

        Image hive = c.getImage("Design/MenuPrincipaux/TheHives.png");
        ImageView hiveIm = new ImageView(hive);
        hiveIm.setFitHeight(tailleDeCase*1.3);
        hiveIm.setFitWidth(tailleDeCase*1.3*1.59);
        AnchorPane.setLeftAnchor(hiveIm, (double) 5);
        AnchorPane.setTopAnchor(hiveIm, (double) 5);
        pane.getChildren().add(hiveIm);

        StackPane hex = new StackPane();
        hex.prefHeightProperty().bind(primaryStage.heightProperty());
        hex.prefWidthProperty().bind(primaryStage.widthProperty());
        Image hexagone = c.getImage("Design/MenuPrincipaux/Hexagone.png");
        ImageView hexagoneIm = new ImageView(hexagone);
        hexagoneIm.setFitHeight(tailleDeCase*4);
        hexagoneIm.setFitWidth(tailleDeCase*4);
        hex.getChildren().add(hexagoneIm);

        Label newGame=new Label();
        Label chargerPartie = new Label();
        Label statistiques = new Label();
        Label credits = new Label();
        Label regles = new Label();

        if(controller.langue=="Français"){
            newGame.setText("Nouvelle partie");
            chargerPartie.setText("Reprendre Partie");
            statistiques.setText("Statistiques");
            credits.setText("Crèdits");
            regles.setText("Règles");
        }
        else if(controller.langue=="English"){
            newGame.setText("New Game");
            chargerPartie.setText("Load Game");
            statistiques.setText("Scores");
            credits.setText("Credits");
            regles.setText("Rules");
        }
        else if(controller.langue=="Italiano"){
            newGame.setText("Nuova Partita");
            chargerPartie.setText("Carica Partita");
            statistiques.setText("Statistica");
            credits.setText("Crediti");
            regles.setText("Regoli");
        }
        else if(controller.langue=="Русский"){
            newGame.setText("Новая Игра");
            chargerPartie.setText("Загрузить Игру");
            statistiques.setText("Статистика");
            credits.setText("Разработчики");
            regles.setText("Правила");
        }
        else if(controller.langue=="Deutsch"){
            newGame.setText("Neues Spiel");
            chargerPartie.setText("Spiel Laden");
            statistiques.setText("Statistik");
            credits.setText("Credits");
            regles.setText("Regeln");
        }

        double flecheLargeur = tailleDeCase*4-30;
        double flecheHauteur = flecheLargeur/7.24;
        double flecheLargeur_en_bas = (tailleDeCase*8)/3;
        double flecheHauteur_en_bas = flecheLargeur_en_bas/5.4;

        GridPane menu_hex = new GridPane();
        int ligne = 100/5;
        int colonne = 100/1;
        Outils.fixerRepartition(menu_hex, Outils.HORIZONTAL, ligne, ligne, ligne, ligne, ligne);
        Outils.fixerRepartition(menu_hex, Outils.VERTICAL, colonne);
        menu_hex.setMaxWidth(tailleDeCase*4);
        menu_hex.setMinWidth(tailleDeCase*4);
        menu_hex.setMaxHeight(tailleDeCase*4);
        menu_hex.setMinHeight(tailleDeCase*4);
        StackPane NewGame = new StackPane();
        Image fleche = c.getImage("Design/MenuPrincipaux/FlecheDuMenuDansHexagone.png");
        ImageView flecheImNG = new ImageView(fleche);
        flecheImNG.setFitHeight(flecheHauteur);
        flecheImNG.setFitWidth(flecheLargeur);
        newGame.setFont(new Font(police, flecheHauteur-10));
        newGame.setTextFill(Color.web("#fbe5b5"));
        newGame.setAlignment(CENTER);
        NewGame.getChildren().add(flecheImNG);
        NewGame.getChildren().add(newGame);
        NewGame.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            controller.goToChoixJoueur();
        });
        menu_hex.add(NewGame, 0, 1);
        StackPane ChargerPartie = new StackPane();
        ImageView flecheImCP = new ImageView(fleche);
        flecheImCP.setFitHeight(flecheHauteur);
        flecheImCP.setFitWidth(flecheLargeur);
        chargerPartie.setFont(new Font(police, flecheHauteur-10));
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
        menu_hex.add(ChargerPartie, 0, 2);
        StackPane Regles = new StackPane();
        ImageView flecheImR = new ImageView(fleche);
        flecheImR.setFitHeight(flecheHauteur);
        flecheImR.setFitWidth(flecheLargeur);
        regles.setFont(new Font(police, flecheHauteur-10));
        regles.setTextFill(Color.web("#fbe5b5"));
        regles.setAlignment(CENTER);
        Regles.getChildren().add(flecheImR);
        Regles.getChildren().add(regles);
        Regles.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            controller.goToRegles();
        });
        menu_hex.add(Regles, 0, 3);
        hex.getChildren().add(menu_hex);
        pane.getChildren().add(hex);

        StackPane Preferences = new StackPane();
        Image preferences = c.getImage("Design/MenuPrincipaux/BouttonParametre.png");
        ImageView prefIm = new ImageView(preferences);
        prefIm.setFitHeight(tailleDeCase/2);
        prefIm.setFitWidth(tailleDeCase/2*1.07);
        Preferences.getChildren().add(prefIm);
        Preferences.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            Preferences p = new Preferences(primaryStage, controller, "menu");
            pane.getChildren().add(p);
        });
        AnchorPane.setRightAnchor(Preferences, (double) tailleDeCase/2*1.07 + 10);
        AnchorPane.setTopAnchor(Preferences, (double) 5);
        pane.getChildren().add(Preferences);

        StackPane Plein = new StackPane();
        Image plein = c.getImage("Design/MenuPrincipaux/pleinEcran.png");
        ImageView pleinIm = new ImageView(plein);
        pleinIm.setFitHeight(tailleDeCase/2);
        pleinIm.setFitWidth(tailleDeCase/2*1.07);
        Plein.getChildren().add(pleinIm);
        Plein.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            if(controller.pleinEcran==0){
                primaryStage.setWidth(max_width);
                primaryStage.setHeight(max_height);
                controller.old_height=height;
                controller.old_width=width;
                controller.goToMenu();
                controller.pleinEcran=1;
            }
            else{
                primaryStage.setWidth(controller.old_width);
                primaryStage.setHeight(controller.old_height);
                controller.goToMenu();
                controller.pleinEcran=0;
            }
            //primaryStage.setFullScreen(true);
            //primaryStage.setFullScreenExitHint("Sortie de plein écran - esc");
        });
        AnchorPane.setRightAnchor(Plein, (double) 5);
        AnchorPane.setTopAnchor(Plein, (double) 5);
        pane.getChildren().add(Plein);

        StackPane Statistiques = new StackPane();
        Image gauche = c.getImage("Design/MenuPrincipaux/FlecheEnBasGauche.png");
        ImageView gaucheIm = new ImageView(gauche);
        gaucheIm.setFitHeight(flecheHauteur_en_bas);
        gaucheIm.setFitWidth(flecheLargeur_en_bas);
        statistiques.setFont(new Font(police, flecheHauteur-10));
        statistiques.setTextFill(Color.web("#fbe5b5"));
        statistiques.setAlignment(CENTER);
        Statistiques.getChildren().add(gaucheIm);
        Statistiques.getChildren().add(statistiques);
        Statistiques.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            controller.goToStat();
        });
        AnchorPane.setLeftAnchor(Statistiques, (double) 5);
        AnchorPane.setBottomAnchor(Statistiques, (double) height*0.12);
        pane.getChildren().add(Statistiques);

        StackPane Credits = new StackPane();
        Image droite = c.getImage("Design/MenuPrincipaux/FlecheEnBasDroite.png");
        ImageView droiteIm = new ImageView(droite);
        droiteIm.setFitHeight(flecheHauteur_en_bas);
        droiteIm.setFitWidth(flecheLargeur_en_bas);
        credits.setFont(new Font(police, flecheHauteur_en_bas-10));
        credits.setTextFill(Color.web("#fbe5b5"));
        credits.setAlignment(CENTER);
        Credits.getChildren().add(droiteIm);
        Credits.getChildren().add(credits);
        Credits.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            controller.goToCredits();
        });
        AnchorPane.setRightAnchor(Credits, (double) 5);
        AnchorPane.setBottomAnchor(Credits, (double) height*0.12);
        pane.getChildren().add(Credits);

        this.getChildren().add(pane);


    }
}
