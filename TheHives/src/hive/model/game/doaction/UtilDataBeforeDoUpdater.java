/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.doaction;

import hive.model.board.Cell;
import hive.model.board.Honeycomb;
import hive.model.board.Tile;
import hive.model.board.TilesStack;
import hive.model.game.utildata.TilesInfluence;
import hive.model.game.utildata.UtilData;
import hive.model.players.actions.ActionVisitor;
import hive.model.players.actions.MoveAction;
import hive.model.players.actions.NoAction;
import hive.model.players.actions.PutAction;
import util.hexagons.iterators.NeighborsIterator;

/**
 *
 * @author Thomas
 */
public class UtilDataBeforeDoUpdater implements ActionVisitor
{
    UtilData data;
    
    UtilDataBeforeDoUpdater(UtilData data)
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
        
        // influences (according to hive put rules (tiles put at level 0))
        data.influences.get(action.tile.color).addInfluence(action.where.comb);
        
        // placements
        data.placements_initialized = false;
        
        // nbgroups
        
    }

    @Override
    public void visit(MoveAction action)
    {
        Tile tile = action.source.getTile();
        
        // tiles
        data.tiles.get(tile.color).get(tile.type).remove(action.source);
        data.tiles.get(tile.color).get(tile.type).add(action.destination);

        
        // nb_tiles
        
        // nb_combs
        // if the tile leaves a comb for an other one already occupied
        if(action.source.comb.value().size() == 1 && action.destination.comb.value().size() >= 1)
            data.nb_combs -= 1;
        // if the tile shares a comb but will occupy an empty comb
        else if(action.source.comb.value().size() >= 2 && action.destination.comb.value().size() == 0)
            data.nb_combs += 1;
        
        // last
        data.last_undo = null;
        
        // trace
        data.trace.push(action);
        
        // influences
        if(action.source.stack().size() == 1)
            data.influences.get(tile.color).removeInfluence(action.source.comb);
        else
        {
            assert action.source.level == action.source.stack().size() - 1; // must not be below an other tile
            Tile below = new Cell(action.source).down().getTile();
            if(tile.color != below.color)
            {
                data.influences.get(tile.color).removeInfluence(action.source.comb);
                data.influences.get(below.color).addInfluence(action.source.comb);
            }
        }
        if(action.destination.comb.value().size() == 0)
            data.influences.get(tile.color).addInfluence(action.destination.comb);
        else
        {
            assert action.destination.level == action.destination.stack().size(); // must not be below an other tile
            Tile below = new Cell(action.destination).down().getTile();
            if(tile.color != below.color)
            {
                data.influences.get(below.color).removeInfluence(action.destination.comb);
                data.influences.get(tile.color).addInfluence(action.destination.comb);
            }
        }
        
        // placements
        data.placements_initialized = false;
        
        // nbgroups
        
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
        
        // influences
        
        // placements
        data.placements_initialized = false;
        
        // nbgroups
        
    }
}
