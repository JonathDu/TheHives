/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.plateau.handlers.mousehandlers.SocleHandler;
import hive.controller.plateau.PlateauController;
import hive.model.board.Board;
import hive.model.board.Cell;
import javafx.scene.Parent;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import util.Matrix;
import util.Vector2i;

/**
 *
 * @author jonathan
 */
public class NodeRuche extends Parent {

    private final Matrix<NodeComb> tab;
    private final int largeur;
    private final int hauteur;
    private final int longueurPion = 40;
    
    private final CacheImage c;
    private final PlateauController plateauController;
    private final Board board;
    private InterfacePlateau plateau;

    public NodeRuche(CacheImage c, PlateauController plateauController) {
        this.c = c;
        this.board = plateauController.game.state.board;
        this.plateauController = plateauController;
        hauteur = plateauController.game.state.board.getData().sizeY();
        largeur = plateauController.game.state.board.getData().sizeX();
        tab = new Matrix<>(hauteur, largeur);
        initTab();
    }

    public void initTab() {
        double center = ((sqrt(3) / 2) * longueurPion);
        double h = sqrt(-Math.pow(center, 2) + Math.pow(longueurPion, 2));
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                Vector2i pos = new Vector2i(x, y);

                NodeComb cell = new NodeComb(c, longueurPion);
                if (board.getHexagon(pos).value != null) {
                    cell.majComb(board.getHexagon(pos), plateau, plateauController);
                }
                cell.setLayoutX(x * (longueurPion + h) + x  * 5);

                if (x % 2 != 0) {
                    cell.setLayoutY((y * 2 * center) + center + y  * 5);
                } else {
                    cell.setLayoutY(y * 2 * center + y  * 5);
                }
                tab.setAt(pos, cell);
                this.getChildren().add(tab.getAt(pos));
            }
        }
    }

    public void updateTab() {
        this.getChildren().clear();
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                Vector2i pos = new Vector2i(x, y);
                tab.getAt(pos).majComb(null, plateau, plateauController);
                this.getChildren().add(tab.getAt(pos));
            }
        }
    }

    public void setHandler(InterfacePlateau plateau) {
        this.plateau = plateau;
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                Vector2i pos = new Vector2i(x, y);

                SocleHandler handler = new SocleHandler(plateauController, pos);
                tab.getAt(pos).addEventFilter(MouseEvent.MOUSE_CLICKED, handler);

            }
        }
    }

    public void selectPlayerCell(Vector2i pos) { // pour la source
        tab.getAt(pos).setSelected(Color.rgb(246, 6, 189));
    }
    
    public void selectIACell(Vector2i pos) {
        tab.getAt(pos).setSelected(Color.rgb(255, 0, 0));
    }

    public void deselectCell(Vector2i pos) {
        tab.getAt(pos).setNotSelected();
    }
    

    public void majSource(Cell source) {
        tab.getAt(source.comb.pos).majComb(source.comb, plateau, plateauController);
    }

    public void majDestination(Cell destination) {
        tab.getAt(destination.comb.pos).majComb(destination.comb, plateau, plateauController);
    }

    public void majDestinations(ArrayList<Cell> destinations) {
        destinations.forEach((destination)
                -> {
            majDestination(destination);
        });
    }

    public void majPlacement(Cell placement) {
        tab.getAt(placement.comb.pos).majComb(placement.comb, plateau, plateauController);
    }

    public void surlignerDestinationsPossibles(ArrayList<Cell> cells) {
        for (int i = 0; i < cells.size(); i++) {
            tab.getAt(cells.get(i).comb.pos).setSelected(Color.rgb(4, 246, 118));
        }
    }

    public void desurlignerDestinationsPossibles(ArrayList<Cell> cells) {
        for (int i = 0; i < cells.size(); i++) {
            tab.getAt(cells.get(i).comb.pos).setNotSelected();
        }
    }

//    public void majTaille() {
//
//        this.getChildren().clear();
//        for (int i = 0; i < largeur; i++) {
//            for (int j = 0; j < hauteur; j++) {
//
//                tab.getAt(new Vector2i(j, i)).setLayoutX(i * (longueurPion + largeurPion));
//
//                if (i % 2 == 0) {
//                    tab.getAt(new Vector2i(j, i)).setLayoutY(j * 2 * largeurPion);
//                } else {
//                    tab.getAt(new Vector2i(j, i)).setLayoutY((j * 2 * largeurPion) + largeurPion);
//                }
//
//                tab.getAt(new Vector2i(j, i)).modifierTaille(longueurPion);
//                this.getChildren().add(tab.getAt(new Vector2i(j, i)));
//            }
//        }
//    }
}
