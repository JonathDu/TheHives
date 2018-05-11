/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.model.board.Cell;
import hive.model.insects.InsectType;
import javafx.scene.Parent;
import javafx.scene.paint.Color;

/**
 *
 * @author jonathan
 */
public class InterfaceCell extends Parent{
    private InterfacePion pion;
    private Cell cellule;
    private CacheImage c;
    public InterfaceCell(CacheImage c){
        this.c = c;
        this.pion = new InterfacePion(Color.WHITE, InsectType.SPIDER, c);  
        this.getChildren().add(pion);
    }
    
    public void setCell(Cell cellule){
        this.cellule = cellule;
        this.pion = new InterfacePion(Color.WHITE, this.cellule.getValue().get(0).type, c);
        this.getChildren().add(pion);
    }
}
