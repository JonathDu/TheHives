/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.gamescene.game;

import hive.model.board.Cell;
import hive.model.players.actions.Action;
import hive.model.players.decisions.Decision;
import hive.model.players.decisions.HumanDecision;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Thomas
 */
public class CellHandler implements EventHandler<MouseEvent>
{
    GameController controller;
    Cell cell;
    
    public CellHandler(GameController controller, Cell cell)
    {
        this.controller = controller;
        this.cell = cell;
    }
    
    @Override
    public void handle(MouseEvent event)
    {
        if(event.getEventType() == MouseEvent.MOUSE_CLICKED)
        {
            Decision decision = controller.progress.game.state.turn.getCurrent().decision;
            if(decision instanceof HumanDecision)
            {
                HumanDecision human_decision = (HumanDecision)decision;
                
                switch(controller.builder.getState())
                {
                case BEGIN:
                    controller.builder.setSource(cell);
                case SOURCE_SELECTED:
                    controller.builder.setDestination(cell); // TODO vérifier que c bien une autre cellule etc
                    Action action = controller.builder.produce();
                    human_decision.setAction(action);
                    controller.progress.doAction();
                    // TODO mettre à jour graphiquement source et destination (pas besoin de faire tout le plateau)
                case TILE_SELECTED:
                    controller.builder.setPlacement(cell);
                    // TODO
                }
            }
        }
        // if MOUSE_MOVED (exemple)
        else if(true)
        {
            // information about the tile ? IA or not
            // etc
        }
    }
}
