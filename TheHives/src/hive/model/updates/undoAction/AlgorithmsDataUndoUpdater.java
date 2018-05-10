/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.updates.undoAction;

import hive.model.game.AlgorithmsData;
import hive.model.players.actions.ActionVisitor;
import hive.model.players.actions.MoveAction;
import hive.model.players.actions.NoAction;
import hive.model.players.actions.PutAction;

/**
 *
 * @author Thomas
 */
public class AlgorithmsDataUndoUpdater implements ActionVisitor
{
    AlgorithmsData data;
    
    AlgorithmsDataUndoUpdater(AlgorithmsData data)
    {
        this.data = data;
    }
    
    @Override
    public void visit(PutAction action)
    {
        data.tiles.get(action.tile.color).get(action.tile.type).remove(action.where);
    }

    @Override
    public void visit(MoveAction action)
    {
        
    }

    @Override
    public void visit(NoAction action)
    {
        
    }
}
