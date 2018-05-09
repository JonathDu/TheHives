/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.updates;

import hive.model.GameState;
import hive.model.updates.AlgorithmsDataUpdateApplier;
import hive.model.updates.BoardUpdateApplier;
import hive.model.updates.BoardUpdateDisapplier;
import hive.model.players.ActionVisitor;
import hive.model.players.MoveAction;
import hive.model.players.NoAction;
import hive.model.players.PutAction;

/**
 *
 * @author Thomas
 */
public class ActionApplier implements ActionVisitor
{
    BoardUpdateApplier applier;
    BoardUpdateDisapplier disapplier;
    AlgorithmsDataUpdateApplier updater;

    public ActionApplier(GameState state)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void visit(PutAction action)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(MoveAction action)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(NoAction action)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
