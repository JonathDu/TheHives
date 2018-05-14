/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import com.sun.javafx.sg.prism.NGNode;
import hive.model.insects.InsectType;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 *
 * @author jonathan
 */
public class InterfacePion extends Parent {

    public static int LONGUEUR = 40;
    public static int LARGEUR = (int) (LONGUEUR / 1.4);

    public InterfacePion(Color couleur, InsectType typePions, CacheImage c) {

        Group g = new Group();
        ImageView img = null;

        Polygon hexagon = new Polygon();

        //Adding coordinates to the polygon 
        hexagon.getPoints().addAll(new Double[]{
            10.0, 60.0,
            LARGEUR + 10.0, LARGEUR + 60.0,
            LARGEUR + LONGUEUR + 10.0, LARGEUR + 60.0,
            2 * LARGEUR + LONGUEUR + 10.0, 60.0,
            LARGEUR + LONGUEUR + 10.0, 60.0 - LARGEUR,
            LARGEUR + 10.0, 60.0 - LARGEUR});
        hexagon.setFill(couleur);
        hexagon.setStroke(Color.BLACK);
        g.getChildren().add(hexagon);

        if (typePions != null) {
            switch (typePions) {
                case QUEEN_BEE:
                    img = c.getImage("hive/vue/rsc/images/bee.png");
                    break;
                case GRASSHOPPER:
                    img = c.getImage("hive/vue/rsc/images/grasshopper.png");
                    break;
                case BEETLE:
                    img = c.getImage("hive/vue/rsc/images/beetle.png");
                    break;
                default:
                    break;

            }
            img.setFitHeight(LONGUEUR / 2);
            img.setLayoutX(LONGUEUR);
            img.setLayoutY(LONGUEUR);

            img.setPreserveRatio(true);

            g.getChildren().add(img);
        }

        this.getChildren().add(g);
    }

    public InterfacePion(Color couleur, InsectType typePions, CacheImage c, int longueur) {
        int largeur = (int) (longueur/1.4);
        Group g = new Group();
        ImageView img = null;

        Polygon hexagon = new Polygon();

        //Adding coordinates to the polygon 
        hexagon.getPoints().addAll(new Double[]{
            10.0, 60.0,
            largeur + 10.0, largeur + 60.0,
            largeur + longueur + 10.0, largeur + 60.0,
            2 * largeur + longueur + 10.0, 60.0,
            largeur + longueur + 10.0, 60.0 - largeur,
            largeur + 10.0, 60.0 - largeur});
        hexagon.setFill(couleur);
        hexagon.setStroke(Color.BLACK);
        g.getChildren().add(hexagon);

        if (typePions != null) {
            switch (typePions) {
                case QUEEN_BEE:
                    img = c.getImage("hive/vue/rsc/images/bee.png");
                    break;
                case GRASSHOPPER:
                    img = c.getImage("hive/vue/rsc/images/grasshopper.png");
                    break;
                case BEETLE:
                    img = c.getImage("hive/vue/rsc/images/beetle.png");
                    break;
                default:
                    break;

            }
            img.setFitHeight(longueur / 2);
            img.setLayoutX(longueur);
            img.setLayoutY(longueur);

            img.setPreserveRatio(true);

            g.getChildren().add(img);
        }

        this.getChildren().add(g);
    }
}
