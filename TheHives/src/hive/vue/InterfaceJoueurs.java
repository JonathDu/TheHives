/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import hive.controller.SavesGesture;
import hive.model.game.Game;
import hive.model.players.decisions.IA.Level;
import javafx.geometry.Pos;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 *
 * @author Adeline
 */
public class InterfaceJoueurs extends Interface {

    String versionIA1;
    String versionIA2;
    int est_ia_ia = 0, est_h_ai = 0, est_ai_h = 0, est_h_h = 0, est_f1 = 0, est_f2 = 0, est_m1 = 0, est_m2 = 0, est_d1 = 0, est_d2 = 0;
    TextField Name1 = new TextField();
    TextField Name2 = new TextField();
    Level level1 = null;
    Level level2 = null;
    private final Label joueur1;
    private final Label joueur2;
    private final Button valider;

    public InterfaceJoueurs(Stage primaryStage, Controller controller, CacheImage c) {
        super(primaryStage, controller, c);

        AnchorPane pane = new AnchorPane();
        pane.prefWidthProperty().bind(primaryStage.widthProperty());
        pane.prefHeightProperty().bind(primaryStage.heightProperty());

        double hauteurDeGrille = tailleDeCase * 4.2;
        double hauteurDeLigne = hauteurDeGrille / 4;
        double largeurDeGrille = width - 50;
        double largeurDeColonne = largeurDeGrille / 3;

        double largeurBouton;
        double hauteurBouton;
        if (width > max_screen_width * 0.75) {
            largeurBouton = largeurDeColonne * 0.6;
        } else {
            largeurBouton = largeurDeColonne;
        }
        hauteurBouton = largeurBouton / 7.2375;

        double flecheLargeur = tailleDeCase * 4 - 30;
        double flecheHauteur = flecheLargeur / 7.24;

        joueur1 = new Label();
        joueur2 = new Label();
        valider = new Button();

        Image hexagone = c.getImage("niveau/hexagoneCoupé.png");
        Image rectangle = c.getImage("niveau/RectangleCoupé.png");

        Name1.setFont(new Font(15));
        Name2.setFont(new Font(15));
        Name1.setText(null);
        Name2.setText(null);
        setTextWithCurrentLanguage();

        BorderPane bp = new BorderPane();
        bp.prefHeightProperty().bind(pane.heightProperty());
        bp.prefWidthProperty().bind(pane.widthProperty());

        AnchorPane top = new AnchorPane();
        top.prefHeightProperty().bind(bp.heightProperty().multiply(0.13));
        top.prefWidthProperty().bind(bp.widthProperty());

        top.getChildren().add(droite);

        AnchorPane.setLeftAnchor(boutonRetourMenu, (double) 5);
        AnchorPane.setTopAnchor(boutonRetourMenu, (double) 5);
        top.getChildren().add(boutonRetourMenu);

        bp.setTop(top);

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundFond = new BackgroundImage(hexagone, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        BackgroundSize backgroundSize2 = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundFond2 = new BackgroundImage(rectangle, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize2);
        Image facile_im = c.getImage(controller.gestionnaireLangage.getText("image_facile"));
        BackgroundSize facile_imSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage facile_imFond = new BackgroundImage(facile_im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, facile_imSize);
        Image moyenne_im = c.getImage(controller.gestionnaireLangage.getText("image_moyenne"));
        BackgroundSize moyenne_imSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage moyenne_imFond = new BackgroundImage(moyenne_im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, moyenne_imSize);
        Image difficile_im = c.getImage(controller.gestionnaireLangage.getText("image_difficile"));
        BackgroundSize difficile_imSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage difficile_imFond = new BackgroundImage(difficile_im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, difficile_imSize);
        Image change_im = c.getImage("exchange1.png");
        BackgroundSize change_imSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage change_imFond = new BackgroundImage(change_im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, change_imSize);
        
        AnchorPane grille_p = new AnchorPane();
        grille_p.prefHeightProperty().bind(bp.heightProperty().multiply(0.6));
        grille_p.prefWidthProperty().bind(bp.widthProperty().multiply(0.8));
        StackPane grille_sp = new StackPane();
        grille_sp.prefHeightProperty().bind(grille_p.heightProperty());
        grille_sp.prefWidthProperty().bind(grille_p.widthProperty());
        GridPane grille = new GridPane();
        int ligne = 100 / 3;
        int colonne = 100 / 1;
        Outils.fixerRepartition(grille, Outils.HORIZONTAL, ligne, ligne, ligne);
        Outils.fixerRepartition(grille, Outils.VERTICAL, colonne);
        grille.prefHeightProperty().bind(grille_sp.heightProperty());
        grille.prefWidthProperty().bind(grille_sp.widthProperty());
        
        final ToggleGroup ia1 = new ToggleGroup();
        final ToggleGroup ia2 = new ToggleGroup();
        final ToggleGroup j = new ToggleGroup();
        MyRadioBouton bouton = new MyRadioBouton(primaryStage, controller);
        
        StackPane grille_l1_sp = new StackPane();
        grille_l1_sp.prefHeightProperty().bind(grille.heightProperty().divide(3));
        grille_l1_sp.prefWidthProperty().bind(grille.widthProperty());
        GridPane grille_l1 = new GridPane();
        int ligne1 = 100 / 1;
        int colonne1 = 100 / 4;
        Outils.fixerRepartition(grille_l1, Outils.HORIZONTAL, ligne1);
        Outils.fixerRepartition(grille_l1, Outils.VERTICAL, colonne1, colonne1, colonne1, colonne1);
        grille_l1.prefHeightProperty().bind(grille_l1_sp.heightProperty());
        grille_l1.prefWidthProperty().bind(grille_l1_sp.widthProperty());
        
        StackPane hh = new StackPane();
        hh.prefHeightProperty().bind(grille_l1.heightProperty());
        hh.prefWidthProperty().bind(grille_l1.widthProperty().divide(4));
        ToggleButton humains;
        humains = bouton.creer("humains");
        humains.prefHeightProperty().bind(hh.heightProperty().multiply(0.8));
        humains.prefWidthProperty().bind(hh.widthProperty().multiply(0.8));
        Image h_h_im = c.getImage("plusDeBoutons/plusDeBoutons/BoutonHumainVsHumain.png");
        BackgroundSize h_h_imSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage h_h_imFond = new BackgroundImage(h_h_im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, h_h_imSize);
        background = new Background(h_h_imFond);
        humains.setBackground(background);
        //humains.setBackground(Background.EMPTY);
        humains.setToggleGroup(j);
        hh.getChildren().add(humains);
        grille_l1.add(hh, 0, 0);
        
        StackPane h_ia = new StackPane();
        ToggleButton hIA;
        hIA = bouton.creer("h_IA");
        h_ia.prefHeightProperty().bind(grille_l1.heightProperty());
        h_ia.prefWidthProperty().bind(grille_l1.widthProperty().divide(4));
        est_h_ai = 1;
        hIA.setSelected(true);
        background = new Background(backgroundFond);
        h_ia.setBackground(background);
        hIA.prefHeightProperty().bind(h_ia.heightProperty().multiply(0.8));
        hIA.prefWidthProperty().bind(h_ia.widthProperty().multiply(0.8));
        Image h_ia_im = c.getImage("plusDeBoutons/plusDeBoutons/BoutonIAVsHumain.png");
        BackgroundSize h_ia_imSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage h_ia_imFond = new BackgroundImage(h_ia_im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, h_ia_imSize);
        background = new Background(h_ia_imFond);
        hIA.setBackground(background);
        //hIA.setBackground(Background.EMPTY);
        hIA.setToggleGroup(j);
        hIA.setSelected(true);
        est_h_ai = 1;
        h_ia.getChildren().add(hIA);
        grille_l1.add(h_ia, 1, 0);
        
        StackPane ia_h = new StackPane();
        ia_h.prefHeightProperty().bind(grille_l1.heightProperty());
        ia_h.prefWidthProperty().bind(grille_l1.widthProperty().divide(4));
        ToggleButton IAh;
        IAh = bouton.creer("IA_h");
        IAh.prefHeightProperty().bind(ia_h.heightProperty().multiply(0.8));
        IAh.prefWidthProperty().bind(ia_h.widthProperty().multiply(0.8));
        Image ia_h_im = c.getImage("plusDeBoutons/plusDeBoutons/BoutonInverse.png");
        BackgroundSize ia_h_imSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage ia_h_imFond = new BackgroundImage(ia_h_im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, ia_h_imSize);
        background = new Background(ia_h_imFond);
        IAh.setBackground(background);
        IAh.setToggleGroup(j);
        ia_h.getChildren().add(IAh);
        grille_l1.add(ia_h, 2, 0);
        
        StackPane ia_ia = new StackPane();
        ia_ia.prefHeightProperty().bind(grille_l1.heightProperty());
        ia_ia.prefWidthProperty().bind(grille_l1.widthProperty().divide(4));
        ToggleButton IAs;
        IAs = bouton.creer("IAs");
        IAs.prefHeightProperty().bind(ia_ia.heightProperty().multiply(0.8));
        IAs.prefWidthProperty().bind(ia_ia.widthProperty().multiply(0.8));
        Image ia_ia_im = c.getImage("plusDeBoutons/plusDeBoutons/BoutonPersoRobotVsRobo.png");
        BackgroundSize ia_ia_imSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage ia_ia_imFond = new BackgroundImage(ia_ia_im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, ia_ia_imSize);
        background = new Background(ia_ia_imFond);
        IAs.setBackground(background);
        IAs.setToggleGroup(j);
        ia_ia.getChildren().add(IAs);
        grille_l1.add(ia_ia, 3, 0);
        grille_l1_sp.getChildren().add(grille_l1);
        grille.add(grille_l1_sp, 0, 0);
        
        StackPane grille_l2_sp = new StackPane();
        grille_l2_sp.prefHeightProperty().bind(grille.heightProperty().divide(3));
        grille_l2_sp.prefWidthProperty().bind(grille.widthProperty());
        GridPane grille_l2 = new GridPane();
        int ligne2 = 100 / 1;
        int colonne2 = 100 / 3;
        Outils.fixerRepartition(grille_l2, Outils.HORIZONTAL, ligne2);
        Outils.fixerRepartition(grille_l2, Outils.VERTICAL, colonne2, colonne2, colonne2);
        grille_l2.prefHeightProperty().bind(grille_l2_sp.heightProperty());
        grille_l2.prefWidthProperty().bind(grille_l2_sp.widthProperty());
        
        StackPane n1 = new StackPane();
        n1.prefHeightProperty().bind(grille_l2.heightProperty());
        n1.prefWidthProperty().bind(grille_l2.widthProperty().divide(3));
        Name1.prefHeightProperty().bind(n1.heightProperty().multiply(0.5));
        Name1.prefWidthProperty().bind(n1.widthProperty().multiply(0.8));
        Name1.setAlignment(Pos.CENTER);
        n1.getChildren().add(Name1);
        grille_l2.add(n1, 1, 0);
        grille_l2_sp.getChildren().add(grille_l2);
        grille.add(grille_l2_sp, 0, 1);
        
        StackPane grille_l3_sp = new StackPane();
        grille_l3_sp.prefHeightProperty().bind(grille.heightProperty().divide(3));
        grille_l3_sp.prefWidthProperty().bind(grille.widthProperty());
        GridPane grille_l3 = new GridPane();
        int ligne3 = 100 / 1;
        int colonne3 = 100 / 3;
        Outils.fixerRepartition(grille_l3, Outils.HORIZONTAL, ligne3);
        Outils.fixerRepartition(grille_l3, Outils.VERTICAL, colonne3, colonne3, colonne3);
        grille_l3.prefHeightProperty().bind(grille_l3_sp.heightProperty());
        grille_l3.prefWidthProperty().bind(grille_l3_sp.widthProperty());
        
        StackPane f2 = new StackPane();
        StackPane m2 = new StackPane();
        StackPane d2 = new StackPane();
        f2.prefHeightProperty().bind(grille_l3.heightProperty());
        f2.prefWidthProperty().bind(grille_l3.widthProperty().divide(3));
        ToggleButton facile;
        facile = bouton.creer("facile"); //Facile, Einfach
        facile.prefHeightProperty().bind(f2.heightProperty().multiply(0.9));
        facile.prefWidthProperty().bind(f2.widthProperty().multiply(0.9));
        background = new Background(facile_imFond);
        facile.setBackground(background);
        facile.setToggleGroup(ia2);
        f2.getChildren().add(facile);
        grille_l3.add(f2, 0, 0);
        m2.prefHeightProperty().bind(grille_l3.heightProperty());
        m2.prefWidthProperty().bind(grille_l3.widthProperty().divide(3));
        ToggleButton moyenne;
        moyenne = bouton.creer("moyenne");
        background = new Background(backgroundFond2);
        m2.setBackground(background);
        moyenne.prefHeightProperty().bind(m2.heightProperty().multiply(0.9));
        moyenne.prefWidthProperty().bind(m2.widthProperty().multiply(0.9));
        background = new Background(moyenne_imFond);
        moyenne.setBackground(background);
        est_m2 = 1;
        moyenne.setSelected(true);
        background = new Background(backgroundFond2);
        m2.setBackground(background);
        moyenne.setToggleGroup(ia2);
        m2.getChildren().add(moyenne);
        grille_l3.add(m2, 1, 0);
        d2.prefHeightProperty().bind(grille_l3.heightProperty());
        d2.prefWidthProperty().bind(grille_l3.widthProperty().divide(3));
        ToggleButton difficile;
        difficile = bouton.creer("difficile");
        difficile.prefHeightProperty().bind(d2.heightProperty().multiply(0.9));
        difficile.prefWidthProperty().bind(d2.widthProperty().multiply(0.9));
        background = new Background(difficile_imFond);
        difficile.setBackground(background);
        difficile.setToggleGroup(ia2);
        d2.getChildren().add(difficile);
        grille_l3.add(d2, 2, 0);
        versionIA2 = "moyenne";
        ia2.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                    Toggle old_toggle, Toggle new_toggle) {
                if (ia2.getSelectedToggle() != null) {
                    if (facile.isSelected()) {
                       if (est_m2 == 1) {
                            m2.setBackground(Background.EMPTY);
                            est_m2 = 0;
                        } else if (est_d2 == 1) {
                            d2.setBackground(Background.EMPTY);
                            est_d2 = 0;
                        }
                        if (est_f2 == 0 && est_m2 == 0 && est_d2 == 0) {
                            background = new Background(backgroundFond2);
                            f2.setBackground(background);
                            est_f2 = 1;
                        }
                    } else if (moyenne.isSelected()) {
                        if (est_f2 == 1) {
                            f2.setBackground(Background.EMPTY);
                            est_f2 = 0;
                        } else if (est_d2 == 1) {
                            d2.setBackground(Background.EMPTY);
                            est_d2 = 0;
                        }
                        if (est_f2 == 0 && est_m2 == 0 && est_d2 == 0) {
                            background = new Background(backgroundFond2);
                            m2.setBackground(background);
                            est_m2 = 1;
                        }
                    } else if (difficile.isSelected()) {
                        if (est_f2 == 1) {
                            f2.setBackground(Background.EMPTY);
                            est_f2 = 0;
                        } else if (est_m2 == 1) {
                            m2.setBackground(Background.EMPTY);
                            est_m2 = 0;
                        }
                        if (est_f2 == 0 && est_m2 == 0 && est_d2 == 0) {
                            background = new Background(backgroundFond2);
                            d2.setBackground(background);
                            est_d2 = 1;
                        }
                    }

                    versionIA2 = ia2.getSelectedToggle().getUserData().toString();
                    System.out.println("IA2 : " + versionIA2);
                }
            }
        });
        grille_l3_sp.getChildren().add(grille_l3);
        grille.add(grille_l3_sp, 0, 2);
        
        j.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                    Toggle old_toggle, Toggle new_toggle) {
                if (j.getSelectedToggle() != null) {
                    grille.getChildren().remove(grille_l2_sp);
                    grille.getChildren().remove(grille_l3_sp);
                    grille_l2_sp.getChildren().remove(grille_l2);
                    grille_l3_sp.getChildren().remove(grille_l3);
                    grille_l2.getChildren().remove(0, grille_l2.getChildren().size());
                    grille_l3.getChildren().remove(0, grille_l3.getChildren().size());
                    if (humains.isSelected()) {
                        if (est_ia_ia == 1) {
                            est_f1 = 0;
                            est_f2 = 0;
                            est_m1 = 0;
                            est_m2 = 0;
                            est_d1 = 0;
                            est_d2 = 0;
                            //grille.getChildren().remove(grille.getChildren().size() - 6, grille.getChildren().size());
                            ia_ia.setBackground(Background.EMPTY);
                            est_ia_ia = 0;
                            versionIA1 = null;
                            versionIA2 = null;
                        } else if (est_h_ai == 1) {
                            //grille.getChildren().remove(grille.getChildren().size() - 3, grille.getChildren().size());
                            est_f2 = 0;
                            est_m2 = 0;
                            est_d2 = 0;
                            h_ia.setBackground(Background.EMPTY);
                            est_h_ai = 0;
                            versionIA2 = null;
                            Name1.setText(null);
                        }else if (est_ai_h == 1) {
                            //grille.getChildren().remove(grille.getChildren().size() - 3, grille.getChildren().size());
                            est_f1 = 0;
                            est_m1 = 0;
                            est_d1 = 0;
                            ia_h.setBackground(Background.EMPTY);
                            est_ai_h = 0;
                            versionIA1 = null;
                            Name2.setText(null);
                        }
                        if (est_h_h == 0 && est_h_ai == 0 && est_ia_ia == 0) {
                            background = new Background(backgroundFond);
                            hh.setBackground(background);
                            est_h_h = 1;
                            StackPane n1 = new StackPane();
                            n1.prefHeightProperty().bind(grille_l2.heightProperty());
                            n1.prefWidthProperty().bind(grille_l2.widthProperty().divide(3));
                            Name1.prefHeightProperty().bind(n1.heightProperty().multiply(0.5));
                            Name1.prefWidthProperty().bind(n1.widthProperty().multiply(0.8));
                            Name1.setAlignment(Pos.CENTER);
                            n1.getChildren().add(Name1);
                            grille_l2.add(n1, 1, 0);
                            grille_l2_sp.getChildren().add(grille_l2);
                            grille.add(grille_l2_sp, 0, 1);
                            StackPane n2 = new StackPane();
                            n2.prefHeightProperty().bind(grille_l3.heightProperty());
                            n2.prefWidthProperty().bind(grille_l3.widthProperty().divide(3));
                            Name2.prefHeightProperty().bind(n2.heightProperty().multiply(0.5));
                            Name2.prefWidthProperty().bind(n2.widthProperty().multiply(0.8));
                            Name2.setAlignment(Pos.CENTER);
                            n2.getChildren().add(Name2);
                            grille_l3.add(n2, 1, 0);
                            grille_l3_sp.getChildren().add(grille_l3);
                            grille.add(grille_l3_sp, 0, 2);
                        }
                    } else if (hIA.isSelected()) {
                        if (est_ia_ia == 1) {
                            est_f1 = 0;
                            est_f2 = 0;
                            est_m1 = 0;
                            est_m2 = 0;
                            est_d1 = 0;
                            est_d2 = 0;
                            //grille.getChildren().remove(grille.getChildren().size() - 6, grille.getChildren().size());
                            ia_ia.setBackground(Background.EMPTY);
                            est_ia_ia = 0;
                            versionIA1 = null;
                            versionIA2 = null;
                        } else if (est_h_h == 1) {
                            //grille.getChildren().remove(grille.getChildren().size() - 3, grille.getChildren().size());
                            hh.setBackground(Background.EMPTY);
                            est_h_h = 0;
                            Name1.setText(null);
                            Name2.setText(null);
                        }else if (est_ai_h == 1) {
                            //grille.getChildren().remove(grille.getChildren().size() - 3, grille.getChildren().size());
                            est_f1 = 0;
                            est_m1 = 0;
                            est_d1 = 0;
                            ia_h.setBackground(Background.EMPTY);
                            est_ai_h = 0;
                            versionIA1 = null;
                            Name2.setText(null);
                        }
                        if (est_h_h == 0 && est_h_ai == 0 && est_ia_ia == 0) {
                            background = new Background(backgroundFond);
                            h_ia.setBackground(background);
                            est_h_ai = 1;
                            StackPane n1 = new StackPane();
                            n1.prefHeightProperty().bind(grille_l2.heightProperty());
                            n1.prefWidthProperty().bind(grille_l2.widthProperty().divide(3));
                            Name1.prefHeightProperty().bind(n1.heightProperty().multiply(0.5));
                            Name1.prefWidthProperty().bind(n1.widthProperty().multiply(0.8));
                            Name1.setAlignment(Pos.CENTER);
                            n1.getChildren().add(Name1);
                            grille_l2.add(n1, 1, 0);
                            grille_l2_sp.getChildren().add(grille_l2);
                            grille.add(grille_l2_sp, 0, 1);
                            StackPane f2 = new StackPane();
                            StackPane m2 = new StackPane();
                            StackPane d2 = new StackPane();
                            f2.prefHeightProperty().bind(grille_l3.heightProperty());
                            f2.prefWidthProperty().bind(grille_l3.widthProperty().divide(3));
                            ToggleButton facile;
                            facile = bouton.creer("facile"); //Facile, Einfach
                            facile.prefHeightProperty().bind(f2.heightProperty().multiply(0.9));
                            facile.prefWidthProperty().bind(f2.widthProperty().multiply(0.9));
                            background = new Background(facile_imFond);
                            facile.setBackground(background);
                            facile.setToggleGroup(ia2);
                            f2.getChildren().add(facile);
                            grille_l3.add(f2, 0, 0);
                            m2.prefHeightProperty().bind(grille_l3.heightProperty());
                            m2.prefWidthProperty().bind(grille_l3.widthProperty().divide(3));
                            ToggleButton moyenne;
                            moyenne = bouton.creer("moyenne");
                            moyenne.prefHeightProperty().bind(m2.heightProperty().multiply(0.9));
                            moyenne.prefWidthProperty().bind(m2.widthProperty().multiply(0.9));
                            background = new Background(moyenne_imFond);
                            moyenne.setBackground(background);
                            moyenne.setToggleGroup(ia2);
                            m2.getChildren().add(moyenne);
                            grille_l3.add(m2, 1, 0);
                            d2.prefHeightProperty().bind(grille_l3.heightProperty());
                            d2.prefWidthProperty().bind(grille_l3.widthProperty().divide(3));
                            ToggleButton difficile;
                            difficile = bouton.creer("difficile");
                            difficile.prefHeightProperty().bind(d2.heightProperty().multiply(0.9));
                            difficile.prefWidthProperty().bind(d2.widthProperty().multiply(0.9));
                            background = new Background(difficile_imFond);
                            difficile.setBackground(background);
                            difficile.setToggleGroup(ia2);
                            d2.getChildren().add(difficile);
                            grille_l3.add(d2, 2, 0);
                            versionIA2 = "moyenne";
                            ia2.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                                public void changed(ObservableValue<? extends Toggle> ov,
                                        Toggle old_toggle, Toggle new_toggle) {
                                    if (ia2.getSelectedToggle() != null) {
                                        if (facile.isSelected()) {
                                           if (est_m2 == 1) {
                                                m2.setBackground(Background.EMPTY);
                                                est_m2 = 0;
                                            } else if (est_d2 == 1) {
                                                d2.setBackground(Background.EMPTY);
                                                est_d2 = 0;
                                            }
                                            if (est_f2 == 0 && est_m2 == 0 && est_d2 == 0) {
                                                background = new Background(backgroundFond2);
                                                f2.setBackground(background);
                                                est_f2 = 1;
                                            }
                                        } else if (moyenne.isSelected()) {
                                            if (est_f2 == 1) {
                                                f2.setBackground(Background.EMPTY);
                                                est_f2 = 0;
                                            } else if (est_d2 == 1) {
                                                d2.setBackground(Background.EMPTY);
                                                est_d2 = 0;
                                            }
                                            if (est_f2 == 0 && est_m2 == 0 && est_d2 == 0) {
                                                background = new Background(backgroundFond2);
                                                m2.setBackground(background);
                                                est_m2 = 1;
                                            }
                                        } else if (difficile.isSelected()) {
                                            if (est_f2 == 1) {
                                                f2.setBackground(Background.EMPTY);
                                                est_f2 = 0;
                                            } else if (est_m2 == 1) {
                                                m2.setBackground(Background.EMPTY);
                                                est_m2 = 0;
                                            }
                                            if (est_f2 == 0 && est_m2 == 0 && est_d2 == 0) {
                                                background = new Background(backgroundFond2);
                                                d2.setBackground(background);
                                                est_d2 = 1;
                                            }
                                        }

                                        versionIA2 = ia2.getSelectedToggle().getUserData().toString();
                                        System.out.println("IA2 : " + versionIA2);
                                    }
                                }
                            });
                            grille_l3_sp.getChildren().add(grille_l3);
                            grille.add(grille_l3_sp, 0, 2);

                        }
                    } else if (IAh.isSelected()) {
                        if (est_ia_ia == 1) {
                            est_f1 = 0;
                            est_f2 = 0;
                            est_m1 = 0;
                            est_m2 = 0;
                            est_d1 = 0;
                            est_d2 = 0;
                            //grille.getChildren().remove(grille.getChildren().size() - 6, grille.getChildren().size());
                            ia_ia.setBackground(Background.EMPTY);
                            est_ia_ia = 0;
                            versionIA1 = null;
                            versionIA2 = null;
                        } else if (est_h_h == 1) {
                            //grille.getChildren().remove(grille.getChildren().size() - 3, grille.getChildren().size());
                            hh.setBackground(Background.EMPTY);
                            est_h_h = 0;
                            Name1.setText(null);
                            Name2.setText(null);
                        }else if (est_h_ai == 1) {
                            //grille.getChildren().remove(grille.getChildren().size() - 3, grille.getChildren().size());
                            est_f2 = 0;
                            est_m2 = 0;
                            est_d2 = 0;
                            h_ia.setBackground(Background.EMPTY);
                            est_h_ai = 0;
                            Name1.setText(null);
                            versionIA2 = null;
                        }
                        if (est_h_h == 0 && est_h_ai == 0 && est_ia_ia == 0) {
                            background = new Background(backgroundFond);
                            ia_h.setBackground(background);
                            est_ai_h = 1;
                            StackPane f1 = new StackPane();
                            StackPane m1 = new StackPane();
                            StackPane d1 = new StackPane();
                            f1.prefHeightProperty().bind(grille_l2.heightProperty());
                            f1.prefWidthProperty().bind(grille_l2.widthProperty().divide(3));
                            ToggleButton facile1;
                            facile1 = bouton.creer("facile"); //Facile, Einfach
                            facile1.prefHeightProperty().bind(f1.heightProperty().multiply(0.9));
                            facile1.prefWidthProperty().bind(f1.widthProperty().multiply(0.9));
                            background = new Background(facile_imFond);
                            facile1.setBackground(background);
                            facile1.setToggleGroup(ia1);
                            f1.getChildren().add(facile1);
                            grille_l2.add(f1, 0, 0);
                            m1.prefHeightProperty().bind(grille_l2.heightProperty());
                            m1.prefWidthProperty().bind(grille_l2.widthProperty().divide(3));
                            ToggleButton moyenne1;
                            moyenne1 = bouton.creer("moyenne");
                            moyenne1.prefHeightProperty().bind(m1.heightProperty().multiply(0.9));
                            moyenne1.prefWidthProperty().bind(m1.widthProperty().multiply(0.9));
                            background = new Background(moyenne_imFond);
                            moyenne1.setBackground(background);
                            moyenne1.setToggleGroup(ia1);
                            m1.getChildren().add(moyenne1);
                            grille_l2.add(m1, 1, 0);
                            d1.prefHeightProperty().bind(grille_l2.heightProperty());
                            d1.prefWidthProperty().bind(grille_l2.widthProperty().divide(3));
                            ToggleButton difficile1;
                            difficile1 = bouton.creer("difficile");
                            difficile1.prefHeightProperty().bind(d2.heightProperty().multiply(0.9));
                            difficile1.prefWidthProperty().bind(d2.widthProperty().multiply(0.9));
                            background = new Background(difficile_imFond);
                            difficile1.setBackground(background);
                            difficile1.setToggleGroup(ia1);
                            d1.getChildren().add(difficile1);
                            grille_l2.add(d1, 2, 0);
                            ia1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                                public void changed(ObservableValue<? extends Toggle> ov,
                                        Toggle old_toggle, Toggle new_toggle) {
                                    if (ia1.getSelectedToggle() != null) {
                                        if (facile1.isSelected()) {
                                           if (est_m1 == 1) {
                                                m1.setBackground(Background.EMPTY);
                                                est_m1 = 0;
                                            } else if (est_d1 == 1) {
                                                d1.setBackground(Background.EMPTY);
                                                est_d1 = 0;
                                            }
                                            if (est_f1 == 0 && est_m1 == 0 && est_d1 == 0) {
                                                background = new Background(backgroundFond2);
                                                f1.setBackground(background);
                                                est_f1 = 1;
                                            }
                                        } else if (moyenne1.isSelected()) {
                                            if (est_f1 == 1) {
                                                f1.setBackground(Background.EMPTY);
                                                est_f1 = 0;
                                            } else if (est_d1 == 1) {
                                                d1.setBackground(Background.EMPTY);
                                                est_d1 = 0;
                                            }
                                            if (est_f1 == 0 && est_m1 == 0 && est_d1 == 0) {
                                                background = new Background(backgroundFond2);
                                                m1.setBackground(background);
                                                est_m1 = 1;
                                            }
                                        } else if (difficile1.isSelected()) {
                                            if (est_f1 == 1) {
                                                f1.setBackground(Background.EMPTY);
                                                est_f1 = 0;
                                            } else if (est_m1 == 1) {
                                                m1.setBackground(Background.EMPTY);
                                                est_m1 = 0;
                                            }
                                            if (est_f1 == 0 && est_m1 == 0 && est_d1 == 0) {
                                                background = new Background(backgroundFond2);
                                                d1.setBackground(background);
                                                est_d1 = 1;
                                            }
                                        }

                                        versionIA1 = ia1.getSelectedToggle().getUserData().toString();
                                        System.out.println("IA1 : " + versionIA1);
                                    }
                                }
                            });
                            grille_l2_sp.getChildren().add(grille_l2);
                            grille.add(grille_l2_sp, 0, 1);

                            StackPane n2 = new StackPane();
                            n2.prefHeightProperty().bind(grille_l3.heightProperty());
                            n2.prefWidthProperty().bind(grille_l3.widthProperty().divide(3));
                            Name2.prefHeightProperty().bind(n2.heightProperty().multiply(0.5));
                            Name2.prefWidthProperty().bind(n2.widthProperty().multiply(0.8));
                            Name2.setAlignment(Pos.CENTER);
                            n2.getChildren().add(Name2);
                            grille_l3.add(n2, 1, 0);
                            grille_l3_sp.getChildren().add(grille_l3);
                            grille.add(grille_l3_sp, 0, 2);
                            
                        }
                    } else if (IAs.isSelected()) {
                        if (est_h_ai == 1) {
                            //grille.getChildren().remove(grille.getChildren().size() - 4, grille.getChildren().size());
                            est_f2 = 0;
                            est_m2 = 0;
                            est_d2 = 0;
                            h_ia.setBackground(Background.EMPTY);
                            est_h_ai = 0;
                            versionIA2 = null;
                            Name1.setText(null);
                        } else if (est_h_h == 1) {
                            //grille.getChildren().remove(grille.getChildren().size() - 2, grille.getChildren().size());
                            hh.setBackground(Background.EMPTY);
                            est_h_h = 0;
                            Name1.setText(null);
                            Name2.setText(null);
                        }else if (est_ai_h == 1) {
                            //grille.getChildren().remove(grille.getChildren().size() - 3, grille.getChildren().size());
                            est_f1 = 0;
                            est_m1 = 0;
                            est_d1 = 0;
                            ia_h.setBackground(Background.EMPTY);
                            est_ai_h = 0;
                            versionIA1 = null;
                            Name2.setText(null);
                        }
                        if (est_h_h == 0 && est_h_ai == 0 && est_ia_ia == 0) {
                            background = new Background(backgroundFond);
                            ia_ia.setBackground(background);
                            est_ia_ia = 1;
                            StackPane f1 = new StackPane();
                            StackPane m1 = new StackPane();
                            StackPane d1 = new StackPane();
                            f1.prefHeightProperty().bind(grille_l2.heightProperty());
                            f1.prefWidthProperty().bind(grille_l2.widthProperty().divide(3));
                            ToggleButton facile1;
                            facile1 = bouton.creer("facile"); //Facile, Einfach
                            facile1.prefHeightProperty().bind(f1.heightProperty().multiply(0.9));
                            facile1.prefWidthProperty().bind(f1.widthProperty().multiply(0.9));
                            background = new Background(facile_imFond);
                            facile1.setBackground(background);
                            facile1.setToggleGroup(ia1);
                            f1.getChildren().add(facile1);
                            grille_l2.add(f1, 0, 0);
                            m1.prefHeightProperty().bind(grille_l2.heightProperty());
                            m1.prefWidthProperty().bind(grille_l2.widthProperty().divide(3));
                            ToggleButton moyenne1;
                            moyenne1 = bouton.creer("moyenne");
                            moyenne1.prefHeightProperty().bind(m1.heightProperty().multiply(0.9));
                            moyenne1.prefWidthProperty().bind(m1.widthProperty().multiply(0.9));
                            background = new Background(moyenne_imFond);
                            moyenne1.setBackground(background);
                            moyenne1.setToggleGroup(ia1);
                            m1.getChildren().add(moyenne1);
                            grille_l2.add(m1, 1, 0);
                            d1.prefHeightProperty().bind(grille_l2.heightProperty());
                            d1.prefWidthProperty().bind(grille_l2.widthProperty().divide(3));
                            ToggleButton difficile1;
                            difficile1 = bouton.creer("difficile");
                            difficile1.prefHeightProperty().bind(d2.heightProperty().multiply(0.9));
                            difficile1.prefWidthProperty().bind(d2.widthProperty().multiply(0.9));
                            background = new Background(difficile_imFond);
                            difficile1.setBackground(background);
                            difficile1.setToggleGroup(ia1);
                            d1.getChildren().add(difficile1);
                            grille_l2.add(d1, 2, 0);
                            ia1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                                public void changed(ObservableValue<? extends Toggle> ov,
                                        Toggle old_toggle, Toggle new_toggle) {
                                    if (ia1.getSelectedToggle() != null) {
                                        if (facile1.isSelected()) {
                                           if (est_m1 == 1) {
                                                m1.setBackground(Background.EMPTY);
                                                est_m1 = 0;
                                            } else if (est_d1 == 1) {
                                                d1.setBackground(Background.EMPTY);
                                                est_d1 = 0;
                                            }
                                            if (est_f1 == 0 && est_m1 == 0 && est_d1 == 0) {
                                                background = new Background(backgroundFond2);
                                                f1.setBackground(background);
                                                est_f1 = 1;
                                            }
                                        } else if (moyenne1.isSelected()) {
                                            if (est_f1 == 1) {
                                                f1.setBackground(Background.EMPTY);
                                                est_f1 = 0;
                                            } else if (est_d1 == 1) {
                                                d1.setBackground(Background.EMPTY);
                                                est_d1 = 0;
                                            }
                                            if (est_f1 == 0 && est_m1 == 0 && est_d1 == 0) {
                                                background = new Background(backgroundFond2);
                                                m1.setBackground(background);
                                                est_m1 = 1;
                                            }
                                        } else if (difficile1.isSelected()) {
                                            if (est_f1 == 1) {
                                                f1.setBackground(Background.EMPTY);
                                                est_f1 = 0;
                                            } else if (est_m1 == 1) {
                                                m1.setBackground(Background.EMPTY);
                                                est_m1 = 0;
                                            }
                                            if (est_f1 == 0 && est_m1 == 0 && est_d1 == 0) {
                                                background = new Background(backgroundFond2);
                                                d1.setBackground(background);
                                                est_d1 = 1;
                                            }
                                        }

                                        versionIA1 = ia1.getSelectedToggle().getUserData().toString();
                                        System.out.println("IA1 : " + versionIA1);
                                    }
                                }
                            });
                            grille_l2_sp.getChildren().add(grille_l2);
                            grille.add(grille_l2_sp, 0, 1);



                            StackPane f2 = new StackPane();
                            StackPane m2 = new StackPane();
                            StackPane d2 = new StackPane();
                            f2.prefHeightProperty().bind(grille_l3.heightProperty());
                            f2.prefWidthProperty().bind(grille_l3.widthProperty().divide(3));
                            ToggleButton facile;
                            facile = bouton.creer("facile"); //Facile, Einfach
                            facile.prefHeightProperty().bind(f2.heightProperty().multiply(0.9));
                            facile.prefWidthProperty().bind(f2.widthProperty().multiply(0.9));
                            background = new Background(facile_imFond);
                            facile.setBackground(background);
                            facile.setToggleGroup(ia2);
                            f2.getChildren().add(facile);
                            grille_l3.add(f2, 0, 0);
                            m2.prefHeightProperty().bind(grille_l3.heightProperty());
                            m2.prefWidthProperty().bind(grille_l3.widthProperty().divide(3));
                            ToggleButton moyenne;
                            moyenne = bouton.creer("moyenne");
                            moyenne.prefHeightProperty().bind(m2.heightProperty().multiply(0.9));
                            moyenne.prefWidthProperty().bind(m2.widthProperty().multiply(0.9));
                            background = new Background(moyenne_imFond);
                            moyenne.setBackground(background);
                            moyenne.setToggleGroup(ia2);
                            m2.getChildren().add(moyenne);
                            grille_l3.add(m2, 1, 0);
                            d2.prefHeightProperty().bind(grille_l3.heightProperty());
                            d2.prefWidthProperty().bind(grille_l3.widthProperty().divide(3));
                            ToggleButton difficile;
                            difficile = bouton.creer("difficile");
                            difficile.prefHeightProperty().bind(d2.heightProperty().multiply(0.9));
                            difficile.prefWidthProperty().bind(d2.widthProperty().multiply(0.9));
                            background = new Background(difficile_imFond);
                            difficile.setBackground(background);
                            difficile.setToggleGroup(ia2);
                            d2.getChildren().add(difficile);
                            grille_l3.add(d2, 2, 0);
                            versionIA2 = "moyenne";
                            ia2.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                                public void changed(ObservableValue<? extends Toggle> ov,
                                        Toggle old_toggle, Toggle new_toggle) {
                                    if (ia2.getSelectedToggle() != null) {
                                        if (facile.isSelected()) {
                                           if (est_m2 == 1) {
                                                m2.setBackground(Background.EMPTY);
                                                est_m2 = 0;
                                            } else if (est_d2 == 1) {
                                                d2.setBackground(Background.EMPTY);
                                                est_d2 = 0;
                                            }
                                            if (est_f2 == 0 && est_m2 == 0 && est_d2 == 0) {
                                                background = new Background(backgroundFond2);
                                                f2.setBackground(background);
                                                est_f2 = 1;
                                            }
                                        } else if (moyenne.isSelected()) {
                                            if (est_f2 == 1) {
                                                f2.setBackground(Background.EMPTY);
                                                est_f2 = 0;
                                            } else if (est_d2 == 1) {
                                                d2.setBackground(Background.EMPTY);
                                                est_d2 = 0;
                                            }
                                            if (est_f2 == 0 && est_m2 == 0 && est_d2 == 0) {
                                                background = new Background(backgroundFond2);
                                                m2.setBackground(background);
                                                est_m2 = 1;
                                            }
                                        } else if (difficile.isSelected()) {
                                            if (est_f2 == 1) {
                                                f2.setBackground(Background.EMPTY);
                                                est_f2 = 0;
                                            } else if (est_m2 == 1) {
                                                m2.setBackground(Background.EMPTY);
                                                est_m2 = 0;
                                            }
                                            if (est_f2 == 0 && est_m2 == 0 && est_d2 == 0) {
                                                background = new Background(backgroundFond2);
                                                d2.setBackground(background);
                                                est_d2 = 1;
                                            }
                                        }

                                        versionIA2 = ia2.getSelectedToggle().getUserData().toString();
                                        System.out.println("IA2 : " + versionIA2);
                                    }
                                }
                            });
                            grille_l3_sp.getChildren().add(grille_l3);
                            grille.add(grille_l3_sp, 0, 2);

                        }
                    }
                }
            }
        });
        
        
        
        
        
        
        
        
        
        grille_sp.getChildren().add(grille);
        AnchorPane.setRightAnchor(grille_sp, (double) 0);
        AnchorPane.setTopAnchor(grille_sp, (double) 0);
        AnchorPane.setLeftAnchor(grille_sp, (double) 0);
        AnchorPane.setBottomAnchor(grille_sp, (double) 0);
        grille_p.getChildren().add(grille_sp);

        
        bp.setCenter(grille_p);
        
        StackPane valider_sp = new StackPane();
        valider_sp.prefHeightProperty().bind(bp.heightProperty().multiply(0.27));
        valider_sp.prefWidthProperty().bind(bp.widthProperty());
        Image val = c.getImage("Design/MenuPrincipaux/FlecheDuMenuDansHexagone.png");
        ImageView valIm = new ImageView(val);
        valIm.setFitHeight(flecheHauteur);
        valIm.setFitWidth(flecheLargeur * 0.6);
        valider_sp.getChildren().add(valIm);
        valider.setTextFill(Color.web("#fbe5b5"));
        valider.setBackground(Background.EMPTY);
        valider_sp.getChildren().add(valider);
        valider_sp.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //System.out.println(est_h_h + " " + est_h_ai + " " + est_ia_ia + " " + est_f1 + " " + est_m1 + " " + est_d1 + " " + est_f2 + " " + est_m2 + " " + est_d2);
                String joueur_1 = new String();
                String joueur_2 = new String();
                if (est_h_h == 1) {
                    if (Name1.getText() == null) {
                        joueur_1 = controller.gestionnaireLangage.getText("text_joueur1");
                    } else {
                        joueur_1 = Name1.getCharacters().toString();
                    }
                    if (Name2.getText() == null) {
                        joueur_2 = controller.gestionnaireLangage.getText("text_joueur2");
                    } else {
                        joueur_2 = Name1.getCharacters().toString();
                    }
                    controller.goToPlateau(joueur_1, joueur_2, null, null);
                } else if (est_h_ai == 1) {
                    joueur_1 = Name1.getCharacters().toString();
                    if (Name1.getText() == null) {
                        joueur_1 = controller.gestionnaireLangage.getText("text_joueur1");
                    }
                    if (est_f2 == 1) {
                        level2 = Level.EASY;
                    } else if (est_m2 == 1) {
                        level2 = Level.MEDIUM;
                    } else if (est_d2 == 1) {
                        level2 = Level.HARD;
                    } else {
                        level2 = Level.MEDIUM;
                    }
                    controller.goToPlateau(joueur_1, creerNomIA(level2), null, level2);

                } else if (est_ai_h == 1) {
                    joueur_2 = Name2.getCharacters().toString();
                    if (Name2.getText() == null) {
                        joueur_2 = controller.gestionnaireLangage.getText("text_joueur2");
                    }
                    if (est_f1 == 1) {
                        level1 = Level.EASY;
                    } else if (est_m1 == 1) {
                        level1 = Level.MEDIUM;
                    } else if (est_d1 == 1) {
                        level1 = Level.HARD;
                    } else {
                        level1 = Level.MEDIUM;
                    }
                    controller.goToPlateau(creerNomIA(level1), joueur_2, level1, null);

                } else if (est_ia_ia == 1) {
                    if (est_f1 == 1) {
                        level1 = Level.EASY;
                    } else if (est_m1 == 1) {
                        level1 = Level.MEDIUM;
                    } else if (est_d1 == 1) {
                        level1 = Level.HARD;
                    } else {
                        level1 = Level.MEDIUM;
                    }
                    if (est_f2 == 1) {
                        level2 = Level.EASY;
                    } else if (est_m2 == 1) {
                        level2 = Level.MEDIUM;
                    } else if (est_d2 == 1) {
                        level2 = Level.HARD;
                    } else {
                        level2 = Level.MEDIUM;
                    }
                    controller.goToPlateau(creerNomIA(level1), creerNomIA(level2), level1, level2);
                };
            }
        });
        bp.setBottom(valider_sp);
        
        StackPane left = new StackPane();
        left.prefWidthProperty().bind(bp.widthProperty().multiply(0.1));
        left.prefHeightProperty().bind(bp.heightProperty().multiply(0.6));
        bp.setLeft(left);
        StackPane right = new StackPane();
        right.prefWidthProperty().bind(bp.widthProperty().multiply(0.1));
        right.prefHeightProperty().bind(bp.heightProperty().multiply(0.6));
        bp.setRight(right);
        
        AnchorPane.setTopAnchor(bp, (double) 0);
        AnchorPane.setBottomAnchor(bp, (double) 0);
        AnchorPane.setLeftAnchor(bp, (double) 0);
        AnchorPane.setRightAnchor(bp, (double) 0);
        pane.getChildren().add(bp);

        this.panePrincipale.getChildren().add(pane);

    }

    public String creerNomIA(Level ia) {
        if (ia == null) {
            return null;
        }
        switch (ia) {
            case EASY:
                return "facile";
            case MEDIUM:
                return "moyenne";
            case HARD:
                return "Difficile";
            default:
                return "Facile";
        }
    }

    @Override
    public void setTextWithCurrentLanguage() {
        joueur1.setText(controller.gestionnaireLangage.getText("text_joueur1"));
        joueur2.setText(controller.gestionnaireLangage.getText("text_joueur2"));
        Name1.setPromptText(controller.gestionnaireLangage.getText("text_nom"));
        Name2.setPromptText(controller.gestionnaireLangage.getText("text_nom"));
        valider.setText(controller.gestionnaireLangage.getText("text_valider"));
    }

}
