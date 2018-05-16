/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.gamescene.game.GameController;
import hive.controller.gamescene.game.handlers.TilePlateauHandler;
import hive.model.board.Cell;
import hive.model.board.Honeycomb;
import hive.model.players.TeamColor;
import java.util.ArrayList;
import javafx.event.EventType;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javax.sound.midi.ControllerEventListener;

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
        this.socle = new InterfacePion(Color.TRANSPARENT, null, c);
        this.socle.hexagon.setStroke(Color.TRANSPARENT);
        this.pions = new ArrayList<>();
        this.getChildren().add(pions.get(0));
    }

    public InterfaceComb(CacheImage c, int taille) {
        this.c = c;
        this.socle = new InterfacePion(Color.TRANSPARENT, null, c, taille);
        this.socle.hexagon.setStroke(Color.TRANSPARENT);
        this.pions = new ArrayList<>();
        this.getChildren().add(socle);
    }

    public void addTile(Cell tile, InterfacePlateau plateau, GameController controller) {
        Color couleur = null;

        if (tile.getTile().color == TeamColor.BLACK) {
            couleur = Color.GRAY;
        } else {
            couleur = Color.WHITE;
        }
        int i = 0;
        InterfacePion pion = new InterfacePion(couleur, tile.getTile().type, c);
        pion.addEventHandler(MouseEvent.MOUSE_CLICKED, new TilePlateauHandler(controller, plateau, tile.comb.pos));
        this.pions.add(tile.level, pion);
        this.getChildren().add(this.pions.get(this.pions.size() - 1));
    }

    public void majTile(Honeycomb comb, InterfacePlateau plateau, GameController controller) {
        pions.clear();
        this.getChildren().clear();
        this.getChildren().add(socle);
        for (int i = 0; i < comb.value().size(); i++) {
            Color couleur = null;

            if (comb.value().get(i).color == TeamColor.BLACK) {
                couleur = Color.GRAY;
            } else {
                couleur = Color.WHITE;
            }

            pions.add(i, new InterfacePion(couleur, comb.value().get(i).type, c));
            this.pions.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, new TilePlateauHandler(controller, plateau, comb.pos));
            this.getChildren().add(pions.get(i));
        }
    }

    public void removeTile() {
        this.pions.remove(this.pions.size() - 1);
    }

    public void setSelected(Color col) {
        socle.hexagon.setStroke(col);
        for (int i = 0; i < pions.size(); i++) {
            pions.get(i).hexagon.setStroke(col);
        }
    }

    public void setNotSelected() {
        socle.hexagon.setStroke(Color.TRANSPARENT);
        for (int i = 0; i < pions.size(); i++) {
            pions.get(i).hexagon.setStroke(Color.BLACK);
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
