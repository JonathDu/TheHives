/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.rules;

import hive.model.board.Cell;
import hive.model.board.Honeycomb;
import hive.model.board.Tile;
import hive.model.game.GameState;
import hive.model.game.utildata.TilesInfluence;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import util.hexagons.iterators.NeighborsIterator;

/**
 *
 * @author Thomas
 */
public class HivePutRules implements PutRules, Serializable
{

    @Override
    public ArrayList<Cell> getPossiblePlacements(GameState state, Tile tile)
    {
        ArrayList<Cell> list = new ArrayList<>();
        
        if(state.data.nb_tiles == 0)
        {
            // return center
            list.add(new Cell(state.board.getCenter()));
            return list;
        }
        else if(state.data.nb_tiles == 1)
        {
            // return center neighbors
            NeighborsIterator neighbors = new NeighborsIterator(state.board.getCenter());
            while(neighbors.hasNext())
                list.add(new Cell((Honeycomb)neighbors.next().hexagon));
            return list;
        }
        
        TilesInfluence current_occurences = state.data.influences.get(state.turn.getCurrent().color);
        TilesInfluence opponent_occurences = state.data.influences.get(state.turn.getOpponent().color);
        
        Set<Map.Entry<Honeycomb, Integer>> mapping = current_occurences.entrySet();
        
        // iterates on current influenced combs
        Iterator<Map.Entry<Honeycomb, Integer>> iterator = mapping.iterator();
        while(iterator.hasNext())
        {
            Map.Entry<Honeycomb, Integer> pair = iterator.next();
            // the comb must be empty to put a tile (influenced combs may be occupied by current or opponent tiles)
            if(!pair.getKey().value().isEmpty())
                continue;
            
            // if it's not influenced, the pair <comb, 0> should not be contained in the map, otherwise we find a positive value
            assert pair.getValue() > 0;
            
            // the comb must not be in conflict with opponents tiles
            if(opponent_occurences.containsKey(pair.getKey()))
                continue;
            
            // we can add the cell at level 0
            list.add(new Cell(pair.getKey()));
        }
        
        /*Board board = state.board;
        
        if(state.data.nb_tiles == 0)
        {
            // return center
            list.add(new Cell(state.board.getCenter()));
            return list;
        }
        else if(state.data.nb_tiles == 1)
        {
            // return center neighbors
            NeighborsIterator neighbors = new NeighborsIterator(state.board.getCenter());
            while(neighbors.hasNext())
                list.add(new Cell((Honeycomb)neighbors.next().hexagon));
            return list;
        }
        
        // naive version
        for(int y = 0; y < board.getData().sizeY(); ++y)
        {
            for(int x = 0; x < board.getData().sizeX(); ++x)
            {
                Honeycomb comb = board.getHexagon(new Vector2i(x, y));
                if(comb.value().isEmpty() && HiveFunctions.hasNeighbors(comb) && HiveFunctions.neighborsHaveSameColor(comb, state.turn.getCurrent().color))
                    list.add(new Cell(comb));
            }
        }*/
        
        return list;
    }
}
