/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.gamescene.game;

import hive.model.board.Cell;
import hive.model.board.Tile;
import hive.model.insects.InsectType;
import hive.model.players.actions.Action;
import hive.model.players.decisions.Decision;
import hive.model.players.decisions.HumanDecision;
import hive.vue.InterfacePlateauMain;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import util.Vector2i;

/**
 *
 * @author jonathan
 */
public class TileHandler implements EventHandler<MouseEvent> {

    GameController controller;
    InterfacePlateauMain uiMain;
    Tile tile;

    public TileHandler(GameController controller, InterfacePlateauMain uiMain, InsectType insectType) {
        this.controller = controller;
        this.uiMain = uiMain;
        this.tile = new Tile(insectType, controller.progress.game.state.turn.getCurrent().color);
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            //System.out.println(tile);
            
            Decision decision = controller.progress.game.state.turn.getCurrent().decision;
            if (decision instanceof HumanDecision) {
                
                HumanDecision human_decision = (HumanDecision) decision;

                switch (controller.builder.getState()) {
                    case BEGIN:
                        System.out.println("Tile selectionnée");
                        controller.builder.setTile(tile);
                        /*
                        uiMain.surlignerTile(tile); // MAJ graphique : surligne la tile selectionnée
                        // TODO : MAJ graphique : les destinations possibles
                        */
                        break;
                    case TILE_SELECTED:
                        if(tile.type != controller.builder.tile.type)
                        {
                            System.out.println("Changement de tile");
                            //uiMain.desurlignerTile(controller.builder.tile);
                            controller.builder.setTile(tile);
                            //uiMain.surlignerTile(tile);
                            
                        }
                        else
                        {
                            System.out.println("Aucun changement : tile deja selectionnée");
                        }
                        break;
                }
            }
        } else if (true) // autre evenement ? mouseOver ?
        {
            // information about the tile ? IA or not
            // etc
        }
    }
}
