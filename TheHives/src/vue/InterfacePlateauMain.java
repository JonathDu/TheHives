/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import vue.InterfacePion;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author jonathan
 */
public class InterfacePlateauMain extends Parent {

    GridPane grille;

    public InterfacePlateauMain(DoubleProperty p, String nomJoueur, Color couleur) {
        grille = new GridPane();
        //grille.setPrefHeight(600 - 0.05*600);
        grille.prefWidthProperty().bind(p);
        //grille.setMinHeight(Region.USE_COMPUTED_SIZE);

        for (int i = 0; i < 10; i++) {
            RowConstraints r = new RowConstraints();
            r.setPercentHeight(10);
            r.setFillHeight(true);

            grille.getRowConstraints().add(r);
        }
        grille.add(new Label(nomJoueur), 0, 5);

        grille.add(new InterfacePion(couleur), 0, 0);
        grille.add(new InterfacePion(couleur), 0, 1);
        grille.add(new InterfacePion(couleur), 0, 2);
        grille.add(new InterfacePion(couleur), 0, 3);
        grille.add(new InterfacePion(couleur), 0, 4);

        this.getChildren().add(grille);

    }

}
