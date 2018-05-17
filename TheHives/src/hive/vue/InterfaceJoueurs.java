/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import javafx.geometry.Pos;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import hive.thehives.TheHives;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Adeline
 */
public class InterfaceJoueurs extends Parent{

    String versionIA1;
    String versionIA2;
    int est_h1=0, est_h2=0, est_ai1=0, est_ai2=0;
    TextField Name1 = new TextField();
    TextField Name2 = new TextField();
    public InterfaceJoueurs(Stage primaryStage, TheHives i) {
        
        if(i.pleinEcran==1){
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("Sortie de plein écran - esc");
        }
        
        int height = (int) primaryStage.getHeight();
        int width = (int) primaryStage.getWidth();
        DropShadow shadow = new DropShadow();
        int tailleDeCase = width/8;

        AnchorPane pane = new AnchorPane();
        pane.prefWidthProperty().bind(primaryStage.widthProperty());
        pane.prefHeightProperty().bind(primaryStage.heightProperty());
        
        Group utiles3 = new Group();
        Bouton preferences = new Bouton(primaryStage, i, "preferences", pane, "joueurs");
        Bouton sortie = new Bouton(primaryStage, i, "sortie", pane, "joueurs");
        Bouton ecran = new Bouton(primaryStage, i, "ecran", pane, "joueurs");
        utiles3.getChildren().addAll(preferences, sortie, ecran);
        AnchorPane.setRightAnchor(utiles3, (double) 0);
        AnchorPane.setTopAnchor(utiles3, (double) 0);
        //AnchorPane.setLeftAnchor(utiles3, (double) width-tailleDeCase*2);
        AnchorPane.setBottomAnchor(utiles3, (double) height-tailleDeCase*2);
        pane.getChildren().add(utiles3);
        Group utiles1 = new Group();
        Bouton menu = new Bouton(primaryStage, i, "menu", pane, "joueurs");
        utiles1.getChildren().addAll(menu);
        AnchorPane.setLeftAnchor(utiles1, (double) 0);
        AnchorPane.setRightAnchor(utiles1, (double) width-tailleDeCase);
        AnchorPane.setTopAnchor(utiles1, (double) 0);
        AnchorPane.setBottomAnchor(utiles1, (double) height-tailleDeCase);
        pane.getChildren().add(utiles1);
       
        int maxJoueur = width/2;
        int minJoueur = maxJoueur/2;
        
        GridPane grille = new GridPane();
        int ligne = 100/6;
        int colonne = 100/3;
        Outils.fixerRepartition(grille, Outils.HORIZONTAL, ligne, ligne, ligne, ligne, ligne, ligne);
        Outils.fixerRepartition(grille, Outils.VERTICAL, colonne, colonne, colonne);
//        grille.prefHeightProperty().bind(primaryStage.heightProperty());
//        grille.prefWidthProperty().bind(primaryStage.widthProperty());
        grille.setMaxWidth(width/8);
        grille.setMinWidth(width/12);
        grille.setMaxHeight(height*0.8);
        grille.setMinHeight(height*0.7);
        double hauteurDeGrille = height*0.7;
        double hauteurDeLigne = hauteurDeGrille/6;
        
        System.out.println(grille.getHeight());
        
        
        Label joueur1 = new Label(); // jocatori, Spiler
        Label joueur2 = new Label();
        
        Button valider = new Button(); // Invio, Enter Bestätigen
        if(i.langue=="Français"){
            joueur1.setText("Joueur 1");
            joueur2.setText("Joueur 2");
            Name1.setPromptText("Votre prenom"); // nome , Name
            Name2.setPromptText("Votre prenom");
            valider.setText("Valider");
        }
        else if(i.langue=="English"){
            joueur1.setText("Player 1");
            joueur2.setText("Player 2");
            Name1.setPromptText("Name");
            Name2.setPromptText("Name");
            valider.setText("Commit");
        }
        else if(i.langue=="Italiano"){
            joueur1.setText("Jocatore 1");
            joueur2.setText("Jocatore 2");
            Name1.setPromptText("Nome");
            Name2.setPromptText("Nome");
            valider.setText("Invio");
        }
        else if(i.langue=="Русский"){
            joueur1.setText("Игрок 1");
            joueur2.setText("Игрок 2");
            Name1.setPromptText("Имя");
            Name2.setPromptText("Имя");
            valider.setText("Подтвердить");
        }
        else if(i.langue=="Deutsch"){
            joueur1.setText("Spiler 1");
            joueur2.setText("Spiler 2");
            Name1.setPromptText("Name");
            Name2.setPromptText("Name");
            valider.setText("Bestätigen");
        }
        
        joueur1.setFont(new Font("Copperplate", maxJoueur/10));
        joueur1.setAlignment(Pos.CENTER);
        joueur1.setMinSize(minJoueur, 30);
        joueur1.setMaxSize(maxJoueur, 70);
        StackPane jo1 = new StackPane();
        jo1.getChildren().add(joueur1);
        grille.add(jo1, 1, 0);
        
        final ToggleGroup j1 = new ToggleGroup();
        RadioBouton bouton = new RadioBouton(primaryStage, i);
        ToggleButton humain1;
        humain1 = bouton.creer("humain1");
        humain1.setToggleGroup(j1);
        StackPane hu1 = new StackPane();
        hu1.getChildren().add(humain1);
        grille.add(hu1, 0, 1);
        ToggleButton IA1;
        IA1 = bouton.creer("IA1");
        IA1.setToggleGroup(j1);
        StackPane i1 = new StackPane();
        i1.getChildren().add(IA1);
        grille.add(i1, 2, 1);
        
        Name1.setText(null);
        final ToggleGroup ia1 = new ToggleGroup();
        j1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                Toggle old_toggle, Toggle new_toggle) {
                if (j1.getSelectedToggle() != null) {
                    if(humain1.isSelected()){
                        if(est_ai1==1){
                            grille.getChildren().remove(grille.getChildren().size()-3, grille.getChildren().size());
                            est_ai1=0;
                            versionIA1=null;
                        }
                        est_h1=1;
                        Name1.setMinSize(width/10, 30);
                        Name1.setMaxHeight(40);
                        Name1.setAlignment(Pos.CENTER);
                        StackPane n1 = new StackPane();
                        n1.getChildren().add(Name1);
                        grille.add(n1, 1, 2);
                    }
                    else if(IA1.isSelected()){
                        if(est_h1==1){
                            grille.getChildren().remove(grille.getChildren().size()-1);
                            est_h1=0;
                            //Name1 = new TextField();
                             Name1.setText(null);
                        }
                        est_ai1=1;
                        ToggleButton facile;
                        facile = bouton.creer("facile1"); //Facile, Einfach
                        facile.setToggleGroup(ia1);
                        StackPane f1 = new StackPane();
                        f1.getChildren().add(facile);
                        grille.add(f1, 0, 2);
                        ToggleButton moyenne;
                        moyenne = bouton.creer("moyenne1"); //Media, Mittel/Normal
                        moyenne.setToggleGroup(ia1);
                        StackPane m1 = new StackPane();
                        m1.getChildren().add(moyenne);
                        grille.add(m1, 1, 2);
                        ToggleButton difficile;
                        difficile = bouton.creer("difficile1"); //Difficile, Schwer
                        difficile.setToggleGroup(ia1);
                        StackPane d1 = new StackPane();
                        d1.getChildren().add(difficile);
                        grille.add(d1, 2, 2);
                        ia1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                                public void changed(ObservableValue<? extends Toggle> ov,
                                    Toggle old_toggle, Toggle new_toggle) {
                                    if (ia1.getSelectedToggle() != null) {
                                        versionIA1 = ia1.getSelectedToggle().getUserData().toString();
                                        System.out.println("IA1 : " + versionIA1);
                                    }
                                }
                            });
                    }
                }
            }
        });
        
        
        joueur2.setFont(new Font("Copperplate", maxJoueur/10));
        joueur2.setAlignment(Pos.CENTER);
        joueur2.setMinSize(width/10, 30);
        joueur2.setMaxSize(maxJoueur, 70);
        StackPane jo2 = new StackPane();
        jo2.getChildren().add(joueur2);
        grille.add(jo2, 1, 3);
        
        final ToggleGroup j2 = new ToggleGroup();
        ToggleButton humain2;
        humain2 = bouton.creer("humain2");
        humain2.setToggleGroup(j2);
        StackPane hu2 = new StackPane();
        hu2.getChildren().add(humain2);
        grille.add(hu2, 0, 4);
        ToggleButton IA2;
        IA2 = bouton.creer("IA2");
        IA2.setToggleGroup(j2);
        StackPane i2 = new StackPane();
        i2.getChildren().add(IA2);
        grille.add(i2, 2, 4);
        
        Name2.setText(null);
        
        final ToggleGroup ia2 = new ToggleGroup();
        j2.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                Toggle old_toggle, Toggle new_toggle) {
                if (j2.getSelectedToggle() != null) {
                    if(humain2.isSelected()){
                        if(est_ai2==1){
                            grille.getChildren().remove(grille.getChildren().size()-3, grille.getChildren().size());
                            est_ai2=0;
                            versionIA2=null;
                        }
                        est_h2=1;
                        Name2.setMinSize(width/10, 30);
                        Name2.setMaxHeight(40);
                        Name2.setAlignment(Pos.CENTER);
                        StackPane n2 = new StackPane();
                        n2.getChildren().add(Name2);
                        grille.add(n2, 1, 5);
                    }
                    else if(IA2.isSelected()){
                        if(est_h2==1){
                            grille.getChildren().remove(grille.getChildren().size()-1);
                            est_h2=0;
                            //Name2 = new TextField();
                             Name2.setText(null);
                        }
                        est_ai2=1;
                        ToggleButton facile;
                        facile = bouton.creer("facile2");
                        facile.setToggleGroup(ia2);
                        StackPane f2 = new StackPane();
                        f2.getChildren().add(facile);
                        grille.add(f2, 0, 5);
                        ToggleButton moyenne;
                        moyenne = bouton.creer("moyenne2");
                        moyenne.setToggleGroup(ia2);
                        StackPane m2 = new StackPane();
                        m2.getChildren().add(moyenne);
                        grille.add(m2, 1, 5);
                        ToggleButton difficile;
                        difficile = bouton.creer("difficile2");
                        difficile.setToggleGroup(ia2);
                        StackPane d2 = new StackPane();
                        d2.getChildren().add(difficile);
                        grille.add(d2, 2, 5);
                        ia2.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                                public void changed(ObservableValue<? extends Toggle> ov,
                                    Toggle old_toggle, Toggle new_toggle) {
                                    if (ia2.getSelectedToggle() != null) {
                                        versionIA2 = ia2.getSelectedToggle().getUserData().toString();
                                        System.out.println("IA2 : " + versionIA2);
                                    }
                                }
                            });
                    }
                }
            }
        });
        
       
        AnchorPane.setLeftAnchor(grille, (double) width/8);
        AnchorPane.setRightAnchor(grille, (double) width/8);
        AnchorPane.setTopAnchor(grille, (double) 60);
        AnchorPane.setBottomAnchor(grille, (double) 200);
        pane.getChildren().add(grille);
        
        valider.setFont(new Font("Copperplate", width/35));
        valider.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent event) -> {
            valider.setEffect(shadow);
        });
        valider.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent event) -> {
            valider.setEffect(null);
        });
        valider.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Enregistrer ! ");
                System.out.println("Name1 : " + Name1.getCharacters());
                System.out.println("IA1 : " + versionIA1);
                System.out.println("Name2 : " + Name2.getCharacters());
                System.out.println("IA2 : " + versionIA2);
                String joueur_1, joueur_2;
                if(Name1.getText()!=null){
                    joueur_1 = Name1.getCharacters().toString();
                }
                else{
                    joueur_1 = versionIA1;
                }   
                if(Name2.getText()!=null){
                    joueur_2 = Name2.getCharacters().toString();
                }
                else{
                    joueur_2 = versionIA2;
                }   //i.goToPlateau(Name1.getCharacters().toString(), Name2.getCharacters().toString());
                i.goToPlateau(joueur_1, joueur_2);
                
        System.out.println(joueur_1);
        System.out.println(joueur_2);
            }
        });
        AnchorPane.setBottomAnchor(valider, (double) 140);
        //AnchorPane.setTopAnchor(valider, (double) height - 50);
        AnchorPane.setLeftAnchor(valider, (double) tailleDeCase*3);
        AnchorPane.setRightAnchor(valider, (double) tailleDeCase*3);
        pane.getChildren().add(valider);
        
       
        this.getChildren().add(pane);

    }

    

}
