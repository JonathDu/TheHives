/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.updates.doAction;

import hive.model.board.Board;
import hive.model.players.actions.PutAction;
import hive.model.players.actions.MoveAction;
import hive.model.board.Tile;
import hive.model.board.TilesStack;
import hive.model.players.actions.ActionVisitor;
import hive.model.players.actions.NoAction;

/**
 *
 * @author Thomas
 */
public class BoardDoUpdater implements ActionVisitor
{
    Board board;
    
    public BoardDoUpdater(Board board)
    {
        this.board = board;
    }
    
    @Override
    public void visit(PutAction action)
    {
        TilesStack stack = action.where.cell.getValue();
        assert stack.isEmpty();
        stack.push(action.tile);
    }
    
    @Override
    public void visit(MoveAction action)
    {
        TilesStack stack = action.source.cell.getValue();
        Tile t = stack.remove(action.source.index);
        action.destination.cell.getValue().add(action.destination.index, t);
    }

    @Override
    public void visit(NoAction action)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
