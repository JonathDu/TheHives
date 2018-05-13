/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.doaction;

import hive.model.board.Cell;
import hive.model.board.Tile;
import hive.model.players.actions.Action;
import hive.model.players.actions.PutAction;

/**
 *
 * @author Thomas
 */
class PutActionSelection implements ActionSelection
{
    public Cell where;
    public Tile tile;

    @Override
    public Action produceAction()
    {
        return new PutAction(where, tile);
    }

    @Override
    public void accept(ActionSelectionVisitor visitor)
    {
        visitor.visit(this);
    }
}
