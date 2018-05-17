/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.gamescene.game.handlers;

import hive.controller.gamescene.game.GameController;
import hive.model.board.Cell;
import hive.model.players.decisions.Decision;
import hive.model.players.decisions.HumanDecision;
import hive.vue.InterfacePlateau;
import javafx.scene.input.MouseEvent;
import util.Vector2i;

/**
 * Appelé lorsque l'on clique sur une cellule du plateau vide
 *
 * @author Thomas
 */
public class SocleHandler extends HandlerPlateau
{

    Cell cellClicked;

    public SocleHandler(GameController controller, InterfacePlateau uiPlateau, Vector2i pos)
    {
        super(controller, uiPlateau);
        cellClicked = new Cell(game.state.board.getHexagon(pos), 0);
    }

    @Override
    public void handle(MouseEvent event)
    {
        System.out.println("--- SOCLE ---");

        if (event.getEventType() == MouseEvent.MOUSE_CLICKED)
        {
            Decision decision = game.state.turn.getCurrent().decision;

            if (!(decision instanceof HumanDecision))
            {
                return;
            }

            HumanDecision human_decision = (HumanDecision) decision;

            switch (controller.builder.getState())
            {
                case SOURCE_SELECTED:
                    System.out.println("Destination selectionnée");
                    moveOnBoard(human_decision, cellClicked);
                    break;
                case TILE_SELECTED:
                    System.out.println("Placement selectionné");
                    putOnBoard(human_decision, cellClicked);
                    break;
            }
        }
    }
}
