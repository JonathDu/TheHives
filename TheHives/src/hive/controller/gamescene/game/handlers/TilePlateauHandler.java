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
import hive.vue.InterfaceRuche;
import javafx.scene.input.MouseEvent;
import util.Vector2i;

/**
 * Appelé lorsque l'on clique sur une cellule du plateau contenant quelque chose
 *
 * @author Thomas
 */
public class TilePlateauHandler extends HandlerPlateau
{

    Cell cellClicked;
    InterfaceRuche uiRuche;

    public TilePlateauHandler(GameController controller, InterfacePlateau uiPlateau, Vector2i pos)
    {
        super(controller, uiPlateau);
        cellClicked = new Cell(game.state.board.getHexagon(pos), game.state.board.getHexagon(pos).value().size() - 1);
        uiRuche = uiPlateau.ruche;
    }

    @Override
    public void handle(MouseEvent event)
    {
        System.out.println("--- TILE PLATEAU ---");
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
                case BEGIN:
                    System.out.println("Source selectionnée");

                    controller.builder.setSource(cellClicked);
                    controller.builder.setPossibleDestinations(game.rules.getPossibleDestinations(game.state, cellClicked));

                    uiRuche.selectCell(action_source.comb.pos);
                    uiRuche.surlignerCells(controller.builder.possibleDestinations);
                    break;
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
