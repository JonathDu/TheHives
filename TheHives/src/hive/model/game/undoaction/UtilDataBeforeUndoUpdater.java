/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.undoaction;

import hive.model.board.Cell;
import hive.model.board.Tile;
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
public class UtilDataBeforeUndoUpdater implements ActionVisitor
{
    UtilData data;
    
    public UtilDataBeforeUndoUpdater(UtilData data)
    {
        this.data = data;
    }
    
    @Override
    public void visit(PutAction action)
    {
        // tiles
        data.tiles.get(action.tile.color).get(action.tile.type).remove(action.where);
        
        // nb_tiles
        data.nb_tiles -= 1;
        
        // nb_combs (according to hive put rules (tiles put at level 0))
        data.nb_combs -= 1;
        
        // influences
        data.influences.get(action.tile.color).removeInfluence(action.where.comb);
        
        // placements
        data.placements_initialized = false;
        
        // nbgroups
        
    }

    @Override
    public void visit(MoveAction action)
    {
        Tile tile = action.destination.getTile();
        
        // tiles
        data.tiles.get(tile.color).get(tile.type).remove(action.destination);
        data.tiles.get(tile.color).get(tile.type).add(action.source);
        
        // nb_tiles
        
        // nb_combs
        // if the tile leaves a comb for an other one already occupied
        if(action.destination.comb.value().size() == 1 && action.source.comb.value().size() >= 1)
            data.nb_combs -= 1;
        // if the tile shares a comb but will occupy an empty comb
        else if(action.destination.comb.value().size() >= 2 && action.source.comb.value().size() == 0)
            data.nb_combs += 1;
        
        // influences
        if(action.destination.comb.value().size() == 1)
            data.influences.get(tile.color).removeInfluence(action.destination.comb);
        else
        {
            assert action.destination.level == action.destination.stack().size() - 1; // must not be below an other tile
            Tile below = new Cell(action.destination).down().getTile();
            if(tile.color != below.color)
            {
                data.influences.get(tile.color).removeInfluence(action.destination.comb);
                data.influences.get(below.color).addInfluence(action.destination.comb);
            }
        }
        if(action.source.stack().size() == 0)
            data.influences.get(tile.color).addInfluence(action.source.comb);
        else
        {
            assert action.source.level == action.source.stack().size(); // must not be below an other tile
            Tile below = new Cell(action.source).down().getTile();
            if(tile.color != below.color)
            {
                data.influences.get(below.color).removeInfluence(action.source.comb);
                data.influences.get(tile.color).addInfluence(action.source.comb);
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
        
        // influences
        
        // placements
        data.placements_initialized = false;
        
        // nbgroups
        
    }
}
