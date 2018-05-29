/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.plateau.PlateauController;
import hive.controller.plateau.handlers.mousehandlers.TilePlateauHandler;
import hive.model.board.Cell;
import hive.model.board.Honeycomb;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;

/**
 *
 * @author jonathan
 */
public class NodeComb extends Parent {

    private final ArrayList<NodePion> pions;
    public NodePion socle;
    private final CacheImage c;

    public NodeComb(CacheImage c) {
        this.c = c;
        this.socle = new NodePion(null, null, c);
        this.pions = new ArrayList<>();
        this.getChildren().add(pions.get(0));
    }

    public NodeComb(CacheImage c, int taille) {
        this.c = c;
        this.socle = new NodePion(null, null, c, taille);
        this.pions = new ArrayList<>();
        this.getChildren().add(socle);
    }

    public void majComb(Honeycomb comb, InterfacePlateau plateau, PlateauController plateauController, int longueur) {
        pions.clear();
        setSelected(Color.TRANSPARENT);
        this.getChildren().clear();
        this.getChildren().add(socle);
        if (comb != null) {
            for (int i = 0; i < comb.value().size(); i++) {
                pions.add(i, new NodePion(comb.value().get(i).color, comb.value().get(i).type, c, longueur));
                pions.get(i).addEventFilter(MouseEvent.MOUSE_CLICKED, new TilePlateauHandler(plateauController, new Cell(comb, i), this));
                pions.get(i).addEventFilter(MouseEvent.DRAG_DETECTED, new TilePlateauHandler(plateauController, new Cell(comb, i), this));
                pions.get(i).setLayoutX(4 * i);
                pions.get(i).setLayoutY(4 * i);
                this.getChildren().add(pions.get(i));
            }
        }
    }

    public void removeTile() {
        this.pions.remove(this.pions.size() - 1);
    }

    public void setSelected(Color col) {
        socle.hexagon.setStroke(col);
        socle.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                event.acceptTransferModes(TransferMode.ANY);

                event.consume();
            }
        });
        for (int i = 0; i < pions.size(); i++) {
            
            pions.get(i).hexagon.setStroke(col);
            pions.get(i).setOnDragOver(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    event.acceptTransferModes(TransferMode.ANY);

                    event.consume();
                }
            });
        }
    }

    public void setNotSelected() {
        socle.hexagon.setStroke(Color.TRANSPARENT);
        socle.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                event.consume();
            }
        });
        for (int i = 0; i < pions.size(); i++) {
            pions.get(i).hexagon.setStroke(Color.TRANSPARENT);
            pions.get(i).setOnDragOver(new EventHandler<DragEvent>() {
                public void handle(DragEvent event) {
                    event.consume();
                }
            });
        }
    }

}
