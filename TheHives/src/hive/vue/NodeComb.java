/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.plateau.PlateauController;
import hive.controller.plateau.handlers.mousehandlers.TilePlateauHandler;
import hive.model.board.Cell;
import hive.model.board.Honeycomb;
import java.util.ArrayList;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author jonathan
 */
public class NodeComb extends Parent {

    private ArrayList<NodePion> pions;
    public NodePion socle;
    private CacheImage c;

    public NodeComb(CacheImage c) {
        this.c = c;
        this.socle = new NodePion(null, null, c);
//        this.socle.hexagon.setStroke(Color.BLACK);
        this.pions = new ArrayList<>();
        this.getChildren().add(pions.get(0));
    }

    public NodeComb(CacheImage c, int taille) {
        this.c = c;
        this.socle = new NodePion(null, null, c, taille);
//        this.socle.hexagon.setStroke(Color.BLACK);
        this.pions = new ArrayList<>();
        this.getChildren().add(socle);
    }

//    public void addTile(Cell tile, InterfacePlateau plateau, GameController controller) {
//        Cell cell = new Cell(comb, level);
//        int i = 0;
//        InterfacePion pion = new InterfacePion(tile.getTile().color, tile.getTile().type, c);
//        pion.addEventFilter(MouseEvent.MOUSE_CLICKED, new TilePlateauHandler(controller, plateau, tile.comb.pos));
//        this.pions.add(tile.level, pion);
//        this.getChildren().add(this.pions.get(this.pions.size() - 1));
//    }
    
    public void majComb(Honeycomb comb, InterfacePlateau plateau, PlateauController plateauController,int longueur) {
        pions.clear();
        setSelected(Color.TRANSPARENT);
        this.getChildren().clear();
        this.getChildren().add(socle);
        if (comb != null) {
            for (int i = 0; i < comb.value().size(); i++) {
                pions.add(i, new NodePion(comb.value().get(i).color, comb.value().get(i).type, c, longueur));
                pions.get(i).addEventFilter(MouseEvent.MOUSE_CLICKED, new TilePlateauHandler(plateauController, new Cell(comb, i)));
                pions.get(i).setLayoutX(4 * i);
                pions.get(i).setLayoutY(4 * i);
                this.getChildren().add(pions.get(i));
            }
        }

    }

    public void removeTile() {
        this.pions.remove(this.pions.size() - 1);
    }

    public void setSelected(Color col) {
        socle.hexagon.setStroke(col);
        for (int i = 0; i < pions.size(); i++) {
            pions.get(i).hexagon.setStrokeWidth(3);
            pions.get(i).hexagon.setStroke(col);
        }
    }

    public void setNotSelected() {
        socle.hexagon.setStroke(Color.TRANSPARENT);
        for (int i = 0; i < pions.size(); i++) {
            pions.get(i).hexagon.setStroke(Color.TRANSPARENT);
        }
    }
     

}
