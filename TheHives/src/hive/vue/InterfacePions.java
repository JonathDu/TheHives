/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.model.insects.InsectType;
import hive.model.players.TeamColor;
import java.util.ArrayList;
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
    private ArrayList<InterfacePion> pions;

    public InterfacePions(TeamColor couleur, int nbPions, InsectType typePions, CacheImage c) {
        pions = new ArrayList<>();
        Group g = new Group();
        for(int i=0; i<nbPions; i++){
            InterfacePion pion = new InterfacePion(couleur, typePions, c);
            pion.setLayoutX(i*10);
            g.getChildren().add(pion);
            pions.add(pion);
        }
        this.getChildren().add(g);
    }
    
    public void setSelected(Color couleur){
        this.pions.get(pions.size()-1).hexagon.setStroke(couleur);
    }
    
    public void maj(){
        
    }

}
