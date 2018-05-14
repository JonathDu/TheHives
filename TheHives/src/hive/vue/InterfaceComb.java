/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.model.board.Cell;
import hive.model.board.Honeycomb;
import hive.model.insects.InsectType;
import hive.model.players.TeamColor;
import javafx.scene.Parent;
import javafx.scene.paint.Color;

/**
 *
 * @author jonathan
 */
public class InterfaceComb extends Parent {

    private InterfacePion pion;
    private Honeycomb comb;
    private CacheImage c;

    public InterfaceComb(CacheImage c) {
        this.c = c;
        this.pion = new InterfacePion(Color.TRANSPARENT, null, c);
        this.getChildren().add(pion);
    }

    public InterfaceComb(CacheImage c, int taille) {
        this.c = c;
        this.pion = new InterfacePion(Color.TRANSPARENT, null, c, taille);
        this.getChildren().add(pion);
    }

    public void setComb(Honeycomb comb) {
        Color couleur = null;
        this.comb = comb;

        if (this.comb.value().get(0).color == TeamColor.BLACK) {
            couleur = Color.GRAY;
        } else {
            couleur = Color.WHITE;
        }
        int i = 0;
        while (this.comb.value().get(i) != null) {
            
            InterfacePion pionx = new InterfacePion(couleur, this.comb.value().get(i).type, c);
            this.getChildren().add(pionx);
            i++;
        }
    }

    public void removeComb() {
        this.pion = new InterfacePion(Color.TRANSPARENT, null, c);
    }

    public void modifierTaille(int longueur) {
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
                this.pion = pionx;
                i++;
            }
        } else {
            this.pion = new InterfacePion(Color.TRANSPARENT, null, c, longueur);
            this.getChildren().add(pion);

        }
    }
}
