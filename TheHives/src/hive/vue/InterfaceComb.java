/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.plateauscene.game.GameController;
import hive.controller.plateauscene.game.mousehandlers.TilePlateauHandler;
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
public class InterfaceComb extends Parent {

    private ArrayList<InterfacePion> pions;
    public InterfacePion socle;
    private CacheImage c;

    public InterfaceComb(CacheImage c) {
        this.c = c;
        this.socle = new InterfacePion(null, null, c);
//        this.socle.hexagon.setStroke(Color.BLACK);
        this.pions = new ArrayList<>();
        this.getChildren().add(pions.get(0));
    }

    public InterfaceComb(CacheImage c, int taille) {
        this.c = c;
        this.socle = new InterfacePion(null, null, c, taille);
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

    public void majComb(Honeycomb comb, InterfacePlateau plateau, GameController plateauController) {
        pions.clear();
        this.getChildren().clear();
        this.getChildren().add(socle);
        for (int i = 0; i < comb.value().size(); i++) {
            pions.add(i, new InterfacePion(comb.value().get(i).color, comb.value().get(i).type, c));
            pions.get(i).addEventFilter(MouseEvent.MOUSE_CLICKED, new TilePlateauHandler(plateauController, plateau, new Cell(comb, i)));
            this.getChildren().add(pions.get(i));
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
    /*public void modifierTaille(int longueur) {
        this.getChildren().clear();
        Color couleur = null;
        if (this.comb != null) {
            if (this.comb.value().get(0).color == TeamColor.BLACK) {
                couleur = Color.GRAY;
            } else {
                couleur = Color.WHITE;
            }
            int i = 0;
            while (this.comb.value().get(i) != null) {
                InterfacePion pionx = new InterfacePion(couleur, this.comb.value().get(i).type, c);
                this.getChildren().add(pionx);
                this.pions = pionx;
                i++;
            }
        } else {
            this.pions = new InterfacePion(Color.TRANSPARENT, null, c, longueur);
            this.getChildren().add(pions);

        }
    }
     */
}
