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
    public Polygon hexagon;

    public InterfacePion(Color couleur, InsectType typePions, CacheImage c) {

        Group g = new Group();
        hexagon = createHexagon(LONGUEUR, couleur);
        g.getChildren().add(hexagon);
        ImageView img = createImage(typePions, LONGUEUR, c);
        if (img != null) {
            g.getChildren().add(img);
        }

        this.getChildren().add(g);
    }

    public InterfacePion(Color couleur, InsectType typePions, CacheImage c, int longueur) {
        Group g = new Group();
        hexagon = createHexagon(longueur, couleur);
        g.getChildren().add(hexagon);

        ImageView img = createImage(typePions, longueur, c);
        if (img != null) {
            g.getChildren().add(img);
        }
        this.getChildren().add(g);
    }

    private Polygon createHexagon(int longueur, Color couleur) {
        int largeur = (int) (longueur / 1.4);

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
        return hexagon;
    }

    private ImageView createImage(InsectType type, int longueur, CacheImage c) {
        ImageView v = null;
        if (type != null) {
            switch (type) {
                case QUEEN_BEE:
                    v = c.getImage("hive/vue/rsc/images/bee.png");
                    break;
                case GRASSHOPPER:
                    v = c.getImage("hive/vue/rsc/images/grasshopper.png");
                    break;
                case BEETLE:
                    v = c.getImage("hive/vue/rsc/images/beetle.png");
                    break;
                default:
                    break;

            }
            v.setFitHeight(longueur / 2);
            v.setLayoutX(longueur);
            v.setLayoutY(longueur);

            v.setPreserveRatio(true);

        }
        return v;
    }
}
