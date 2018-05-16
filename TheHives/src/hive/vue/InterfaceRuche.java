/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.gamescene.game.CellHandler;
import hive.controller.gamescene.game.GameController;
import hive.model.board.Board;
import hive.model.board.Cell;
import hive.model.board.Honeycomb;
import javafx.scene.Parent;
import hive.vue.InterfaceComb;
import java.util.ArrayList;
import javafx.scene.input.MouseEvent;
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
    private final int longueurPion = 40;
    private final int largeurPion = (int) (longueurPion / 1.4);
    int width, height;
    private GameController controller;

    public InterfaceRuche(CacheImage c, int width, int height, GameController controller) {
//        this.width = width;
//        this.height = height;
//        
//        
//        longueurPion = width/60;
//        largeurPion = (int) (longueurPion / 1.4);
        this.controller = controller;
        hauteur = controller.progress.game.state.board.getData().sizeY();
        largeur = controller.progress.game.state.board.getData().sizeX();
        tab = new Matrix<>(hauteur, largeur);
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                Vector2i pos = new Vector2i(x,y);
                CellHandler handler = new CellHandler(controller, this, pos);
                
                InterfaceComb cell = new InterfaceComb(c, longueurPion);
                cell.setLayoutX(x * (longueurPion + largeurPion));
                
                
                cell.addEventFilter(MouseEvent.MOUSE_CLICKED, handler);
                
                
                if (x % 2 == 0) {
                    cell.setLayoutY(y * 2 * largeurPion);
                } else {
                    cell.setLayoutY((y * 2 * largeurPion) + largeurPion);
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
    
    public void selectCell(Vector2i pos)
    {
        
    }
    
    public void majCells(ArrayList<Cell> cells)
    {
        
    }
    
    public void surlignerCells(ArrayList<Cell> cells)
    {
        
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
