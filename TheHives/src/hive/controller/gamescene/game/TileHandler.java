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
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import util.Vector2i;

/**
 *
 * @author jonathan
 */
public class TileHandler implements EventHandler<MouseEvent> {

    GameController controller;
    Tile tile;

    public TileHandler(GameController controller, InsectType insectType) {
        this.controller = controller;
        this.tile = new Tile(insectType, controller.progress.game.state.turn.getCurrent().color);
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            System.out.println(tile);
            
//            Decision decision = controller.progress.game.state.turn.getCurrent().decision;
//            if (decision instanceof HumanDecision) {
//                
//                HumanDecision human_decision = (HumanDecision) decision;
//
//                switch (controller.builder.getState()) {
//                    case BEGIN:
//                        controller.builder.setTile(tile);
//                        // TODO : mettre a jour graphiquement la cell source selectionnée + les destinations possibles
//                        break;
//                    case SOURCE_SELECTED:
//                        //TODO
//                        break;
//                    case TILE_SELECTED:
//                        controller.builder.setTile(tile);
//                        // TODO : mettre a jour graphiquement la tile selectionnée + les destinations possibles
//                        break;
//                    case PLACEMENT_SELECTED:
//                        //TODO
//                        break;
//                }
//            }
        } else if (true) // autre evenement ? mouseOver ?
        {
            // information about the tile ? IA or not
            // etc
        }
    }
}
