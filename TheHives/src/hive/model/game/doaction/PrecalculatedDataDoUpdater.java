/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.doaction;

import hive.model.board.Tile;
import hive.model.game.utildata.PrecalculatedData;
import hive.model.players.actions.ActionVisitor;
import hive.model.players.actions.MoveAction;
import hive.model.players.actions.NoAction;
import hive.model.players.actions.PutAction;

/**
 *
 * @author Thomas
 */
public class PrecalculatedDataDoUpdater implements ActionVisitor
{
    PrecalculatedData data;
    
    PrecalculatedDataDoUpdater(PrecalculatedData data)
    {
        this.data = data;
    }
    
    @Override
    public void visit(PutAction action)
    {
        // tiles
        data.tiles.get(action.tile.color).get(action.tile.type).add(action.where);
        
        // nb_tiles
        data.nb_tiles += 1;
        
        // nb_combs (according to hive put rules (tiles put at level 0))
        data.nb_combs += 1;
        
        // last
        data.last_undo = null;
        
        // trace
        data.trace.push(action);
        
        // placements
        data.placements = null;
        
    }

    @Override
    public void visit(MoveAction action)
    {
        // tiles
        Tile tile = action.source.getTile();
        data.tiles.get(tile.color).get(tile.type).remove(action.source);
        data.tiles.get(tile.color).get(tile.type).add(action.destination);
        
        // nb_tiles
        
        // nb_combs
        // if the tile leaves a comb for an other one already occupied
        if(action.source.comb.value().size() == 1 && action.destination.comb.value().size() >= 1)
            data.nb_combs -= 1;
        // if the tile shares a comb but will occupy an empty comb
        else if(action.source.comb.value().size() > 1 && action.destination.comb.value().size() == 0)
            data.nb_combs += 1;
        
        // last
        data.last_undo = null;
        
        // trace
        data.trace.push(action);
        
        // placements
        data.placements = null;
    }

    @Override
    public void visit(NoAction action)
    {
        // tiles
        
        // nb_tiles
        
        // nb_combs
        
        // last
        data.last_undo = null;
        
        // trace
        data.trace.push(action);
        
        // placements
        data.placements = null;
    }
}
