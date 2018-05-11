/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import hive.model.insects.InsectType;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 *
 * @author jonathan
 */
public class InterfacePions extends Parent {

    private int type;

    public InterfacePions(Color couleur, int nbPions, InsectType typePions, CacheImage c) {
        Group g = new Group();
        ImageView img = null;

        switch (typePions) {
            case QUEEN_BEE:
                img = c.getImage("vue/rsc/images/bee.png");
                break;
            case GRASSHOPPER:
                img = c.getImage("vue/rsc/images/grasshopper.png");
                break;
            case BEETLE:
                img = c.getImage("vue/rsc/images/beetle.png");
                break;
            default:
                img = c.getImage("vue/rsc/images/ant.png");
        }

        for (int i = 0; i < nbPions; i++) {
            Polygon hexagon = new Polygon();

            //Adding coordinates to the polygon 
            hexagon.getPoints().addAll(new Double[]{
                10.0 + i * 10.0, 60.0,
                30.0 + i * 10.0, 80.0,
                60.0 + i * 10.0, 80.0,
                80.0 + i * 10.0, 60.0,
                60.0 + i * 10.0, 40.0,
                30.0 + i * 10.0, 40.0});
            hexagon.setFill(couleur);
            hexagon.setStroke(Color.BLACK);

            img.setFitHeight(25);
            img.setLayoutX(30 + i * 10);
            img.setLayoutY(50);
            img.setPreserveRatio(true);

            g.getChildren().add(hexagon);
        }

        g.getChildren().add(img);
        this.getChildren().add(g);
    }

}
