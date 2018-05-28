/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.plateau.graphicaction;

import hive.controller.plateau.PlateauController;
import hive.model.board.Cell;
import hive.model.players.actions.ActionVisitor;
import hive.model.players.actions.MoveAction;
import hive.model.players.actions.NoAction;
import hive.model.players.actions.PutAction;
import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class ActionGraphicUpdaterHelp implements ActionVisitor
{

    PlateauController gameController;

    public ActionGraphicUpdaterHelp(PlateauController gameController)
    {
        this.gameController = gameController;
    }

    @Override
    public void visit(PutAction action)
    {
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(action.where);

        gameController.builder.setTile(action.tile);
        gameController.builder.setDestinations(cells);

        gameController.uiPlateau.getInterfacePlateauMain(gameController.game.state.turn.current.color).surlignerTile(action.tile);
        gameController.uiPlateau.ruche.surlignerDestinationsPossibles(cells);
    }

    @Override
    public void visit(MoveAction action)
    {
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(action.destination);

        gameController.builder.setSource(action.source);
        gameController.builder.setDestinations(cells);

        gameController.uiPlateau.ruche.selectPlayerCell(action.source.comb.pos);
        gameController.uiPlateau.ruche.surlignerDestinationsPossibles(cells);
    }

    @Override
    public void visit(NoAction action)
    {
    }
}
