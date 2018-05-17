/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.insects.behaviors;

import hive.model.board.Cell;
import hive.model.game.rules.HiveFunctions;
import hive.model.board.Honeycomb;
import hive.model.board.Tile;
import hive.model.board.TilesStack;
import hive.model.game.Game;
import hive.model.game.GameState;
import hive.model.insects.InsectBehavior;
import java.util.ArrayList;
import util.Iterators;
import util.hexagons.iterators.BreadthIterator;
import util.hexagons.iterators.Neighbor;
import util.hexagons.iterators.NeighborsIterator;

/**
 *
 * @author Thomas
 */
public class SoldierAntBehavior implements InsectBehavior
{

    @Override
    public ArrayList<Cell> getPossibleDestinations(GameState state, Cell cell)
    {
        assert cell.level == 0;
        
        ArrayList<Cell> list = new ArrayList<>();
        
        if(HiveFunctions.isCrushed(cell) || !HiveFunctions.isConnexWithout(cell, state.data.nb_combs))
            return list;
        
        Tile tile = cell.comb.value().pop();
        
        NeighborsIterator neighbors = new NeighborsIterator(cell.comb);
        
        // for each neighbor
        while(neighbors.hasNext())
        {
            Neighbor<TilesStack> neighbor = neighbors.next();
            
            // only empty neighbors
            if(!neighbor.hexagon.value().isEmpty())
                continue;
            
            // breadth first search starting at empty neighbor, and searching empty hexagon with at least 1 neighbor
            BreadthIterator<TilesStack> iterator = new BreadthIterator<>(
                    neighbor.hexagon,
                    hexagon -> !hexagon.equals(cell.comb) &&
                            hexagon.value().isEmpty() &&
                            Iterators.searchN(new NeighborsIterator<TilesStack>(hexagon), n -> !n.hexagon.value().isEmpty(), 1));
            
            while(iterator.hasNext())
                list.add(new Cell((Honeycomb)iterator.next()));
        }
            
        cell.comb.value().push(tile);
        
        return list;
    }
    
}
