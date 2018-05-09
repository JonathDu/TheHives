/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

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
public class InterfacePion extends Parent{
    private int type;
    public InterfacePion(Color couleur) {
        Group g = new Group();
        Polygon hexagon = new Polygon();

        //Adding coordinates to the polygon 
        hexagon.getPoints().addAll(new Double[]{
            10.0, 60.0,
            30.0, 80.0,
            60.0, 80.0,
            80.0, 60.0,
            60.0, 40.0,
            30.0, 40.0,});
        hexagon.setFill(couleur);
        hexagon.setStroke(Color.BLACK);
        ImageView i = new ImageView(new Image("vue/rsc/images/ant.png"));
        i.setFitHeight(25);
        i.setLayoutX(30);
        i.setLayoutY(50);
        i.setPreserveRatio(true);
       
        
        g.getChildren().add(hexagon);
        g.getChildren().add(i);
        this.getChildren().add(g);
    }
    
}
