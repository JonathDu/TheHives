/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.insects.behaviors;

import hive.model.board.Cell;
import hive.model.game.rules.HiveFunctions;
import hive.model.board.Honeycomb;
import hive.model.board.TilesStack;
import hive.model.game.GameState;
import hive.model.insects.InsectBehavior;
import java.util.ArrayList;
import util.Iterators;
import util.hexagons.iterators.Neighbor;
import util.hexagons.iterators.NeighborsIterator;

/**
 *
 * @author Thomas
 */
public class BeetleBehavior implements InsectBehavior
{

    @Override
    public ArrayList<Cell> getPossibleDestinations(GameState state, Cell cell)
    {
        ArrayList<Cell> list = new ArrayList<>();
        
        if(HiveFunctions.isCrushed(cell) || !HiveFunctions.isConnexWithout(cell, state.data.nb_combs))
            return list;
        
        NeighborsIterator neighbors = new NeighborsIterator(cell.comb);
        
        // for each neighbor
        while (neighbors.hasNext())
        {
            Neighbor<TilesStack> neighbor = neighbors.next();
            // if the beetle is below (or same level)
            if (cell.comb.value().size() <= neighbor.hexagon.value().size()) 
            {
                // the beetle can climb over it
                list.add(new Cell((Honeycomb)neighbor.hexagon));
            }
           // otherwise the beetle is above
            else
            {
                // if the beetle is free to slide or go down
                if (HiveFunctions.isFreeAtSide(cell, neighbor.from))
                {
                    // if the beetle is on the floor
                    if(cell.level == 0)
                    {
                        // the beetle can slide but the queen has to stay connected with other tiles
                        NeighborsIterator<TilesStack> around_neighbor = new NeighborsIterator<>(neighbor.hexagon);
                        
                        // if we have found 2 neighbors, it will stay connex anyway (we already count the queen in it)
                        if(Iterators.searchN(around_neighbor, n -> !n.hexagon.value().isEmpty(), 2))
                            list.add(new Cell((Honeycomb)neighbor.hexagon));
                    }
                    // otherwise it will stay connex anyway
                    else
                        list.add(new Cell((Honeycomb)neighbor.hexagon));
                }
                // otherwise the beetle can't move
            }
        }
        return list;
    }
}
