/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import hive.thehives.TheHives;
import hive.vue.Bouton;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Adeline
 */
public class InterfaceStatistiques extends Parent {


    public InterfaceStatistiques(Stage primaryStage, Controller controller) {
      int height = (int) primaryStage.getHeight();
      int width = (int) primaryStage.getWidth();
      DropShadow shadow = new DropShadow();
      int tailleDeCase = width/8;
      int maxJoueur = (int) ((int) width/2.5);
      int minJoueur = maxJoueur/2;
        if(controller.pleinEcran==1){

            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("Sortie de plein écran - esc");
        }
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


        StackPane Preferences = new StackPane();
        Image preferences = c.getImage("Design/MenuPrincipaux/BouttonParametre.png");
        ImageView prefIm = new ImageView(preferences);
        prefIm.setFitHeight(tailleDeCase/2);
        prefIm.setFitWidth(tailleDeCase/2*1.07);
        Preferences.getChildren().add(prefIm);
        Preferences.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            /*Preferences p = new Preferences(primaryStage, i);
            pane.getChildren().add(p);
            StackPane pref = new StackPane();
            Image imageQ = c.getImage("exit3.png");
            ImageView ImQ = new ImageView(imageQ);
            ImQ.setFitHeight(tailleDeCase/2.5);
            ImQ.setFitWidth(tailleDeCase/2.5);
            pref.getChildren().add(ImQ);
            pref.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event1) -> {
                pane.getChildren().remove(pane.getChildren().size()-2, pane.getChildren().size());
                controller.goToRegles();
            });
            AnchorPane.setRightAnchor(pref, (double) 5);
            AnchorPane.setTopAnchor(pref, (double) 5);
            pane.getChildren().add(pref);*/

            Preferences p = new Preferences(primaryStage, controller, "stat");
            pane.getChildren().add(p);
        });
        AnchorPane.setRightAnchor(Preferences, (double) tailleDeCase/2*1.07 + 15);
        AnchorPane.setTopAnchor(Preferences, (double) 5);
        pane.getChildren().add(Preferences);

        StackPane Plein = new StackPane();
        Image plein = c.getImage("Design/MenuPrincipaux/pleinEcran.png");
        ImageView pleinIm = new ImageView(plein);
        pleinIm.setFitHeight(tailleDeCase/2);
        pleinIm.setFitWidth(tailleDeCase/2*1.07);
        Plein.getChildren().add(pleinIm);
        Plein.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            controller.pleinEcran=1;
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("Sortie de plein écran - esc");
        });
        AnchorPane.setRightAnchor(Plein, (double) 10);
        AnchorPane.setTopAnchor(Plein, (double) 5);
        pane.getChildren().add(Plein);


        StackPane Menu = new StackPane();
        Image menu = c.getImage("Design/FenetrePlateau/bouttonRetourMenu.png");
        ImageView menuIm = new ImageView(menu);
        menuIm.setFitHeight(tailleDeCase/2);
        menuIm.setFitWidth(tailleDeCase/2*1.07);
        Menu.getChildren().add(menuIm);
        Menu.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            controller.goToMenu();
        });
        AnchorPane.setLeftAnchor(Menu, (double) 5);
        AnchorPane.setTopAnchor(Menu, (double) 5);
        pane.getChildren().add(Menu);

        Label stat = new Label();
        if(controller.langue=="Français"){
            stat.setText("Statistiques");
        }
        else if(controller.langue=="English"){
            stat.setText("Statistics");
        }
        else if(controller.langue=="Italiano"){
            stat.setText("Statistiche");
        }
        else if(controller.langue=="Русский"){
            stat.setText("Статистика");
        }
        else if(controller.langue=="Deutsch"){
            stat.setText("Statistik");
        }
        stat.setFont(new Font(police, width/35));
        stat.setAlignment(Pos.CENTER);
        stat.setMinSize(width/60, 30);
        stat.setMaxSize(width/2, 70);
        AnchorPane.setTopAnchor(stat, (double) height/10);
        AnchorPane.setLeftAnchor(stat, (double) tailleDeCase*2);
        AnchorPane.setRightAnchor(stat, (double) tailleDeCase*2);
        pane.getChildren().add(stat);

        ListView<GridPane> liste = new ListView<>();



        for(int j=0; j<10;j++){
            GridPane statistiques = new GridPane();int ligne = 100/2;
            int colonne = 100/2;
            Outils.fixerRepartition(statistiques, Outils.HORIZONTAL, ligne, ligne);
            Outils.fixerRepartition(statistiques, Outils.VERTICAL, colonne, colonne);
            //        grille.prefHeightProperty().bind(primaryStage.heightProperty());
            //        statistiques.prefWidthProperty().bind(primaryStage.widthProperty());
            //statistiques.setMaxWidth(width/3);
            //statistiques.setMinWidth(width/5);
            statistiques.setMaxHeight(height*0.2);
            statistiques.setMinHeight(height*0.15);
            double hauteurDeGrille = height*0.4;
            double hauteurDeLigne = hauteurDeGrille/2;
            Label joueur1 = new Label();
            joueur1.setText("name1");
            Label joueur2 = new Label();
            joueur2.setText("name2");
            Label score1 = new Label();
            score1.setText("score1");
            Label score2 = new Label();
            score2.setText("score2");
            joueur1.setFont(new Font(police, maxJoueur/20));
            joueur1.setAlignment(Pos.CENTER);
            joueur1.setMinSize(minJoueur, 30);
            joueur1.setMaxSize(maxJoueur, 70);
            StackPane j1 = new StackPane();
            j1.getChildren().add(joueur1);
            statistiques.add(j1, 0, 0);
            joueur2.setFont(new Font(police, maxJoueur/20));
            joueur2.setAlignment(Pos.CENTER);
            joueur2.setMinSize(minJoueur, 30);
            joueur2.setMaxSize(maxJoueur, 70);
            StackPane j2 = new StackPane();
            j2.getChildren().add(joueur2);
            statistiques.add(j2, 1, 0);
            score1.setFont(new Font(police, maxJoueur/20));
            score1.setAlignment(Pos.CENTER);
            score1.setMinSize(minJoueur, 30);
            score1.setMaxSize(maxJoueur, 70);
            StackPane s1 = new StackPane();
            s1.getChildren().add(score1);
            statistiques.add(s1, 0, 1);
            score2.setFont(new Font(police, maxJoueur/20));
            score2.setAlignment(Pos.CENTER);
            score2.setMinSize(minJoueur, 30);
            score2.setMaxSize(maxJoueur, 70);
            StackPane s2 = new StackPane();
            s2.getChildren().add(score2);
            statistiques.add(s2, 1, 1);
            liste.getItems().add(statistiques);
        }



        AnchorPane.setTopAnchor(liste, (double) tailleDeCase);
        AnchorPane.setLeftAnchor(liste, (double) tailleDeCase*2);
        AnchorPane.setRightAnchor(liste, (double) tailleDeCase*2);
        AnchorPane.setBottomAnchor(liste, (double) tailleDeCase);
        pane.getChildren().add(liste);
       // pane.getChildren().add(statistiques);

        this.getChildren().add(pane);
    }

}
