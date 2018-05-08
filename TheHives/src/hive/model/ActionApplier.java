/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model;

import hive.model.players.PutAction;
import hive.model.players.MoveAction;
import hive.model.board.Tile;
import hive.model.board.TilesStack;
import hive.model.players.ActionVisitor;

/**
 *
 * @author Thomas
 */
public class ActionApplier implements ActionVisitor
{
    @Override
    public void visit(PutAction action)
    {
        TilesStack stack = action.where.hexagon.getValue();
        assert stack.isEmpty();
        stack.push(action.tile);
    }
    
    @Override
    public void visit(MoveAction action)
    {
        TilesStack stack = action.source.hexagon.getValue();
        Tile t = stack.remove(action.source.index);
        action.destination.hexagon.getValue().add(action.destination.index, t);
    }
}
