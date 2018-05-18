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
 * Fait une action lorsque l'on selectionne la source
 *
 * @author Thomas
 */
public class TilePlateauHandler extends HandlerPlateau
{

    Cell cellClicked;

    public TilePlateauHandler(GameController controller, InterfacePlateau uiPlateau, Cell cellClicked)
    {
        super(controller, uiPlateau);
        this.cellClicked = cellClicked;
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

            switch (controller.builder.getState())
            {
                case BEGIN:
                    if (cellClicked.getTile().color != controller.progress.game.state.turn.getCurrent().color)
                    {
                        return;
                    }
                    System.out.println("Source selectionnée");

                    controller.builder.setSource(cellClicked);
                    controller.builder.setPossibleDestinations(game.rules.getPossibleDestinations(game.state, cellClicked));

                    uiPlateau.ruche.selectCell(controller.builder.source.comb.pos);
                    uiPlateau.ruche.surlignerCells(controller.builder.possibleDestinations);
                    event.consume();
                    break;
            }
        }
    }
}
