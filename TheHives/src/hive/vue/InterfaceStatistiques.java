/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Adeline
 */
public class InterfaceStatistiques extends Interface {

    private final Label stat;
    
    public InterfaceStatistiques(Stage primaryStage, Controller controller, CacheImage c) {
        super(primaryStage, controller, c);

        AnchorPane pane = new AnchorPane();
        pane.prefWidthProperty().bind(primaryStage.widthProperty());
        pane.prefHeightProperty().bind(primaryStage.heightProperty());

        AnchorPane.setRightAnchor(boutonPreference, (double) tailleDeCase / 2 * 1.07 + 15);
        AnchorPane.setTopAnchor(boutonPreference, (double) 5);
        pane.getChildren().add(boutonPreference);

        AnchorPane.setRightAnchor(boutonPleinEcran, (double) 10);
        AnchorPane.setTopAnchor(boutonPleinEcran, (double) 5);
        pane.getChildren().add(boutonPleinEcran);

        AnchorPane.setLeftAnchor(boutonRetourMenu, (double) 5);
        AnchorPane.setTopAnchor(boutonRetourMenu, (double) 5);
        pane.getChildren().add(boutonRetourMenu);

        stat = new Label();

        setTextWithCurrentLanguage();

        stat.setFont(new Font(police, width / 35));
        stat.setAlignment(Pos.CENTER);
        stat.setMinSize(width / 60, 30);
        stat.setMaxSize(width / 2, 70);
        AnchorPane.setTopAnchor(stat, (double) tailleDeCase * 0.6);
        AnchorPane.setLeftAnchor(stat, (double) tailleDeCase * 2);
        AnchorPane.setRightAnchor(stat, (double) tailleDeCase * 2);
        pane.getChildren().add(stat);

        ListView<GridPane> liste = new ListView<>();

        for (int j = 0; j < 10; j++) {
            GridPane statistiques = new GridPane();
            int ligne = 100 / 2;
            int colonne = 100 / 2;
            Outils.fixerRepartition(statistiques, Outils.HORIZONTAL, ligne, ligne);
            Outils.fixerRepartition(statistiques, Outils.VERTICAL, colonne, colonne);
            //        grille.prefHeightProperty().bind(primaryStage.heightProperty());
            //        statistiques.prefWidthProperty().bind(primaryStage.widthProperty());
            //statistiques.setMaxWidth(width/3);
            //statistiques.setMinWidth(width/5);
            statistiques.setMaxHeight(tailleDeCase * 1.2);
            statistiques.setMinHeight(tailleDeCase * 1.2);
            double hauteurDeGrille = tailleDeCase * 2.4;
            double hauteurDeLigne = hauteurDeGrille / 2;
            Label joueur1 = new Label();
            joueur1.setText("name1");
            Label joueur2 = new Label();
            joueur2.setText("name2");
            Label score1 = new Label();
            score1.setText("score1");
            Label score2 = new Label();
            score2.setText("score2");
            joueur1.setFont(new Font(police, maxJoueur / 20));
            joueur1.setAlignment(Pos.CENTER);
            joueur1.setMinSize(minJoueur, 30);
            joueur1.setMaxSize(maxJoueur, 70);
            StackPane j1 = new StackPane();
            j1.getChildren().add(joueur1);
            statistiques.add(j1, 0, 0);
            joueur2.setFont(new Font(police, maxJoueur / 20));
            joueur2.setAlignment(Pos.CENTER);
            joueur2.setMinSize(minJoueur, 30);
            joueur2.setMaxSize(maxJoueur, 70);
            StackPane j2 = new StackPane();
            j2.getChildren().add(joueur2);
            statistiques.add(j2, 1, 0);
            score1.setFont(new Font(police, maxJoueur / 20));
            score1.setAlignment(Pos.CENTER);
            score1.setMinSize(minJoueur, 30);
            score1.setMaxSize(maxJoueur, 70);
            StackPane s1 = new StackPane();
            s1.getChildren().add(score1);
            statistiques.add(s1, 0, 1);
            score2.setFont(new Font(police, maxJoueur / 20));
            score2.setAlignment(Pos.CENTER);
            score2.setMinSize(minJoueur, 30);
            score2.setMaxSize(maxJoueur, 70);
            StackPane s2 = new StackPane();
            s2.getChildren().add(score2);
            statistiques.add(s2, 1, 1);
            liste.getItems().add(statistiques);
        }

        AnchorPane.setTopAnchor(liste, (double) tailleDeCase);
        AnchorPane.setLeftAnchor(liste, (double) tailleDeCase * 2);
        AnchorPane.setRightAnchor(liste, (double) tailleDeCase * 2);
        AnchorPane.setBottomAnchor(liste, (double) tailleDeCase);
        pane.getChildren().add(liste);

        this.panePrincipale.getChildren().add(pane);
    }

    @Override
    public void setTextWithCurrentLanguage()
    {
        stat.setText(controller.gestionnaireLangage.getText("text_statistiques"));
    }
}
