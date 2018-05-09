/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import vue.CacheImage;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.animation.AnimationTimer;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import thehives.TheHives;

/**
 *
 * @author jonathan
 */
public class InterfacePlateau extends Parent {

    GridPane grille;

    Stage stage;
    String nomJoueur1;
    String nomJoueur2;

    public InterfacePlateau(TheHives i, Stage stage, String nomJoueur1, String nomJoueur2) {
        this.nomJoueur1 = nomJoueur1;
        this.nomJoueur2 = nomJoueur2;

        grille = new GridPane();
        //grille.setHgap(10);
        //grille.setVgap(10);

        grille.add(new InterfacePlateauTool(new CacheImage(), stage), 0, 0, 5, 1);

        Outils.fixerRepartition(grille, Outils.VERTICAL, 10, 0.5, 79, 0.5, 10);
        Outils.fixerRepartition(grille, Outils.HORIZONTAL, 5, 0.5, 94.5);
        grille.add(new InterfacePlateauMain(grille.getColumnConstraints().get(0).prefWidthProperty(), nomJoueur1, Color.WHITE), 0, 2);
        grille.add(new InterfacePlateauMain(grille.getColumnConstraints().get(0).prefWidthProperty(), nomJoueur2, Color.GRAY), 4, 2);

        //grille.add(separation(), 0, 2);
        grille.prefHeightProperty().bind(stage.heightProperty());
        grille.prefWidthProperty().bind(stage.widthProperty());
        grille.add(Outils.separation(), 0, 1, 5, 1);
        grille.add(Outils.separation(), 1, 1, 1, 2);
        grille.add(Outils.separation(), 3, 1, 1, 2);
        this.getChildren().add(grille);

    }

}
