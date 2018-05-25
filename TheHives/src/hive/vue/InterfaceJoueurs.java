/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
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
import javafx.scene.layout.GridPane;

/**
 *
 * @author Adeline
 */
public class InterfaceJoueurs extends Interface {

    String versionIA1;
    String versionIA2;

    int est_ia_ia = 0, est_h_ai = 0, est_h_h = 0, est_f1 = 0, est_f2 = 0, est_m1 = 0, est_m2 = 0, est_d1 = 0, est_d2 = 0;
    TextField Name1 = new TextField();
    TextField Name2 = new TextField();
    Level level1 = null;
    Level level2 = null;

    public InterfaceJoueurs(Stage primaryStage, Controller controller, CacheImage c) {
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

        GridPane grille = new GridPane();
        int ligne = 100 / 4;
        int colonne = 100 / 3;
        Outils.fixerRepartition(grille, Outils.HORIZONTAL, ligne, ligne, ligne, ligne);
        Outils.fixerRepartition(grille, Outils.VERTICAL, colonne, colonne, colonne);
//        grille.prefHeightProperty().bind(primaryStage.heightProperty());
//        grille.prefWidthProperty().bind(primaryStage.widthProperty());
        grille.setMaxWidth(width - 50);
        grille.setMinWidth(width - 50);
        grille.setMaxHeight(tailleDeCase * 4.2);
        grille.setMinHeight(tailleDeCase * 4.2);
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

        Label joueur1 = new Label();
        Label joueur2 = new Label();

        Button valider = new Button();

        joueur1.setText(controller.gestionnaireLangage.getText("text_joueur1"));
        joueur2.setText(controller.gestionnaireLangage.getText("text_joueur2"));
        Name1.setText(controller.gestionnaireLangage.getText("text_nom"));
        Name2.setText(controller.gestionnaireLangage.getText("text_nom"));
        valider.setText(controller.gestionnaireLangage.getText("text_valider"));

        Image hexagone = c.getImage("niveau/hexagoneCoupé.png");
        ImageView hexagoneIm = new ImageView(hexagone);
        hexagoneIm.setFitHeight(hauteurDeLigne + 20);
        hexagoneIm.setFitWidth(hauteurDeLigne + 20);
        Image rectangle = c.getImage("niveau/RectangleCoupé.png");
        ImageView rectangleIm1 = new ImageView(rectangle);
        rectangleIm1.setFitHeight(hauteurBouton + 20);
        rectangleIm1.setFitWidth(largeurBouton + 20);
        ImageView rectangleIm2 = new ImageView(rectangle);
        rectangleIm2.setFitHeight(hauteurBouton + 20);
        rectangleIm2.setFitWidth(largeurBouton + 20);

        Name1.setText(null);
        Name2.setText(null);
        Name1.setPromptText(controller.gestionnaireLangage.getText("text_nom"));
        Name2.setPromptText(controller.gestionnaireLangage.getText("text_nom"));
        final ToggleGroup ia1 = new ToggleGroup();
        final ToggleGroup ia2 = new ToggleGroup();

        final ToggleGroup j = new ToggleGroup();
        MyRadioBouton bouton = new MyRadioBouton(primaryStage, controller);
        ToggleButton humains;
        humains = bouton.creer("humains");
        humains.setBackground(Background.EMPTY);
        humains.setToggleGroup(j);
        StackPane hh = new StackPane();
        hh.getChildren().add(humains);
        grille.add(hh, 0, 1);

        ToggleButton hIA;
        hIA = bouton.creer("h_IA");
        hIA.setBackground(Background.EMPTY);
        hIA.setToggleGroup(j);
        hIA.setSelected(true);
        est_h_ai = 1;
        StackPane h_ia = new StackPane();
        h_ia.getChildren().add(hexagoneIm);
        h_ia.getChildren().add(hIA);
        grille.add(h_ia, 1, 1);
        Name1.setMinSize(tailleDeCase * 0.8, 30);
        Name1.setMaxHeight(40);
        Name1.setAlignment(Pos.CENTER);
        StackPane n1 = new StackPane();
        n1.getChildren().add(Name1);
        grille.add(n1, 1, 2);
        ToggleButton facile;
        facile = bouton.creer("facile"); //Facile, Einfach
        facile.setBackground(Background.EMPTY);
        facile.setToggleGroup(ia2);
        StackPane f2 = new StackPane();
        f2.getChildren().add(facile);
        grille.add(f2, 0, 3);
        ToggleButton moyenne;
        moyenne = bouton.creer("moyenne"); //Media, Mittel/Normal
        moyenne.setBackground(Background.EMPTY);
        moyenne.setToggleGroup(ia2);
        est_m2 = 1;
        moyenne.setSelected(true);
        StackPane m2 = new StackPane();
        m2.getChildren().add(rectangleIm2);
        m2.getChildren().add(moyenne);
        grille.add(m2, 1, 3);
        ToggleButton difficile;
        difficile = bouton.creer("difficile"); //Difficile, Schwer
        difficile.setBackground(Background.EMPTY);
        difficile.setToggleGroup(ia2);
        StackPane d2 = new StackPane();
        d2.getChildren().add(difficile);
        grille.add(d2, 2, 3);
        versionIA2 = "moyenne";
        ia2.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                    Toggle old_toggle, Toggle new_toggle) {
                if (ia2.getSelectedToggle() != null) {
                    if (facile.isSelected()) {
                        if (est_m2 == 1) {
                            grille.getChildren().remove(f2);
                            grille.getChildren().remove(m2);
                            grille.getChildren().remove(d2);
                            m2.getChildren().remove(rectangleIm2);
                            grille.add(f2, 0, 3);
                            grille.add(m2, 1, 3);
                            grille.add(d2, 2, 3);
                            est_m2 = 0;
                        } else if (est_d2 == 1) {
                            grille.getChildren().remove(f2);
                            grille.getChildren().remove(m2);
                            grille.getChildren().remove(d2);
                            d2.getChildren().remove(rectangleIm2);
                            grille.add(f2, 0, 3);
                            grille.add(m2, 1, 3);
                            grille.add(d2, 2, 3);
                            est_d2 = 0;
                        }
                        if (est_f2 == 0 && est_m2 == 0 && est_d2 == 0) {
                            grille.getChildren().remove(f2);
                            f2.getChildren().remove(facile);
                            f2.getChildren().add(rectangleIm2);
                            f2.getChildren().add(facile);
                            grille.add(f2, 0, 3);
                            est_f2 = 1;
                        }
                    } else if (moyenne.isSelected()) {
                        if (est_f2 == 1) {
                            grille.getChildren().remove(f2);
                            grille.getChildren().remove(m2);
                            grille.getChildren().remove(d2);
                            f2.getChildren().remove(rectangleIm2);
                            grille.add(f2, 0, 3);
                            grille.add(m2, 1, 3);
                            grille.add(d2, 2, 3);
                            est_f2 = 0;
                        } else if (est_d2 == 1) {
                            grille.getChildren().remove(f2);
                            grille.getChildren().remove(m2);
                            grille.getChildren().remove(d2);
                            d2.getChildren().remove(rectangleIm2);
                            grille.add(f2, 0, 3);
                            grille.add(m2, 1, 3);
                            grille.add(d2, 2, 3);
                            est_d2 = 0;
                        }
                        if (est_f2 == 0 && est_m2 == 0 && est_d2 == 0) {
                            grille.getChildren().remove(m2);
                            m2.getChildren().remove(moyenne);
                            m2.getChildren().add(rectangleIm2);
                            m2.getChildren().add(moyenne);
                            grille.add(m2, 1, 3);
                            est_m2 = 1;
                        }
                    } else if (difficile.isSelected()) {
                        if (est_f2 == 1) {
                            grille.getChildren().remove(f2);
                            grille.getChildren().remove(m2);
                            grille.getChildren().remove(d2);
                            f2.getChildren().remove(rectangleIm2);
                            grille.add(f2, 0, 3);
                            grille.add(m2, 1, 3);
                            grille.add(d2, 2, 3);
                            est_f2 = 0;
                        } else if (est_m2 == 1) {
                            grille.getChildren().remove(f2);
                            grille.getChildren().remove(m2);
                            grille.getChildren().remove(d2);
                            m2.getChildren().remove(rectangleIm2);
                            grille.add(f2, 0, 3);
                            grille.add(m2, 1, 3);
                            grille.add(d2, 2, 3);
                            est_m2 = 0;
                        }
                        if (est_f2 == 0 && est_m2 == 0 && est_d2 == 0) {
                            grille.getChildren().remove(d2);
                            d2.getChildren().remove(difficile);
                            d2.getChildren().add(rectangleIm2);
                            d2.getChildren().add(difficile);
                            grille.add(d2, 2, 3);
                            est_d2 = 1;
                        }
                    }

                    versionIA2 = ia2.getSelectedToggle().getUserData().toString();
                    System.out.println("IA2 : " + versionIA2);
                }
            }
        });

        ToggleButton IAs;
        IAs = bouton.creer("IAs");
        IAs.setBackground(Background.EMPTY);
        IAs.setToggleGroup(j);
        StackPane ia_ia = new StackPane();
        ia_ia.getChildren().add(IAs);
        grille.add(ia_ia, 2, 1);

        StackPane f1 = new StackPane();
        StackPane m1 = new StackPane();
        StackPane d1 = new StackPane();
        StackPane n2 = new StackPane();

        j.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                    Toggle old_toggle, Toggle new_toggle) {
                if (j.getSelectedToggle() != null) {
                    if (humains.isSelected()) {
                        if (est_ia_ia == 1) {
                            est_f1 = 0;
                            est_f2 = 0;
                            est_m1 = 0;
                            est_m2 = 0;
                            est_d1 = 0;
                            est_d2 = 0;

                            grille.getChildren().remove(h_ia);
                            grille.getChildren().remove(ia_ia);
                            grille.getChildren().remove(hh);
                            grille.getChildren().remove(grille.getChildren().size() - 6, grille.getChildren().size());
                            ia_ia.getChildren().remove(hexagoneIm);
                            grille.add(hh, 0, 1);
                            grille.add(h_ia, 1, 1);
                            grille.add(ia_ia, 2, 1);
                            est_ia_ia = 0;
                            versionIA1 = null;
                            versionIA2 = null;
                        } else if (est_h_ai == 1) {
                            est_m2 = 0;
                            est_d1 = 0;
                            est_d2 = 0;

                            grille.getChildren().remove(h_ia);
                            grille.getChildren().remove(ia_ia);
                            grille.getChildren().remove(hh);
                            grille.getChildren().remove(grille.getChildren().size() - 4, grille.getChildren().size());
                            h_ia.getChildren().remove(hexagoneIm);
                            grille.add(hh, 0, 1);
                            grille.add(h_ia, 1, 1);
                            grille.add(ia_ia, 2, 1);
                            est_h_ai = 0;
                            versionIA2 = null;

                            Name1.setText(null);
                        }
                        if (est_h_h == 0 && est_h_ai == 0 && est_ia_ia == 0) {
                            grille.getChildren().remove(hh);
                            hh.getChildren().remove(humains);
                            hh.getChildren().add(hexagoneIm);
                            hh.getChildren().add(humains);
                            grille.add(hh, 0, 1);
                            est_h_h = 1;
                            Name1.setMinSize(tailleDeCase * 0.8, 30);
                            Name1.setMaxHeight(40);
                            Name1.setAlignment(Pos.CENTER);
                            StackPane n1 = new StackPane();
                            n1.getChildren().add(Name1);
                            grille.add(n1, 1, 2);
                            Name2.setMinSize(tailleDeCase * 0.8, 30);
                            Name2.setMaxHeight(40);
                            Name2.setAlignment(Pos.CENTER);
                            StackPane n2 = new StackPane();
                            n2.getChildren().add(Name2);
                            grille.add(n2, 1, 3);
                        }
                    } else if (hIA.isSelected()) {
                        if (est_ia_ia == 1) {
                            est_f1 = 0;
                            est_f2 = 0;
                            est_m1 = 0;
                            est_m2 = 0;
                            est_d1 = 0;
                            est_d2 = 0;

                            grille.getChildren().remove(h_ia);
                            grille.getChildren().remove(ia_ia);
                            grille.getChildren().remove(hh);
                            grille.getChildren().remove(grille.getChildren().size() - 6, grille.getChildren().size());
                            ia_ia.getChildren().remove(hexagoneIm);
                            grille.add(hh, 0, 1);
                            grille.add(h_ia, 1, 1);
                            grille.add(ia_ia, 2, 1);
                            est_ia_ia = 0;
                            versionIA1 = null;
                            versionIA2 = null;
                        } else if (est_h_h == 1) {
                            grille.getChildren().remove(h_ia);
                            grille.getChildren().remove(ia_ia);
                            grille.getChildren().remove(hh);
                            grille.getChildren().remove(grille.getChildren().size() - 2, grille.getChildren().size());
                            hh.getChildren().remove(hexagoneIm);
                            grille.add(hh, 0, 1);
                            grille.add(h_ia, 1, 1);
                            grille.add(ia_ia, 2, 1);
                            est_h_h = 0;
                            Name1.setText(null);
                            Name2.setText(null);
                        }
                        if (est_h_h == 0 && est_h_ai == 0 && est_ia_ia == 0) {
                            grille.getChildren().remove(h_ia);
                            h_ia.getChildren().remove(hIA);
                            h_ia.getChildren().add(hexagoneIm);
                            h_ia.getChildren().add(hIA);
                            grille.add(h_ia, 1, 1);
                            est_h_ai = 1;
                            Name1.setMinSize(tailleDeCase * 0.8, 30);
                            Name1.setMaxHeight(40);
                            Name1.setAlignment(Pos.CENTER);
                            StackPane n1 = new StackPane();
                            n1.getChildren().add(Name1);
                            grille.add(n1, 1, 2);
                            ToggleButton facile2;
                            facile2 = bouton.creer("facile"); //Facile, Einfach
                            facile2.setBackground(Background.EMPTY);
                            facile2.setToggleGroup(ia2);
                            StackPane f2 = new StackPane();
                            f2.getChildren().add(facile2);
                            grille.add(f2, 0, 3);
                            ToggleButton moyenne2;
                            moyenne2 = bouton.creer("moyenne"); //Media, Mittel/Normal
                            moyenne2.setBackground(Background.EMPTY);
                            moyenne2.setToggleGroup(ia2);
                            StackPane m2 = new StackPane();
                            m2.getChildren().add(moyenne2);
                            grille.add(m2, 1, 3);
                            ToggleButton difficile2;
                            difficile2 = bouton.creer("difficile"); //Difficile, Schwer
                            difficile2.setBackground(Background.EMPTY);
                            difficile2.setToggleGroup(ia2);
                            StackPane d2 = new StackPane();
                            d2.getChildren().add(difficile2);
                            grille.add(d2, 2, 3);
                            ia2.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                                public void changed(ObservableValue<? extends Toggle> ov,
                                        Toggle old_toggle, Toggle new_toggle) {
                                    if (ia2.getSelectedToggle() != null) {
                                        if (facile2.isSelected()) {
                                            if (est_m2 == 1) {
                                                grille.getChildren().remove(f2);
                                                grille.getChildren().remove(m2);
                                                grille.getChildren().remove(d2);
                                                m2.getChildren().remove(rectangleIm2);
                                                grille.add(f2, 0, 3);
                                                grille.add(m2, 1, 3);
                                                grille.add(d2, 2, 3);
                                                est_m2 = 0;
                                            } else if (est_d2 == 1) {
                                                grille.getChildren().remove(f2);
                                                grille.getChildren().remove(m2);
                                                grille.getChildren().remove(d2);
                                                d2.getChildren().remove(rectangleIm2);
                                                grille.add(f2, 0, 3);
                                                grille.add(m2, 1, 3);
                                                grille.add(d2, 2, 3);
                                                est_d2 = 0;
                                            }
                                            if (est_f2 == 0 && est_m2 == 0 && est_d2 == 0) {
                                                grille.getChildren().remove(f2);
                                                f2.getChildren().remove(facile2);
                                                f2.getChildren().add(rectangleIm2);
                                                f2.getChildren().add(facile2);
                                                grille.add(f2, 0, 3);
                                                est_f2 = 1;
                                            }
                                        } else if (moyenne2.isSelected()) {
                                            if (est_f2 == 1) {
                                                grille.getChildren().remove(f2);
                                                grille.getChildren().remove(m2);
                                                grille.getChildren().remove(d2);
                                                f2.getChildren().remove(rectangleIm2);
                                                grille.add(f2, 0, 3);
                                                grille.add(m2, 1, 3);
                                                grille.add(d2, 2, 3);
                                                est_f2 = 0;
                                            } else if (est_d2 == 1) {
                                                grille.getChildren().remove(f2);
                                                grille.getChildren().remove(m2);
                                                grille.getChildren().remove(d2);
                                                d2.getChildren().remove(rectangleIm2);
                                                grille.add(f2, 0, 3);
                                                grille.add(m2, 1, 3);
                                                grille.add(d2, 2, 3);
                                                est_d2 = 0;
                                            }
                                            if (est_f2 == 0 && est_m2 == 0 && est_d2 == 0) {
                                                grille.getChildren().remove(m2);
                                                m2.getChildren().remove(moyenne2);
                                                m2.getChildren().add(rectangleIm2);
                                                m2.getChildren().add(moyenne2);
                                                grille.add(m2, 1, 3);
                                                est_m2 = 1;
                                            }
                                        } else if (difficile2.isSelected()) {
                                            if (est_f2 == 1) {
                                                grille.getChildren().remove(f2);
                                                grille.getChildren().remove(m2);
                                                grille.getChildren().remove(d2);
                                                f2.getChildren().remove(rectangleIm2);
                                                grille.add(f2, 0, 3);
                                                grille.add(m2, 1, 3);
                                                grille.add(d2, 2, 3);
                                                est_f2 = 0;
                                            } else if (est_m2 == 1) {
                                                grille.getChildren().remove(f2);
                                                grille.getChildren().remove(m2);
                                                grille.getChildren().remove(d2);
                                                m2.getChildren().remove(rectangleIm2);
                                                grille.add(f2, 0, 3);
                                                grille.add(m2, 1, 3);
                                                grille.add(d2, 2, 3);
                                                est_m2 = 0;
                                            }
                                            if (est_f2 == 0 && est_m2 == 0 && est_d2 == 0) {
                                                grille.getChildren().remove(d2);
                                                d2.getChildren().remove(difficile2);
                                                d2.getChildren().add(rectangleIm2);
                                                d2.getChildren().add(difficile2);
                                                grille.add(d2, 2, 3);
                                                est_d2 = 1;
                                            }
                                        }

                                        versionIA2 = ia2.getSelectedToggle().getUserData().toString();
                                        System.out.println("IA2 : " + versionIA2);
                                    }
                                }
                            });

                        }
                    } else if (IAs.isSelected()) {
                        if (est_h_ai == 1) {
                            est_f2 = 0;
                            est_m2 = 0;
                            est_d2 = 0;

                            grille.getChildren().remove(h_ia);
                            grille.getChildren().remove(ia_ia);
                            grille.getChildren().remove(hh);
                            grille.getChildren().remove(grille.getChildren().size() - 4, grille.getChildren().size());
                            h_ia.getChildren().remove(hexagoneIm);
                            grille.add(hh, 0, 1);
                            grille.add(h_ia, 1, 1);
                            grille.add(ia_ia, 2, 1);
                            est_h_ai = 0;
                            versionIA1 = null;
                            Name1.setText(null);
                        } else if (est_h_h == 1) {
                            grille.getChildren().remove(h_ia);
                            grille.getChildren().remove(ia_ia);
                            grille.getChildren().remove(hh);
                            grille.getChildren().remove(grille.getChildren().size() - 2, grille.getChildren().size());
                            hh.getChildren().remove(hexagoneIm);
                            grille.add(hh, 0, 1);
                            grille.add(h_ia, 1, 1);
                            grille.add(ia_ia, 2, 1);
                            est_h_h = 0;
                            Name1.setText(null);
                            Name2.setText(null);
                        }
                        if (est_h_h == 0 && est_h_ai == 0 && est_ia_ia == 0) {
                            grille.getChildren().remove(ia_ia);
                            ia_ia.getChildren().remove(IAs);
                            ia_ia.getChildren().add(hexagoneIm);
                            ia_ia.getChildren().add(IAs);
                            grille.add(ia_ia, 2, 1);
                            est_ia_ia = 1;
                            ToggleButton facile1;
                            facile1 = bouton.creer("facile"); //Facile, Einfach
                            facile1.setBackground(Background.EMPTY);
                            facile1.setToggleGroup(ia1);
                            StackPane f1 = new StackPane();
                            f1.getChildren().add(facile1);
                            grille.add(f1, 0, 2);
                            ToggleButton moyenne1;
                            moyenne1 = bouton.creer("moyenne"); //Media, Mittel/Normal
                            moyenne1.setBackground(Background.EMPTY);
                            moyenne1.setToggleGroup(ia1);
                            StackPane m1 = new StackPane();
                            m1.getChildren().add(moyenne1);
                            grille.add(m1, 1, 2);
                            ToggleButton difficile1;
                            difficile1 = bouton.creer("difficile"); //Difficile, Schwer
                            difficile1.setBackground(Background.EMPTY);
                            difficile1.setToggleGroup(ia1);
                            StackPane d1 = new StackPane();
                            d1.getChildren().add(difficile1);
                            grille.add(d1, 2, 2);
                            ia1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                                public void changed(ObservableValue<? extends Toggle> ov,
                                        Toggle old_toggle, Toggle new_toggle) {
                                    if (ia1.getSelectedToggle() != null) {
                                        if (facile1.isSelected()) {
                                            if (est_m1 == 1) {
                                                grille.getChildren().remove(f1);
                                                grille.getChildren().remove(m1);
                                                grille.getChildren().remove(d1);
                                                m1.getChildren().remove(rectangleIm1);
                                                grille.add(f1, 0, 2);
                                                grille.add(m1, 1, 2);
                                                grille.add(d1, 2, 2);
                                                est_m1 = 0;
                                            } else if (est_d1 == 1) {
                                                grille.getChildren().remove(f1);
                                                grille.getChildren().remove(m1);
                                                grille.getChildren().remove(d1);
                                                d1.getChildren().remove(rectangleIm1);
                                                grille.add(f1, 0, 2);
                                                grille.add(m1, 1, 2);
                                                grille.add(d1, 2, 2);
                                                est_d1 = 0;
                                            }
                                            if (est_f1 == 0 && est_m1 == 0 && est_d1 == 0) {
                                                grille.getChildren().remove(f1);
                                                f1.getChildren().remove(facile1);
                                                f1.getChildren().add(rectangleIm1);
                                                f1.getChildren().add(facile1);
                                                grille.add(f1, 0, 2);
                                                est_f1 = 1;
                                            }
                                        } else if (moyenne1.isSelected()) {
                                            if (est_f1 == 1) {
                                                grille.getChildren().remove(f1);
                                                grille.getChildren().remove(m1);
                                                grille.getChildren().remove(d1);
                                                f1.getChildren().remove(rectangleIm1);
                                                grille.add(f1, 0, 2);
                                                grille.add(m1, 1, 2);
                                                grille.add(d1, 2, 2);
                                                est_f1 = 0;
                                            } else if (est_d1 == 1) {
                                                grille.getChildren().remove(f1);
                                                grille.getChildren().remove(m1);
                                                grille.getChildren().remove(d1);
                                                d1.getChildren().remove(rectangleIm1);
                                                grille.add(f1, 0, 2);
                                                grille.add(m1, 1, 2);
                                                grille.add(d1, 2, 2);
                                                est_d1 = 0;
                                            }
                                            if (est_f1 == 0 && est_m1 == 0 && est_d1 == 0) {
                                                grille.getChildren().remove(m1);
                                                m1.getChildren().remove(moyenne1);
                                                m1.getChildren().add(rectangleIm1);
                                                m1.getChildren().add(moyenne1);
                                                grille.add(m1, 1, 2);
                                                est_m1 = 1;
                                            }
                                        } else if (difficile1.isSelected()) {
                                            if (est_f1 == 1) {
                                                grille.getChildren().remove(f1);
                                                grille.getChildren().remove(m1);
                                                grille.getChildren().remove(d1);
                                                f1.getChildren().remove(rectangleIm1);
                                                grille.add(f1, 0, 2);
                                                grille.add(m1, 1, 2);
                                                grille.add(d1, 2, 2);
                                                est_f1 = 0;
                                            } else if (est_m1 == 1) {
                                                grille.getChildren().remove(f1);
                                                grille.getChildren().remove(m1);
                                                grille.getChildren().remove(d1);
                                                m1.getChildren().remove(rectangleIm1);
                                                grille.add(f1, 0, 2);
                                                grille.add(m1, 1, 2);
                                                grille.add(d1, 2, 2);
                                                est_m1 = 0;
                                            }
                                            if (est_f1 == 0 && est_m1 == 0 && est_d1 == 0) {
                                                grille.getChildren().remove(d1);
                                                d1.getChildren().remove(difficile1);
                                                d1.getChildren().add(rectangleIm1);
                                                d1.getChildren().add(difficile1);
                                                grille.add(d1, 2, 2);
                                                est_d1 = 1;
                                            }
                                        }

                                        versionIA1 = ia1.getSelectedToggle().getUserData().toString();
                                        System.out.println("IA1 : " + versionIA1);
                                    }

                                }
                            });

                            ToggleButton facile2;
                            facile2 = bouton.creer("facile"); //Facile, Einfach
                            facile2.setBackground(Background.EMPTY);
                            facile2.setToggleGroup(ia2);
                            StackPane f2 = new StackPane();
                            f2.getChildren().add(facile2);
                            grille.add(f2, 0, 3);
                            ToggleButton moyenne2;
                            moyenne2 = bouton.creer("moyenne"); //Media, Mittel/Normal
                            moyenne2.setBackground(Background.EMPTY);
                            moyenne2.setToggleGroup(ia2);
                            StackPane m2 = new StackPane();
                            m2.getChildren().add(moyenne2);
                            grille.add(m2, 1, 3);
                            ToggleButton difficile2;
                            difficile2 = bouton.creer("difficile"); //Difficile, Schwer
                            difficile2.setBackground(Background.EMPTY);
                            difficile2.setToggleGroup(ia2);
                            StackPane d2 = new StackPane();
                            d2.getChildren().add(difficile2);
                            grille.add(d2, 2, 3);
                            ia2.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                                public void changed(ObservableValue<? extends Toggle> ov,
                                        Toggle old_toggle, Toggle new_toggle) {
                                    if (ia2.getSelectedToggle() != null) {
                                        if (facile2.isSelected()) {
                                            if (est_m2 == 1) {
                                                grille.getChildren().remove(f2);
                                                grille.getChildren().remove(m2);
                                                grille.getChildren().remove(d2);
                                                m2.getChildren().remove(rectangleIm2);
                                                grille.add(f2, 0, 3);
                                                grille.add(m2, 1, 3);
                                                grille.add(d2, 2, 3);
                                                est_m2 = 0;
                                            } else if (est_d2 == 1) {
                                                grille.getChildren().remove(f2);
                                                grille.getChildren().remove(m2);
                                                grille.getChildren().remove(d2);
                                                d2.getChildren().remove(rectangleIm2);
                                                grille.add(f2, 0, 3);
                                                grille.add(m2, 1, 3);
                                                grille.add(d2, 2, 3);
                                                est_d2 = 0;
                                            }
                                            if (est_f2 == 0 && est_m2 == 0 && est_d2 == 0) {
                                                grille.getChildren().remove(f2);
                                                f2.getChildren().remove(facile2);
                                                f2.getChildren().add(rectangleIm2);
                                                f2.getChildren().add(facile2);
                                                grille.add(f2, 0, 3);
                                                est_f2 = 1;
                                            }
                                        } else if (moyenne2.isSelected()) {
                                            if (est_f2 == 1) {
                                                grille.getChildren().remove(f2);
                                                grille.getChildren().remove(m2);
                                                grille.getChildren().remove(d2);
                                                f2.getChildren().remove(rectangleIm2);
                                                grille.add(f2, 0, 3);
                                                grille.add(m2, 1, 3);
                                                grille.add(d2, 2, 3);
                                                est_f2 = 0;
                                            } else if (est_d2 == 1) {
                                                grille.getChildren().remove(f2);
                                                grille.getChildren().remove(m2);
                                                grille.getChildren().remove(d2);
                                                d2.getChildren().remove(rectangleIm2);
                                                grille.add(f2, 0, 3);
                                                grille.add(m2, 1, 3);
                                                grille.add(d2, 2, 3);
                                                est_d2 = 0;
                                            }
                                            if (est_f2 == 0 && est_m2 == 0 && est_d2 == 0) {
                                                grille.getChildren().remove(m2);
                                                m2.getChildren().remove(moyenne2);
                                                m2.getChildren().add(rectangleIm2);
                                                m2.getChildren().add(moyenne2);
                                                grille.add(m2, 1, 3);
                                                est_m2 = 1;
                                            }
                                        } else if (difficile2.isSelected()) {
                                            if (est_f2 == 1) {
                                                grille.getChildren().remove(f2);
                                                grille.getChildren().remove(m2);
                                                grille.getChildren().remove(d2);
                                                f2.getChildren().remove(rectangleIm2);
                                                grille.add(f2, 0, 3);
                                                grille.add(m2, 1, 3);
                                                grille.add(d2, 2, 3);
                                                est_f2 = 0;
                                            } else if (est_m2 == 1) {
                                                grille.getChildren().remove(f2);
                                                grille.getChildren().remove(m2);
                                                grille.getChildren().remove(d2);
                                                m2.getChildren().remove(rectangleIm2);
                                                grille.add(f2, 0, 3);
                                                grille.add(m2, 1, 3);
                                                grille.add(d2, 2, 3);
                                                est_m2 = 0;
                                            }
                                            if (est_f2 == 0 && est_m2 == 0 && est_d2 == 0) {
                                                grille.getChildren().remove(d2);
                                                d2.getChildren().remove(difficile2);
                                                d2.getChildren().add(rectangleIm2);
                                                d2.getChildren().add(difficile2);
                                                grille.add(d2, 2, 3);
                                                est_d2 = 1;
                                            }
                                        }

                                        versionIA2 = ia2.getSelectedToggle().getUserData().toString();
                                        System.out.println("IA2 : " + versionIA2);
                                    }

                                }

                            });
                        }
                    }
                }
            }
        });

        AnchorPane.setLeftAnchor(grille, (double) 0);
        AnchorPane.setRightAnchor(grille, (double) 0);
        AnchorPane.setTopAnchor(grille, (double) 30);
        AnchorPane.setBottomAnchor(grille, (double) tailleDeCase * 2);
        pane.getChildren().add(grille);

        StackPane valider_sp = new StackPane();
        valider.setFont(new Font(police, tailleDeCase * 0.23));
        valider_sp.getChildren().add(valider);
        valider_sp.setMaxSize(tailleDeCase, 40);
        valider_sp.setMinSize(tailleDeCase, 40);
        valider_sp.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

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

                    controller.goToPlateau(joueur_1, null, null, level2);

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

                    controller.goToPlateau(null, null, level1, level2);
                }   //controller.goToPlateau(Name1.getCharacters().toString(), Name2.getCharacters().toString());

                System.out.println(joueur_1 + " " + joueur_2);
                //controller.goToPlateau(joueur_1, joueur_2, level1, level2);
            }
        });
        if (height == max_screen_height) {
            AnchorPane.setBottomAnchor(valider_sp, (double) tailleDeCase * 1.5);
        } else {
            AnchorPane.setBottomAnchor(valider_sp, (double) tailleDeCase);
        }
        //AnchorPane.setTopAnchor(valider, (double) height - 50);
        AnchorPane.setLeftAnchor(valider_sp, (double) width / 2 - tailleDeCase);
        AnchorPane.setRightAnchor(valider_sp, (double) width / 2 - tailleDeCase);
        this.boutonPleinEcran.toFront();
        this.boutonPreference.toFront();
        this.boutonRetourMenu.toFront();
        pane.getChildren().add(valider_sp);
        this.panePrincipale.getChildren().add(pane);

    }

    public Level creerIA(String joueur) {
        if (joueur == null) {
            return null;
        }
        switch (joueur) {
            case "facile":
                return Level.EASY;
            case "moyenne":
                return Level.MEDIUM;
            case "difficile":
                return Level.HARD;
            default:
                return Level.EASY;
        }
    }

    public void majRetourPreference() {
    }

}
