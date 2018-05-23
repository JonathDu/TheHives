/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import hive.thehives.TheHives;
import java.awt.Dimension;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author Adeline
 */
class Preferences extends Parent{


    String type;



    Preferences(Stage primaryStage, Controller controller, String origin){
        int height = (int) primaryStage.getHeight();
        int width = (int) primaryStage.getWidth();
        int tailleDeCase;
        if(width/8>height/6){
            tailleDeCase = height/6;
        }
        else{
            tailleDeCase = width/8;
        }
        int maxJoueur = width/2;
        int minJoueur = maxJoueur/2;

        CacheImage c = new CacheImage();
        String police;
        if(controller.langue == "Russe"){
            police = "Copperplate";
        }
        else{
            police = "Papyrus";
        }


        AnchorPane pane = new AnchorPane();

        GridPane grille_pref = new GridPane();
        Outils.fixerRepartition(grille_pref, Outils.HORIZONTAL, 25, 75);
        Outils.fixerRepartition(grille_pref, Outils.VERTICAL, 100);


        GridPane grille = new GridPane();
        int ligne = 100/5;
        int colonne = 100/3;
        Outils.fixerRepartition(grille, Outils.HORIZONTAL, ligne, ligne, ligne, ligne);
        Outils.fixerRepartition(grille, Outils.VERTICAL, colonne+5, colonne-10, colonne+5);
        //grille.prefWidthProperty().bind(primaryStage.widthProperty());
        //grille.prefHeightProperty().bind(primaryStage.heightProperty());
        grille.setMaxWidth((width-30));
        grille.setMinWidth((width-30));
        grille.setMaxHeight(((width-30)/1.35)*0.75);
        grille.setMinHeight(((width-30)/1.35)*0.75);
        double hauteurDeGrille = ((width-30)/1.35)*0.75;
        double hauteurDeLigne = hauteurDeGrille/4;
        double largeurDeGrille = width-30;
        double largeurDeColonne = largeurDeGrille/2;
        Pane paneRec = new Pane();
        Rectangle rec = new Rectangle();
        rec.widthProperty().bind(primaryStage.widthProperty());
        rec.heightProperty().bind(primaryStage.heightProperty());
        //rec.setHeight(height);
        //rec.setWidth(width);
        rec.setFill(Color.GREY);
        rec.setOpacity(0.5);
        rec.setSmooth(true);
        paneRec.getChildren().add(rec);
        AnchorPane.setBottomAnchor(paneRec, (double) 0);
        AnchorPane.setTopAnchor(paneRec, (double) 0);
        AnchorPane.setLeftAnchor(paneRec, (double) 0);
        AnchorPane.setRightAnchor(paneRec, (double) 0);
        pane.getChildren().add(paneRec);

        Image plateau = c.getImage("PlateauCentral.png");
        ImageView plateauIm = new ImageView(plateau);
        plateauIm.setFitHeight((width-30)/1.35);
        plateauIm.setFitWidth(width-30);
        StackPane plat = new StackPane();
        plat.getChildren().add(plateauIm);

        Label preferences = new Label("Préfèrences");
        preferences.setFont(new Font(police, maxJoueur/10));
        preferences.setTextFill(Color.web("#ffff66"));
        preferences.setAlignment(Pos.CENTER);
        preferences.setMinSize(minJoueur, 30);
        preferences.setMaxSize(maxJoueur, 70);
        StackPane p = new StackPane();
        p.getChildren().add(preferences);
        //AnchorPane.setBottomAnchor(p, (double) 0);
        /*AnchorPane.setTopAnchor(p, (double) 20);
        AnchorPane.setLeftAnchor(p, (double) width/8);
        AnchorPane.setRightAnchor(p, (double) width/8);
        pane.getChildren().add(p);*/
        //grille.add(p, 1, 0);
        grille_pref.add( p, 0, 0);

        Label langues = new Label("Langues");
        langues.setFont(new Font(police, maxJoueur/14));
        langues.setTextFill(Color.web("#ffff66"));
        langues.setAlignment(Pos.CENTER);
        langues.setMinSize(minJoueur, 30);
        langues.setMaxSize(maxJoueur, 70);
        StackPane l = new StackPane();
        l.getChildren().add(langues);
        grille.add(l, 0, 0);

        final ComboBox<String> choix = new ComboBox<String>();
        choix.getItems().addAll("Français", "English", "Italiano", "Deutsch", "Русский");
        choix.setValue(controller.langue);
        choix.setCellFactory(
            new Callback<ListView<String>, ListCell<String>>() {
                @Override public ListCell<String> call(ListView<String> param) {
                    final ListCell<String> cell = new ListCell<String>() {
                        {
                            super.setPrefWidth(largeurDeColonne/2);
                        }
                        @Override public void updateItem(String item,
                            boolean empty) {
                                super.updateItem(item, empty);
                                if (item != null) {
                                    setText(item);
                                    //setTextFill(Color.web("#ffff66"));
                                    setFont(new Font(police, maxJoueur/18));
                                }
                                else {
                                    setText(null);
                                }
                            }
                };
                return cell;
            }
        });
        StackPane ch = new StackPane();
        ch.getChildren().add(choix);
        grille.add(ch, 2, 0);

        Label aide = new Label("Activer l'aide");
        aide.setFont(new Font(police, maxJoueur/14));
        aide.setTextFill(Color.web("#ffff66"));
        aide.setAlignment(Pos.CENTER);
        aide.setMinSize(minJoueur, 30);
        aide.setMaxSize(maxJoueur, 70);
        StackPane a = new StackPane();
        a.getChildren().add(aide);
        grille.add(a, 0, 1);

        CheckBox aide_oui = new CheckBox("");
        aide_oui.setSelected(true);
        StackPane a_oui = new StackPane();
        a_oui.getChildren().add(aide_oui);
        grille.add(a_oui, 2, 1);

        Label theme = new Label("Thème");
        theme.setFont(new Font(police, maxJoueur/14));
        theme.setTextFill(Color.web("#ffff66"));
        theme.setAlignment(Pos.CENTER);
        theme.setMinSize(minJoueur, 30);
        theme.setMaxSize(maxJoueur, 70);
        StackPane t = new StackPane();
        t.getChildren().add(theme);
        grille.add(t, 0, 2);

        /*GridPane fond = new GridPane();
        Outils.fixerRepartition(fond, Outils.HORIZONTAL, 100);
        Outils.fixerRepartition(fond, Outils.VERTICAL, colonne, colonne, colonne);
        fond.setMaxWidth(largeurDeColonne);
        fond.setMaxHeight(hauteurDeLigne);
        fond.setMinWidth(largeurDeColonne);
        fond.setMinHeight(hauteurDeLigne);
        Button suivant = new Button(">");
        suivant.setFont(new Font(police, maxJoueur/20));
        suivant.setTextFill(Color.web("#ffff66"));
        suivant.setAlignment(Pos.CENTER);
        suivant.setMinSize(55, 30);
        StackPane s = new StackPane();
        s.getChildren().add(suivant);
        fond.add(s, 2, 0);
        //suivant.setMaxSize(10, 70);
        Label name = new Label("Nom");
        name.setFont(new Font(police, maxJoueur/20));
        name.setTextFill(Color.web("#ffff66"));
        name.setAlignment(Pos.CENTER);
        name.setMinSize(minJoueur, 30);
        name.setMaxSize(maxJoueur, 70);
        StackPane n = new StackPane();
        n.getChildren().add(name);
        fond.add(n, 1, 0);
        Button precedent = new Button("<");
        precedent.setFont(new Font(police, maxJoueur/20));
        precedent.setTextFill(Color.web("#ffff66"));
        precedent.setAlignment(Pos.CENTER);
        precedent.setMinSize(55, 30);
        StackPane pr = new StackPane();
        pr.getChildren().add(precedent);
        fond.add(pr, 0, 0);
        //precedent.setMaxSize(10, 70);
        //fond.getChildren().addAll(precedent, name, suivant);
        StackPane f = new StackPane();
        f.getChildren().add(fond);
        grille.add(f, 1, 2);*/

        GridPane fond = new GridPane();
        Outils.fixerRepartition(fond, Outils.HORIZONTAL, 100);
        Outils.fixerRepartition(fond, Outils.VERTICAL, colonne, colonne);
        fond.setMaxWidth(largeurDeColonne);
        fond.setMaxHeight(hauteurDeLigne);
        fond.setMinWidth(largeurDeColonne);
        fond.setMinHeight(hauteurDeLigne);
        final ToggleGroup f = new ToggleGroup();
        RadioBouton bouton = new RadioBouton(primaryStage, controller);
        ToggleButton jour;
        jour = bouton.creer("jour");
        //jour.setBackground(Background.EMPTY);
        jour.setToggleGroup(f);
        StackPane j = new StackPane();
        j.getChildren().add(jour);
        fond.add(j, 0, 0);
        ToggleButton nuit;
        nuit = bouton.creer("nuit");
        //nuit.setBackground(Background.EMPTY);
        nuit.setToggleGroup(f);
        StackPane n = new StackPane();
        n.getChildren().add(nuit);
        fond.add(n, 1, 0);
        f.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                                public void changed(ObservableValue<? extends Toggle> ov,
                                    Toggle old_toggle, Toggle new_toggle) {
                                    if (f.getSelectedToggle() != null) {

                                                controller.typeTheme = f.getSelectedToggle().getUserData().toString();
                                                System.out.println("type : " + type);
                                    }
                                }
                            });

        StackPane f1 = new StackPane();
        f1.getChildren().add(fond);
        grille.add(f1, 2, 2);

        grille_pref.add(grille, 0, 1);
        plat.getChildren().add(grille_pref);

        AnchorPane.setLeftAnchor(plat, (double) 5);
        AnchorPane.setRightAnchor(plat, (double) 5);
        AnchorPane.setTopAnchor(plat, (double) 5);
        AnchorPane.setBottomAnchor(plat, (double) 5);
        pane.getChildren().add(plat);

        DropShadow shadow = new DropShadow();
        Button valider = new Button("Valider");
        valider.setFont(new Font(police, width/35));
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
                System.out.println(choix.getValue());
                controller.langue = choix.getValue();
                System.out.println(aide_oui.isSelected());
                pane.getChildren().clear();
                if(origin=="menu"){
                    controller.goToMenu();
                }
                else if(origin=="joueurs"){
                    controller.goToChoixJoueur();
                }
                else if(origin=="charger"){
                    try {
                        controller.goToChargerPartie();
                    } catch (IOException ex) {
                        Logger.getLogger(Bouton.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if(origin=="regles"){
                    controller.goToRegles();
                }
                else if(origin=="stat"){
                    controller.goToStat();
                }
                else if(origin=="credits"){
                    controller.goToCredits();
                }

            }
        });
        valider.setMinHeight(20);
        //valider.setMaxHeight(11);
        AnchorPane.setBottomAnchor(valider, (double) 90);
        //AnchorPane.setTopAnchor(valider, (double) height - 100);
        AnchorPane.setLeftAnchor(valider, (double) tailleDeCase*3);
        AnchorPane.setRightAnchor(valider, (double) tailleDeCase*3);


        pane.getChildren().add(valider);

        StackPane quiter_pref = new StackPane();
            Image imageQ = c.getImage("exit3.png");
            ImageView ImQ = new ImageView(imageQ);
            ImQ.setFitHeight(tailleDeCase/2.5);
            ImQ.setFitWidth(tailleDeCase/2.5);
            quiter_pref.getChildren().add(ImQ);
            quiter_pref.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event1) -> {
                pane.getChildren().clear();
                if(origin=="menu"){
                    controller.goToMenu();
                }
                else if(origin=="joueurs"){
                    controller.goToChoixJoueur();
                }
                else if(origin=="charger"){
                    try {
                        controller.goToChargerPartie();
                    } catch (IOException ex) {
                        Logger.getLogger(Bouton.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if(origin=="regles"){
                    controller.goToRegles();
                }
                else if(origin=="stat"){
                    controller.goToStat();
                }
                else if(origin=="credits"){
                    controller.goToCredits();
                }
            });
            AnchorPane.setRightAnchor(quiter_pref, (double) 5);
            AnchorPane.setTopAnchor(quiter_pref, (double) 5);
            pane.getChildren().add(quiter_pref);

        this.getChildren().add(pane);


    }
}
