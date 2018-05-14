/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.model.board.Cell;
import javafx.scene.Parent;
import hive.vue.InterfaceCell;
import javafx.event.EventType;
import util.Matrix;
import util.Vector2i;

/**
 *
 * @author jonathan
 */
public class InterfaceRuche extends Parent {

    private final Matrix<InterfaceCell> tab;
    private final int largeur = 24;
    private final int hauteur = 10;
    private int longueurPion = 40;
    private int largeurPion = (int) (longueurPion / 1.4);

    public InterfaceRuche(CacheImage c) {
        tab = new Matrix<>(hauteur, largeur);
        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                InterfaceCell cell = new InterfaceCell(c, longueurPion);
                cell.setLayoutX(i * (longueurPion + largeurPion));

                if (i % 2 == 0) {
                    cell.setLayoutY(j * 2 * largeurPion);
                } else {
                    cell.setLayoutY((j * 2 * largeurPion) + largeurPion);
                }
                tab.setAt(new Vector2i(j, i), cell);
                this.getChildren().add(tab.getAt(new Vector2i(j, i)));

            }
        }
        this.setOnScroll((event) -> {

            if (event.getDeltaY() < 0 && longueurPion > 10) {
                longueurPion = longueurPion - 3;
                largeurPion = (int) (longueurPion / 1.4);
                majTaille();
            }
            else if (event.getDeltaY() > 0 && longueurPion < 80) {
                longueurPion = longueurPion + 3;
                largeurPion = (int) (longueurPion / 1.4);
                majTaille();
            }
        });
    }

    public void placerPremierPion(Cell c) {
        Vector2i pos = new Vector2i(hauteur / 2, largeur / 2);
        tab.getAt(pos).setCell(c);
    }

    public void placerPion(Cell c, Vector2i pos) {
        if (pos.x < hauteur && pos.y < largeur && pos.x >= 0 && pos.y >= 0) {
            tab.getAt(pos).setCell(c);
        }
    }

    public void placerPion(Cell c, int x, int y) {
        if (x < hauteur && y < largeur && x >= 0 && y >= 0) {
            tab.getAt(x, y).setCell(c);
        }
    }

    public void enleverPion(int x, int y) {
        if (x < hauteur && y < largeur && x >= 0 && y >= 0) {
            tab.getAt(x, y).removeCell();
        }
    }

    public void enleverPion(Vector2i pos) {
        if (pos.x < hauteur && pos.y < largeur && pos.x >= 0 && pos.y >= 0) {
            tab.getAt(pos).removeCell();
        }
    }

    public void majTaille() {

        this.getChildren().clear();
        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {

                tab.getAt(new Vector2i(j, i)).setLayoutX(i * (longueurPion + largeurPion));

                if (i % 2 == 0) {
                    tab.getAt(new Vector2i(j, i)).setLayoutY(j * 2 * largeurPion);
                } else {
                    tab.getAt(new Vector2i(j, i)).setLayoutY((j * 2 * largeurPion) + largeurPion);
                }

                tab.getAt(new Vector2i(j, i)).modifierTaille(longueurPion);
                this.getChildren().add(tab.getAt(new Vector2i(j, i)));
            }
        }
    }
}
