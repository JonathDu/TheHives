/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import com.sun.javafx.sg.prism.NGNode;
import hive.model.insects.InsectType;
import hive.model.players.TeamColor;
import static java.lang.Math.sqrt;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;

/**
 *
 * @author jonathan
 */
public class InterfacePion extends Parent {

    public static int LONGUEUR = 40;
    public static int LARGEUR = (int) (LONGUEUR / 1.4);
    public Polygon hexagon;

    public InterfacePion(TeamColor couleur, InsectType typePions, CacheImage c) {

        Group g = new Group();
        hexagon = createHexagon(LONGUEUR);
        ImagePattern img = createImage(typePions, LONGUEUR, c, couleur);
        if (img != null) {
            hexagon.setFill(img);

        } else {
            hexagon.setFill(Color.TRANSPARENT);
        }
        g.getChildren().add(hexagon);

        this.getChildren().add(g);
    }

    public InterfacePion(TeamColor couleur, InsectType typePions, CacheImage c, int longueur) {
        Group g = new Group();
        hexagon = createHexagon(longueur);

        ImagePattern img = createImage(typePions, longueur, c, couleur);
        if (img != null) {
            hexagon.setFill(img);
        } else {
            hexagon.setFill(Color.TRANSPARENT);
        }
        g.getChildren().add(hexagon);

        this.getChildren().add(g);
    }

    private Polygon createHexagon(double side) {
        double center = ((sqrt(3) / 2) * side);
        double hauteur = sqrt(-Math.pow(center, 2) + Math.pow(side, 2));
        Polygon hexagon = new Polygon();

        //Adding coordinates to the polygon 
        hexagon.getPoints().addAll(new Double[]{
            0.0, center,
            hauteur, 2 * center,
            hauteur + side, 2 * center,
            side * 2, center,
            side + hauteur, 0.0,
            hauteur, 0.0,});
        return hexagon;
    }

    private ImagePattern createImage(InsectType type, int longueur, CacheImage c, TeamColor couleur) {
        ImagePattern v = null;
        Image i = null;
        if (type != null) {
            switch (type) {
                case QUEEN_BEE:
                    if (couleur == TeamColor.BLACK) {
                        i = c.getImage("pionQueenB.png");
                    } else {
                        i = c.getImage("pionQueenW.png");
                    }
                    v = new ImagePattern(i, 0, 0, 1, 1, true);
                    break;
                case GRASSHOPPER:
                    if (couleur == TeamColor.BLACK) {
                        i = c.getImage("pionSauterelleB.png");
                    } else {
                        i = c.getImage("pionSauterelleW.png");
                    }
                    v = new ImagePattern(i, 0, 0, 1, 1, true);
                    break;
                case BEETLE:
                    if (couleur == TeamColor.BLACK) {
                        i = c.getImage("pionScarabeB.png");
                    } else {
                        i = c.getImage("pionScarabeW.png");
                    }
                    v = new ImagePattern(i, 1, 1, 1, 1, true);
                    v.isProportional();
                    break;
                default:
                    break;

            }

        }
        return v;
    }
}
