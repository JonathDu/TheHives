/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.doaction;

import hive.model.game.ActionsTrace;
import hive.model.players.actions.ActionVisitor;
import hive.model.players.actions.MoveAction;
import hive.model.players.actions.NoAction;
import hive.model.players.actions.PutAction;

/**
 *
 * @author Thomas
 */
public class TraceDoUpdater implements ActionVisitor
{
    ActionsTrace trace;
    
    TraceDoUpdater(ActionsTrace trace)
    {
        this.trace = trace;
    }
    
    @Override
    public void visit(PutAction action)
    {
        trace.push(action);
    }

    @Override
    public void visit(MoveAction action)
    {
        trace.push(action);
    }

    @Override
    public void visit(NoAction action)
    {
        trace.push(action);
    }
}
