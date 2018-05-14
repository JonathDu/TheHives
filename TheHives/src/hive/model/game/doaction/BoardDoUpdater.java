/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.doaction;

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
        TilesStack stack = action.where.comb.value();
        assert stack.isEmpty();
        stack.push(action.tile);
    }
    
    @Override
    public void visit(MoveAction action)
    {
        Tile t = action.source.comb.value().remove(action.source.level);
        action.destination.comb.value().add(action.destination.level, t);
    }

    @Override
    public void visit(NoAction action)
    {
        
    }
}
