/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.gamescene.game;

import hive.model.board.Cell;
import hive.model.board.Honeycomb;
import hive.model.players.actions.Action;
import hive.model.players.decisions.Decision;
import hive.model.players.decisions.HumanDecision;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import util.Vector2i;

/**
 *
 * @author Thomas
 */
public class CellHandler implements EventHandler<MouseEvent>
{

    GameController controller;
    Cell cell;

    public CellHandler(GameController controller, Vector2i pos)
    {
        this.controller = controller;
        this.cell = new Cell(controller.progress.game.state.board.getHexagon(pos));
    }

    @Override
    public void handle(MouseEvent event)
    {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED)
        {
            Decision decision = controller.progress.game.state.turn.getCurrent().decision;
            if (decision instanceof HumanDecision)
            {
                //System.out.println(cell);
                
                HumanDecision human_decision = (HumanDecision) decision;

                switch (controller.builder.getState())
                {
                    case BEGIN:
                        System.out.println("Source selectionnée");
                        controller.builder.setSource(cell);
                        // TODO : mettre a jour graphiquement la cell source selectionnée + les destinations possibles
                        break;
                    case SOURCE_SELECTED:
                        if (cell != controller.builder.source) //si on ne clique pas sur la cellule deja selectionnée
                        {
                            System.out.println("Destination selectionnée");

                            controller.builder.setDestination(cell);
                            Action action = controller.builder.produce();
                            /*human_decision.setAction(action);
                            controller.progress.doAction();*/
                            // TODO : mettre à jour graphiquement source et destination (pas besoin de faire tout le plateau)
                        }
                        else
                        {
                            System.out.println("Aucun changement : source = destination");
                        }
                        break;
                    case TILE_SELECTED:
                        System.out.println("Placement selectionné");
                        controller.builder.setPlacement(cell);
                        Action action = controller.builder.produce();
                        // TODO : mettre a jour graphiquement la tile selectionnée + les destinations possibles
                        break;
                }
            }
        }
        else if (true) // autre evenement ? mouseOver ?
        {
            // information about the tile ? IA or not
            // etc
        }
    }
}
