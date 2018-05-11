/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import javafx.scene.Parent;
import hive.vue.InterfaceCell;
import util.Matrix;
import util.Vector2i;

/**
 *
 * @author jonathan
 */
public class InterfaceRuche extends Parent {

    private final Matrix<InterfaceCell> tab;
    private final int largeur = 24;
    private final int longueur = 24;

    public InterfaceRuche(CacheImage c) {
        tab = new Matrix<>(largeur, longueur);
        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < longueur; j++) {
                InterfaceCell cell = new InterfaceCell(c);
                cell.setLayoutX(i * (InterfacePion.LONGUEUR + InterfacePion.LARGEUR));

                if (i % 2 == 0) {
                    cell.setLayoutY(j *2* InterfacePion.LARGEUR);
                } else {
                    cell.setLayoutY((j*2*InterfacePion.LARGEUR)+InterfacePion.LARGEUR);
                }
                tab.setAt(new Vector2i(j, i), cell);
                this.getChildren().add(tab.getAt(new Vector2i(j, i)));

            }
        }

    }
}
