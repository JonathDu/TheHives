/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import hive.model.players.decisions.Level;
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
    int est_ia_ia = 0, est_h_ai = 0, est_h_h = 0;
    TextField Name1 = new TextField();
    TextField Name2 = new TextField();

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
        largeurBouton = largeurDeColonne;
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
        Name1.setText(null);
        Name2.setText(null);
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
        facile = bouton.creer("facile1"); //Facile, Einfach
        facile.setBackground(Background.EMPTY);
        facile.setToggleGroup(ia1);
        StackPane f1 = new StackPane();
        f1.getChildren().add(facile);
        grille.add(f1, 0, 3);
        ToggleButton moyenne;
        moyenne = bouton.creer("moyenne1"); //Media, Mittel/Normal
        moyenne.setBackground(Background.EMPTY);
        moyenne.setToggleGroup(ia1);
        moyenne.setSelected(true);
        StackPane m1 = new StackPane();
        m1.getChildren().add(moyenne);
        grille.add(m1, 1, 3);
        ToggleButton difficile;
        difficile = bouton.creer("difficile1"); //Difficile, Schwer
        //difficile.setBackground(Background.EMPTY);
        difficile.setToggleGroup(ia1);
        StackPane d1 = new StackPane();
        d1.getChildren().add(difficile);
        grille.add(d1, 2, 3);
        versionIA1 = "moyenne";
        ia1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                    Toggle old_toggle, Toggle new_toggle) {
                if (ia1.getSelectedToggle() != null) {
                    versionIA1 = ia1.getSelectedToggle().getUserData().toString();
                    System.out.println("IA1 : " + versionIA1);
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

        j.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                    Toggle old_toggle, Toggle new_toggle) {
                if (j.getSelectedToggle() != null) {
                    if (humains.isSelected()) {
                        if (est_ia_ia == 1) {
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
                            ToggleButton facile;
                            facile = bouton.creer("facile1"); //Facile, Einfach
                            facile.setBackground(Background.EMPTY);
                            facile.setToggleGroup(ia1);
                            StackPane f1 = new StackPane();
                            f1.getChildren().add(facile);
                            grille.add(f1, 0, 3);
                            ToggleButton moyenne;
                            moyenne = bouton.creer("moyenne1"); //Media, Mittel/Normal
                            moyenne.setBackground(Background.EMPTY);
                            moyenne.setToggleGroup(ia1);
                            StackPane m1 = new StackPane();
                            m1.getChildren().add(moyenne);
                            grille.add(m1, 1, 3);
                            ToggleButton difficile;
                            difficile = bouton.creer("difficile1"); //Difficile, Schwer
                            difficile.setBackground(Background.EMPTY);
                            difficile.setToggleGroup(ia1);
                            StackPane d1 = new StackPane();
                            d1.getChildren().add(difficile);
                            grille.add(d1, 2, 3);
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
                    } else if (IAs.isSelected()) {
                        if (est_h_ai == 1) {
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
                            facile1 = bouton.creer("facile1"); //Facile, Einfach
                            facile1.setBackground(Background.EMPTY);
                            facile1.setToggleGroup(ia1);
                            StackPane f1 = new StackPane();
                            f1.getChildren().add(facile1);
                            grille.add(f1, 0, 2);
                            ToggleButton moyenne1;
                            moyenne1 = bouton.creer("moyenne1"); //Media, Mittel/Normal
                            moyenne1.setBackground(Background.EMPTY);
                            moyenne1.setToggleGroup(ia1);
                            StackPane m1 = new StackPane();
                            m1.getChildren().add(moyenne1);
                            grille.add(m1, 1, 2);
                            ToggleButton difficile1;
                            difficile1 = bouton.creer("difficile1"); //Difficile, Schwer
                            difficile1.setBackground(Background.EMPTY);
                            difficile1.setToggleGroup(ia1);
                            StackPane d1 = new StackPane();
                            d1.getChildren().add(difficile1);
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
                            ToggleButton facile2;
                            facile2 = bouton.creer("facile2"); //Facile, Einfach
                            facile2.setBackground(Background.EMPTY);
                            facile2.setToggleGroup(ia2);
                            StackPane f2 = new StackPane();
                            f2.getChildren().add(facile2);
                            grille.add(f2, 0, 3);
                            ToggleButton moyenne2;
                            moyenne2 = bouton.creer("moyenne2"); //Media, Mittel/Normal
                            moyenne2.setBackground(Background.EMPTY);
                            moyenne2.setToggleGroup(ia2);
                            StackPane m2 = new StackPane();
                            m2.getChildren().add(moyenne2);
                            grille.add(m2, 1, 3);
                            ToggleButton difficile2;
                            difficile2 = bouton.creer("difficile2"); //Difficile, Schwer
                            difficile2.setBackground(Background.EMPTY);
                            difficile2.setToggleGroup(ia2);
                            StackPane d2 = new StackPane();
                            d2.getChildren().add(difficile2);
                            grille.add(d2, 2, 3);
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
                System.out.println("Enregistrer ! ");
                System.out.println("Name1 : " + Name1.getCharacters());
                System.out.println("IA1 : " + versionIA1);
                System.out.println("Name2 : " + Name2.getCharacters());
                System.out.println("IA2 : " + versionIA2);
                String joueur_1 = new String();
                String joueur_2 = new String();
                if (est_h_h == 1) {
                    joueur_1 = Name1.getCharacters().toString();
                } else {
                    joueur_1 = versionIA1;
                }
                if (Name2.getText() != null) {
                    joueur_2 = Name2.getCharacters().toString();
                } else if (est_h_ai == 1) {
                    joueur_1 = Name1.getCharacters().toString();
                    joueur_2 = versionIA1;
                } else if (est_ia_ia == 1) {
                    joueur_1 = versionIA1;
                    joueur_2 = versionIA2;
                }   //controller.goToPlateau(Name1.getCharacters().toString(), Name2.getCharacters().toString());

                Level level1 = Level.EASY; //TODO : faire une fonction qui donne le level de l'IA1 et l'IA2
                Level level2 = Level.EASY;
                controller.goToPlateau(joueur_1, joueur_2, level1, level2);

                System.out.println(joueur_1);
                System.out.println(joueur_2);
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
        pane.getChildren().add(valider_sp);
        this.panePrincipale.getChildren().add(pane);
        this.getChildren().add(panePrincipale);

    }

    public void majRetourPreference() {
    }

}
