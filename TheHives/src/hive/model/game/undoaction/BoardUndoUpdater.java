/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.undoaction;

import hive.model.board.Board;
import hive.model.board.Tile;
import hive.model.board.TilesStack;
import hive.model.players.actions.ActionVisitor;
import hive.model.players.actions.MoveAction;
import hive.model.players.actions.NoAction;
import hive.model.players.actions.PutAction;

/**
 *
 * @author Thomas
 */
public class BoardUndoUpdater implements ActionVisitor
{   
    Board board;
    
    public BoardUndoUpdater(Board board)
    {
        this.board = board;
    }
    
    @Override
    public void visit(PutAction action)
    {
        TilesStack stack = action.where.comb.getValue();
        stack.pop();
        assert stack.isEmpty();
    }
    
    @Override
    public void visit(MoveAction action)
    {
        TilesStack stack = action.destination.comb.getValue();
        Tile t = stack.remove(action.destination.index);
        stack.add(action.source.index, t);
    }

    @Override
    public void visit(NoAction action)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
