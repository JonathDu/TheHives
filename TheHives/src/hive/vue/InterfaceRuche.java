/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.plateauscene.game.mousehandlers.SocleHandler;
import hive.controller.plateauscene.game.GameController;
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
public class InterfaceRuche extends Parent {

    private final Matrix<InterfaceComb> tab;
    private final int largeur;
    private final int hauteur;
    private int longueurPion = 40;
    int width, height;

    private GameController plateauController;
    private final Board board;
    private InterfacePlateau plateau;

    public InterfaceRuche(CacheImage c, GameController plateauController) {
//        this.width = width;
//        this.height = height;
//
//
//        longueurPion = width/60;
//        largeurPion = (int) (longueurPion / 1.4);



        double center = ((sqrt(3) / 2) * longueurPion);
        double h = sqrt(-Math.pow(center, 2) + Math.pow(longueurPion, 2));

        this.board = plateauController.progress.game.state.board;
        this.plateauController = plateauController;
        hauteur = plateauController.progress.game.state.board.getData().sizeY();
        largeur = plateauController.progress.game.state.board.getData().sizeX();
        tab = new Matrix<>(hauteur, largeur);
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                Vector2i pos = new Vector2i(x, y);

                InterfaceComb cell = new InterfaceComb(c, longueurPion);
                cell.setLayoutX(x * (longueurPion + h) + 100);

                if (x % 2 != 0) {
                    cell.setLayoutY((y * 2 * center) + center  + 100);
                } else {
                    cell.setLayoutY(y * 2 * center  + 100);
                }
                tab.setAt(pos, cell);
                this.getChildren().add(tab.getAt(pos));
            }
        }
        
        
        
//        this.setOnScroll((event) -> {
//
//            if (event.getDeltaY() < 0 && longueurPion > 10) {
//                longueurPion = longueurPion - 3;
//                largeurPion = (int) (longueurPion / 1.4);
//                majTaille();
//            }
//            else if (event.getDeltaY() > 0 && longueurPion < 80) {
//                longueurPion = longueurPion + 3;
//                largeurPion = (int) (longueurPion / 1.4);
//                majTaille();
//            }
//        });
    }

    public void setHandler(InterfacePlateau plateau) {
        this.plateau = plateau;
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                Vector2i pos = new Vector2i(x, y);

                SocleHandler handler = new SocleHandler(plateauController, plateau, pos);
                tab.getAt(pos).addEventFilter(MouseEvent.MOUSE_CLICKED, handler);
                tab.getAt(pos).addEventFilter(MouseEvent.MOUSE_RELEASED, handler);
                tab.getAt(pos).addEventFilter(MouseEvent.MOUSE_DRAGGED, handler);
                
            }
        }
    }

    public void selectCell(Vector2i pos) { // pour la source
        tab.getAt(pos).setSelected(Color.rgb(246, 6, 189));
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
        destinations.forEach((destination) ->
        {
            majDestination(destination);
        });
    }
    
    public void majPlacement(Cell placement) {
        tab.getAt(placement.comb.pos).majComb(placement.comb, plateau, plateauController);
    }

    public void surlignerDestinationsPossibles(ArrayList<Cell> cells) {
        for (int i = 0; i < cells.size(); i++) {
            tab.getAt(cells.get(i).comb.pos).setSelected(Color.rgb( 4,246,118));
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
