/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author jonathan
 */
public class Outils {
    final static int HORIZONTAL = 0;
    final static int VERTICAL = 1;
    
    static public Node separation() {
        Pane resultat;
        Rectangle r;

        r = new Rectangle();
        r.setFill(Color.BLUE);
        resultat = new Pane();
        resultat.getChildren().add(r);
        r.widthProperty().bind(resultat.widthProperty());
        r.heightProperty().bind(resultat.heightProperty());
        return resultat;
    }
    static public void fixerRepartition(GridPane grid, int type, double... pourcentage) {
        for (int i = 0; i < pourcentage.length; i++) {
            switch (type) {
                case HORIZONTAL:
                    RowConstraints r = new RowConstraints();
                    r.setPercentHeight(pourcentage[i]);
                    r.setFillHeight(true);
                    grid.getRowConstraints().add(r);
                    break;
                case VERTICAL:
                    ColumnConstraints c = new ColumnConstraints();
                    c.setPercentWidth(pourcentage[i]);
                    c.setFillWidth(true);
                    grid.getColumnConstraints().add(c);
                    break;
                default:
                    throw new RuntimeException("Unknowm constraints type : " + type);
            }
        }
    }
}
