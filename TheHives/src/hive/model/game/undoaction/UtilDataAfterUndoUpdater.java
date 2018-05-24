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
public class UtilDataAfterUndoUpdater implements ActionVisitor
{
    UtilData data;
    
    UtilDataAfterUndoUpdater(UtilData data)
    {
        this.data = data;
    }
    
    @Override
    public void visit(PutAction action)
    {
        // tiles
        
        // nb_tiles
        
        // nb_combs
        
        // last
        
        // trace
        
        // influences
        
        // placements
        
        // nbgroups (according to hive put rules (tiles put at level 0))
        data.nbgroups.removeComb(action.where.comb);
    }

    @Override
    public void visit(MoveAction action)
    {
        // tiles
        
        // nb_tiles
        
        // nb_combs
        
        // last
        
        // trace
        
        // influences
        
        // placements
        
        // nbgroups (according to hive put rules (tiles put at level 0))
        if(action.destination.level == 0)
            data.nbgroups.removeComb(action.destination.comb);
        if(action.source.level == 0)
            data.nbgroups.addComb(action.source.comb);
    }

    @Override
    public void visit(NoAction action)
    {
        // tiles
        
        // nb_tiles
        
        // nb_combs
        
        // last
        
        // trace
        
        // influences
        
        // placements
        
        // nbgroups
        
    }
}
