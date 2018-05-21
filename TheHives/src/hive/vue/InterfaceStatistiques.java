/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.thehives.TheHives;
import hive.vue.Bouton;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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

    public InterfaceStatistiques(Stage primaryStage, TheHives i) {
        if(i.pleinEcran==1){
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("Sortie de plein écran - esc");
            
            
        }
        
        AnchorPane pane = new AnchorPane();
        pane.prefWidthProperty().bind(primaryStage.widthProperty());
        pane.prefHeightProperty().bind(primaryStage.heightProperty());
        
        int height = (int) primaryStage.getHeight();
        int width = (int) primaryStage.getWidth();
        int tailleDeCase = width/8;
        int maxJoueur = (int) ((int) width/2.5);
        int minJoueur = maxJoueur/2;
        
        Group utiles3 = new Group();
        Bouton preferences = new Bouton(primaryStage, i, "preferences", pane, "stat");
        Bouton sortie = new Bouton(primaryStage, i, "sortie", pane, "stat");
        Bouton ecran = new Bouton(primaryStage, i, "ecran", pane, "stat");
        utiles3.getChildren().addAll(preferences, sortie, ecran);
        AnchorPane.setRightAnchor(utiles3, (double) 0);
        AnchorPane.setTopAnchor(utiles3, (double) 0);
        pane.getChildren().add(utiles3);
        
        Group utiles1 = new Group();
        Bouton menu = new Bouton(primaryStage, i, "menu", pane, "stat");
        utiles1.getChildren().addAll(menu);
        
        AnchorPane.setLeftAnchor(utiles1, (double) 0);
        AnchorPane.setTopAnchor(utiles1, (double) 0);
        pane.getChildren().add(utiles1);
        
        Label stat = new Label();
        if(i.langue=="Français"){
            stat.setText("Statistiques");
        }
        else if(i.langue=="English"){
            stat.setText("Statistics");
        }
        else if(i.langue=="Italiano"){
            stat.setText("Statistiche");
        }
        else if(i.langue=="Русский"){
            stat.setText("Статистика");
        }
        else if(i.langue=="Deutsch"){
            stat.setText("Statistik");
        }
        stat.setFont(new Font("Copperplate", width/35));
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
            joueur1.setFont(new Font("Copperplate", maxJoueur/20));
            joueur1.setAlignment(Pos.CENTER);
            joueur1.setMinSize(minJoueur, 30);
            joueur1.setMaxSize(maxJoueur, 70);
            StackPane j1 = new StackPane();
            j1.getChildren().add(joueur1);
            statistiques.add(j1, 0, 0);
            joueur2.setFont(new Font("Copperplate", maxJoueur/20));
            joueur2.setAlignment(Pos.CENTER);
            joueur2.setMinSize(minJoueur, 30);
            joueur2.setMaxSize(maxJoueur, 70);
            StackPane j2 = new StackPane();
            j2.getChildren().add(joueur2);
            statistiques.add(j2, 1, 0);
            score1.setFont(new Font("Copperplate", maxJoueur/20));
            score1.setAlignment(Pos.CENTER);
            score1.setMinSize(minJoueur, 30);
            score1.setMaxSize(maxJoueur, 70);
            StackPane s1 = new StackPane();
            s1.getChildren().add(score1);
            statistiques.add(s1, 0, 1);
            score2.setFont(new Font("Copperplate", maxJoueur/20));
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
