/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.undoaction;

import hive.model.game.ActionsTrace;
import hive.model.players.actions.ActionVisitor;
import hive.model.players.actions.MoveAction;
import hive.model.players.actions.NoAction;
import hive.model.players.actions.PutAction;

/**
 *
 * @author Thomas
 */
public class TraceUndoUpdater implements ActionVisitor
{
    ActionsTrace trace;
    
    TraceUndoUpdater(ActionsTrace trace)
    {
        this.trace = trace;
    }
    
    @Override
    public void visit(PutAction action)
    {
        trace.pop();
    }

    @Override
    public void visit(MoveAction action)
    {
        trace.pop();
    }

    @Override
    public void visit(NoAction action)
    {
        trace.pop();
    }
    
}
