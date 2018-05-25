/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.undoaction;

import hive.model.game.utildata.UtilData;
import hive.model.players.actions.ActionVisitor;
import hive.model.players.actions.MoveAction;
import hive.model.players.actions.NoAction;
import hive.model.players.actions.PutAction;

/**
 *
 * @author Thomas
 */
public class TracesUndoUpdater implements ActionVisitor
{
    UtilData data;
    
    public TracesUndoUpdater(UtilData data)
    {
        this.data = data;
    }
    
    @Override
    public void visit(PutAction action)
    {
        // trace
        data.trace.pop();
        
        // undos
        data.undos.push(action);
    }

    @Override
    public void visit(MoveAction action)
    {
        // trace
        data.trace.pop();
        
        // undos
        data.undos.push(action);
    }

    @Override
    public void visit(NoAction action)
    {
        // trace
        data.trace.pop();
        
        // undos
        data.undos.push(action);
    }
}
