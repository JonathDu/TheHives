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
import javafx.scene.paint.Color;

/**
 *
 * @author jonathan
 */
public class NodePions extends Parent {

    private int type;
    private ArrayList<NodePion> pions;
    private NodePion socle;
    CacheImage c;

    public NodePions(TeamColor couleur, int nbPions, InsectType typePions, CacheImage c) {
        socle = new NodePion(null, null, c);
        pions = new ArrayList<>();
        this.c = c;
        Group g = new Group();
        g.getChildren().add(socle);
        for (int i = 0; i < nbPions; i++) {
            NodePion pion = new NodePion(couleur, typePions, c);
            pion.setLayoutX(i * 10);
            g.getChildren().add(pion);
            pions.add(pion);
        }
        this.getChildren().add(g);
    }

    public void setSelected(Color couleur) {
        this.pions.get(pions.size() - 1).hexagon.setStroke(couleur);
    }

    public void unsetSelected() {
        this.pions.get(pions.size() - 1).hexagon.setStroke(Color.TRANSPARENT);
    }

    public void maj(TeamColor couleur, int nbPions, InsectType typePions) {
        this.getChildren().clear();
        pions.clear();
        Group g = new Group();
        g.getChildren().add(socle);
        for (int i = 0; i < nbPions; i++) {
            NodePion pion = new NodePion(couleur, typePions, c);
            pion.setLayoutX(i * 10);
            g.getChildren().add(pion);
            pions.add(pion);
        }
        this.getChildren().add(g);
    }
}
