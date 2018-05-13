/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.model.board.Cell;
import hive.model.insects.InsectType;
import hive.model.players.TeamColor;
import javafx.scene.Parent;
import javafx.scene.paint.Color;

/**
 *
 * @author jonathan
 */
public class InterfaceCell extends Parent {

    private InterfacePion pion;
    private Cell cellule;
    private CacheImage c;

    public InterfaceCell(CacheImage c) {
        this.c = c;
        this.pion = new InterfacePion(Color.TRANSPARENT, null, c);
        this.getChildren().add(pion);
    }

    public InterfaceCell(CacheImage c, int taille) {
        this.c = c;
        this.pion = new InterfacePion(Color.TRANSPARENT, null, c, taille);
        this.getChildren().add(pion);
    }

    public void setCell(Cell cellule) {
        Color couleur = null;
        this.cellule = cellule;

        if (this.cellule.comb.value().get(0).color == TeamColor.BLACK) {
            couleur = Color.GRAY;
        } else {
            couleur = Color.WHITE;
        }

        this.pion = new InterfacePion(couleur, this.cellule.comb.value().get(0).type, c);
        this.getChildren().add(pion);
    }

    public void removeCell() {
        this.pion = new InterfacePion(Color.TRANSPARENT, null, c);
    }

    public void modifierTaille(int longueur) {
        this.getChildren().clear();
        Color couleur = null;
        if (this.cellule != null) {
            if (this.cellule.comb.value().get(0).color == TeamColor.BLACK) {
                couleur = Color.GRAY;
            } else {
                couleur = Color.WHITE;
            }

            this.pion = new InterfacePion(couleur, this.cellule.comb.value().get(0).type, c, longueur);
        }else{
            this.pion = new InterfacePion(Color.TRANSPARENT, null, c, longueur);
        }
        this.getChildren().add(pion);
    }
}
