/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.doaction;

import hive.model.board.Honeycomb;
import hive.model.board.Tile;
import hive.model.board.TilesStack;
import hive.model.game.utildata.TilesInfluence;
import hive.model.game.utildata.UtilData;
import hive.model.players.actions.ActionVisitor;
import hive.model.players.actions.MoveAction;
import hive.model.players.actions.NoAction;
import hive.model.players.actions.PutAction;

/**
 *
 * @author Thomas
 */
public class UtilDataAfterDoUpdater implements ActionVisitor
{
    UtilData data;
    
    UtilDataAfterDoUpdater(UtilData data)
    {
        this.data = data;
    }
    
    @Override
    public void visit(PutAction action)
    {
        // tiles
        
        // nb_tiles
        
        // nb_combs (according to hive put rules (tiles put at level 0))
        
        // last
        
        // trace
        
        // influences
        
        // placements
        
        // nbgroups
        data.nbgroups.addComb(action.where.comb);
    }

    @Override
    public void visit(MoveAction action)
    {
        // tiles
        
        // nb_tiles
        
        // nb_combs (according to hive put rules (tiles put at level 0))
        
        // last
        
        // trace
        
        // influences
        
        // placements
        
        // nbgroups
        if(action.source.level == 0)
            data.nbgroups.removeComb(action.source.comb);
        if(action.destination.level == 0)
            data.nbgroups.addComb(action.destination.comb);
    }

    @Override
    public void visit(NoAction action)
    {
        // tiles
        
        // nb_tiles
        
        // nb_combs (according to hive put rules (tiles put at level 0))
        
        // last
        
        // trace
        
        // influences
        
        // placements
        
        // nbgroups
        
    }
}
